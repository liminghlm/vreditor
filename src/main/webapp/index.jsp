<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <script src="jquery-3.3.1.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>
<%--<a href="ftp://192.168.11.207/data/dwe.jpg">aaa</a>--%>
<%--<img src="/test">
<img src="/project/getMainView">--%>

<div>
    <div id="mainview">
        <button id="btn">我是主场景</button>
        <img id="a" src="/project/getMainView?projectid=1">
    </div>

</div>
<script>
    $(function () {
        $("#btn").click(function(){
                $.ajax({
                    type: 'GET',
                    url: '/project/getMainView?projectid=1',
                    dataTyp: 'json',
                    success: function(result){
                        console.log(result);
                    }
                })
        })
    })
    
</script>
</body>
</html>
