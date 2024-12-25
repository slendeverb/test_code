let toIndex = function (){
    location.href='/BlackJack'
}

document.getElementById("cancel").addEventListener('click', toIndex)

let checkPasswordRegister = function (){
    if(document.getElementById('checkPasswordRegister').checked){
        document.getElementById('password').type="text"
        document.getElementById("repassword").type="text"
    }else {
        document.getElementById('password').type="password"
        document.getElementById("repassword").type="password"
    }
}

document.getElementById("checkPasswordRegister").addEventListener('click', checkPasswordRegister)