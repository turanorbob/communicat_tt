package com.example.service;

import com.example.entity.ApiParams;
import com.example.vo.AddApiParamsVo;
import com.example.vo.ApiParamsSearchVo;
import com.example.vo.UpdateApiParamsVo;
import org.springframework.data.domain.Page;

public interface IApiParamsService {
    boolean create(AddApiParamsVo param);

    boolean update(UpdateApiParamsVo param);

    boolean delete(String id);

    ApiParams detail(String id);

    Page<ApiParams> page(ApiParamsSearchVo param);
}
