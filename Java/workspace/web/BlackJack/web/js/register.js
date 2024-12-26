let cancelButton = document.getElementById("cancel");
cancelButton.addEventListener("click", function (event) {
    location.href='/BlackJack'
})

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