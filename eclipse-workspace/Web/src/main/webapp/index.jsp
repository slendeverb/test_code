<%@page import="bean.TestBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>test</title>
	</head>
	<body>
		<%
			out.print("<h2>你好</h2>");
			request.setCharacterEncoding("UTF-8");
			TestBean testBean=new TestBean();
			if(testBean.getName()==null){
				testBean.setName("test");
			}
			out.print(testBean.getName()+"<br>");
		%>
	</body>
</html>