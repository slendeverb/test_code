let registerButton = document.getElementById("register");

registerButton.addEventListener("click", function (event) {
    location.href='/BlackJack/register.jsp'
})

let checkPasswordLoginCheckbox = document.getElementById("checkPasswordLogin");
checkPasswordLoginCheckbox.addEventListener("change", function (event) {
    if(checkPasswordLoginCheckbox["checked"]){
        document.getElementById('password').type="text"
    }else {
        document.getElementById('password').type="password"
    }
})