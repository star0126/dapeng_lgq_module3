
function ckempId() {
    var empId = $("#empId").val();
    var empIdinfo = $("#empId-info");
    empIdinfo.html("");
    if (empId==null || empId==""){
        empIdinfo.html("不能为空！");
        return false;
    }
    return true;
}

function ckempPwd() {
    var empPwd = $("#empPwd").val();
    var empPwdinfo = $("#empPwd-info");
    empPwdinfo.html("");
    if (empPwd==null || empPwd==""){
        empPwdinfo.html("不能为空！");
        return false;
    }
    return true;
}

$("#empPwd").blur(ckempPwd);
$("#empId").blur(ckempId);

function logins() {
    var flag = true;
    if (!ckempId()){flag=false};
    if (!ckempPwd()){flag=false};
    if (flag){
        var empId = $("#empId").val();
        var empPwd = $("#empPwd").val();
        var data = {"empId":empId,"empPwd":empPwd};
        $.post("/emp_login",data,function (dt) {
            $("#empId-info").html("");
            $("#empPwd-info").html("");
            $("#emp-info").html("");
            switch (dt.code) {
                case 0:  //登录成功
                    location.href="/index";
                    break;
                case 1:
                    $("#empId-info").html(dt.msg);
                    break;
                case 2:
                    $("#empPwd-info").html(dt.msg);
                    break;
                default:
                    $("#emp-info").html(dt.msg);
                    break;
            }
        },"json");
    }
}


/*$(function(){*/
    //登录点击事件
    $('#switch_qlogin').click(function(){
        $("#empPhone").val("");
        $("#empEmail").val("");
        $("#empName").val("");
        $("#empIds").val("");
        $('#switch_login').removeClass("switch_btn_focus").addClass('switch_btn');
        $('#switch_qlogin').removeClass("switch_btn").addClass('switch_btn_focus');
        $('#switch_bottom').animate({left:'0px',width:'70px'});
        $('#qlogin').css('display','none');
        $('#web_qr_login').css('display','block');

    });

    //注册点击事件
    $('#switch_login').click(function(){
            $('#switch_login').removeClass("switch_btn").addClass('switch_btn_focus');
            $('#switch_qlogin').removeClass("switch_btn_focus").addClass('switch_btn');
            $('#switch_bottom').animate({left:'154px',width:'70px'});

            $('#qlogin').css('display','block');
            $('#web_qr_login').css('display','none');

    });


//表单验证
function ckId() {
    var empId = $("#empIds").val();
    var empIds_info = $("#empIds_info");
    empIds_info.html("");
    if (empId==null || empId==""){
        empIds_info.html("工号不能为空！");
        return false;
    }
    return true;
}

function ckname() {
    var empName = $("#empName").val();
    var empName_info = $("#empName_info");
    empName_info.html("");
    if (empName==null || empName==""){
        empName_info.html("用户名不能为空！");
        return false;
    }
    return true;
}

function ckpwd() {
    var empPwd = $("#empPwd2").val();
    var empPwd2_info = $("#empPwd2_info");
    empPwd2_info.html("");
    if (empPwd==null || empPwd==""){
        empPwd2_info.html("密码不能为空！");
        return false;
    }
    return true;
}



function ckdept() {
    var empDept = $("#empDept").val();
    var empDept_info = $("#empDept_info");
    empDept_info.html("");
    if (empDept==null || empDept==""){
        empDept_info.html("部门不能为空！");
        return false;
    }
    return  true;
}

function ckRole() {
    var empRole = $("#empRole").val();
    var empRole_info = $("#empRole_info");
    empRole_info.html("");
    if (empRole==null || empRole==""){
        empRole_info.html("职位不能为空！");
        return false;
    } 
    return true;
}
    
function ckstatus() {
    var empStatus = $("#empStatus").val();
    var empStatus_info = $("#empStatus_info");
    empStatus_info.html("");
    if (empStatus==null || empStatus==""){
        empStatus_info.html("状态不能为空！");
        return false;
    }
    return true;
}

function ckemail() {
    var empEmail = $("#empEmail").val();
    var empEmail_info = $("#empEmail_info");
    var regEmail = /^[A-Za-z0-9]+([_\.][A-Za-z0-9]+)*@([A-Za-z0-9\-]+\.)+[A-Za-z]{2,6}$/;
    empEmail_info.html("");
    if (empEmail==null || empEmail==""){
        empEmail_info.html("邮箱不能为空！");
        return false;
    }else if (!regEmail.test(empEmail)){
        empEmail_info.html("邮箱格式不正确！");
        return false;
    }
    return true;
}

function ckphone() {
    var empPhone = $("#empPhone").val();
    var empPhone_info = $("#empPhone_info");
    empPhone_info.html("");
    if (empPhone==null || empPhone==""){
        empPhone_info.html("电话不能为空！");
        return false;
    }
    return true;
}

//绑定失去焦点事件
$("#empPhone").blur(ckphone);
$("#empEmail").blur(ckemail);
$("#empStatus").blur(ckstatus);
$("#empRole").blur(ckRole);
$("#empDept").blur(ckdept);
$("#empPwd2").blur(ckpwd);
$("#empName").blur(ckname);
$("#empIds").blur(ckId);

//提交表单
$("#reg").click(function(){
    var flag = true;
    if (!ckphone()){flag=false};
    if (!ckemail()){flag=false};
    if (!ckstatus()){flag=false};
    if (!ckRole()){flag=false};
    if (!ckdept()){flag=false};
    if (!ckpwd()){flag=false};
    if (!ckname()){flag=false};
    if (!ckId()){flag=false};
    if (flag){
        var empPhone = $("#empPhone").val();
        var empEmail = $("#empEmail").val();
        var empStatus = $("#empStatus").val();
        var empRole = $("#empRole").val();
        var empDept = $("#empDept").val();
        var empSex = $("#empSex").val();
        var empPwd = $("#empPwd2").val();
        var empName = $("#empName").val();
        var empId = $("#empIds").val();
        var data = {"empId":empId,"empName":empName,"empPwd":empPwd,"empSex":empSex,"empDept":empDept,
                    "empRole":empRole,"empStatus":empStatus,"empEmail":empEmail,"empPhone":empPhone};
        $.post("/emp_register",data,function (sig) {
            if (sig>0){
                $("#info-modal2").html("注册成功！初始密码：123456，请返回登录！");
                $("#alertModel2").modal("show");
            }else {
                $("#info-modal2").html("注册失败！");
                $("#alertModel2").modal("show");
            }
        },"json");
    }
});
$("#empDept").change(function(){
    var empDept = $("#empDept").val();
    if (empDept==null || empDept==""){
        var empId = $("#empIds").val("");
    }else {
        var myDate = new Date();
        var year = myDate.getFullYear();    // 获取完整的年份(4位,1970-????)
        var mohtn = eval(myDate.getMonth()+"+"+"1");       // 获取当前月份(0-11,0代表1月)
        var data = myDate.getDate();        // 获取当前日(1-31)
        var hours = myDate.getHours();       // 获取当前小时数(0-23)
        var minute = myDate.getMinutes();     // 获取当前分钟数(0-59)
        var sec = myDate.getSeconds();     // 获取当前秒数(0-59)
        var dt =year+""+mohtn+""+data+""+hours+""+minute+""+sec;
        var num = '';
        for(var i=0;i<3;i++)
        {
            num+=Math.floor(Math.random()*10);
        }
        if (empDept==2 || empDept==4 || empDept==5 ){
            var str1 = "YD"+dt+""+num;
            $("#empIds").val(str1);
        }else {
            var str2 = "XD"+dt+""+num;
            $("#empIds").val(str2);
        }

    }
});

