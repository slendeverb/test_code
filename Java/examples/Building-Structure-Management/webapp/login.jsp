<%--
  Created by IntelliJ IDEA.
  User: HYD
  Date: 2024/6/25
  Time: 10:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page isELIgnored="false" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登陆页面</title>

    <script type="text/javascript" src="scripts/jquery/jquery-1.7.1.js"></script>
    <link href="style/authority/basic_layout.css" rel="stylesheet" type="text/css">
    <link href="style/authority/common_style.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="scripts/authority/commonAll.js"></script>
    <script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.js"></script>
    <script type="text/javascript" src="scripts/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
    <link rel="stylesheet" href="https://cdn.staticfile.net/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://cdn.staticfile.net/jquery/2.1.1/jquery.min.js"></script>
    <script src="https://cdn.staticfile.net/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css" href="style/authority/jquery.fancybox-1.3.4.css" media="screen"></link>
    <script type="text/javascript" src="scripts/artDialog/artDialog.js?skin=default"></script>

    <link href="http://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.6.3/css/font-awesome.min.css" rel="stylesheet">

    <style>
        .form-horizontal{
            background: #fff;
            padding-bottom: 40px;
            border-radius: 15px;
            text-align: center;
        }
        .form-horizontal .heading{
            display: block;
            font-size: 35px;
            font-weight: 700;
            padding: 35px 0;
            border-bottom: 1px solid #f0f0f0;
            margin-bottom: 30px;
        }
        .form-horizontal .form-group{
            padding: 0 40px;
            margin: 0 0 25px 0;
            position: relative;
        }
        .form-horizontal .form-control{
            background: #f0f0f0;
            border: none;
            border-radius: 20px;
            box-shadow: none;
            padding: 0 20px 0 45px;
            height: 40px;
            transition: all 0.3s ease 0s;
        }
        .form-horizontal .form-control:focus{
            background: #e0e0e0;
            box-shadow: none;
            outline: 0 none;
        }
        .form-horizontal .form-group i{
            position: absolute;
            top: 12px;
            left: 60px;
            font-size: 17px;
            color: #c8c8c8;
            transition : all 0.5s ease 0s;
        }
        .form-horizontal .form-control:focus + i{
            color: #00b4ef;
        }
        .form-horizontal .fa-question-circle{
            display: inline-block;
            position: absolute;
            top: 12px;
            right: 60px;
            font-size: 20px;
            color: #808080;
            transition: all 0.5s ease 0s;
        }
        .form-horizontal .fa-question-circle:hover{
            color: #000;
        }
        .form-horizontal .main-checkbox{
            float: left;
            width: 20px;
            height: 20px;
            background: #11a3fc;
            border-radius: 50%;
            position: relative;
            margin: 5px 0 0 5px;
            border: 1px solid #11a3fc;
        }
        .form-horizontal .main-checkbox label{
            width: 20px;
            height: 20px;
            position: absolute;
            top: 0;
            left: 0;
            cursor: pointer;
        }
        .form-horizontal .main-checkbox label:after{
            content: "";
            width: 10px;
            height: 5px;
            position: absolute;
            top: 5px;
            left: 4px;
            border: 3px solid #fff;
            border-top: none;
            border-right: none;
            background: transparent;
            opacity: 0;
            -webkit-transform: rotate(-45deg);
            transform: rotate(-45deg);
        }
        .form-horizontal .main-checkbox input[type=checkbox]{
            visibility: hidden;
        }
        .form-horizontal .main-checkbox input[type=checkbox]:checked + label:after{
            opacity: 1;
        }
        .form-horizontal .text{
            float: left;
            margin-left: 7px;
            line-height: 20px;
            padding-top: 5px;
            text-transform: capitalize;
        }
        .form-horizontal .btn{
            float: right;
            font-size: 14px;
            color: #fff;
            background: #00b4ef;
            border-radius: 30px;
            padding: 10px 25px;
            border: none;
            text-transform: capitalize;
            transition: all 0.5s ease 0s;
        }

        .toggle-password {
            cursor: pointer;
            user-select: none;
            margin-left: 5px;/* 防止文本被选中 */
        }

        @media only screen and (max-width: 479px){
            .form-horizontal .form-group{
                padding: 0 25px;
            }
            .form-horizontal .form-group i{
                left: 45px;
            }
            .form-horizontal .btn{
                padding: 10px 20px;
            }
        }
    </style>
</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-md-offset-3 col-md-6">

            <form class="form-horizontal" action="<%= request.getContextPath()%>/account?method=login" method="post">
                <span class="heading">建筑结构管理模块登录</span>
                <div class="form-group">
                    <i class="fa fa-user"></i>
                    <input type="email" class="form-control" id="inputEmail3" required placeholder="电子邮件" name="email1">
                    <div style="color: #ff0000;font-size: 13px;margin-left: -17px">${emailError }</div>
                </div>
                <div class="form-group help">
                    <input type="password" class="form-control" id="inputPassword3" required placeholder="密码" name="pwd">
                    <div style="color: #ff0000">${pwdError }</div>

                    <i class="fa fa-lock"></i>
                    <a href="#" class="fa fa-question-circle"></a>
                </div>
                <div class="form-group">
                    <div class="main-checkbox">
                        <input type="checkbox" value="None" id="checkbox1" name="check"/>
                        <label for="checkbox1"></label>
                    </div>
                    <span class="text">记住我</span>

                    <button type="button" class="btn btn-default" data-toggle="modal" data-target="#registerModal">注册</button>

                    <button type="submit" class="btn btn-default">立刻登录</button>
                </div>
            </form>
        </div>
    </div>
</div>



<form action="<%= request.getContextPath()%>/account?method=register" method="post" onsubmit="return validatePasswordMatch()">
    <div class="modal fade" id="registerModal" tabindex="-1" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="myModalLabel">你好</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <tr>
                        <td class="ui_text_rt" >邮箱:</td>
                        <td class="ui_text_lt">
                            <input type="email" id="accountNum" name="email2"  class="ui_input_txt04" required placeholder="例：xxx@xxx.com"/>
                        </td>
                    </tr>
                    <br>
                    <br>
                    <tr>
                        <td class="ui_text_rt">密码:</td>
                        <td class="ui_text_lt">
                            <input type="password" id="pwd1" name="pwd1"  class="ui_input_txt04" required placeholder="请输入密码"/>
                            <span class="toggle-password" onclick="togglePasswordVisibility()">👁️</span>
                        </td>
                    </tr>
                    <br>
                    <tr>
                        <td class="ui_text_rt">校验:</td>
                        <td class="ui_text_lt">
                            <input type="password" id="pwd2" name="pwd2"  class="ui_input_txt04" required placeholder="请再次输入密码"/>
                        </td>
                    </tr>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    <button type="submit" class="btn btn-primary" >提交</button>
                </div>
            </div>
        </div>
    </div>
</form>


<script>

        function togglePasswordVisibility() {
            var passwordInput = document.getElementById("pwd1");
            if (passwordInput.type === "password") {
                passwordInput.type = "text";
            } else {
                passwordInput.type = "password";
            }
        }

        function validatePasswordMatch() {
            var password = document.getElementById("pwd1").value;
            var confirmPassword = document.getElementById("pwd2").value;

            if (password !== confirmPassword) {
                alert("密码不匹配,请重新输入第二次密码");
                return false;
            }
            return true;
        }

</script>



</body>

</html>
