package com.hm.sm.dao;

import com.hm.sm.entity.Staff;
import org.springframework.stereotype.Repository;

/**
 * @program: sm
 * @Date: 2019/6/21 18:36
 * @Author: Mr.Han
 * @Description:
 */
@Repository("selfDao")
public interface SelfDao {

 /**
  * 登陆
  *
  * @param account
  * @return
  */
 Staff selectByAccount(String account);

}
