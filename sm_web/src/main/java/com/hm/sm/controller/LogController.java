package com.hm.sm.controller;

import com.hm.sm.entity.Log;
import com.hm.sm.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/6/24/024 0:22
 */
@Controller("logController")
public class LogController {


    @Autowired
    private LogService logService;


    public void operationLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list = logService.getOperationLog();
        request.setAttribute("LIST", list);
        request.setAttribute("TYPE", "操作");
        request.getRequestDispatcher("../log_list.jsp").forward(request, response);

    }

    public void systemLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list = logService.getSystemLog();
        request.setAttribute("LIST", list);
        request.setAttribute("TYPE", "系统");
        request.getRequestDispatcher("../log_list.jsp").forward(request, response);

    }

    public void loginLog(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Log> list = logService.getLoginLog();
        request.setAttribute("LIST", list);
        request.setAttribute("TYPE", "登录");
        request.getRequestDispatcher("../log_list.jsp").forward(request, response);

    }

}
