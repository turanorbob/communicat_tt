package com.example.filter;

import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @Description
 * @Author legend <legendl@synnex.com>
 * @Version v1.0.0
 * @Since 1.0
 * @Date 2019/5/30
 */
@Component
@ServletComponentScan
@WebFilter(urlPatterns = "/*")
public class TokenFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String loginPath = "/login.html";

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        if (url.endsWith(loginPath) || url.endsWith("/login")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        HttpSession httpSession = request.getSession();
        Object token = httpSession.getAttribute("token");
        if (token == null) {
            // redirect
            response.sendRedirect(loginPath);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
