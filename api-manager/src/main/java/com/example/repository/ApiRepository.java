package com.example.repository;

import com.example.entity.Api;
import org.springframework.data.repository.CrudRepository;

public interface ApiRepository extends CrudRepository<Api, String> {
}
