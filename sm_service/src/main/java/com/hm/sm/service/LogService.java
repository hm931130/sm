package com.hm.sm.service;

import com.hm.sm.entity.Log;

import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/6/24/024 0:09
 */
public interface LogService {


    void addOperationLog(Log log);

    void addLoginLog(Log log);

    void addSystemLog(Log log);

    List<Log> getSystemLog();

    List<Log> getLoginLog();

    List<Log> getOperationLog();
}
