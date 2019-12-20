package com.system.controllers;


import com.system.dao.BmEmpMapper;
import com.system.entity.BmEmp;
import com.system.entity.EmpDto;
import com.system.service.IBmEmpService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ${author}
 * @since 2019-12-18
 */
@RestController
@RequestMapping("/emp")
public class BmEmpController {

    @Autowired
    private IBmEmpService empService;

    //异步条件获取Emp
    @ApiOperation(value="查找task列表操作", notes="查找任务列表")
    @PostMapping(value = "/lookup/list")
    public Object getEmpList(){
        List<BmEmp> empList = empService.list();
        List<EmpDto> empDtos = new ArrayList<EmpDto>();
        for (BmEmp emp:empList){
            empDtos.add(this.userPacka(emp));
        }
        return empDtos;
    }

    //用户封装
    private EmpDto userPacka(BmEmp emp){
        EmpDto empDto = new EmpDto();
        empDto.setEmpId(emp.getEmpId())
                .setEmpPwd(emp.getEmpPwd())
                .setEmpName(emp.getEmpName())
                .setEmpSex(emp.getEmpSex())
                .setEmpDept(emp.getEmpDept())
                .setEmpRole(emp.getEmpRole())
                .setEmpStatus(emp.getEmpStatus())
                .setEmpEmail(emp.getEmpEmail())
                .setEmpPhone(emp.getEmpPhone())
                .setJurIde(emp.getJurIde());
        return empDto;
    }

}

