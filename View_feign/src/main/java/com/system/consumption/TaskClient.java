package com.system.consumption;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/17 11:41
 * @version: 1.0
 * @description: 项目服务提供者  调用接口
 **/
@FeignClient(value = "task-eureka-client")
public interface TaskClient {
}
