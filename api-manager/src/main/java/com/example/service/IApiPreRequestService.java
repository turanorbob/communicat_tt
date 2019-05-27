package com.example.service;

import com.example.entity.ApiPreRequestScript;
import com.example.vo.AddApiPreRequestVo;
import com.example.vo.ApiPreRequestSearchVo;
import com.example.vo.UpdateApiPreRequestVo;
import org.springframework.data.domain.Page;

public interface IApiPreRequestService {
    boolean create(AddApiPreRequestVo param);

    boolean update(UpdateApiPreRequestVo param);

    boolean delete(String id);

    ApiPreRequestScript detail(String id);

    Page<ApiPreRequestScript> page(ApiPreRequestSearchVo param);
}
