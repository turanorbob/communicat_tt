package com.example.service.impl;

import com.example.repository.ApiEnvCategoryRepository;
import com.example.service.IApiEnvCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
