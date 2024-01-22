<%@ page import="java.io.File" %><%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/1/20
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Pictures</title>
    <script src="js/jquery-3.7.1.min.js"></script>
    <link rel="stylesheet" href="css/pictures.css">
</head>
<body>
    <div id="ImgBox"></div>
    <button id="Add">添加图片</button>
    <script type="text/javascript">
        <%!
            String[] fileName;
        %>
        <%
            String path = "D:\\ideaWorkFile\\TTFweb\\src\\main\\webapp\\jsp\\img";
            File file = new File(path);
            File[] fs = file.listFiles();
            assert fs != null;
            fileName = new String[fs.length];
            int i = 0;
            for(File f : fs){
                if(!f.isDirectory()) {
                    fileName[i] = f.getName();
                    i++;
                }
            }
        %>
        $(function () {
            const arr = []
            <%
                for(int j = 0;j < fileName.length;j++){
            %>
                arr[<%=j%>] = "<%=fileName[j]%>"
            <%
                }
            %>
            arr.forEach((item,index)=>{
                let $img = '<img src="./img/'+item+'" alt="location" title="本地图片">';
                $("#ImgBox").append($img)
            })
            let btn = $('#Add')
            btn.click(function (){
                let number = Math.floor(Math.random() * 10000);
                let $img = '<img src="https://picsum.photos/200/300?' + number + '" alt="request" title="请求图片">';
                $("#ImgBox").append($img)
            })
        })
    </script>
</body>

</html>
