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
@ApiModel(value="BmEmp对象", description="")
public class BmEmp implements Serializable {

    private static final long serialVersionUID=1L;

    @TableId(value = "emp_id", type = IdType.INPUT)
    private String empId;

    @TableField("emp_pwd")
    private String empPwd;

    @TableField("emp_name")
    private String empName;

    @TableField("emp_sex")
    private String empSex;

    @TableField("emp_dept")
    private Integer empDept;

    @TableField("emp_role")
    private String empRole;

    @TableField("emp_status")
    private String empStatus;

    @TableField("emp_email")
    private String empEmail;

    @TableField("emp_phone")
    private String empPhone;

    @TableField("jur_ide")
    private Integer jurIde;


}
