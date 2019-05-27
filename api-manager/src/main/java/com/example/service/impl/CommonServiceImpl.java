package com.example.service.impl;

import com.example.entity.Api;
import com.example.service.IApiService;
import com.example.service.ICommonService;
import com.example.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

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

    @Override
    public Object call(String apiId) {
        Api entity = apiService.detail(apiId);
        if(entity == null){
            return null;
        }
        if(entity.getMethod().equals(HttpMethod.GET.name())){
            return HttpUtil.get(entity.getUrl());
        }
        return null;
    }
}
