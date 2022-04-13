<template>
  <div>
    <h3>Test.vue{{msg}}</h3>
    <button @click="go">go</button>
  </div>
</template>

<script>
export default {
  name: "Test.vue",
  data(){
    return{
      msg:"123"
    }
  },
  methods:{
    go(){
      this.$router.push("/Test")
    }
  }
}
</script>

<style scoped>

</style>
