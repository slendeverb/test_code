// 取消按钮
let cancelButton = document.getElementById("cancel");
// 绑定事件，跳转到主页面
cancelButton.addEventListener("click", function (event) {
    location.href='/BlackJack'
})

// 本来是用于控制密码的显示，但是浏览器自带了，现已废弃
let checkPasswordRegisterCheckbox = document.getElementById("checkPasswordRegister");
checkPasswordRegisterCheckbox.addEventListener("change", function (event) {
    if(checkPasswordRegisterCheckbox["checked"]){
        document.getElementById('password').type="text"
        document.getElementById("confirmPassword").type="text"
    }else {
        document.getElementById('password').type="password"
        document.getElementById("confirmPassword").type="password"
    }
})