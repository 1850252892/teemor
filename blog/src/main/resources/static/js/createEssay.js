var username=1008612;
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
            while(index>=0){
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
    var Content = editor.txt.html();
    var cover=$("#coverImg").attr("src");
    var summary=editor.txt.text();
    var len=summary.length;
    if (len>100){
        summary=summary.substring(0,100);
    }
    $.ajax({
        url: "/admin/essay",
        type: "post",
        dataType: "text",
        contentType:'application/json;charset=utf-8',
        data: JSON.stringify({
            "btitle":essayTitle,
            "bdata":Content,
            "gid":group,
            "uid":username,
            "cover":cover,
            "bSummary":summary

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