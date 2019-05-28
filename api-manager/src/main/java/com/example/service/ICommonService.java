package com.example.service;


import com.alibaba.fastjson.JSONObject;

public interface ICommonService {

    Object call(String apiId, JSONObject params);
}
