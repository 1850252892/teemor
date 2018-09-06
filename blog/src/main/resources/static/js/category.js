$(
    function () {
    getList();


});
function GetUrlParam(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null)
        return unescape(r[2]);
    return null;
}
function getParam(name) {
    var pattern =new RegExp(name+"[0-9]+","g");
    var  str =window.location.pathname;
    var r=str.match(pattern);
    if (r!=null){
        return r.replace(name,'');
    }
    return "";
}

function getList(){
    var category=getParam("category/");
    var date=getParam("archives/");
    var url,value;
    if (category!=""){
        url="/api/essay/category";
        value=category;
        getCategory(value);
    }else{
        url="/api/essay/date";
        value=date;
        $(".essayTitle").append("文章归档 - "+date);
    }
    var username=getParam("u/");
    $.ajax({
        url:url,
        type:"get",
        dateType:"text",
        contentType:"application/json",
        data:{
            'value':value
        },
        success:function (data) {
            var list=JSON.parse(data);
            var box=$(".elist");
            box.empty();
            var length=list.length;
            for (var i=0;i<length;i++){
                // alert(list[i].bdata);
                //     alert(getSimpleText(list[i].bdata));
                var p="<div class='eTitle'><a   href='/u/"+username+"/p/"+list[i].bid+"'>"+list[i].btitle+"</a></div>"+

                    "<div class='p_other'>@ "+list[i].bdate+" "+list[i].nickname+" 浏览("+list[i].browse+")"+"</div>";

                box.append(p);
            }
        },
        error:function () {
            
        }
    });
}

function getSimpleText(html){
    var re1 = new RegExp("<.+?>","g");//匹配html标签的正则表达式，"g"是搜索匹配多个符合的内容
    var msg = html.replace(re1,'');//执行替换成空字符
    return msg;
}

function getCategory(a) {
    $.ajax({
        url:"/api/category",
        type:"get",
        dataType:"text",
        data:{
            "gid":a
        },
        success:function (data) {
            var cat=JSON.parse(data);
            $(".essayTitle").append("文章分类 - "+cat.gname);
        },
        error:function () {
            
        }
    })
}