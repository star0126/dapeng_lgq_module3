package com.system.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author ${author}
 * @since 2019-12-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="TaskDto对象", description="")
public class TaskDto implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer taskId;

    private String taskName;

    private String taskType;

    private String taskCreator;

    private Integer creatorDept;
    private Date creatTime;
    private Date endTime;
    private Date planStartTime;
    private Date planEndTime;

    private String taskExecutor;

    private String taskStatus;

    private String taskSpec;

    //非数据表属性
    private String creatorName; //创建者姓名
    private String deptName; //创建者部门名称
    private String executor; //任务执行者姓名
    private Long bgtime;  //开始时间
    private Long edtime;  //截至时间
    private String sort; //排序字段
}
