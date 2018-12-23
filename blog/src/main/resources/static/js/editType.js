function editGroup() {
    var gid=$("#gId").text();
    var gname=$("#gName").val();
    var glabel=$("#gLabel").val();
    if (gname==""&&glabel==""){
        alert("你没有做出任何更改");
        return;
    }

    $.ajax({
       type:"put",
        url:"/admin/put",
        dataType:"text",
        contentType:'application/json;charset=utf-8',
        data:JSON.stringify({
            "gid":gid,
            "glabel":glabel,
            "gname":gname,
        }) ,
        success:function (data) {
            if (data=="TRUE"){
                alert("修改成功");
                location.reload(false);
            }else if (data=="FALSE"){
                alert("修改失败")
            }
        },
        error:function () {
            alert("服务器响应失败...")
        }
    });

}//editgroup

function creategroup() {
    var gname=$("#groupTitle").val();
    var glabel=$("#groupinfo").val();
    var username='1008612';
    if (gname==""&&glabel==""){
        alert("你没有做出任何更改");
        return;
    }

    $.ajax({
        type:"post",
        url:"/admin/group",
        dataType:"text",
        contentType:'application/json;charset=utf-8',
        data:JSON.stringify({
            "glabel":glabel,
            "gname":gname,
            "uid":username
        }) ,
        success:function (data) {
            if (data=="TRUE"){
                location.reload(false);
            }else if (data=="FALSE"){
                alert("创建失败")
            }
        },
        error:function () {
            alert("服务器响应失败...")
        }
    });
}
window.operateEvents = {
    'click .edit' : function(e, value, row, index) {
        var gName=row.gname;
        var editGid=row.gid;
        $("#gId").empty();
        $("#myModalLabel").empty();
        $("#gId").append(editGid);
        $("#myModalLabel").append("编辑分组："+gName);

    },
    'click .del' : function(e, value, row, index) {
        if(!confirm("确认删除分组吗？"))
            return false;
        $.ajax({
            type : "delete", //ajax delete方式请求
            url : "/admin/group",
            contentType:'application/json;charset=utf-8',
            dataType : "text",//因为处理方法返回的类型 text
            data : row.gid,
            success : function(data) {
                if (data == "TRUE") {
                    alert("删除成功");
                   location.reload(false);
                    $('#reportTable').bootstrapTable("refresh", {
                        url : "/admin/editType"
                    })
                }
            },
            error : function(data) {
                alert("请求失败");
            }
        });
    }
};

$(function () {
    //1.初始化Table
    var oTable = new TableInit();
    oTable.Init();
});

var TableInit = function () {
    var username='1008612';
    var oTableInit = {};
    //初始化Table
    oTableInit.Init = function () {
        $('#reportTable').bootstrapTable({
            url: '/grouplist',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: {"uId":username},//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 5,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            contentType: "application/x-www-form-urlencoded",
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            // height: 500,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "no",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            editable : true,
            columns: [
                {
                    field:'gid',
                    visible:false,
                },
                {
                    field: 'gname',
                    title: '分组名',
                    editable:true

                }, {
                    field: 'glabel',
                    title: '描述',

                },
                {
                    field: 'operate',
                    title: ' ',
                    events:'operateEvents',
                    formatter: edit_btn//自定义方法，添加编辑按钮

                },
                {
                    field: 'operate2',
                    title: ' ',
                    events:'operateEvents',
                    formatter: delete_btn//自定义方法，添加编辑按钮
                }
            ],
            // rowStyle: function (row, index) {
            //     var classesArr = ['success', 'info'];
            //     var strclass = "";
            //     if (index % 2 === 0) {//偶数行
            //         strclass = classesArr[0];
            //     } else {//奇数行
            //         strclass = classesArr[0];
            //     }
            //     return { classes: strclass };
            // },//隔行变色
        });

    };


    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            uid:"1008612"
        };
        return temp;
    };
    return oTableInit;
};
function edit_btn(value, row, index) {
    return  '<a class="edit btn active "  data-toggle="modal" data-target="#myModal">编辑</a>';
}
function delete_btn () {
    return  '<a class="del btn active " >删除</a>';
}
