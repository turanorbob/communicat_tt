package com.example.service.impl;

import com.example.entity.ApiEnv;
import com.example.repository.ApiEnvRepository;
import com.example.service.IApiEnvService;
import com.example.util.IdWorker;
import com.example.vo.AddApiEnvVo;
import com.example.vo.ApiEnvSearchVo;
import com.example.vo.UpdateApiEnvVo;
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
public class ApiEnvServiceImpl implements IApiEnvService {

    @Autowired
    private ApiEnvRepository apiEnvRepository;


    @Override
    public boolean create(AddApiEnvVo param) {
        ApiEnv entity = new ApiEnv();
        BeanUtils.copyProperties(param, entity);
        entity.setId(IdWorker.getUUID32());
        return apiEnvRepository.save(entity) != null;
    }

    @Override
    public boolean update(UpdateApiEnvVo param) {
        Optional<ApiEnv> optionalApi = apiEnvRepository.findById(param.getId());
        if(!optionalApi.isPresent()){
            return false;
        }
        ApiEnv entity = optionalApi.get();
        BeanUtils.copyProperties(param, entity);
        return apiEnvRepository.save(entity) != null;
    }

    @Override
    public boolean delete(String id) {
        if(!apiEnvRepository.existsById(id)){
            return false;
        }
        apiEnvRepository.deleteById(id);
        return true;
    }

    @Override
    public ApiEnv detail(String id) {
        return apiEnvRepository.findById(id).get();
    }

    @Override
    public Page<ApiEnv> page(ApiEnvSearchVo param) {
        Pageable pageable = new PageRequest(param.getPage(),param.getPagesize());

        return apiEnvRepository.findAll(pageable);
    }
}
