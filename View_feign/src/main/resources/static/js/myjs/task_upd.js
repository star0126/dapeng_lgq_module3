

//获取员工信息
function taskExecutor() {
    var taskExecutor = $("#taskExecutor");
    var teor = $("#teor").val();
    $.post("/emp/lookup/list",function (list) {
        var executor = "<option value=\"\">全部</option>";
        for (var i=0;i<list.length;i++){
            if (teor==list[i].empId){
                executor+="<option value='"+list[i].empId+"' selected='selected'>"+list[i].empName+"</option>";
            }else {
                executor+="<option value='"+list[i].empId+"'>"+list[i].empName+"</option>";
            }

        }
        taskExecutor.html(executor);
    },"json");
}
taskExecutor();

//确定修改
function iso() {
    var taskId = $("#taskId").val();
    var taskName = $("#taskName").val();
    var taskType = $("#taskType").val();
    var taskCreator = $("#taskCreator").val();
    var creatorDept = $("#creatorDept").val();
    var creatTime = $("#creatTime").val();
    creatTime = new Date(creatTime).getTime();
    var endTime = $("#endTime").val();
    endTime = new Date(endTime).getTime();
    var taskExecutor = $("#taskExecutor").val();
    var taskStatus = $("#taskStatus").val();
    var taskSpec = $("#taskSpec").html();
    var data = {"taskId":taskId,"taskName":taskName,"taskType":taskType,"taskCreator":taskCreator,"creatorDept":creatorDept,
        "bgtime":creatTime,"edtime":endTime,"taskExecutor":taskExecutor,"taskStatus":taskStatus,"taskSpec":taskSpec};
    var flag = true;
    if (!cktaskName()){flag=false};
    if (!cktaskType()){flag=false};
    if (!cktaskCreator()){flag=false};
    if (!ckcreatorDept()){flag=false};
    if (!ckendTime()){flag=false};
    if (!ckcreatTime()){flag=false};
    if (!cktaskExecutor()){flag=false};
    if (flag==true){
        $("#creatorDept").removeAttr("disabled");
        $.post("/task/upd_task",data,function (sign) {
            if (sign>0){ //成功
                $("#info-modal2").html("修改成功，是否转到列表！");
                $("#alertModel2").modal('show');
            }else { //失败
                $("#info-modal").html("修改失败，请检查！");
                $("#alertModel").modal('show');
            }
        },"json");
        $("#creatorDept").attr("disabled","disabled");
    }else {
        $("#info-modal").html("请检查表单！");
        $("#alertModel").modal('show');
    }
}

//模态框提醒按钮
function oks(obj) {
    $("#alertModel2").modal('hide');
    if (obj==0){
        location.href="/task_info";
    }
}

//取消修改
function noo() {
    location.href="/task_info";
}


//表单验证
function cktaskName() {
    var taskName = $("#taskName").val();
    var taskName_info = $("#taskName_info");
    taskName_info.html("");
    if (taskName==""){
        taskName_info.html("任务名不能为空！");
        return false;
    }
    return true;
}

function cktaskType() {
    var taskType = $("#taskType").val();
    var taskType_info = $("#taskType_info");
    taskType_info.html("");
    if (taskType==""){
        taskType_info.html("任务类型不能为空！");
        return false;
    }
    return true;
}

function cktaskCreator() {
    var taskCreator = $("#taskCreator").val();
    var taskCreator_info = $("#taskCreator_info");
    taskCreator_info.html("");
    if (taskCreator==""){
        taskCreator_info.html("创建者不能为空！");
        return false;
    }
    return true;
}

function ckcreatorDept() {
    var creatorDept = $("#creatorDept").val();
    var creatorDept_info = $("#creatorDept_info");
    creatorDept_info.html("");
    if (creatorDept==""){
        creatorDept_info.html("创建者部门不能为空！");
        return false;
    }
    return true;
}

function ckcreatTime() {
    var creatTime = $("#creatTime").val();
    var creatTime_info = $("#creatTime_info");
    creatTime_info.html("");
    if (creatTime==""){
        creatTime_info.html("请指定创建时间");
        return false;
    }
    return true;
}

function ckendTime() {
    var endTime = $("#endTime").val();
    var endTime_info = $("#endTime_info");
    endTime_info.html("");
    if (endTime==""){
        endTime_info.html("请指定截至时间");
        return false;
    }
    return true;
}

function cktaskExecutor() {
    var taskExecutor = $("#taskExecutor").val();
    var taskExecutor_info = $("#taskExecutor_info");
    taskExecutor_info.html("");
    if (taskExecutor==""){
        taskExecutor_info.html("请指定接收者！");
        return false;
    }
    return true;
}

//绑定失去焦点事件
$("#taskExecutor").blur(cktaskExecutor);
$("#endTime").blur(ckendTime);
$("#creatTime").blur(ckcreatTime);
$("#creatorDept").blur(ckcreatorDept);
$("#taskCreator").blur(cktaskCreator);
$("#taskType").blur(cktaskType);
$("#taskName").blur(cktaskName);