<template>
  <div id='app2'>
    <div v-for="(v,i) in data" v-bind:key="i">
      <input type="text" placeholder="type it" v-model="data[i]"><button @click="del()">取消</button>
    </div>
    <button @click = "createNode">创建表单input节点</button>
    <button @click = "getdata">获取所有input值</button>
  </div>
</template>
<script>
export default {
  data(){
    return{
      data:[],
      index:0

    }
  },
  methods:{
    getdata(){
      console.log(this.data);
    },
    del(){
      event.currentTarget.parentNode.style.display='none';
    },
    createNode(){
      this.index++;
      console.log(this.index);
      var str="请输入选项"
      var ele=str+this.index;
      this.data.push(ele);
    }

  },

}
</script>

<style lang="">

</style>
