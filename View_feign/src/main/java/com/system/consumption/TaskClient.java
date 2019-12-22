package com.system.consumption;

import com.system.config.TaskClientHystrix;
import com.system.entity.EmpDto;
import com.system.entity.TaskDto;
import com.system.entity.TaskInfoDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    //新增task任务
    @RequestMapping(value = "/task/task_add",method = RequestMethod.POST)
    int addTask(@RequestBody TaskDto taskDto);

    //修改taskinfo
    @RequestMapping(value = "/task/upd_task",method = RequestMethod.POST)
    int updTask(@RequestBody TaskDto taskDto);

    //批量删除task
    @RequestMapping(value = "/task/del_task",method = RequestMethod.POST)
    int delTask(@RequestParam(value = "ids") List<Integer> ids);

    //获取一条task
    @RequestMapping(value = "/taskInfo/getTaskInfo/{taskId}",method = RequestMethod.GET)
    TaskInfoDto getTask(@PathVariable("taskId") @RequestParam("taskId") Integer taskId);

}
