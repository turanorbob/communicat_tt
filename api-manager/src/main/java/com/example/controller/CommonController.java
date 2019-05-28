package com.example.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.service.ICommonService;
import com.example.vo.ResultVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public ResultVo<Object> call(@PathVariable("apiId") String apiId, @ApiParam(name = "data", example = "{}") @RequestBody String data) {
        ResultVo<Object> response = new ResultVo<>();
        JSONObject params = JSON.parseObject(data);
        response.setObject(commonService.call(apiId, params));
        return response.success();
    }
}
