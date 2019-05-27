package com.example.repository;

import com.example.entity.ApiHeaders;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApiHeadersRepository extends PagingAndSortingRepository<ApiHeaders, String> {
}
