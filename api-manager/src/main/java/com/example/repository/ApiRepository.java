package com.example.repository;

import com.example.entity.ApiInterface;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApiRepository extends PagingAndSortingRepository<ApiInterface, String> {
    Page<ApiInterface> findAll(Specification<ApiInterface> spec, Pageable pageable);
}
