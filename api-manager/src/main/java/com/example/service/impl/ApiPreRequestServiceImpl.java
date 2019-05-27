package com.example.service.impl;

import com.example.entity.ApiPreRequestScript;
import com.example.repository.ApiPreRequestScriptRepository;
import com.example.service.IApiPreRequestService;
import com.example.util.IdWorker;
import com.example.vo.AddApiPreRequestVo;
import com.example.vo.ApiPreRequestSearchVo;
import com.example.vo.UpdateApiPreRequestVo;
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
public class ApiPreRequestServiceImpl implements IApiPreRequestService {

    @Autowired
    private ApiPreRequestScriptRepository apiPreRequestScriptRepository;


    @Override
    public boolean create(AddApiPreRequestVo param) {
        ApiPreRequestScript entity = new ApiPreRequestScript();
        BeanUtils.copyProperties(param, entity);
        entity.setId(IdWorker.getUUID32());
        return apiPreRequestScriptRepository.save(entity) != null;
    }

    @Override
    public boolean update(UpdateApiPreRequestVo param) {
        Optional<ApiPreRequestScript> optionalApi = apiPreRequestScriptRepository.findById(param.getId());
        if(!optionalApi.isPresent()){
            return false;
        }
        ApiPreRequestScript entity = optionalApi.get();
        BeanUtils.copyProperties(param, entity);
        return apiPreRequestScriptRepository.save(entity) != null;
    }

    @Override
    public boolean delete(String id) {
        if(!apiPreRequestScriptRepository.existsById(id)){
            return false;
        }
        apiPreRequestScriptRepository.deleteById(id);
        return true;
    }

    @Override
    public ApiPreRequestScript detail(String id) {
        return apiPreRequestScriptRepository.findById(id).get();
    }

    @Override
    public Page<ApiPreRequestScript> page(ApiPreRequestSearchVo param) {
        Pageable pageable = new PageRequest(param.getPage(),param.getPagesize());

        return apiPreRequestScriptRepository.findAll(pageable);
    }
}
