package com.hm.sm.global;

import com.hm.sm.entity.Log;
import com.hm.sm.entity.Staff;
import com.hm.sm.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @Author Administrator
 * @Date 2019/6/24/024 0:23
 */
@Component
@Aspect
public class LogAdvice {

    @Autowired
    private LogService logService;

    /**
     * 实现操作日志
     *
     * @param joinPoint
     */

    @After("execution(* com.hm.sm.controller.*.*(..)) &&  !execution(* com.hm.sm.controller.SelfController.*(..)) &&  !execution(* com.hm.sm.controller.*.to*(..))")
    public void operationLog(JoinPoint joinPoint) {
        //1.创建log对象
        Log log = new Log();
        log.setModule(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        log.setOperator(((Staff) session.getAttribute("USER")).getAccount());
        log.setResult("成功");
        logService.addOperationLog(log);

    }

    /**
     * 实现系统日志
     *
     * @param joinPoint
     */

    @AfterThrowing(throwing = "e", pointcut = "execution(* com.hm.sm.controller.*.*(..)) &&  !execution(* com.hm.sm.controller.SelfController.*(..))")
    public void systemLog(JoinPoint joinPoint, Throwable e) {
        //1.创建log对象
        Log log = new Log();
        log.setModule(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();

        log.setOperator(((Staff) session.getAttribute("USER")).getAccount());
        log.setResult(e.getMessage());
        logService.addSystemLog(log);

    }

    /**
     * 实现登录日志
     * 登录日志
     *
     * @param joinPoint
     */

    @After("execution(* com.hm.sm.controller.SelfController.login(..))")
    public void loginLog(JoinPoint joinPoint) {
        log(joinPoint);
    }

    /**
     * 实现登录日志
     * 登出日志
     *
     * @param joinPoint
     */

    @Before("execution(* com.hm.sm.controller.SelfController.logout(..))")
    public void logoutLog(JoinPoint joinPoint) {
        log(joinPoint);
    }

    private void log(JoinPoint joinPoint) {
        //1.创建log对象
        Log log = new Log();
        log.setModule(joinPoint.getTarget().getClass().getSimpleName());
        log.setOperation(joinPoint.getSignature().getName());
        HttpServletRequest request = (HttpServletRequest) joinPoint.getArgs()[0];
        HttpSession session = request.getSession();
        Staff staff = (Staff) session.getAttribute("USER");
        if (staff == null) {
            String account = request.getParameter("account");
            log.setOperator(account);
            log.setResult("失败");
        } else {
            log.setOperator(staff.getAccount());
            log.setResult("成功");
        }
        logService.addLoginLog(log);
    }
}
