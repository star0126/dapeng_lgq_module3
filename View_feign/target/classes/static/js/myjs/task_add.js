

//获取员工信息
function taskExecutor() {
    var taskExecutor = $("#taskExecutor");
    $.post("/emp/lookup/list",function (list) {
        var executor = "<option value=\"\">全部</option>";
        for (var i=0;i<list.length;i++){
                executor+="<option value='"+list[i].empId+"'>"+list[i].empName+"</option>";
        }
        taskExecutor.html(executor);
    },"json");
}
taskExecutor();

//取消的方法
function checkCancel() {
    location.href="/task_info";
}

//保存的方法
function adds() {
    var taskName = $("#taskName").val();
    var taskType = $("#taskType").val();
    var taskCreator = $("#taskCreator").val();
    var creatorDept = $("#creatorDept").val();
    var endTime = $("#endTime").val();
    endTime = new Date(endTime).getTime();
    var taskExecutor = $("#taskExecutor").val();
    var taskStatus = "已保存";
    var taskSpec = $("#taskSpec").html();
    var data = {"taskName":taskName,"taskType":taskType,"taskCreator":taskCreator,"creatorDept":creatorDept,
        "edtime":endTime,"taskExecutor":taskExecutor,"taskStatus":taskStatus,"taskSpec":taskSpec};
    var flag = true;
    if (!cktaskName()){flag=false};
    if (!cktaskType()){flag=false};
    if (!cktaskCreator()){flag=false};
    if (!ckcreatorDept()){flag=false};
    if (!ckendTime()){flag=false};
    if (!cktaskExecutor()){flag=false};
    if (flag==true){
        $.post("/task/add_task",data,function (sign) {
            if (sign>0){ //成功
                $("#info-modal2").html("保存成功，是否转到列表！");
                $("#alertModel2").modal('show');
            }else { //失败
                $("#info-modal").html("保存失败，请检查！");
                $("#alertModel").modal('show');
            }
        },"json");
    }else {
        $("#info-modal").html("请检查表单！");
        $("#alertModel").modal('show');
    }
}

//创建的方法
function creat() {
    var taskName = $("#taskName").val();
    var taskType = $("#taskType").val();
    var taskCreator = $("#taskCreator").val();
    var creatorDept = $("#creatorDept").val();
    var endTime = $("#endTime").val();
    endTime = new Date(endTime).getTime();
    var taskExecutor = $("#taskExecutor").val();
    var taskStatus = "已创建";
    var taskSpec = $("#taskSpec").html();
    var data = {"taskName":taskName,"taskType":taskType,"taskCreator":taskCreator,"creatorDept":creatorDept,
                "edtime":endTime,"taskExecutor":taskExecutor,"taskStatus":taskStatus,"taskSpec":taskSpec};
    var flag = true;
    if (!cktaskName()){flag=false};
    if (!cktaskType()){flag=false};
    if (!cktaskCreator()){flag=false};
    if (!ckcreatorDept()){flag=false};
    if (!ckendTime()){flag=false};
    if (!cktaskExecutor()){flag=false};
    if (flag==true){
        $.post("/task/add_task",data,function (sign2) {
            if (sign2>0){ //成功
                $("#info-modal2").html("创建成功，是否转到列表！");
                $("#alertModel2").modal('show');
            }else { //失败
                $("#info-modal").html("创建失败，请检查！");
                $("#alertModel").modal('show');
            }
        },"json");
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
$("#creatorDept").blur(ckcreatorDept);
$("#taskCreator").blur(cktaskCreator);
$("#taskType").blur(cktaskType);
$("#taskName").blur(cktaskName);
