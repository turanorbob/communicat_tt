package com.example.service;

import com.example.entity.ApiParams;
import com.example.vo.AddApiParamsVo;
import com.example.vo.ApiParamsSearchVo;
import com.example.vo.UpdateApiParamsVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IApiParamsService {
    boolean create(AddApiParamsVo param);

    boolean update(UpdateApiParamsVo param);

    boolean delete(String id);

    ApiParams detail(String id);

    List<ApiParams> findAllByApiId(String apiId);

    Page<ApiParams> page(ApiParamsSearchVo param);
}
