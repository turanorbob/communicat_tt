package com.example.controller;

import com.example.entity.ApiBody;
import com.example.service.IApiBodyService;
import com.example.vo.AddApiBodyVo;
import com.example.vo.ApiBodySearchVo;
import com.example.vo.ResultVo;
import com.example.vo.UpdateApiBodyVo;
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
@RequestMapping("/apiBody")
@io.swagger.annotations.Api(tags = "api-body")
public class ApiBodyController {

    @Autowired
    private IApiBodyService apiBodyService;

    @ApiOperation(value = "create")
    @PostMapping("/create")
    public ResultVo<Boolean> create(AddApiBodyVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiBodyService.create(param));
        return response.success();
    }

    @ApiOperation(value = "update")
    @PostMapping("/update")
    public ResultVo<Boolean> update(UpdateApiBodyVo param){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiBodyService.update(param));
        return response.success();
    }

    @ApiOperation(value = "delete")
    @GetMapping("/delete/{id}")
    public ResultVo<Boolean> delete(@PathVariable("id") String id){
        ResultVo<Boolean> response = new ResultVo<>();
        response.setObject(apiBodyService.delete(id));
        return response.success();
    }

    @ApiOperation(value = "detail")
    @GetMapping("/detail/{id}")
    public ResultVo<ApiBody> detail(@PathVariable("id") String id){
        ResultVo<ApiBody> response = new ResultVo<>();
        response.setObject(apiBodyService.detail(id));
        return response.success();
    }

    @ApiOperation(value = "search")
    @PostMapping("/search")
    public ResultVo<Page<ApiBody>> search(ApiBodySearchVo param){
        ResultVo<Page<ApiBody>> response = new ResultVo<>();
        response.setObject(apiBodyService.page(param));
        return response.success();
    }
}
