package com.example.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.ApiInterface;
import com.example.entity.ApiBody;
import com.example.entity.ApiHeaders;
import com.example.entity.ApiParams;
import com.example.service.*;
import com.example.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/27
 */
@Service
public class CommonServiceImpl implements ICommonService {

    @Autowired
    private IApiService apiService;
    @Autowired
    private IApiParamsService apiParamsService;
    @Autowired
    private IApiHeadersService apiHeadersService;
    @Autowired
    private IApiBodyService apiBodyService;

    @Override
    public Object call(String apiId, JSONObject params) {
        ApiInterface entity = apiService.detail(apiId);
        if(entity == null){
            return null;
        }
        List<ApiHeaders> headers = apiHeadersService.findAllByApiId(apiId);
        List<ApiParams> queryParams = apiParamsService.findAllByApiId(apiId);
        ApiBody apiBody = apiBodyService.findByApiId(apiId);
        if (apiBody != null) {
            apiBody.setContent(parse(apiBody.getContent(), params));
        }
        return HttpUtil.call(entity, queryParams, headers, apiBody);
    }

    private String parse(String content, JSONObject params) {
        for (String key : params.keySet()) {
            content = content.replaceAll("\\$\\{".concat(key).concat("\\}")
                    , params.get(key) != null ? params.getString(key) : "");
        }
        return content;
    }
}
