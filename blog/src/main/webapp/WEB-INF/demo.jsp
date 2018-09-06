<%--
  Created by IntelliJ IDEA.
  User: 1850252892@qq.com
  Date: 2018/1/26
  Time: 15:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>demo</title>


    <script type="text/javascript" charset="utf-8" src="/js/jquery-3.2.1.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.parse.js"></script>

    <script type="text/javascript" charset="utf-8" src="/ueditor/third-party/SyntaxHighlighter/shCore.js"></script>
    <link rel="stylesheet" href="/ueditor/third-party/SyntaxHighlighter/shCoreDefault.css">

    <style type="text/css">
        div{
            width:100%;
        }
    </style>
</head>
<body>
    hello worlsssd;


    <div>
        <h1>完整demo</h1>
        <script id="editor" type="text/plain" style="width:1024px;height:500px;"></script></div>
        <button type="button" onclick="submit()">提交</button>

        <div id="data"></div>

</body>
<script type="text/javascript">
    var ue = UE.getEditor('editor');


    function submit() {
        var Content = ue.getAllHtml();
        alert(Content);
        $.ajax({
            url:"/upload",
            type:"post",
            dataType:"text",
            data:{
                "data":Content
            },
            success:function(a) {
              //
                alert(a);
                $("#data").append(a);
                SyntaxHighlighter.all();
            },
            erro:function() {
              //
                alert("server erro!!!")
            }
        });
    }
</script></html>
