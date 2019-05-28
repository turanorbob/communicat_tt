package com.example.service.impl;

import com.example.entity.ApiEnvCategory;
import com.example.repository.ApiEnvCategoryRepository;
import com.example.service.IApiEnvCategoryService;
import com.example.util.IdWorker;
import com.example.vo.AddApiEnvCategoryVo;
import com.example.vo.ApiEnvCategorySearchVo;
import com.example.vo.UpdateApiEnvCategoryVo;
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
public class ApiEnvCategoryServiceImpl implements IApiEnvCategoryService {

    @Autowired
    private ApiEnvCategoryRepository apiEnvCategoryRepository;


    @Override
    public boolean create(AddApiEnvCategoryVo param) {
        ApiEnvCategory entity = new ApiEnvCategory();
        BeanUtils.copyProperties(param, entity);
        entity.setId(IdWorker.getUUID32());
        return apiEnvCategoryRepository.save(entity) != null;
    }

    @Override
    public boolean update(UpdateApiEnvCategoryVo param) {
        Optional<ApiEnvCategory> optionalApi = apiEnvCategoryRepository.findById(param.getId());
        if(!optionalApi.isPresent()){
            return false;
        }
        ApiEnvCategory entity = optionalApi.get();
        BeanUtils.copyProperties(param, entity);
        return apiEnvCategoryRepository.save(entity) != null;
    }

    @Override
    public boolean delete(String id) {
        if(!apiEnvCategoryRepository.existsById(id)){
            return false;
        }
        apiEnvCategoryRepository.deleteById(id);
        return true;
    }

    @Override
    public ApiEnvCategory detail(String id) {
        return apiEnvCategoryRepository.findById(id).get();
    }

    @Override
    public Page<ApiEnvCategory> page(ApiEnvCategorySearchVo param) {
        Pageable pageable = new PageRequest(param.getPage(),param.getPagesize());

        return apiEnvCategoryRepository.findAll(pageable);
    }
}
