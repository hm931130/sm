package com.hm.sm.controller;

import com.hm.sm.entity.Staff;
import com.hm.sm.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @program: sm
 * @Date: 2019/6/21 18:42
 * @Author: Mr.Han
 * @Description:
 */
@Controller("selfController")
public class SelfController {

    @Autowired
    private SelfService selfService;

    public void toLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String account = request.getParameter("account");
        String password = request.getParameter("password");
        Staff staff = selfService.login(account, password);
        if (staff == null) {
            response.sendRedirect("toLogin.do");
        } else {
            //保存session
            HttpSession session = request.getSession();
            session.setAttribute("USER", staff);
            response.sendRedirect("main.do");
        }
    }

    public void main(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("index.jsp").forward(request, response);

    }

    public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        session.setAttribute("USER", null);
        response.sendRedirect("toLogin.do");
    }

    public void info(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("../info.jsp").forward(request, response);
    }

    public void toChangePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("../change_password.jsp").forward(request, response);
    }

    public void changePassword(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        HttpSession session = request.getSession();
        Staff staff = (Staff) session.getAttribute("USER");
        String password = request.getParameter("password");
        String password1 = request.getParameter("password1");
        //原密码正确
        if (staff.getPassword().equals(password)) {
            selfService.changePassword(staff.getId(), password1);
            response.getWriter().print("<script type=\"text/javascript\">parent.location.href=\"../logout.do\"</script>");
        } else {
            response.sendRedirect("toChangePassword.do");
        }
    }

}
