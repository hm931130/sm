package com.hm.sm.controller;

import com.hm.sm.entity.Department;
import com.hm.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @program: sm
 * @Date: 2019/6/19 17:13
 * @Author: Mr.Han
 * @Description:
 */
@Controller("departmentController")
public class DepartmentController {


 @Autowired
 private DepartmentService departmentService;


 public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  List<Department> departments = departmentService.getAll();
  request.setAttribute("LIST", departments);
  request.getRequestDispatcher("../department_list.jsp").forward(request, response);
 }

 public void toEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  int id = Integer.parseInt(request.getParameter("id"));
  Department department = departmentService.get(id);
  request.setAttribute("OBJ", department);
  request.getRequestDispatcher("../department_edit.jsp").forward(request, response);

 }

 public void edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  int id = Integer.parseInt(request.getParameter("id"));
  String name = request.getParameter("name");
  String address = request.getParameter("address");
  Department department = new Department();
  department.setId(id);
  department.setName(name);
  department.setAddress(address);
  departmentService.edit(department);
  response.sendRedirect("list.do");
 }

 public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  String name = request.getParameter("name");
  String address = request.getParameter("address");
  Department department = new Department();
  department.setName(name);
  department.setAddress(address);
  departmentService.add(department);
  response.sendRedirect("list.do");
 }

 public void toAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  request.getRequestDispatcher("../department_add.jsp").forward(request, response);
 }

 public void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  int id = Integer.parseInt(request.getParameter("id"));
  departmentService.remove(id);
  response.sendRedirect("list.do");
 }
}
