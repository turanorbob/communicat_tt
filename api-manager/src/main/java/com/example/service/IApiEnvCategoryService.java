package com.example.service;

import com.example.entity.ApiEnvCategory;
import com.example.vo.AddApiEnvCategoryVo;
import com.example.vo.ApiEnvCategorySearchVo;
import com.example.vo.UpdateApiEnvCategoryVo;
import org.springframework.data.domain.Page;

public interface IApiEnvCategoryService {
    boolean create(AddApiEnvCategoryVo param);

    boolean update(UpdateApiEnvCategoryVo param);

    boolean delete(String id);

    ApiEnvCategory detail(String id);

    Page<ApiEnvCategory> page(ApiEnvCategorySearchVo param);
}
