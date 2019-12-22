package com.system.consumption;


import com.system.config.UserClientHystrix;
import com.system.entity.EmpDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/17 10:58
 * @version: 1.0
 * @description: 用户服务提供者  调用接口
 **/
@FeignClient(value = "user-eureka-client",fallback = UserClientHystrix.class)
public interface UserClient {



    //登录
    @RequestMapping(value = "/emp/emp_login",method = RequestMethod.POST)
    String login(@RequestBody EmpDto emp);

    //修改个人信息
    @RequestMapping(value = "/emp/empUpd",method = RequestMethod.POST)
    int upd(@RequestBody EmpDto emp);

    //用户注册
    @RequestMapping(value = "/emp/register",method = RequestMethod.POST)
    int register(@RequestBody EmpDto emp);

    //获取用户信息列表
    @RequestMapping(value = "/emp/lookup/list",method = RequestMethod.POST)
    Object getEmpList();
}
