package com.yoosal.zqmh;

import com.yoosal.zqmh.pojo.Admin;
import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by 秦明涛 on 2015/6/10.
 * Desc
 */
public class AdminFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse res = (HttpServletResponse) servletResponse;

        Admin admin = (Admin) req.getSession().getAttribute(GlobalVariable.ADMIN_SESSION_KEY); //登录
        if (admin == null || StringUtils.isEmpty(admin.getName())) {
            if (!req.getRequestURI().contains("login") && !req.getRequestURI().contains("uploadImg")) {
                res.sendRedirect(req.getContextPath() + "/bg/login.jsp");
                return;
            }
        }
        filterChain.doFilter(req, res);
    }

    private boolean isJsp(HttpServletRequest request) {
        String uri = request.getRequestURI();
        return uri.endsWith(".html");
    }

    public void destroy() {

    }
}
