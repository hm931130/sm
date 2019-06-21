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

 public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  int id = Integer.parseInt(request.getParameter("id"));
  Staff staff = staffService.findById(id);
  request.setAttribute("OBJ", staff);
  List<Department> departments = departmentService.getAll();
  request.setAttribute("DLIST", departments);
  request.getRequestDispatcher("../staff_edit.jsp").forward(request, response);
 }

 public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  int id = Integer.parseInt(request.getParameter("id"));
  Staff staff = staffService.findById(id);

  String account = request.getParameter("account");
  int did = Integer.parseInt(request.getParameter("did"));
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
  staff.setAccount(account);
  staff.setDid(did);
  staff.setName(name);
  staff.setSex(sex);
  staff.setIdNumber(idNumber);
  staff.setBornDate(bornDate);
  staff.setInfo(info);
  staffService.updateStaff(staff);
  response.sendRedirect("list.do");

 }

 public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  int id = Integer.parseInt(request.getParameter("id"));
  staffService.deleteStaff(id);
  response.sendRedirect("list.do");
 }

 public void detail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  int id = Integer.parseInt(request.getParameter("id"));
  Staff staff = staffService.findById(id);
  request.setAttribute("OBJ", staff);
  request.getRequestDispatcher("../staff_detail.jsp").forward(request, response);
 }
}
