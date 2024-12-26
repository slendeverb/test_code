<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>注册</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/register.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/BlackJack.css">
    </head>
    <body>
        <header>
            <h2>BlackJack</h2>
        </header>

        <main>
            <span>注册：</span>
            <span>${pageContext.session.getAttribute("errorMessage")}</span>
            <%
                session.removeAttribute("errorMessage");
            %>
            <form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
                <label for="username">请输入账号：</label> <input type="text" name="username" id="username">
                <br><label for="password">请输入密码：</label> <input type="password" name="password" id="password">
                <label for="checkPasswordRegister">查看密码</label> <input type="checkbox" id="checkPasswordRegister">
                <br><label for="confirmPassword">请重复密码：</label> <input type="password" name="confirmPassword" id="confirmPassword">
                <br><input type="submit" value="注册">
                <input type="button" value="取消" id="cancel">
            </form>
        </main>

        <script type="text/javascript" src="js/register.js"></script>
    </body>
</html>
