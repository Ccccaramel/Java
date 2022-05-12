<template>

</template>

<script>
// 定义一些公共的属性和方法
// const httpUrl = 'http://localhost:8080' // 测试
const httpUrl = 'http://121.36.141.65:8080' // 正式
function commonFun() {
  console.log("公共方法")
}
// 暴露出这些属性和方法
export default {
  httpUrl,
  commonFun,
  name: "common"
}
</script>

<style scoped>

</style>
