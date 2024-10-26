var arr=[1,2,3,4];
arr[10]=10;
arr[9]="A";
arr[8]=true;

for (var i=0; i<arr.length; i++){
    if(!isNaN(arr[i])){
        console.log(i+":"+arr[i]);
    }
}

arr.forEach((e)=>{
    console.log(e);
})