<script setup lang="js" name="Title">
  function changeBackgroundColor(){
    var title = document.getElementsByClassName("title")[0];
    title.setAttribute("style", "background-color: " + "orange");
  }
  defineProps(["title"])
</script>

<template>
  <div class="app">
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