package com.example.repository;

import com.example.entity.ApiHeaders;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface ApiHeadersRepository extends PagingAndSortingRepository<ApiHeaders, String> {
    List<ApiHeaders> findAllByApiId(String apiId);
}
