package com.example.controller;

import com.example.vo.ResultVo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/28
 */
@RestController
@RequestMapping("/test")
@io.swagger.annotations.Api(tags = "test")
public class TestController {

    @ApiOperation(value = "put")
    @PostMapping("/put")
    public ResultVo<PutModel> create(@RequestBody PutModel param) {
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
