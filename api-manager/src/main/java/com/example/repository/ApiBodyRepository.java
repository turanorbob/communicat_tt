package com.example.repository;

import com.example.entity.ApiBody;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApiBodyRepository extends PagingAndSortingRepository<ApiBody, String> {
    ApiBody findByApiId(String apiId);

    Page<ApiBody> findAll(Specification<ApiBody> spec, Pageable pageable);
}
