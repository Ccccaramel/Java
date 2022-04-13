<template>
  <div id='app2'>
    <div id="mount-point"><h1>在本组件中动态创建新的节点</h1></div>
    <button @click = "addNode">添加节点</button>
  </div>
</template>
<script>
import Vue from 'vue';
export default {
  data(){
    return{

    }
  },
  methods:{
    addNode(){
      // 创建构造器
      var Profile = Vue.extend({
        template: '<button @click="fn">内部创建的节点组件</button>',
        data: function () {
          return {
            msg:'hello world'
          }
        },
        methods:{
          fn(){
            console.log(this.msg)
          }
        }
      })
      // 创建 Profile 实例，并挂载到一个元素上。
      new Profile().$mount('#mount-point')
    }
  },

}
</script>

<style lang="">

</style>

