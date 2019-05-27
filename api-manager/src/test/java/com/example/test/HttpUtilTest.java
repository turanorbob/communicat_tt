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
        System.out.println(HttpUtil.get(url));
    }

    @Test
    public void testPost(){
        String url = "http://www.izhishi.site/admin/getLogin";
        Map<String, String> params = new HashMap<>();
        params.put("userName", "jiangli");
        params.put("userPwd", "jiangli");
        System.out.println(HttpUtil.postFormData(url, params));
    }

}
