package com.hm.sm.service.impl;

import com.hm.sm.dao.DepartmentDao;
import com.hm.sm.entity.Department;
import com.hm.sm.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("departmentService")
public class DepartmentServiceImpl implements DepartmentService {
 @Autowired
 private DepartmentDao departmentDao;

 public void add(Department department) {
  departmentDao.insert(department);
 }

 public void remove(Integer id) {
  departmentDao.delete(id);
 }

 public void edit(Department department) {
  departmentDao.update(department);
 }

 public Department get(Integer id) {
  return departmentDao.findById(id);
 }

 public List<Department> getAll() {
  return departmentDao.findAll();
 }
}
