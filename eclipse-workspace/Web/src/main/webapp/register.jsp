<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>注册</title>
        <link rel="stylesheet" type="text/css" href="css/register.css">
    </head>
    <body>
        <header>
            <h2>BlackJack</h2>
        </header>

        <main>
            <h3>注册</h3>
            <form action="/Web/RegisterServlet" method="post">
                请输入账号：<input type="text" name="username" id="username">
                <br>请输入密码：<input type="password" name="password" id="password">
                查看密码<input type="checkbox" id="checkPasswordRegister">
                <br>请重复密码：<input type="password" name="repassword" id="repassword">
                <br><input type="submit" value="注册">
                <input type="button" value="取消" id="cancel">
            </form>
        </main>

        <script type="text/javascript" src="js/register.js"></script>
    </body>
</html>
