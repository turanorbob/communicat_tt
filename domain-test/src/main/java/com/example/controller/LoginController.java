package com.example.controller;

import io.swagger.annotations.Api;
import lombok.extern.java.Log;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/29
 */
@Log
@RestController
@RequestMapping("/user")
@Api(tags = "登录")
public class LoginController {

    @GetMapping("/login")
    public Object login(ModelMap map, HttpSession httpSession) {
        Map<String, String> data = new HashMap<>();
        log.info("sessionId1: " + httpSession.getId());
        data.put("sessionid", httpSession.getId());
        httpSession.setAttribute("token", httpSession.getId());
        return data;
    }

}
