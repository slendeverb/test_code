let toRegister = function (){
    location.href='/BlackJack/register.jsp'
}

document.getElementById('register').addEventListener('click', toRegister)

let checkPasswordLogin = function (){
    if(document.getElementById('checkPasswordLogin').checked){
        document.getElementById('password').type="text"
    }else {
        document.getElementById('password').type="password"
    }
}

document.getElementById("checkPasswordLogin").addEventListener('click', checkPasswordLogin)