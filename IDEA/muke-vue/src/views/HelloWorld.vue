<template>
  <div class="HelloWorld">
    <h3>HelloWorld</h3>
    <h1>{{ msg }}</h1>
  </div>

</template>

<script>
import global from './Common.vue'
export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App'
    }
  }
};
$(function () {
  alert(123);
});
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
