package com.system.controllers;


import com.system.dao.BmTaskInfoMapper;
import com.system.entity.BmTask;
import com.system.entity.BmTaskInfo;
import com.system.entity.TaskInfoDto;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import java.util.Date;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-12-21
 */
@RestController
@RequestMapping("/taskInfo")
public class BmTaskInfoController {

    @Autowired
    private BmTaskInfoMapper taskInfoMapper;

    @ApiOperation(value="获取一条任务的操作", notes="获取一条任务")
    @GetMapping(value = "/getTaskInfo/{taskId}")
    //获取一条任务信息，根据主键
    public TaskInfoDto getTaskInfo(@PathVariable("taskId")  @RequestParam("taskId") Integer taskId){
        BmTaskInfo taskInfo = taskInfoMapper.selectById(taskId);
        TaskInfoDto taskInfoDto = this.packTaskInfo(taskInfo);
        return taskInfoDto;
}

    private TaskInfoDto packTaskInfo(BmTaskInfo taskInfo){
        TaskInfoDto taskInfoDto = new TaskInfoDto();
        taskInfoDto.setTaskId(taskInfo.getTaskId())
                .setTaskName(taskInfo.getTaskName())
                .setTaskType(taskInfo.getTaskType())
                .setTaskCreator(taskInfo.getTaskCreator())
                .setCreatorName(taskInfo.getCreatorName())
                .setCreatorDept(taskInfo.getCreatorDept())
                .setDeptName(taskInfo.getDeptName())
                .setCreatTime(taskInfo.getCreatTime())
                .setEndTime(taskInfo.getEndTime())
                .setPlanEndTime(taskInfo.getPlanEndTime())
                .setPlanStartTime(taskInfo.getPlanStartTime())
                .setTaskExecutor(taskInfo.getTaskExecutor())
                .setExecutor(taskInfo.getTaskExecutor())
                .setTaskStatus(taskInfo.getTaskStatus())
                .setTaskSpec(taskInfo.getTaskSpec());
        return taskInfoDto;

    }



}

