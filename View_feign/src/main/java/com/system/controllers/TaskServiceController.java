package com.system.controllers;

import com.system.consumption.TaskClient;
import com.system.entity.TaskDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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



}
