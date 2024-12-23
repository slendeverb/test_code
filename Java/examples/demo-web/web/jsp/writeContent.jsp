<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="file" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>writeContent</title>
</head>
<body bgcolor="cyan">
<font size="2">
    <%
        String fileDir=request.getParameter("fileDir");
        String fileName=request.getParameter("fileName");
        String fileContent=request.getParameter("fileContent");
        byte[] c=fileContent.getBytes("iso-8859-1");
        fileContent=new String(c);
    %>
    <file:WriteTag fileDir="<%=fileDir%>" fileName="<%=fileName%>" fileContent="<%=fileContent%>"></file:WriteTag>
</font>
<a href="lookContent.jsp">我要读文件</a>
</body>
</html>
