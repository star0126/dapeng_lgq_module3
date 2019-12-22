package com.system.controllers;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.system.dao.BmDeptMapper;
import com.system.dao.BmEmpMapper;
import com.system.dao.BmTaskInfoMapper;
import com.system.dao.BmTaskMapper;
import com.system.entity.BmTask;
import com.system.entity.BmTaskInfo;
import com.system.entity.TaskDto;
import com.system.entity.TaskInfoDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.text.ParseException;
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
    @Autowired
    private BmTaskInfoMapper taskInfoMapper;
    @Autowired
    private BmEmpMapper empMapper;
    @Autowired
    private BmDeptMapper deptMapper;

    @ApiOperation(value="查找task列表操作", notes="查找任务列表")
    @PostMapping(value = "/list")
    public Map<Object,Object> list(@RequestBody TaskDto taskDto,  String sort,
                                    @RequestParam(value = "pageIndex",required = true,defaultValue = "0")Integer pageIndex,
                                    @RequestParam(value = "pageSize",required = true,defaultValue = "5")Integer pageSize){
        Map<Object,Object> map = new HashMap<Object, Object>();
        QueryWrapper<BmTaskInfo> queryWrapper = new QueryWrapper<>();
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
        int total = taskInfoMapper.selectCount(queryWrapper);
        //动态拼接sql
        String lastSql = " ";
        //排序
        if (sort!=null && !"".equals(sort)){
            lastSql +=" order by "+sort;
        }
        //分页
        lastSql += " limit " + pageIndex + "," + pageSize;
        queryWrapper.last(lastSql);
        List<BmTaskInfo> data = taskInfoMapper.selectList(queryWrapper);
        map.put("total",total);
        map.put("data",data);
        return map;
    }


    @ApiOperation(value="新增任务的操作", notes="新增任务")
    @PostMapping(value = "/task_add")
    public int taskAdd(@RequestBody TaskDto taskDto){
        int sign;
        try {
            BmTask task = this.packTask(taskDto);
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ed = sd.format(taskDto.getEdtime());
            Date parse = sd.parse(ed);
            task.setEndTime(parse)
                    .setCreatTime(new Date());
            sign = taskMapper.insert(task);
            if (sign>0){
                BmTaskInfo taskInfo = packTaskInfo(task);
                sign = taskInfoMapper.insert(taskInfo);

            }
        } catch (ParseException e) {
            sign = 0;
            e.printStackTrace();
        }
        return sign;

    }

    @ApiOperation(value="修改任务的操作", notes="修改任务")
    @PostMapping(value = "/upd_task")
    public int updTask(@RequestBody TaskDto taskDto){
        int sign = 0;
        try {
            SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String ed = sd.format(taskDto.getEdtime());
            String bg = sd.format(taskDto.getBgtime());
            Date endTime = sd.parse(ed);
            Date creatTime = sd.parse(bg);
            taskDto.setEndTime(endTime)
                    .setCreatTime(creatTime);
            BmTask task = this.packTask(taskDto);
            sign = taskMapper.updateById(task);
            if (sign>0){
                BmTaskInfo taskInfo = packTaskInfo(task);
                sign=taskInfoMapper.updateById(taskInfo);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            return sign;
        }

    }

    @ApiOperation(value="删除任务的操作", notes="删除任务")
    @PostMapping(value = "/del_task")
    public int delTask(@RequestParam(value = "ids") List<Integer> ids){
        int sign = taskMapper.deleteBatchIds(ids);
        if (sign>0){
            sign = taskInfoMapper.deleteBatchIds(ids);
        }
        return sign;
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

    //task转换为taskinfo
    private BmTaskInfo packTaskInfo(BmTask task){
        BmTaskInfo taskInfo = new BmTaskInfo();
        taskInfo.setTaskId(task.getTaskId())
                .setTaskName(task.getTaskName())
                .setTaskType(task.getTaskType())
                .setTaskCreator(task.getTaskCreator())
                .setCreatorName(empMapper.selectById(task.getTaskCreator()).getEmpName())
                .setCreatorDept(task.getCreatorDept())
                .setDeptName(deptMapper.selectById(task.getCreatorDept()).getDeptName())
                .setCreatTime(task.getCreatTime())
                .setEndTime(task.getEndTime())
                .setPlanEndTime(task.getPlanEndTime())
                .setPlanStartTime(task.getPlanStartTime())
                .setTaskExecutor(task.getTaskExecutor())
                .setExecutor(empMapper.selectById(task.getTaskExecutor()).getEmpName())
                .setTaskStatus(task.getTaskStatus())
                .setTaskSpec(task.getTaskSpec());
        return taskInfo;
    }



}

