<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="file" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>giveContent</title>
    <a href="giveContent.jsp">我要写文件</a>
    <a href="lookContent.jsp">我要读文件</a>
</head>
<body bgcolor="yellow">
<font size="2">
    <form action="writeContent.jsp" method="post">
        请选择一个目录:
        <select name="fileDir">
            <option value="C:/1000">C:/1000</option>
            <option value="D:/2000">D:/2000</option>
            <option value="D:/1000">D:/1000</option>
        </select><br>
        输入保存文件的名字:<input type="text" name="fileName"><br>
        输入文件的内容:<br>
        <textarea name="fileContent" cols="28" rows="5"></textarea><br>
        <input type="submit" value="提交">
    </form>
</font>
</body>
</html>
