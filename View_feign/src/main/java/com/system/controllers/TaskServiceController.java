package com.system.controllers;

import com.system.consumption.TaskClient;
import com.system.entity.TaskDto;
import com.system.entity.TaskInfoDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @create: 2019/12/18 9:43
 * @version: 1.0
 * @description:
 **/
@Api(tags = "任务列表操作控制器")
@RestController
public class TaskServiceController {

    @Resource
    private TaskClient taskClient;

    //查找task列表
    @ApiOperation(value="查找显示任务列表", notes="任务列表展示")
    @PostMapping(value = "/sys/list")
    public Map<Object,Object> list(TaskDto taskDto, String sort,
                                   @RequestParam(value = "pageIndex",required = true,defaultValue = "0")Integer pageIndex,
                                   @RequestParam(value = "pageSize",required = true,defaultValue = "5")Integer pageSize){
        return taskClient.list(taskDto,sort,pageIndex,pageSize);
    }

    //新增一条task
    @ApiOperation(value="用户新增任务", notes="新增任务")
    @PostMapping(value = "/sys/addTask")
    public int addTask(TaskDto taskDto){
        return taskClient.addTask(taskDto);
    }



    //修改一条task
    @ApiOperation(value="用户修改任务", notes="修改任务")
    @PostMapping(value = "/sys/updTask")
    int updTask(TaskDto taskDto){
        return taskClient.updTask(taskDto);
    }

    //批量删除的方法
    @ApiOperation(value="用户批量删除的任务", notes="批量删除")
    @PostMapping(value = "/sys/delTask")
    int delTask(@RequestParam(value = "ids")List<Integer> ids){
        return taskClient.delTask(ids);
    }





}
