package com.system.controllers;


import com.system.dao.BmDeptMapper;
import com.system.dao.BmEmpMapper;
import com.system.entity.BmDept;
import com.system.entity.BmEmp;
import com.system.entity.EmpDto;
import com.system.mytemplate.ErrorMessage;
import com.system.mytemplate.GeneralException;
import com.system.mytemplate.JsonResult;
import com.system.util.RedisUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @ProjectName: dapeng_lgq_module3
 * @since: JDK-1.8
 * @author: dapeng-liguoqing
 * @version: 1.0
 * @description: emp控制器
 *
 * @since 2019-12-18
 */
@RestController
@RequestMapping(value = "/emp")
public class BmEmpController {

    @Autowired
    BmEmpMapper bmEmpMapper;
    @Autowired
    BmDeptMapper bmDeptMapper;
    @Autowired
    RedisUtil redisUtil;

    @ApiOperation(value="登陆操作", notes="登录处理")
    @PostMapping(value = "/emp_login")
    public String login(@RequestBody EmpDto emp){
        BmEmp loginEmp = new BmEmp();
        loginEmp
                .setEmpId(emp.getEmpId())
                .setEmpPwd(emp.getEmpPwd());
        loginEmp = bmEmpMapper.selectById(loginEmp);
        if (loginEmp==null || "".equals(loginEmp)){
            throw new GeneralException(1,"用户不存在!");
        }
        String empPwd = loginEmp.getEmpPwd();
        if (empPwd.equals(emp.getEmpPwd())){
            BmDept dept = bmDeptMapper.selectById(loginEmp.getEmpDept());
            String empKey = loginEmp.getEmpId();
            String deptKey = dept.getDeptId().toString();
            if (!redisUtil.hasKey(empKey)){
                redisUtil.setRedis(empKey,loginEmp);
            }
            if (!redisUtil.hasKey(deptKey)){
                redisUtil.setRedis(deptKey,dept);
            }
            Map<Object,Object> map = new  HashMap<Object,Object>();
            map.put("empid",empKey);
            return JsonResult.failed(0, "登录成功！",map);
        }
        throw new GeneralException(2,"登录密码错误！");
    }


    @ApiOperation(value="用户修改信息操作", notes="用户信息修改")
    @PostMapping(value = "/empUpd")
    public int upd(@RequestBody EmpDto emp){
        BmEmp updEmp = this.userPacka(emp);
        int sign = bmEmpMapper.updateById(updEmp);
        return sign;
    }

    @ApiOperation(value="用户注册操作", notes="用户注册")
    @PostMapping(value = "/register")
    public int register(@RequestBody EmpDto emp){
        boolean ind = emp==null || "".equals(emp)?false:true;
        if (!ind){throw new  IllegalArgumentException(ErrorMessage.REGISTERPARAMETERNUL.getText());}
        BmEmp regEmp = userPacka(emp);
        int sign=bmEmpMapper.insert(regEmp);
        return sign;
    }

    //用户封装
    private BmEmp userPacka(EmpDto emp){
        BmEmp bmEmp = new BmEmp();
        bmEmp.setEmpId(emp.getEmpId())
                .setEmpPwd(emp.getEmpPwd())
                .setEmpName(emp.getEmpName())
                .setEmpSex(emp.getEmpSex())
                .setEmpDept(emp.getEmpDept())
                .setEmpRole(emp.getEmpRole())
                .setEmpStatus(emp.getEmpStatus())
                .setEmpEmail(emp.getEmpEmail())
                .setEmpPhone(emp.getEmpPhone())
                .setJurIde(emp.getJurIde());
        return bmEmp;
    }



}

