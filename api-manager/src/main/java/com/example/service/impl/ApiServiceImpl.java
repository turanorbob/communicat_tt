package com.example.service.impl;

import com.example.entity.Api;
import com.example.repository.ApiRepository;
import com.example.service.IApiService;
import com.example.util.IdWorker;
import com.example.vo.AddApiVo;
import com.example.vo.ApiSearchVo;
import com.example.vo.UpdateApiVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/24
 */
@Service
public class ApiServiceImpl implements IApiService {

    @Autowired
    private ApiRepository apiRepository;

    @Override
    public boolean create(AddApiVo param) {
        Api entity = new Api();
        BeanUtils.copyProperties(param, entity);
        entity.setId(IdWorker.getUUID32());
        return apiRepository.save(entity) != null;
    }

    @Override
    public boolean update(UpdateApiVo param) {
        Optional<Api> optionalApi = apiRepository.findById(param.getId());
        if(!optionalApi.isPresent()){
            return false;
        }
        Api entity = optionalApi.get();
        BeanUtils.copyProperties(param, entity);
        return apiRepository.save(entity) != null;
    }

    @Override
    public boolean delete(String id) {
        if(!apiRepository.existsById(id)){
            return false;
        }
        apiRepository.deleteById(id);
        return true;
    }

    @Override
    public Api detail(String id) {
        return apiRepository.findById(id).get();
    }

    @Override
    public Page<Api> page(ApiSearchVo param) {
        Pageable pageable = new PageRequest(param.getPage(),param.getPagesize());

        return apiRepository.findAll(pageable);
    }
}
