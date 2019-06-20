package com.hm.sm.dao;

import com.hm.sm.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @program: sm
 * @Date: 2019/6/19 16:48
 * @Author: Mr.Han
 * @Description:
 */
@Repository("departmentDao")
public interface DepartmentDao {

 /**
  * 添加部门
  *
  * @param department
  */
 void insert(Department department);

 /**
  * 更新部门
  *
  * @param department
  */
 void update(Department department);

 /**
  * 删除部门
  *
  * @param id
  */
 void delete(Integer id);

 /**
  * 查找部门
  *
  * @param id
  * @return
  */
 Department findById(Integer id);

 /**
  * 查找所有部门
  *
  * @return
  */
 List<Department> findAll();
}
