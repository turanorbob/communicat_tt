package com.example.service;

import com.example.entity.ApiEnv;
import com.example.vo.*;
import org.springframework.data.domain.Page;

public interface IApiEnvService {
    boolean create(AddApiEnvVo param);

    boolean update(UpdateApiEnvVo param);

    boolean delete(String id);

    ApiEnv detail(String id);

    Page<ApiEnv> page(ApiEnvSearchVo param);
}
