package com.example.controller;

import com.example.entity.ApiInterface;
import com.example.repository.ApiRepository;
import com.example.util.QueryWrapper;
import com.example.vo.ResultVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/28
 */
@Slf4j
@RestController
@RequestMapping("/test")
@Api(tags = "test")
public class TestController {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ApiRepository apiRepository;

    @ApiOperation(value = "test")
    @PutMapping("/test")
    public ResultVo<Object> test() {
        ResultVo<Object> response = new ResultVo<>();


        /*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        Root<ApiBody> root = query.from(ApiBody.class);
        query.select(criteriaBuilder.count(root.get("id")));
        Predicate predicate = criteriaBuilder.equal(root.get("id"), "95a16d59a14f4681805ebcc93a960935");
        query.where(predicate);
        Long singleResult = entityManager.createQuery(query).getSingleResult();
        response.setObject(singleResult);*/

        /*CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<ApiBody> query = criteriaBuilder.createQuery(ApiBody.class);
        Root<ApiBody> root = query.from(ApiBody.class);
        Predicate condition = criteriaBuilder.equal(root.get("id"), "95a16d59a14f4681805ebcc93a960935");
        query.where(condition);
        response.setObject(entityManager.createQuery(query).getResultList());*/

        /*Specification<ApiInterface> specification = new Specification<ApiInterface>() {

            @Override
            public Predicate toPredicate(Root<ApiInterface> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>(); //所有的断言
                *//*Predicate condition = cb.equal(root.get("id"), "95a16d59a14f4681805ebcc93a960935");
                predicates.add(condition);*//*
                return cb.and(predicates.toArray(new Predicate[0]));
            }
        };
        Pageable pageable = new PageRequest(0,10);
        Page<ApiInterface> data = apiRepository.findAll(specification, pageable);
        response.setObject(data);*/

        QueryWrapper<ApiInterface> wrapper = new QueryWrapper<>();

        String hsql = "select api from ApiInterface api left join ApiBody body on api.id=body.apiId";
        response.setObject(wrapper.list(entityManager, hsql));

       /* QueryWrapper<ApiInterface> wrapper = new QueryWrapper<>();

        String sql = "select * from api";
        response.setObject(wrapper.list2(entityManager, sql, ApiInterface.class));*/

        return response.success();
    }

    @ApiOperation(value = "put")
    @PutMapping("/put")
    public ResultVo<PutModel> put(@RequestBody PutModel param) {
        ResultVo<PutModel> response = new ResultVo<>();
        response.setObject(param);
        return response.success();
    }

    @ApiOperation(value = "test get request body")
    @GetMapping("/get1")
    public ResultVo<PutModel> get1(@ModelAttribute PutModel param) {
        ResultVo<PutModel> response = new ResultVo<>();
        response.setObject(param);
        return response.success();
    }

    @ApiOperation(value = "test get request body")
    @GetMapping("/get2")
    public ResultVo<PutModel> get2(@RequestBody PutModel param) {
        ResultVo<PutModel> response = new ResultVo<>();
        response.setObject(param);
        return response.success();
    }

    @ApiModel
    @Data
    public static class PutModel {
        private String name;
    }
}
