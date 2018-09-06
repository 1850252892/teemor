$(
    function(){
        getEssayData();
        getCommentList();
        getParam();
    }

);

var essayData;
var essayId;
var praise="praise";
var diss="diss";
function GetUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}



function getEssayData() {
    essayId=getParam("p/");
    $.ajax({
        url:"/essay",
        type:"get",
        dataType:"text",
        data:{
            "bId":essayId
        },
        success:function (da) {
            var group=JSON.parse(da);
            essayData=group;
            essayId=group.bid;
            $(".essayData").append(""+group.bdata);
            $(".essayTitle").append(group.btitle);
            $("#essayGroup").append("<a href='/queryGroup?groupid="+group.gid+"'>"+group.gname+"</a>");

           var dd=group.btitle+" | "+group.bdate+" | 阅读（"+group.browse+")｜<a href='javascript:isPraise("+"praise"+")' id='praise'>赞("+group.great+")</a>/<a href='javascript:isPraise(diss)' id='diss'>踩("+group.diss+")</a>";
            $("#essayDate").append(dd);
        },
        error:function () {
            
        }
    })
}

function isPraise(statu) {
    $.ajax({
        url:"/essay/great",
        type:"put",
        dataType:"text",
        contentType:"application/json",
        data:JSON.stringify({
            "statu":statu,
            "bid":essayId
        }),
        success:function (data) {
            if (data=="TRUE"){
                getBother();
            }
        },
        erro:function () {
            alert("服务器异常...")
        }
    });
}
function getBother() {
    $.ajax({
        url:"/essay/essayother",
        type:"get",
        dataType:"text",
        data:{
            "bid":essayId
        },
        success:function (data) {
           var d=JSON.parse(data);
            $("#praise").empty();
           $("#praise").append("赞("+d.great+")");
            $("#diss").empty();
            $("#diss").append("踩("+d.diss+")");
        },
        error:function () {
            
        }
    })
}

function submitComment(a){
    var commentData=$("#comment").val();
    if(commentData==""){
        alert("你没有输入任何内容！");
        return;
    }
    var username=$.cookie("myCloud_username");
    var bId=essayData.bid;
    $.ajax({
       url:"/comment",
        type:"post",
        dataType:"text",
        contentType:"application/json",
        data:JSON.stringify({
            "bid":bId,
            "bcdata":commentData,
            "uid":username
        }),
        success:function (data) {
            if (data=="TRUE"){
                $("#comment").val("");
                alert("提交评论成功");

            }
        },
        error:function () {
            alert("提交评论失败！");
        }
    });
}
//赞同评论
function prs(a) {
    $.ajax({
        url:"/comment/praise",
        type:"put",
        dataType:"text",
        contentType:"application/text",
        data:a,
        success:function (data) {
            if (data=="FALSE"){
                alert("操作异常");
            }else{
                $("#"+a).empty();
                $("#"+a).append("赞("+data+")");
            }
        },
        error:function () {
            alert("提交评论失败！");
        }
    });
}
//获取评论列表
function getCommentList(){
    $.ajax({
        url:"/comment",
        type:"get",
        dataType:"text",
        data:{
            "bid":essayId
        },
        success:function (data) {

            var cl=JSON.parse(data);
            var length=cl.length;
            $("#comment_count").empty();
            $("#comment_count").append("评论列表("+length+")/");
            var box=$(".comment_list");
            box.empty();
            for (var i=length-1;i>=0;i--){
                var nickname="\""+cl[i].nickname.toString()+"\"";
                var value=
                    "<div class='comment_li'>" +
                         "<div class='cTitle'>" +
                            "#" +(i+1)+"楼 "+cl[i].bcdate+" | <a href='/user?username="+cl[i].uid+"'>"+cl[i].nickname+
                        "</a></div>" +
                        "<div class='cBody'>" +
                            cl[i].bcdata+
                        "</div>"+
                        "<div class='cBottom'>" +
                        "<a  href='javascript:prs(\""+cl[i].bcid+"\")' class='praise' id='"+cl[i].bcid+"'>赞("+cl[i].great+")</a>"+
                        "<a onclick='asComment("+nickname+")' class='cc' href='#comment'>回复/</a>" ;
                        "</div>";
                    "</div>";

                box.append(value);
            }
        },
        error:function () {
            alert("服务器异常");
        }

    })
}
//回复评论
function asComment(q) {
    $("#comment").val("@"+q+" ");
}

