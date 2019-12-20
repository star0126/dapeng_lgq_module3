package com.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

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
@ApiModel(value="BmTask对象", description="")
public class BmTask implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "task_id", type = IdType.AUTO)
    private Integer taskId;

    @TableField("task_name")
    private String taskName;

    @TableField("task_type")
    private String taskType;

    @TableField("task_creator")
    private String taskCreator;

    @TableField("creator_dept")
    private Integer creatorDept;

    @TableField("creat_time")
    private Date creatTime;

    @TableField("end_time")
    private Date endTime;

    @TableField("plan_start_time")
    private Date planStartTime;

    @TableField("plan_end_time")
    private Date planEndTime;

    @TableField("task_executor")
    private String taskExecutor;

    @TableField("task_status")
    private String taskStatus;

    @TableField("task_spec")
    private String taskSpec;


}
