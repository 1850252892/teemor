$(function () {
    essayList();
});

function essayList() {
    $.ajax({
        url:"/essaylist",
        type:"get",
        dateType:"text",
        contentType:"application/text",
        data:{
            'username':"1008612"
        },
        success:function (data) {
            var list=JSON.parse(data);
            var box=$(".e_list");
            box.empty();
            var length=list.length;
            for (var i=0;i<length;i++){
                // var p="<div class='e'><div class='eTitle'><a   href='/essay/essayData?essayId="+list[i].bid+"'>"+list[i].btitle+"</a></div>"+
                //
                //     "<div class='p_other'>@ "+list[i].bdate+" "+list[i].nickname+" 浏览("+list[i].browse+")"+"</div></div>";

                var p="<div class='e'><div class='eTitle'><a   href='/u/"+list[i].uid+"/p/"+list[i].bid+"'>"+list[i].btitle+"</a></div>"+

                    "<div class='p_other'>@ "+list[i].bdate+" "+list[i].nickname+" 浏览("+list[i].browse+")"+"</div></div>";
                box.append(p);
            }
        },
        error:function () {

        }
    });
}