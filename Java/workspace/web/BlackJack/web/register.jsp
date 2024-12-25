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
            <h3>注册：</h3>
            <form action="/BlackJack/RegisterServlet" method="post">
                请输入账号：<input type="text" name="username" id="username">
                <br>请输入密码：<input type="password" name="password" id="password">
                查看密码<input type="checkbox" id="checkPasswordRegister">
                <br>请重复密码：<input type="password" name="repassword" id="repassword">
                <br><input type="submit" value="注册">
                <input type="button" value="取消" id="cancel" onclick="location.href='/BlackJack'">
            </form>
        </main>

        <script type="text/javascript">
            let checkPasswordRegisterFunc = function (e){
                if(document.getElementById('checkPasswordRegister')["checked"]){
                    document.getElementById('password').type="text"
                    document.getElementById("repassword").type="text"
                }else {
                    document.getElementById('password').type="password"
                    document.getElementById("repassword").type="password"
                }
            }
            document.getElementById("checkPasswordRegister").addEventListener('click', checkPasswordRegisterFunc)
        </script>
    </body>
</html>
