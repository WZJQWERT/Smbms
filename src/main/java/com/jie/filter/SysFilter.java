package com.jie.filter;


import com.jie.pojo.User;
import com.jie.util.Constants;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SysFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request =(HttpServletRequest) req ;
        HttpServletResponse response=(HttpServletResponse) resp;
        //过滤器，从session 获取用户
        User user = (User) request.getSession().getAttribute(Constants.USER_SESSION);
        if(user==null){//已经被移除或者注销或者weidenglv
            //踩坑，这个是**你项目**下面的err.jsp
            response.sendRedirect("/Smbms/error.jsp");
        }else{
            chain.doFilter(req,resp);
        }
    }

    @Override
    public void destroy() {

    }
}

