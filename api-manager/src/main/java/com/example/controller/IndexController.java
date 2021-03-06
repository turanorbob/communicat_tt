package com.example.controller;

import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
@Controller
public class IndexController {

    @GetMapping("/")
    public String index(ModelMap map, HttpSession httpSession) {
        log.info("sessionId: " + httpSession.getId());
        Map<String, String> data = new HashMap<>();
        data.put("name", "legend");
        map.putAll(data);
        return "views/index";
    }

    @GetMapping("/welcome")
    public String welcome(HttpSession httpSession) {
        log.info("sessionId: " + httpSession.getId());
        return "views/welcome";
    }

    @PostMapping("/post")
    @ResponseBody
    public String post(HttpSession httpSession) {
        log.info("sessionId: " + httpSession.getId());
        return "post";
    }
}
