package com.hm.sm.service.impl;

import com.hm.sm.dao.SelfDao;
import com.hm.sm.dao.StaffDao;
import com.hm.sm.entity.Staff;
import com.hm.sm.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @program: sm
 * @Date: 2019/6/21 18:41
 * @Author: Mr.Han
 * @Description:
 */
@Service("selfService")
public class SelfServiceImp implements SelfService {
 @Autowired
 private SelfDao selfDao;

 @Autowired
 private StaffDao staffDao;


 public Staff login(String account, String password) {

  Staff staff = selfDao.selectByAccount(account);
  if (staff == null) {
   return null;
  }
  if (staff.getPassword().equals(password)) {
   return staff;
  }
  return null;
 }

 /**
  * 修改密码
  *
  * @param id
  * @param password
  */
 public void changePassword(Integer id, String password) {
  Staff staff = staffDao.findById(id);
  staff.setPassword(password);
  staffDao.updateStaff(staff);
 }
}
