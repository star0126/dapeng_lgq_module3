
//表单验证
function ckId() {
    var empId = $("#empId").val();
    var empId_info = $("#empId_info");
    empId_info.html("");
    if (empId==null || empId==""){
        empId_info.html("工号不能为空！");
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
$("#empName").blur(ckname);
$("#empId").blur(ckId);

//提交表单
function upds() {
    var flag = true;
    if (!ckphone()){flag=false};
    if (!ckemail()){flag=false};
    if (!ckstatus()){flag=false};
    if (!ckRole()){flag=false};
    if (!ckdept()){flag=false};
    if (!ckname()){flag=false};
    if (!ckId()){flag=false};
    if (flag){
        var empPhone = $("#empPhone").val();
        var empEmail = $("#empEmail").val();
        var empStatus = $("#empStatus").val();
        var empRole = $("#empRole").val();
        var empDept = $("#empDept").val();
        var empSex = $("#empSex").val();
        var empName = $("#empName").val();
        var empId = $("#empId").val();
        var data = {"empId":empId,"empName":empName,"empSex":empSex,"empDept":empDept,
            "empRole":empRole,"empStatus":empStatus,"empEmail":empEmail,"empPhone":empPhone};
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