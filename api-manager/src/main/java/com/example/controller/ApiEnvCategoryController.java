package com.example.controller;

import com.example.entity.ApiEnvCategory;
import com.example.service.IApiEnvCategoryService;
import com.example.vo.AddApiEnvCategoryVo;
import com.example.vo.ApiEnvCategorySearchVo;
import com.example.vo.ResultVo;
import com.example.vo.UpdateApiEnvCategoryVo;
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
@RequestMapping("/apiEnvCategory")
@io.swagger.annotations.Api(tags = "api-env-category")
public class ApiEnvCategoryController {

    @Autowired
    private IApiEnvCategoryService apiEnvCategoryService;

    @ApiOperation(value = "create")
    @PostMapping("/create")
    public ResultVo<Boolean> create(AddApiEnvCategoryVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiEnvCategoryService.create(param));
        return response.success();
    }

    @ApiOperation(value = "update")
    @PostMapping("/update")
    public ResultVo<Boolean> update(UpdateApiEnvCategoryVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiEnvCategoryService.update(param));
        return response.success();
    }

    @ApiOperation(value = "delete")
    @GetMapping("/delete/{id}")
    public ResultVo<Boolean> delete(@PathVariable("id") String id){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiEnvCategoryService.delete(id));
        return response.success();
    }

    @ApiOperation(value = "detail")
    @GetMapping("/detail/{id}")
    public ResultVo<ApiEnvCategory> detail(@PathVariable("id") String id){
        ResultVo<ApiEnvCategory> response = new ResultVo<>();
        response.setObject(apiEnvCategoryService.detail(id));
        return response.success();
    }

    @ApiOperation(value = "search")
    @PostMapping("/search")
    public ResultVo<Page<ApiEnvCategory>> search(ApiEnvCategorySearchVo param){
        ResultVo<Page<ApiEnvCategory>> response = new ResultVo<>();
        response.setObject(apiEnvCategoryService.page(param));
        return response.success();
    }
}
