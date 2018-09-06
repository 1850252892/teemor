var username=$.cookie("myCloud_username");
$(function () {
    $.ajax({
        url:"/grouplist",
        type:"get",
        dataType:"text",
        data:{
            "uId":username
        },
        success:function (data) {
            var group=JSON.parse(data);
            var index=group.length-1;
            while(index>0){
                var a="<div style='width: 10em;float: left;'><input type='radio' name='group'  id='group' value="+group[index].gid+">"+group[index].gname+"</div>";
                 $("#type").append(a);

                 // var b="<li><a href='"+"/queryGroup?groupid="+group[index].gid+"'>"+group[index].gname+"</a></li>";
                 // $("#group_list").append(b);
                index--;
            }
            $("#group").eq(0).attr("checked","checked");
        },
        error:function () {
            
        }

    })
});

function submit() {
    var essayTitle=$("#essayTitle").val();
    var group = $("input[name='group']:checked").val();
     // alert("选中的radio的值是：" + group);
    var Content = ue.getAllHtml();
    $.ajax({
        url: "/admin/essay",
        type: "post",
        dataType: "text",
        contentType:'application/json;charset=utf-8',
        data: JSON.stringify({
            "btitle":essayTitle,
            "bdata":Content,
            "gid":group,
            "uid":username

        }),
        success: function (a) {
            //
            if (a=="TRUE")
                location.href="/admin/console";
        },
        erro: function () {
            //
            alert("server erro!!!")
        }
    });
}