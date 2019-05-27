package com.example.controller;

import com.example.entity.ApiHeaders;
import com.example.service.IApiHeadersService;
import com.example.vo.AddApiHeadersVo;
import com.example.vo.ApiHeadersSearchVo;
import com.example.vo.ResultVo;
import com.example.vo.UpdateApiHeadersVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/24
 */
@RestController
@RequestMapping("/apiHeaders")
@io.swagger.annotations.Api(tags = "api-headers")
public class ApiHeadersController {

    @Autowired
    private IApiHeadersService apiHeadersService;

    @ApiOperation(value = "create")
    @PostMapping("/create")
    public ResultVo<Boolean> create(AddApiHeadersVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiHeadersService.create(param));
        return response.success();
    }

    @ApiOperation(value = "update")
    @PostMapping("/update")
    public ResultVo<Boolean> update(UpdateApiHeadersVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiHeadersService.update(param));
        return response.success();
    }

    @ApiOperation(value = "delete")
    @GetMapping("/delete/{id}")
    public ResultVo<Boolean> delete(@PathVariable("id") String id){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiHeadersService.delete(id));
        return response.success();
    }

    @ApiOperation(value = "detail")
    @GetMapping("/detail/{id}")
    public ResultVo<ApiHeaders> detail(@PathVariable("id") String id){
        ResultVo<ApiHeaders> response = new ResultVo<>();
        response.setObject(apiHeadersService.detail(id));
        return response.success();
    }

    @ApiOperation(value = "search")
    @PostMapping("/search")
    public ResultVo<Page<ApiHeaders>> search(ApiHeadersSearchVo param){
        ResultVo<Page<ApiHeaders>> response = new ResultVo<>();
        response.setObject(apiHeadersService.page(param));
        return response.success();
    }
}
