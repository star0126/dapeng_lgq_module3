package com.system.aop;


import org.aopalliance.intercept.Joinpoint;
import org.apache.catalina.connector.RequestFacade;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/23 10:05
 * @version: 1.0
 * @description: 用户操作日志aop
 **/
@Aspect
@Configuration
public class UserOperationLogAop {

    //引入日志记录
    private static final Logger aopLog = LoggerFactory.getLogger(UserOperationLogAop.class);

    @Autowired
    private HttpServletRequest request;

    //定义切点表达式
    @Pointcut("execution(* com.system.controllers.*.*(..))")
    public void pointcut(){}

    //环绕AOP切面
    @Around("pointcut()")
    public Object aopBefore(ProceedingJoinPoint joinPoint){
        Object result = null;
        try {
            //获取当前时间毫秒数
            long start = System.currentTimeMillis();
            //获取用户进入操作的时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String startTime = df.format(start);
            //获取简单类名
            String classSimpleName = joinPoint.getSignature().getDeclaringType().getSimpleName();
            //获取方法名
            String name = joinPoint.getSignature().getName();
            result = joinPoint.proceed();
            long end = System.currentTimeMillis();
            //计算操作时长
            long useTime = end - start;
            //获取传入的参数
            Object[] args = joinPoint.getArgs();
            StringBuilder param = new StringBuilder();
            if (args.length==0){
                param.append("0");
            }else {
                param.append("[ ");
                for (Object o:args){
                    param.append(o.toString()+"、");
                }
                param.append("] ");
            }
            //获取ip地址
            String IP = request.getRemoteAddr();
            aopLog.info(startTime+",ip为"+IP+"的用户进入操作。操作类为："+classSimpleName+"，操作方法："+name+"，参数为："+param+"。操作时长："+useTime+"ms");
            System.out.println(startTime+",ip为"+IP+"的用户进入操作。操作类为："+classSimpleName+"，操作方法："+name+"，参数为："+param+"。操作时长："+useTime+"ms");
            return result;
        } catch (Throwable e) {
            long end = System.currentTimeMillis();
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String endTime = df.format(end);
            aopLog.error(endTime+"----出现异常----"+e.getMessage());
            return result;
        }

    }

}
