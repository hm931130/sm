package com.hm.sm.dao;

import com.hm.sm.entity.Staff;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: sm
 * @Date: 2019/6/20 9:47
 * @Author: Mr.Han
 * @Description:
 */
@Repository("staffDao")
public interface StaffDao {

 void addStaff(Staff staff);

 void updateStaff(Staff staff);

 void deleteStaff(Integer id);

 Staff findById(Integer id);

 List<Staff> findAll();
}
