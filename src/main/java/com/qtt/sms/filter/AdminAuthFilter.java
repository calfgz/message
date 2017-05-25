package com.qtt.sms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.qtt.sms.model.User;
import com.qtt.sms.service.UserService;

/**
 * admin权限拦截器
 */
public class AdminAuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}
    
    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse resp = (HttpServletResponse)response;
        ServletContext contextPath = req.getSession().getServletContext();

        String uri = req.getRequestURI();
        String adminPerfix = "/admin/";
        if (!(uri.startsWith(adminPerfix) && uri.endsWith(".do"))) {
            chain.doFilter(request, response);
            return;
        }
        if (uri.endsWith("login.do") || uri.endsWith("logout.do")) {
            chain.doFilter(request, response);
            return;            
        }
        
        WebApplicationContext webApplicationContext = WebApplicationContextUtils.getWebApplicationContext(contextPath); 
        UserService userService = (UserService) webApplicationContext.getBean("userService");
        
        User user = userService.getCurrentUser(req, resp);
        if (user == null) {
            sendAuthFail(resp, false);
            return;
        } else {
            chain.doFilter(request, response);
            return;                        
        }
    }
    
    public void sendAuthFail(HttpServletResponse resp, boolean json) throws IOException {
        if (json) {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/json");
            resp.getWriter().println("{\"statusCode\":300, \"message\":\"没有权限！\"}");
        } else {
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("text/html");
            resp.getWriter().print("<div class=\"pageContent\">"
                    + "<div style='padding-top:200px;text-align:center;"
                    + "font-size:24px;color:red;'>"
                    + "没有权限!</div></div>");
        }
    }

}
