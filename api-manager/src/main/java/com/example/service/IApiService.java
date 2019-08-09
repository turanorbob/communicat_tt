package com.example.service;

import com.example.entity.ApiInterface;
import com.example.vo.AddApiVo;
import com.example.vo.ApiSearchVo;
import com.example.vo.UpdateApiVo;
import org.springframework.data.domain.Page;

public interface IApiService {
    boolean create(AddApiVo param);

    boolean update(UpdateApiVo param);

    boolean delete(String id);

    ApiInterface detail(String id);

    Page<ApiInterface> page(ApiSearchVo param);
}
