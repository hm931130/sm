package com.hm.sm.service;

import com.hm.sm.entity.Staff;

/**
 * @program: sm
 * @Date: 2019/6/21 18:18
 * @Author: Mr.Han
 * @Description:
 */
public interface SelfService {

 Staff login(String account,String password);

 void changePassword(Integer id, String password);

}
