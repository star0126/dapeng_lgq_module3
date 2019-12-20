package com.system.consumption;

import com.system.config.TaskClientHystrix;
import com.system.entity.EmpDto;
import com.system.entity.TaskDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/17 11:41
 * @version: 1.0
 * @description: 项目服务提供者  调用接口
 **/
@FeignClient(value = "task-eureka-client",fallback = TaskClientHystrix.class)
public interface TaskClient {

    //查找task列表
    @RequestMapping(value = "/task/list",method = RequestMethod.POST)
    Map<Object,Object> list(@RequestBody TaskDto taskDto, @RequestParam String sort,
                            @RequestParam Integer pageIndex,@RequestParam Integer pageSize);

}
