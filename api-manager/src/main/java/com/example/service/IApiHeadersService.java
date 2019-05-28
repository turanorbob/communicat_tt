package com.example.service;

import com.example.entity.ApiHeaders;
import com.example.vo.AddApiHeadersVo;
import com.example.vo.ApiHeadersSearchVo;
import com.example.vo.UpdateApiHeadersVo;
import org.springframework.data.domain.Page;

import java.util.List;

public interface IApiHeadersService {
    boolean create(AddApiHeadersVo param);

    boolean update(UpdateApiHeadersVo param);

    boolean delete(String id);

    ApiHeaders detail(String id);

    List<ApiHeaders> findAllByApiId(String apiId);

    Page<ApiHeaders> page(ApiHeadersSearchVo param);
}
