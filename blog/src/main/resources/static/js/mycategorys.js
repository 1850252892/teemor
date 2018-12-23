$(function () {
    username=1008612;
    $.ajax({
        url:"/groupmodel",
        type:"get",
        dataType:"text",
        data:{
            "username":username
        },
        success:function (data) {
            var group=JSON.parse(data);
            var index=0;
            while(!(group[index]===null||group[index]==undefined)){
                var b="<li><a href='"+"/u/"+username+"/category/"+group[index].gId+"'>"+group[index].gName+"("+group[index].bCount+")</a></li>";
                $("#group_list").append(b);
                index++;
            }
        },
        error:function () {

        }

    })
});

