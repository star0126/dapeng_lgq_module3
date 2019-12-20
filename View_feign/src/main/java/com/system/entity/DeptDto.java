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
@ApiModel(value="BmDept对象", description="")
public class DeptDto implements Serializable {

    private static final long serialVersionUID=1L;

    private Integer deptId;

    private String deptName;

    private Integer parentId;


}
