var myAxios = axios.create({
    baseURL: 'http://localhost/',
    timeout: 5000,
    headers: {'Content-Type': 'application/json'}
});
function getParam(paramName) {
    paramValue = "", isFound = !1;
    if (this.location.search.indexOf("?") == 0 && this.location.search.indexOf("=") > 1) {
        arrSource = unescape(this.location.search).substring(1, this.location.search.length).split("&"), i = 0;
        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++
    }
    return paramValue == "" && (paramValue = null), paramValue
}
$(function () {
    var layer;
    layui.use(['layer','form'],function(){
        layer=layui.layer,form = layui.form;
    })
    var app=new Vue({
        el: '#content',
        data:{
            author:'zhoulk',
            summary_1:'我是妹子UI，中国首个开源 HTML5 跨屏前端框架',
            summary_2:'我不想成为一个庸俗的人。十年百年后，当我们死去，质疑我们的人同样死去，后人看到的是裹足不前、原地打转的你，还是一直奔跑、走到远方的我？',
            article:"",
            tags:"",
            articlesTop:""

        },
        methods: {
            getArticle(){
                var id=getParam("id");
                var _this=this;
                myAxios.get('article/getArticle',{
                    params:{
                        "bId":id
                    }
                })
                    .then(function (response) {
                        var data=response.data;
                        _this.article=data;
                    })
            },
            getTags(){
                var _this=this;
                myAxios.get('groupmodel',{
                    params:{
                        'username':1008612
                    }
                })
                    .then(function (response) {
                        var data=response.data;
                        _this.tags=data;
                    })
            },
            msgBox(data){
                layer.msg(data);
            },
            getTop(){
                var _this=this;
                myAxios.get('article/getArticles',{
                    params:{
                        "pageIndex":1,
                        "pageSize":5
                    }
                })
                    .then(function (response) {
                        var data=response.data;
                        _this.articlesTop=data;
                    })
            }
        }
    });
    app.getArticle();
    app.getTags();
    app.getTop();

})