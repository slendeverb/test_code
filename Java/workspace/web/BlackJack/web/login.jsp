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

        <main class="login">
            <div class="login-box">
                <span id="loginSpan">登录：</span>
                <%--用于显示错误信息--%>
                <span id="errorMessageSpan">${pageContext.session.getAttribute("errorMessage")}</span>
            </div>
            <%
                // 显示完成后从session中删除错误信息
                session.removeAttribute("errorMessage");
            %>
            <form action="${pageContext.request.contextPath}/LoginServlet" method="post" class="login-form">
                <input type="text" name="username" id="username" placeholder="用户名">
                <input type="password" name="password" id="password" placeholder="密码">
                <button type="submit" value="登录">登录</button>
                <button type="button" value="注册" id="register">注册</button>
            </form>
        </main>

        <script type="text/javascript" src="js/login.js"></script>
    </body>
</html>
