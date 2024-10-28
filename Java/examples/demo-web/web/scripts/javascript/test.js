let on=document.getElementsByName("on")[0];
let off=document.getElementsByName("off")[0];
let img=document.getElementById("h1");

on.onclick=function turnOn(){
    img.src="https://upload.wikimedia.org/wikipedia/commons/thumb/3/3c/IMG_logo_%282017%29.svg/330px-IMG_logo_%282017%29.svg.png";
}
off.onclick=function turnOff(){
    img.src="https://www.hollywoodreporter.com/wp-content/uploads/2012/12/img_logo_blue.jpg?w=2000&h=1126&crop=1";
}

let inputText=document.getElementsByName("inputText")[0];
inputText.onfocus=function toLowerCase(){
    inputText.value=inputText.value.toLowerCase();
}
inputText.onblur=function toUpperCase(){
    inputText.value=inputText.value.toUpperCase();
}