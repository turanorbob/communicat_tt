package com.example.repository;

import com.example.entity.ApiParams;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ApiParamsRepository extends PagingAndSortingRepository<ApiParams, String> {
    List<ApiParams> findAllByApiId(String apiId);
}
