<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    <!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

</head>
<body>


<div>
    <div class="input-chunk">
        <div>输入文件：</div>
        <input type="file" value="选择文件" id="upload-file">
        <br>
        <a id="start-upload" href="javascript:void(0);" onclick="uploadFile();">开始上传</a>
    </div>

</div>
<script>
    function uploadFile(){
        var fileObj = document.getElementById("upload-file").files[0]; // 获取文件对象
        var FileController = "/media/create"; // 接收上传文件的后台地址

        if(fileObj){
            alert(fileObj);
            // FormData 对象
            var form = new FormData();
            form.append("file", fileObj);// 文件对象

            // XMLHttpRequest 对象
            var xhr = new XMLHttpRequest();
            xhr.open("post", FileController, true);
            xhr.onload = function () {
                alert(xhr.responseText);
            };
            xhr.send(form);

        }else{
            alert("未选择文件");
        }
    }
    
</script>
</body>
</html>
