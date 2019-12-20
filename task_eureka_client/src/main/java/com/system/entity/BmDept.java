package com.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
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
@ApiModel(value="BmDept对象", description="")
public class BmDept implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    @TableField("dept_name")
    private String deptName;

    @TableField("parent_id")
    private Integer parentId;


}
