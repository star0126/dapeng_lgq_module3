package com.system.controllers;

import com.system.consumption.UserClient;
import com.system.entity.EmpDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/18 9:46
 * @version: 1.0
 * @description:
 **/
@Api(tags = "用户操作控制器")
@RestController
public class UserServiceController {

    @Resource
    private UserClient userClient;


    @ApiOperation(value="登陆操作", notes="登录处理")
    @PostMapping(value = "/login")
    public String login(EmpDto emp){
        return userClient.login(emp);
    }

    @ApiOperation(value="修改用户信息操作", notes="修改用户信息")
    @PostMapping(value = "/sys/emp_upd")
    public int upd(EmpDto emp){
        return userClient.upd(emp);
    }

    @ApiOperation(value="用户注册操作", notes="用户注册")
    @PostMapping(value = "/sys/register")
    public int register(EmpDto emp){
        if (emp==null || "".equals(emp)){return -1;}
        return userClient.register(emp);
    }

    @ApiOperation(value="获取用户列表", notes="用户列表")
    @PostMapping(value = "/sys/lookup/list")
    public Object getEmpList(){
        return userClient.getEmpList();
    }

    @ApiOperation(value="密码校验操作", notes="密码校验")
    @PostMapping(value = "/sys/pwd")
    public int pwds(@RequestParam(value = "pwd",required = true) String pwd,
                    HttpSession session){
        EmpDto empDto = (EmpDto)session.getAttribute("emp");
        int sign ;
        if (pwd.equals(empDto.getEmpPwd())){
            sign=0;
        }else {
            sign=1;
        }
        return sign;
    }



}
