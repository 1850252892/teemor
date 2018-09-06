window.operateEvents = {
    'click .edit' : function(e, value, row, index) {
        var editBid=row.bid;
        location.href="/admin/editEssay?essayId="+editBid;
    },
    'click .delete' : function(e, value, row, index) {
        if(!confirm("确认删除？"))
            return false;
        $.ajax({
            type : "delete", //ajax delete方式请求
            url : "/admin/essay",
            dataType : "text",//因为处理方法返回的类型 text
            data : row.bid,
            success : function(data) {
                if (data == "TRUE") {
                    alert("删除成功");
                    location.reload(false);
                    $('#reportTable').bootstrapTable("refresh", {
                        url : "/admin/console"
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
    var length=$(window).height();
    var l2=$(".left").height();
    if (l2<length){
        $(".left").height(length);
    }
});

var TableInit = function () {
    var username=$.cookie("myCloud_username");
    var oTableInit = {};
    //初始化Table
    oTableInit.Init = function () {
        $('#essayTable').bootstrapTable({
            url: '/essaylist',         //请求后台的URL（*）
            method: 'get',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            pagination: true,                   //是否显示分页（*）
            sortable: true,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: {"username":username},//传递参数（*）
            sidePagination: "client",           //分页方式：client客户端分页，server服务端分页（*）
            pageNumber: 1,                       //初始化加载第一页，默认第一页
            pageSize: 10,                       //每页的记录行数（*）
            pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
            search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
            contentType: "application/x-www-form-urlencoded",
            strictSearch: true,
            showColumns: true,                  //是否显示所有的列
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
             //height: 800,                        //行高，如果没有设置height属性，表格自动根据记录条数觉得表格高度
            uniqueId: "bid",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            cardView: false,                    //是否显示详细视图
            detailView: false,                   //是否显示父子表
            editable : true,
            columns: [
                {
                    field:'bid',
                    visible:false,
                    sortable: true
                },
                {
                    field:'uid',
                    visible:false,
                    sortable: true
                },
                {
                    field: 'btitle',
                    title: '标题',
                    editable:true,
                    formatter:blog_url

                }, {
                    field: 'bdate',
                    title: '发布日期',

                },
                {
                    field: 'operate',
                    title: '编辑',
                    events:'operateEvents',
                    formatter: edit_btn//自定义方法，添加编辑按钮

                },
                {
                    field: 'operate2',
                    title: '删除',
                    events:'operateEvents',
                    formatter: delete_btn//自定义方法，添加编辑按钮
                }
            ],
            // rowStyle: function (row, index) {
            //     var classesArr = ['success', 'info'];
            //     var strclass = "";
            //     if (index % 2 === 0) {//偶数行
            //         strclass = classesArr[1];
            //     } else {//奇数行
            //         strclass = classesArr[1];
            //     }
            //     return { classes: strclass, height:'30px'};
            // },//隔行变色
        });

    };


    //得到查询的参数
    oTableInit.queryParams = function (params) {
        var temp = {   //这里的键的名字和控制器的变量名必须一直，这边改动，控制器也需要改成一样的
            uId:"1008612"
        };
        return temp;
    };
    return oTableInit;
};
function edit_btn(value, row, index) {
    return  '<a class="edit"  data-toggle="modal" data-target="#myModal">编辑</a>';
}
function delete_btn () {
    return  '<a  class="delete">删除</a>';
}

function blog_url(value, row, index){
    return '<a href="'+'/u/'+row.uid+'/p/'+row.bid+'" style="color: #0f0f0f">'+row.btitle+'</a>';
}