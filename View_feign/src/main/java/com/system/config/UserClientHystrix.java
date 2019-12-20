
package com.system.config;

import com.system.consumption.UserClient;
import com.system.entity.EmpDto;


import com.system.mytemplate.JsonResult;
import org.springframework.stereotype.Component;

import java.util.ArrayList;


/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/18 9:36
 * @version: 1.0
 * @description: 用户服务提供者 熔断器
 **/

@Component
public class UserClientHystrix implements UserClient {


    //登录熔断
    @Override
    public String login(EmpDto emp) {
        return JsonResult.failed(100, "服务器忙碌，稍后再试！",null);
    }

    //修改个人信息熔断
    @Override
    public int upd(EmpDto emp) {
        return -1;
    }

    //用户注册的熔断
    @Override
    public int register(EmpDto emp) {
        return -1;
    }

    //获取用户信息列表熔断
    @Override
    public Object getEmpList() {
        return new ArrayList<EmpDto>();
    }
}

