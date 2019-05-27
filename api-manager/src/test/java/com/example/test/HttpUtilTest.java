package com.example.test;

import com.example.util.HttpUtil;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/27
 */
public class HttpUtilTest {

    @Test
    public void testGet(){
        String url = "http://www.izhishi.site";
        System.out.println(HttpUtil.get(url, null));
    }

    @Test
    public void testGetQueryParams(){
        String url = "https://postman-echo.com/get" ;
        Map<String, String> queryParams = new HashMap<>(1);
        queryParams.put("test", "123");
        System.out.println(HttpUtil.get(url, queryParams));
    }

    @Test
    public void testPost(){
        String url = "https://postman-echo.com/post";
        Map<String, String> params = new HashMap<>();
        params.put("strange", "boom");
        System.out.println(HttpUtil.postFormData(url, params));
    }

}
