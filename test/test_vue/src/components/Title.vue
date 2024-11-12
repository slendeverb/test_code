<script setup lang="js" name="Title">
  function changeBackgroundColor(){
    const title = document.getElementsByClassName("title")[0];
    const backgroundColor=document.defaultView.getComputedStyle(title).backgroundColor;
    console.log(backgroundColor);
    if(backgroundColor==="rgb(135, 206, 235)"){
      title.setAttribute("style", "background-color: orange");
    }else if(backgroundColor==="rgb(255, 165, 0)"){
      title.setAttribute("style", "background-color: skyblue");
    }
  }
  defineProps(["title"])
</script>

<template>
  <div>
    <h2 class="title" @click="changeBackgroundColor">{{title}}</h2>
  </div>
</template>

<style scoped>
  .title{
    text-align: center;
    border-radius: 10px;
    background-color: skyblue;
    color: white;
    margin: auto 15%;
    padding: 5px 0;
  }
</style>