<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/1/20
  Time: 18:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Correct</title>
</head>
<body>
    <div id="WarBox"></div>
    <form action="http://localhost:8080/TTFweb_war_exploded/Correct" method="post" enctype="multipart/form-data">
        <label>
            <input type="file" name="warImg" required>
            <textarea name="warSay" rows="5" cols="33" placeholder="请输入你对战争的描述"></textarea>
            <input type="submit">上传
        </label>
    </form>
    <script type="text/javascript">
        window.addEventListener("load",()=>{
            const WarBox = document.getElementById("WarBox");

        })
    </script>
</body>
</html>
