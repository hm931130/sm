package com.hm.sm.service.impl;

import com.hm.sm.dao.StaffDao;
import com.hm.sm.entity.Staff;
import com.hm.sm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @program: sm
 * @Date: 2019/6/20 18:12
 * @Author: Mr.Han
 * @Description:
 */
@Service("staffService")
public class StaffServiceImpl implements StaffService {

 @Autowired
 private StaffDao staffDao;

 public void addStaff(Staff staff) {
  staff.setPassword("123456");
  staff.setStatus("正常");
  staff.setWorkTime(new Date());
  staff.setLeaveTime(new Date());
  staffDao.addStaff(staff);
 }

 public void updateStaff(Staff staff) {
  staffDao.updateStaff(staff);
 }

 public void deleteStaff(Integer id) {
  staffDao.deleteStaff(id);
 }

 public Staff findById(Integer id) {
  return staffDao.findById(id);
 }

 public List<Staff> findAll() {
  return staffDao.findAll();
 }
}
