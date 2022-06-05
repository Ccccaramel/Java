<template>

</template>

<script>
// 定义一些公共的属性和方法
// const httpUrl=process.env.NODE_ENV == 'production'?(process.env.VUE_APP_FLAG == 'pro'?'http://39.105.17.99:8080':'http://localhost:8080'):'http://39.105.17.99:8080';
const httpUrl='http://localhost:8080';
// const httpUrl='http://121.36.141.65:8080';

function commonFun() {
  console.log("公共方法");
}
// 暴露出这些属性和方法
export default {
  httpUrl,
  commonFun,
  name: "common",
}
</script>

<style scoped>

</style>
