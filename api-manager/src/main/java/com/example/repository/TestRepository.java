package com.example.repository;

import com.example.entity.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * <p>
 * description
 * </p>
 *
 * @author Legendl
 * @date 2019-09-03 18:32
 */
public interface TestRepository extends PagingAndSortingRepository<Test, Long> {
    Page<Test> findAll(Specification<Test> spec, Pageable pageable);
}
