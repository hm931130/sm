package com.hm.sm.dao;

import com.hm.sm.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/6/24/024 0:04
 */
@Repository("logDao")
public interface LogDao {

    void insert(Log log);

    List<Log> selectByType(String type);

}
