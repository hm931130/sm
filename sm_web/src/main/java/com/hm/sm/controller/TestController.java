package com.hm.sm.controller;

import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description: TODO(测试控制器)
 * @Author Administrator
 * @Date 2019/6/19/019 0:37
 */

/**
 * test/*.do
 */
@Controller("testController")
public class TestController {


    /**
     * /test/show.do  /show.jsp
     */
    public void show(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("name", "张三");
        request.getRequestDispatcher("../show.jsp").forward(request, response);
    }


}
