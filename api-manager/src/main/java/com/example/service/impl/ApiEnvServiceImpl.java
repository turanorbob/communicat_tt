package com.example.service.impl;

import com.example.repository.ApiEnvRepository;
import com.example.service.IApiEnvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


}
