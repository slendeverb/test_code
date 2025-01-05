// 注册按钮
let registerButton = document.getElementById("register");
// 绑定事件，跳转到注册页面
registerButton.addEventListener("click", function (event) {
    location.href='/BlackJack/register.jsp'
})

// 本来是用于控制密码的显示，但是浏览器自带了，现已废弃
let checkPasswordLoginCheckbox = document.getElementById("checkPasswordLogin");
checkPasswordLoginCheckbox.addEventListener("change", function (event) {
    if(checkPasswordLoginCheckbox["checked"]){
        document.getElementById('password').type="text"
    }else {
        document.getElementById('password').type="password"
    }
})