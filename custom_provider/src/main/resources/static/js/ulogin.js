$(function () {
    getUser();
});
function login() {
    var username=$("#username").val();
    var password=$("#password").val();
    var tips=$("#tips");
    tips.empty();
    if(username==""){
        tips.append("用户名不能为空");
        return;
    }
    if (password==""){
        tips.append("密码不能为空");
        return;
    }

    $.ajax({
        url:"/oauth/token",
        type:"get",
        dataType:"text",
        data:{
            "username":username,
            "password":password,
            "grant_type":"password",
            "client_id":"cloudnote",
            "client_secret":"1008612"
        },
        success:function (data) {
            // alert(data);
            var token=JSON.parse(data);
            // alert(token.access_token)
            $.cookie("myCloud_access_token",token.access_token,{path:"/"});
            $.cookie("myCloud_refresh_token",token.refresh_token,{path:"/"});
            $.cookie("myCloud_username",username,{path:"/"});
            $.cookie("myCloud_status","login",{path:"/"});
            $("#myModal").modal("hide");
            getUser();
        },
        error:function () {
            tips.empty();
            tips.append("用户名或密码错误");
        }
    });
}

function getUser() {
    var username=$.cookie("myCloud_username");
    var token=$.cookie('myCloud_access_token');
    var status=$.cookie("myCloud_status");
    if (username==undefined||token==undefined||status=="logout"){
        $("#username").val(username);
        $("#logIco").attr("src","/img/logIco.jpg");
        var box=$("#user_menu");
        box.empty();
        var data=" <a href=\"#\" data-toggle=\"modal\" data-target=\"#myModal\">登录</a>\n" +
            "        <a href=\"#\">注册</a>";
        box.append(data);
        return;
    }
    $.ajax({
        url:"/user",
        type:"get",
        dataType:"text",
        data:{
            "access_token":token,
            "username":username
        },
        success:function (data) {
            var user=JSON.parse(data);
            var box=$("#user_menu");
            box.empty();
            $("#logIco").attr("src","/img/002.jpg");
            var data=
                " <a href=\"/u/"+user.uid+"\" >主页</a>\n" +
                "<a href=\"/admin/console\">发帖</a>"+
                "<a href=\"#\" onclick='logout()'>注销</a>";
            box.append(data);
        },
        error:function () {

        }
    });
}

function logout() {
    $.get("/logout");
    $.cookie("myCloud_status","logout");
    var username=$.cookie("myCloud_username");
    $("#username").val(username);
    $("#logIco").attr("src","/img/logIco.jpg");
    var box=$("#user_menu");
    box.empty();
    var data=" <a href=\"#\" data-toggle=\"modal\" data-target=\"#myModal\">登录</a>\n" +
        "        <a href=\"#\">注册</a>";
    box.append(data);
}