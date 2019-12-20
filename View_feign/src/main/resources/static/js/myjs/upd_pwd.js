
function ckpwd() {
    var empPwd = $("#empPwd").val();
    var empPwd_info = $("#empPwd_info");
    empPwd_info.html("");
    if (empPwd==null || empPwd==""){
        empPwd_info.html("密码不能为空！");
        return false;
    }
    return true;
}


var pwds ;
$("#empPwd").blur(function () {
    if (ckpwd()) {
        ckoks();
    }

});

function ckoks() {
    var empPwd = $("#empPwd").val();
    var data = {"pwd":empPwd}
    $.post("/sys/pwd",data,function (sig) {
        if (sig == 0) {
            pwds = true;
            $("#empPwd_info").html("密码正确！");
        }else {
            pwds = false;
            $("#empPwd_info").html("密码错误！");
        }
    },"json");
}

function ckpwd1() {
    var empPwd = $("#empPwd1").val();
    var empPwd_info = $("#empPwd1_info");
    empPwd_info.html("");
    if (empPwd==null || empPwd==""){
        empPwd_info.html("新密码不能为空！");
        return false;
    }
    return true;
}

function ckpwd2() {
    var empPwd3 = $("#empPwd1").val();
    var empPwd = $("#empPwd2").val();
    var empPwd_info = $("#empPwd2_info");
    empPwd_info.html("");
    if (empPwd==null || empPwd==""){
        empPwd_info.html("不能为空！");
        return false;
    }else if (empPwd3!=empPwd) {
        empPwd_info.html("两次密码不相同！");
        return false;
    }
    return true;
}

$("#empPwd1").blur(ckpwd1);
$("#empPwd2").blur(ckpwd2);

function okks() {
    var flag = true;
    if (!ckpwd1()){flag=false};
    if (!ckpwd2()){flag=false};
    if (!ckpwd()){flag=false};
    if (!pwds){if (!ckoks()){flag=false} };
    if (flag) {
        var empId = $("#empId").val();
        var empPwd = $("#empPwd1").val();
        var data = {"empId":empId,"empPwd":empPwd};
        $.post("/sys/emp_upd",data,function (sig) {
            if (sig>0){
                window.parent.location.href="/sys/outlogin";
            }else {
                $("#info-modal").html("修改失败！");
                $("#alertModel").modal("show");
            }
        },"json");
    }
}

function checkCancel() {
    location.href="/emp/emp_info";
}