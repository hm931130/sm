package com.hm.sm.service;

import com.hm.sm.entity.Staff;

import java.util.List;

/**
 * @program: sm
 * @Date: 2019/6/20 18:11
 * @Author: Mr.Han
 * @Description:
 */
public interface StaffService {

 void addStaff(Staff staff);

 void updateStaff(Staff staff);

 void deleteStaff(Integer id);

 Staff findById(Integer id);

 List<Staff> findAll();
}
