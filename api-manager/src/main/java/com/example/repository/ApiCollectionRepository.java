package com.example.repository;

import com.example.entity.ApiCollection;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ApiCollectionRepository extends PagingAndSortingRepository<ApiCollection, String> {
}
