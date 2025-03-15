<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="file" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>readContent</title>
</head>
<body bgcolor="cyan">
<font size="2">
    <%
        String fileDir=request.getParameter("fileDir");
        String fileName=request.getParameter("fileName");
    %>
    <file:ReadTag fileDir="<%=fileDir%>" fileName="<%=fileName%>"></file:ReadTag>
</font>
</body>
</html>
