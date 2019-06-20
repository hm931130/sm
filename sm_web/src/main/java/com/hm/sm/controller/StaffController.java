package com.hm.sm.controller;

import com.hm.sm.entity.Department;
import com.hm.sm.entity.Staff;
import com.hm.sm.service.DepartmentService;
import com.hm.sm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @program: sm
 * @Date: 2019/6/20 18:10
 * @Author: Mr.Han
 * @Description:
 */
@Controller("staffController")
public class StaffController {

 @Autowired
 private StaffService staffService;

 @Autowired
 private DepartmentService departmentService;

 public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  List<Staff> list = staffService.findAll();
  request.setAttribute("LIST", list);
  request.getRequestDispatcher("../staff_list.jsp").forward(request, response);
 }

 public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  List<Department> list = departmentService.getAll();
  request.setAttribute("DLIST", list);
  request.getRequestDispatcher("../staff_add.jsp").forward(request, response);
 }

 public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  String account = request.getParameter("account");
  //账户默认密码
  String password = "123456";
  String status = request.getParameter("status");


  String name = request.getParameter("name");
  String sex = request.getParameter("sex");
  String idNumber = request.getParameter("idNumber");
  String bornDateStr = request.getParameter("bornDate");
  Date bornDate = null;
  try {
   bornDate = new SimpleDateFormat("yyyy-mm-dd").parse(bornDateStr);
  } catch (ParseException e) {
   e.printStackTrace();
  }
  String info = request.getParameter("info");
  //部门Id
  int did = Integer.parseInt(request.getParameter("did"));
  Staff staff = new Staff();
  staff.setAccount(account);
  staff.setDid(did);
  staff.setBornDate(bornDate);
  staff.setInfo(info);
  staff.setName(name);
  staff.setSex(sex);
  staff.setIdNumber(idNumber);
  staffService.addStaff(staff);
  response.sendRedirect("list.do");
 }
}
