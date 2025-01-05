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

        <main class="register">
            <div class="register-box">
                <span id="registerSpan">注册：</span>
                <%--用于显示错误信息--%>
                <span id="errorMessageSpan">${pageContext.session.getAttribute("errorMessage")}</span>
            </div>
            <%
                // 显示完成后从session中删除错误信息
                session.removeAttribute("errorMessage");
            %>
            <form action="${pageContext.request.contextPath}/RegisterServlet" method="post" class="register-form">
                <input type="text" name="username" id="username" placeholder="用户名">
                <input type="password" name="password" id="password" placeholder="密码" >
                <input type="password" name="confirmPassword" id="confirmPassword" placeholder="确认密码">
                <button type="submit" value="注册">注册</button>
                <button type="button" value="取消" id="cancel">取消</button>
            </form>
        </main>

        <script type="text/javascript" src="js/register.js"></script>
    </body>
</html>
