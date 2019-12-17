package com.system.controllers;

import com.system.consumption.UserClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/17 10:54
 * @version: 1.0
 * @description: 视图跳转控制器
 **/
@Api(tags = "前端视图跳转控制器")
@Controller
public class ViewController {

    //跳转到登录页面
    @ApiOperation(value="跳转到登陆、注册页面", notes="登录、注册")
    @GetMapping(value = "/login")
    public String goLogin(){
        return "login";
    }

    //登录成功跳转首页映射器
    @ApiOperation(value="登陆成功跳转首页", notes="首页")
    @GetMapping(value = "/sys/index")
    public String goIndex(){
        return "sys/index";
    }

    //内联框架展示用户详情
    @ApiOperation(value="内联框架展示用户详情", notes="用户详情")
    @GetMapping(value = "/emp/emp_info")
    public String goEmpInfo(){
        return "emp/emp_info";
    }

    //跳转到修改用户信息的页面
    @ApiOperation(value="内联框架内修改用户信息", notes="修改用户信息")
    @GetMapping(value = "/emp/upd_emp")
    public String goUpdEmp(){
        return "emp/upd_emp";
    }

    //跳转到修改用户密码的页面
    @ApiOperation(value="内联框架内修改用户密码", notes="修改密码")
    @GetMapping(value = "/emp/upd_pwd")
    public String goUpdPwd(){
        return "emp/upd_pwd";
    }

    //跳转到任务信息列表
    @ApiOperation(value="任务管理信息列表", notes="任务信息列表")
    @GetMapping(value = "/task/task_info")
    public String goTaskInfo(){
        return "task/task_info";
    }

    //跳转到新增任务页面
    @ApiOperation(value="内联框架内新增任务", notes="新增任务")
    @GetMapping(value = "/task/add")
    public String goTaskAdd(){
        return "task/task_add";
    }



}
