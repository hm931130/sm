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
   response.sendRedirect("toLogin");
  } else {
   //保存session
   HttpSession session = request.getSession();
   session.setAttribute("USER", staff);
   response.sendRedirect("main.do");
  }
 }

 public void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
    HttpSession session = request.getSession();
    session.setAttribute("USER",null);
    response.sendRedirect("toLogin");
 }


}
