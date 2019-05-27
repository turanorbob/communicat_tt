package com.example.service;

import com.example.entity.ApiBody;
import com.example.vo.AddApiBodyVo;
import com.example.vo.ApiBodySearchVo;
import com.example.vo.UpdateApiBodyVo;
import org.springframework.data.domain.Page;

public interface IApiBodyService {
    boolean create(AddApiBodyVo param);

    boolean update(UpdateApiBodyVo param);

    boolean delete(String id);

    ApiBody detail(String id);

    Page<ApiBody> page(ApiBodySearchVo param);
}
