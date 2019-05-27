package com.example.repository;

import com.example.entity.Api;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApiRepository extends PagingAndSortingRepository<Api, String> {
}
