$( function(){

    getNewEssay();
    getGroupByDate();
    user();
    // getUser();
    $(".left_menu").height($(window).height());
    $("#comment").empty();
    var key=1;
    $("#essayCategory").click(function () {
        key=0;
        $(".date_essay").fadeOut(500);
        $(".user").fadeOut();
        $(".group_essay").slideDown() ;
        $(".essay_box").animate({left:'450px'});
    });
    $("#essayForDate").click(function () {
        key=0;
        $(".group_essay").fadeOut(500);
        $(".user").fadeOut();
        $(".date_essay").fadeIn(500);
        $(".essay_box").animate({left:'450px'},500
        );
    });
    $("#user_img").click(function () {
        $("#user_menu").slideToggle("slow");
    });
    $(document).click(function () {
            if (key==0){
                key=1;
            }else {
                $(".group_essay").slideUp();
                $(".date_essay").fadeOut(500);
                $(".user").fadeIn();
                $(".essay_box").animate({left:'350px'});

            }

        }

    )
});

function getNewEssay() {
    var username=getParam("u/");
    $.ajax({
        url:"/essaylist",
        type:"get",
        dataType:"text",
        data:{
            "username":username
        },
        success:function (data) {
            var essayList=JSON.parse(data);
            for (var i=0;i<5;i++){
                if (essayList[i]==null){
                    return;
                }
                else {
                    var b="<li><a href='"+"/u/"+username+"/p/"+essayList[i].bid+"'>"+essayList[i].btitle+"</a></li>";
                    $(".essay_list").append(b);
                }
            }
        },
        error:function () {

        }
    })
}
function getGroupByDate() {
    var username=getParam("u/");
    $.ajax({
        url:"/groupbydate",
        type:"get",
        dataType:"text",
        data:{
            "uId":username
        },
        success:function (data) {
            var list=JSON.parse(data);
            var i=0;
            while(!(list[i]==null)){
                var b="<li><a href='"+"/u/"+username+"/archives/"+list[i].date+"'>"+list[i].date+"("+list[i].count+")</a></li>";
                $(".date_list").append(b);
                i++;
            }

        },
        error:function () {

        }
    })
}
function user() {
    var username=getParam("u/");
    $.ajax({
        url:"/user",
        type:"get",
        dataType:"text",
        data:{
            "username":username
        },
        success:function (data) {
            var user=JSON.parse(data);
            $("#author").append("<a href='/u/"+user.uid+"'>"+user.nickname+"</a>")

        },
        error:function () {

        }
    });
}

function getParam(name) {
    var pattern =new RegExp(name+"[0-9]+","g");
    var  str =window.location.pathname;
    var r=str.match(pattern);
    if (r!=null){
        return r.toString().replace(name,'');
    }
    return "";
}