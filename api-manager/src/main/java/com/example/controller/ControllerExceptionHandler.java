package com.example.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/24
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler
    public String handleException(HttpServletResponse response, Exception e) throws IOException {
        response.getWriter().write("{\"code\":\"500\"}");
        return null;
    }
}
