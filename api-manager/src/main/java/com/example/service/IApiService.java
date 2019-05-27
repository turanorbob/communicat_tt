package com.example.service;

import com.example.entity.Api;
import com.example.vo.AddApiVo;
import com.example.vo.ApiSearchVo;
import com.example.vo.UpdateApiVo;
import org.springframework.data.domain.Page;

public interface IApiService {
    boolean create(AddApiVo param);

    boolean update(UpdateApiVo param);

    boolean delete(String id);

    Api detail(String id);

    Page<Api> page(ApiSearchVo param);
}
