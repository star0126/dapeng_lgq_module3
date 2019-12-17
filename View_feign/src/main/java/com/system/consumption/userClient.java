package com.system.consumption;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/17 10:58
 * @version: 1.0
 * @description: 用户服务提供者  调用接口
 **/
@FeignClient(value = "user-eureka-client")
public interface UserClient {

    @RequestMapping(value = "/user/test",method = RequestMethod.GET)
    String test();
}
