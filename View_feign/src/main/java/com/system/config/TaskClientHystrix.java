package com.system.config;

import com.system.consumption.TaskClient;
import com.system.entity.TaskDto;
import com.system.entity.TaskInfoDto;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/18 9:33
 * @version: 1.0
 * @description: 项目服务提供者 熔断器
 **/

@Component
public class TaskClientHystrix implements TaskClient {

    //查找task列表熔断
    @Override
    public Map<Object, Object> list(TaskDto taskDto, String sort, Integer pageIndex, Integer pageSize) {
        Map<Object,Object> map = new HashMap<Object, Object>();
        map.put("total",0);
        map.put("data",0);
        return map;
    }

    //新增一条task的熔断
    @Override
    public int addTask(TaskDto taskDto) {
        return 0;
    }

    //修改task的熔断
    @Override
    public int updTask(TaskDto taskDto) {
        return 0;
    }

    //批量删除task的熔断
    @Override
    public int delTask(List<Integer> ids) {
        return 0;
    }

    //获取一条task的熔断
    @Override
    public TaskInfoDto getTask(Integer taskId) {
        return new TaskInfoDto();
    }




}
