package com.system.controllers;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.dao.BmTaskMapper;
import com.system.entity.BmTask;
import com.system.entity.TaskDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @version: 1.0
 * @description: task控制器
 *
 *
 * @since 2019-12-18
 */
@RestController
@RequestMapping("/task")
public class BmTaskController {

    @Autowired
    private BmTaskMapper taskMapper;

    @ApiOperation(value="查找task列表操作", notes="查找任务列表")
    @PostMapping(value = "/list")
    public Map<Object,Object> list(@RequestBody TaskDto taskDto,  String sort,
                                    @RequestParam(value = "pageIndex",required = true,defaultValue = "0")Integer pageIndex,
                                    @RequestParam(value = "pageSize",required = true,defaultValue = "5")Integer pageSize){
        Map<Object,Object> map = new HashMap<Object, Object>();
        QueryWrapper<BmTask> queryWrapper = new QueryWrapper<>();
        if (taskDto!=null ){
            //状态
            if (taskDto.getTaskStatus()!=null && !"".equals(taskDto.getTaskStatus())){
                queryWrapper.eq("task_status",taskDto.getTaskStatus());
            }
            //创建者
            if (taskDto.getTaskCreator()!=null && !"".equals(taskDto.getTaskCreator())){
                queryWrapper.eq("task_creator",taskDto.getTaskCreator());
            }
            //部门
            if (taskDto.getCreatorDept()!=null){
                queryWrapper.eq("creator_dept",taskDto.getCreatorDept());
            }
            //执行者
            if (taskDto.getTaskExecutor()!=null && !"".equals(taskDto.getTaskExecutor())){
                queryWrapper.eq("task_executor",taskDto.getTaskExecutor());
            }
            //类型
            if (taskDto.getTaskType()!=null && !"".equals(taskDto.getTaskType())){
                queryWrapper.eq("task_type",taskDto.getTaskType());
            }

            //创建时间开始时间
            if (taskDto.getBgtime()!=null){
                Date bgtime = new Date(taskDto.getBgtime());
                queryWrapper.ge("creat_time",bgtime);
            }

            //创建时间止
            if (taskDto.getEdtime()!=null){
                Date edtime = new Date(taskDto.getEdtime());
                queryWrapper.le("creat_time",edtime);
            }
        }
        int total = taskMapper.selectCount(queryWrapper);
        //动态拼接sql
        String lastSql = " ";
        //排序
        if (sort!=null && !"".equals(sort)){
            lastSql +=" order by "+sort;
        }
        //分页
        lastSql += " limit " + pageIndex + "," + pageSize;
        queryWrapper.last(lastSql);
        List<BmTask> data = taskMapper.selectList(queryWrapper);
        map.put("total",total);
        map.put("data",data);
        return map;
    }

    //task封装
    private BmTask packTask(TaskDto taskDto){
        BmTask task = new BmTask();
        task.setTaskId(taskDto.getTaskId())
                .setTaskName(taskDto.getTaskName())
                .setTaskType(taskDto.getTaskType())
                .setTaskCreator(taskDto.getTaskCreator())
                .setCreatorDept(taskDto.getCreatorDept())
                .setCreatTime(taskDto.getCreatTime())
                .setEndTime(taskDto.getEndTime())
                .setPlanStartTime(taskDto.getPlanStartTime())
                .setPlanEndTime(taskDto.getPlanEndTime())
                .setTaskExecutor(taskDto.getTaskExecutor())
                .setTaskStatus(taskDto.getTaskStatus())
                .setTaskSpec(taskDto.getTaskSpec());
        return task;

    }

}

