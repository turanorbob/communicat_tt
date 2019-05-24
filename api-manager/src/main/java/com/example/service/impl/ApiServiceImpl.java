package com.example.service.impl;

import com.example.entity.Api;
import com.example.repository.ApiRepository;
import com.example.service.IApiService;
import com.example.util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int insert(Api param) {
        param.setId(IdWorker.getUUID32());
        apiRepository.save(param);
        return 1;
    }
}
