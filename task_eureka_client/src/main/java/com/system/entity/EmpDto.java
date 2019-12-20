package com.system.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

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
public class EmpDto implements Serializable {

    private static final long serialVersionUID=1L;

    private String empId;

    private String empPwd;

    private String empName;

    private String empSex;

    private Integer empDept;

    private String empRole;

    private String empStatus;

    private String empEmail;

    private String empPhone;

    private Integer jurIde;


}
