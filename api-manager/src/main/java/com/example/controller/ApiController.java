package com.example.controller;

import com.example.entity.ApiInterface;
import com.example.service.IApiService;
import com.example.vo.AddApiVo;
import com.example.vo.ApiSearchVo;
import com.example.vo.ResultVo;
import com.example.vo.UpdateApiVo;
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
@RequestMapping("/api")
@io.swagger.annotations.Api(tags = "api")
public class ApiController {

    @Autowired
    private IApiService apiService;

    @ApiOperation(value = "create")
    @PostMapping("/create")
    public ResultVo<Boolean> create(AddApiVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiService.create(param));
        return response.success();
    }

    @ApiOperation(value = "update")
    @PostMapping("/update")
    public ResultVo<Boolean> update(UpdateApiVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiService.update(param));
        return response.success();
    }

    @ApiOperation(value = "delete")
    @GetMapping("/delete/{id}")
    public ResultVo<Boolean> delete(@PathVariable("id") String id){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiService.delete(id));
        return response.success();
    }

    @ApiOperation(value = "detail")
    @GetMapping("/detail/{id}")
    public ResultVo<ApiInterface> detail(@PathVariable("id") String id){
        ResultVo<ApiInterface> response = new ResultVo<>();
        response.setObject(apiService.detail(id));
        return response.success();
    }

    @ApiOperation(value = "search")
    @PostMapping("/search")
    public ResultVo<Page<ApiInterface>> search(ApiSearchVo param){
        ResultVo<Page<ApiInterface>> response = new ResultVo<>();
        response.setObject(apiService.page(param));
        return response.success();
    }
}
