package com.example.controller;

import com.example.entity.Api;
import com.example.service.IApiService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/24
 */
@RestController
@RequestMapping("/api")
@io.swagger.annotations.Api(tags = "api")
public class ApiController {

    @Autowired
    private IApiService apiService;

    @ApiOperation(value = "create")
    @PostMapping("/create")
    public Api create(Api param){
        apiService.insert(param);
        return param;
    }
}
