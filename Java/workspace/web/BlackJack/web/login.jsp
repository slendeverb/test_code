<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>登录</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/login.css">
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/BlackJack.css">
    </head>
    <body>
        <header>
            <h2>BlackJack</h2>
        </header>

        <main>
            <span>登录：</span>
            <span>${pageContext.session.getAttribute("errorMessage")}</span>
            <%
                session.removeAttribute("errorMessage");
            %>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post">
                <label for="username">请输入账号：</label> <input type="text" name="username" id="username">
                <br><label for="password">请输入密码：</label> <input type="password" name="password" id="password">
                <label for="checkPasswordLogin">查看密码</label> <input type="checkbox" id="checkPasswordLogin">
                <br><input type="submit" value="登录">
                <input type="button" value="注册" id="register">
            </form>
        </main>

        <script type="text/javascript" src="js/login.js"></script>
    </body>
</html>
