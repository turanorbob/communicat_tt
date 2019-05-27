package com.example.controller;

import com.example.entity.ApiCollection;
import com.example.service.IApiCollectionService;
import com.example.vo.AddApiCollectionVo;
import com.example.vo.ApiCollectionSearchVo;
import com.example.vo.ResultVo;
import com.example.vo.UpdateApiCollectionVo;
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
@RequestMapping("/apiCollection")
@io.swagger.annotations.Api(tags = "api-collection")
public class ApiCollectionController {

    @Autowired
    private IApiCollectionService apiCollectionService;

    @ApiOperation(value = "create")
    @PostMapping("/create")
    public ResultVo<Boolean> create(AddApiCollectionVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiCollectionService.create(param));
        return response.success();
    }

    @ApiOperation(value = "update")
    @PostMapping("/update")
    public ResultVo<Boolean> update(UpdateApiCollectionVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiCollectionService.update(param));
        return response.success();
    }

    @ApiOperation(value = "delete")
    @GetMapping("/delete/{id}")
    public ResultVo<Boolean> delete(@PathVariable("id") String id){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiCollectionService.delete(id));
        return response.success();
    }

    @ApiOperation(value = "detail")
    @GetMapping("/detail/{id}")
    public ResultVo<ApiCollection> detail(@PathVariable("id") String id){
        ResultVo<ApiCollection> response = new ResultVo<>();
        response.setObject(apiCollectionService.detail(id));
        return response.success();
    }

    @ApiOperation(value = "search")
    @PostMapping("/search")
    public ResultVo<Page<ApiCollection>> search(ApiCollectionSearchVo param){
        ResultVo<Page<ApiCollection>> response = new ResultVo<>();
        response.setObject(apiCollectionService.page(param));
        return response.success();
    }
}
