package com.example.controller;

import com.example.service.ICommonService;
import com.example.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/27
 */
@RestController
@RequestMapping("/common")
@io.swagger.annotations.Api(tags = "common")
public class CommonController {
    @Autowired
    private ICommonService commonService;

    @ApiOperation(value = "call")
    @PostMapping("/call/{apiId}")
    public ResultVo<Object> call(@PathVariable("apiId") String apiId){
        ResultVo<Object> response = new ResultVo<>();
        response.setObject(commonService.call(apiId));
        return response.success();
    }
}
