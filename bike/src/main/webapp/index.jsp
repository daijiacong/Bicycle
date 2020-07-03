<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8" >
<body>
<h2>Hello World!</h2>
<form action="user/addAvatar" method="post" enctype="multipart/form-data">
    学号：<input name="sno"/> <br>
    姓名：<input name="sname"/> <br>
    上传图片：<input type="file" name="spicture"/><br>
    <input type="submit" value="提交"/>
</form>
</body>
</html>
