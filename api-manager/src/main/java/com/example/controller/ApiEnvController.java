package com.example.controller;

import com.example.entity.ApiEnv;
import com.example.service.IApiEnvService;
import com.example.vo.AddApiEnvVo;
import com.example.vo.ApiEnvSearchVo;
import com.example.vo.ResultVo;
import com.example.vo.UpdateApiEnvVo;
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
@RequestMapping("/apiEnv")
@io.swagger.annotations.Api(tags = "api-env")
public class ApiEnvController {

    @Autowired
    private IApiEnvService apiEnvService;

    @ApiOperation(value = "create")
    @PostMapping("/create")
    public ResultVo<Boolean> create(AddApiEnvVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiEnvService.create(param));
        return response.success();
    }

    @ApiOperation(value = "update")
    @PostMapping("/update")
    public ResultVo<Boolean> update(UpdateApiEnvVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiEnvService.update(param));
        return response.success();
    }

    @ApiOperation(value = "delete")
    @GetMapping("/delete/{id}")
    public ResultVo<Boolean> delete(@PathVariable("id") String id){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiEnvService.delete(id));
        return response.success();
    }

    @ApiOperation(value = "detail")
    @GetMapping("/detail/{id}")
    public ResultVo<ApiEnv> detail(@PathVariable("id") String id){
        ResultVo<ApiEnv> response = new ResultVo<>();
        response.setObject(apiEnvService.detail(id));
        return response.success();
    }

    @ApiOperation(value = "search")
    @PostMapping("/search")
    public ResultVo<Page<ApiEnv>> search(ApiEnvSearchVo param){
        ResultVo<Page<ApiEnv>> response = new ResultVo<>();
        response.setObject(apiEnvService.page(param));
        return response.success();
    }
}
