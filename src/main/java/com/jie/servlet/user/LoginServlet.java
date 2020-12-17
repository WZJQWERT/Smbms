package com.jie.servlet.user;

import com.jie.pojo.User;
import com.jie.service.user.UserService;
import com.jie.service.user.UserServicesImpI;
import com.jie.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start....");

        //获取用户名和密码
       String userCode= req.getParameter("userCode");
       String userPassword= req.getParameter("userPassword");

        //和数据库中的密码对比，调用业务层
        UserService userService=new UserServicesImpI();
        User user =userService.login(userCode,userPassword);//已查出登录
        if(user!=null) {//查有此人，可以登录
            //将用户信息放在Session中
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            //跳转到jsp下面的frame.jsp
            resp.sendRedirect("jsp/frame.jsp");
        }else{//无法登录
            //转发回登录页面，顺带提示他，用户名和密码错误
            req.setAttribute("error","用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
