package com.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@ApiModel(value="TaskInfoDto对象", description="")
public class TaskInfoDto implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer taskId;

    private String taskName;

    private String taskType;

    private String taskCreator;

    private String creatorName;

    private Integer creatorDept;

    private String deptName;

    private Date creatTime;

    private Date endTime;

    private Date planStartTime;

    private Date planEndTime;

    private String taskExecutor;

    private String executor;

    private String taskStatus;

    private String taskSpec;

    private Long bgtime;  //开始时间
    private Long edtime;  //截至时间
    private String sort; //排序字段


}

