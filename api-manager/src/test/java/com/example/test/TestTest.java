package com.example.test;

import com.example.repository.TestRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;
import sun.util.resources.LocaleData;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * description
 * </p>
 *
 * @author Legendl
 * @date 2019-09-03 18:33
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestTest {
    @Autowired
    private TestRepository testRepository;

    @Test
    public void test_findAll() throws ParseException {
        Pageable pageable = new PageRequest(0,10);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = sdf.parse("2019-09-03 18:30:32");
        Date date2 = sdf.parse("2019-09-03 18:30:33");

        Specification<com.example.entity.Test> specification= (Specification<com.example.entity.Test>) (root, query, criteriaBuilder) -> {
            Predicate condition1 = criteriaBuilder.greaterThanOrEqualTo(root.get("createAt"), date1);
            Predicate condition2 = criteriaBuilder.lessThanOrEqualTo(root.get("createAt"), date2);
            query.where(condition1, condition2);
            return null;
        };
        Page<com.example.entity.Test> page = testRepository.findAll(specification, pageable);
        Assert.assertTrue(!CollectionUtils.isEmpty(page.getContent()));
    }
}
