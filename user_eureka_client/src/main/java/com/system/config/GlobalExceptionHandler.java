package com.system.config;


import com.system.mytemplate.GeneralException;
import com.system.mytemplate.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @ProjectName: dapeng_lgq
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/13 16:13
 * @version: 1.0
 * @description: 全局异常处理
 **/
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    //格式化异常记录日志
    private static final String logExceptionFormat = new Date().toString()+",springBoot捕获全局异常: 代号: %s 信息: %s";
    //引入日志信息
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    //不合法参数异常
    @ExceptionHandler({IllegalArgumentException.class})
    public String illeaglAargumentException(IllegalArgumentException ex){
        return resultFormat(HttpStatus.CONFLICT.value(),ex);
    }

    //通用异常处理
    @ExceptionHandler({GeneralException.class})
    public String generalException(GeneralException ex){
        return resultFormat(ex.getCode(),ex);
    }

    //其他异常
    @ExceptionHandler({Exception.class})
    public String exception(Exception ex) {
        return resultFormat(11, ex);
    }

    //定义异常返回值
    private <T extends Throwable> String resultFormat(Integer code, T ex) {
        ex.printStackTrace();
        log.error(String.format(logExceptionFormat, code, ex.getMessage()));
        return JsonResult.failed(code, ex.getMessage(),null);
    }

}
