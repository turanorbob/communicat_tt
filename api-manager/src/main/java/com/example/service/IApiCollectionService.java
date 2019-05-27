package com.example.service;

import com.example.entity.ApiCollection;
import com.example.vo.AddApiCollectionVo;
import com.example.vo.ApiCollectionSearchVo;
import com.example.vo.UpdateApiCollectionVo;
import org.springframework.data.domain.Page;

public interface IApiCollectionService {
    boolean create(AddApiCollectionVo param);

    boolean update(UpdateApiCollectionVo param);

    boolean delete(String id);

    ApiCollection detail(String id);

    Page<ApiCollection> page(ApiCollectionSearchVo param);
}
