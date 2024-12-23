<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>lookContent</title>
    <a href="giveContent.jsp">我要写文件</a>
    <a href="lookContent.jsp">我要读文件</a>
</head>
<body bgcolor="yellow">
<font size="2">
    <form action="readContent.jsp" method="post" name="form">
        输入文件的路径(如:d:/1000):<input type="text" name="fileDir"><br>
        输入文件的名字:(如:Hello.java):<input type="text" name="fileName"><br>
        <input type="submit" value="读取" name="submit">
    </form>
</font>
<a href="giveContent.jsp">我要写文件</a>
</body>
</html>
