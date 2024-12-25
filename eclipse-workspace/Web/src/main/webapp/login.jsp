<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>登录</title>
        <link rel="stylesheet" type="text/css" href="css/login.css">
    </head>
    <body>
        <header>
            <h2>BlackJack</h2>
        </header>

        <main>
            <h3>登录：</h3>
            <form action="/Web/LoginServlet" method="post">
                请输入账号：<input type="text" name="username" id="username">
                <br>请输入密码：<input type="password" name="password" id="password">
                查看密码<input type="checkbox" id="checkPasswordLogin">
                <br><input type="submit" value="登录">
                <input type="button" value="注册" id="register">
            </form>
        </main>

        <script type="text/javascript" src="js/login.js"></script>
    </body>
</html>
