

$('#table').bootstrapTable({
    method: "post", //向服务器请求方式，默认为’get’，可选’post’
    url:"/task/list",
    striped: true, //隔行换色。默认false，当设为true则每行表格的背景会显示灰白相间
    dataField:"data",  //返回的实体数据的键
    dataType: "json", //服务器获取的数据类型，默认为json格式字符串
    pagination: true, //分页条，设为true表示显示
    pageSize: 3, //页大小。默认每页显示10条记录。前提是启用了分页功能。
    pageList:[],
    clickToSelect : true, //是否启用点击选中行
    uniqueId:"taskId", //每一行的唯一标识
    sidePagination:"server",
    search: false, //搜索框。在搜索框内只要输入内容即开始搜索。默认false不显示表格右上方搜索框 ，可设为true，
    maintainSelected :true,
    toolbar:"#toolbar",
    contentType: "application/x-www-form-urlencoded; charset=UTF-8", //请求数据的contentType（内容类型）,默认application/json，用来告诉接收端从服务器发来的消息是序列化后的json字符串
    queryParams: function (params) {
        return {
            pageIndex: params.offset,
            pageSize: params.limit,
            pageNumber:params.pageNumber,
            taskType: $("#taskType").val(),
            taskCreator: $("#taskCreator").val(),
            creatorDept: $("#creatorDept").val(),
            taskExecutor: $("#taskExecutor").val(),
            taskStatus: $("#taskStatus").val(),
            bgtime: $("#bgtime").val(),
            edtime: $("#edtime").val(),
            sort: $("#sort").val(),
        }
    }, //当请求数据时，向服务器发送其余的参数
    columns: [
        {
            field: 'selectItem',
            checkbox: true,
            formatter: function (i, row) { // 每次加载 checkbox 时判断当前 row 的 id 是否已经存在全局 Set() 里
                if ($.inArray(row.taskId,
                    overAllIds) != -1) {// 因为 判断数组里有没有这个 id
                    return {
                        checked: true
                        // 存在则选中
                    }
                }
            }
        },
        {
            title: "编号",
            field: 'taskId',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '任务名',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row) {  //数据转换函数
                return '<a href="/upd/'+row.taskId+'">'+row.taskName+'</a> ';
            }
        },
        {
            title: '创建者',
            field: 'creatorName',
            align: 'center'
        },
        {
            title: '部门',
            field: 'deptName',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '任务类型',
            field: 'taskType',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '创建时间',
            field: 'creatTime',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '截至时间',
            field: 'endTime',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '接收时间',
            field: 'planStartTime',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '提交时间',
            field: 'planEndTime',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '任务执行者',
            field: 'executor',
            align: 'center',
            valign: 'middle'
        },
        {
            title: '状态',
            field: 'taskStatus',
            align: 'center',
            valign: 'middle'
        }
    ],
    pagination:true,
    locale:'zh-CN',

});


//异步获取创建者和执行者的方法
function getEmp() {
    var taskCreator = $("#taskCreator");
    var taskExecutor = $("#taskExecutor");
    $.post("/emp/lookup/list",function (list) {
        var creatList = "<option value=\"\">全部</option>";
        var executor = "<option value=\"\">全部</option>";
        for (var i=0;i<list.length;i++){
            if (list[i].empRole=="项目经理" || list[i].empRole=="组长"){
                creatList+="<option value='"+list[i].empId+"'>"+list[i].empName+"</option>";
            }else {
                executor+="<option value='"+list[i].empId+"'>"+list[i].empName+"</option>";
            }
        }
        taskCreator.html(creatList);
        taskExecutor.html(executor);
    },"json");

}
getEmp();

//点击条件检索按钮
function getData(){
    var btime = $("#btime").val();
    var etime = $("#etime").val();
    var bgtimes = new Date(btime).getTime()/1000;
    $("#bgtime").val(bgtimes);
    var edtimes = new Date(etime).getTime()/1000;
    $("#edtime").val(edtimes);
    if (etime!=null && etime!= '' && btime!=null && btime!=''){
        if (etime>btime){

            //刷新表格并回到第一页
           /* $('#table').bootstrapTable("refreshOptions",{pageNumber:1})*/
            $('#table').bootstrapTable("refresh",{pageNumber:1,bgtime:bgtimes,edtime:edtimes});
            $("#bgtime").val("");
            $("#edtime").val("");
        }else {

            $("#info-modal").html("截至时间不能早于开始时间");
            $("#alertModel").modal('show');
        }
    }else {
        //刷新表格并回到第一页
       $('#table').bootstrapTable("refresh",{pageNumber:1,bgtime:bgtimes,edtime:edtimes});
        $("#bgtime").val("");
        $("#edtime").val("");
    }
}

var overAllIds = new Array(); //全局数组  用来存所有复选框选中的ID

function examine(type, datas) {
    if (type.indexOf('uncheck') == -1) {
        $.each(datas,
            function(i, v) {
                // 添加时，判断一行或多行的 id 是否已经在数组里 不存则添加　
                overAllIds.indexOf(v.taskId) == -1 ? overAllIds
                    .push(v.taskId) : -1;
            });
    } else {
        $.each(datas, function(i, v) {
            overAllIds.splice(overAllIds.indexOf(v.taskId), 1); //删除取消选中行
        });
    }
}

$('#table')
    .on(
        'uncheck.bs.table check.bs.table check-all.bs.table uncheck-all.bs.table',
        function(e, rows) {
            var datas = $.isArray(rows) ? rows : [ rows ]; // 点击时获取选中的行或取消选中的行
            examine(e.type, datas); // 保存到全局 Array() 里
        });



// 删除按钮事件
function del() {
    if (!confirm("是否确认删除？"))
        return;

    if (overAllIds.length == 0) {// rows 主要是为了判断是否选中，下面的else内容才是主要
        alert("请先选择要删除的记录!");
        return;
    } else {
        deleteMs(overAllIds);
    }
}
function deleteMs(ids) {
    $.ajax({
        url : "/task/del",
        data : "ids=" + ids,
        type : "post",
        dataType : "json",
        success : function(data) {
            if (data>0){
                $("#info-modal").html("删除成功！");
                $("#alertModel").modal('show');
                $('#table').bootstrapTable("refresh",{pageNumber:1});
            }else {
                $("#info-modal").html("删除失败！");
                $("#alertModel").modal('show');
            }
        },
        error : function (data) {
            $("#info-modal").html("删除错误！请检查！");
            $("#alertModel").modal('show');
        }
    });
}
