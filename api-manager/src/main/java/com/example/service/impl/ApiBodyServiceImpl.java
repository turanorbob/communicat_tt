package com.example.service.impl;

import com.example.entity.ApiBody;
import com.example.repository.ApiBodyRepository;
import com.example.service.IApiBodyService;
import com.example.util.IdWorker;
import com.example.vo.AddApiBodyVo;
import com.example.vo.ApiBodySearchVo;
import com.example.vo.UpdateApiBodyVo;
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
public class ApiBodyServiceImpl implements IApiBodyService {

    @Autowired
    private ApiBodyRepository apiBodyRepository;


    @Override
    public boolean create(AddApiBodyVo param) {
        ApiBody entity = new ApiBody();
        BeanUtils.copyProperties(param, entity);
        entity.setId(IdWorker.getUUID32());
        return apiBodyRepository.save(entity) != null;
    }

    @Override
    public boolean update(UpdateApiBodyVo param) {
        Optional<ApiBody> optionalApi = apiBodyRepository.findById(param.getId());
        if(!optionalApi.isPresent()){
            return false;
        }
        ApiBody entity = optionalApi.get();
        BeanUtils.copyProperties(param, entity);
        return apiBodyRepository.save(entity) != null;
    }

    @Override
    public boolean delete(String id) {
        if(!apiBodyRepository.existsById(id)){
            return false;
        }
        apiBodyRepository.deleteById(id);
        return true;
    }

    @Override
    public ApiBody detail(String id) {
        return apiBodyRepository.findById(id).get();
    }

    @Override
    public Page<ApiBody> page(ApiBodySearchVo param) {
        Pageable pageable = new PageRequest(param.getPage(),param.getPagesize());

        return apiBodyRepository.findAll(pageable);
    }
}
