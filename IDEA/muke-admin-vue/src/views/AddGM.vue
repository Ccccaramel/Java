<template>
  <div class="container">
    <form name="add_gm" id="add_gm">
      <div class="alert alert-dark" role="alert" style="width:100%;margin-bottom: 16px;elevation: deg">
        <span>学思网-管理员账号生成</span>
      </div>

      <div class="alert alert-danger" role="alert" style="width:100%;margin-bottom: 10px">
        账号生成发布后请尽快通知管理员修改密码并完善信息!
      </div>

      <!--管理员级别选择-->
      <div class="input-group" style="margin-bottom: 6px">
        <div class="input-group-prepend">
          <button class="btn btn-outline-primary">管理员级别选择</button>
        </div>
        <div class="input-group-append">
          <select class="custom-select" id="administratorLevel" name="administratorLevel">
              <option v-for="(power,i) in powerInfoList" v-bind:key="i" :selected="i==0" :value="power.powerNumber" :name="power.powerNumber">{{power.powerClass}}</option>
          </select>
        </div>
      </div>

      <!--账号生成数量-->
      <div class="input-group" style="margin-bottom: 6px">
        <div class="input-group-prepend">
          <button class="btn btn-outline-primary">生成账号数量</button>
        </div>
        <div class="input-group-append">
          <select class="custom-select" id="administratorNumber" name="administratorNumber">
            <option v-for="i in 20" :value="i" :selected="i==1">{{i}}</option>
          </select>
        </div>
      </div>

    </form>
    <hr>
    <button class="btn btn-primary" type="button" id="submitsPapers" name="submitsPapers" @click="account_registration"
            data-toggle="modal" data-target="#exampleModalCenter">立即生成</button>
    <div class="modal fade" id="exampleModalCenter" tabindex="-1" role="dialog"
         aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalCenterTitle">{{msg}}</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body" >
            <blockquote class="blockquote">
              <div id="get-the-initial-account">
                <p v-for="gmInfo in gmInfoList" class="mb-0">账号:{{gmInfo.gmId}}&nbsp;&nbsp;初始密码:{{gmInfo.gmInitPassword}}</p>
              </div>
            </blockquote>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddGM",
  data(){
    return{
      powerList:[],
      tips:true,
      msg:"",
      gmInfoList:[],
    }
  },
  computed: {
    powerInfoList:function (){
      return this.powerList;
    },
  },
  methods:{
    //立即生成管理员账号
    account_registration() {
      var a=$("#administratorLevel").val();
      var b=$("#administratorNumber").val();
      console.log(a+"---"+b);
      var that=this;
      $.ajax({
        url: 'http://localhost:8080/administratorAccountRegistration',
        type: 'post',
        dataType: 'json',
        data : {
          'gmPower' : $("#administratorLevel").val(),
          'quantity' : $("#administratorNumber").val(),
        },
        success: function (json) {
          that.msg="账号密码生成完成";
          $("#get-the-initial-account").empty();
          that.gmInfoList = json.adminList;
        },
        error: function () {
          that.tips=false;
          that.msg="账号密码生成失败";

        },
      });
    },
    getPowerInfo(){
      var that = this;
      //获取与管理员权限相同或低于的权限列表，并填入表单中
      $.ajax({
        url: 'http://localhost:8080/getGMPowerTabel',
        type: 'post',
        dataType: 'json',
        success: function (json) {
          var list = json.gmPowerList;
          that.powerList=list;
        },
        error: function (json) {
          alert("错误");
          that.$router.push({path:'/'});
        },
      });
    },
  },
  mounted() {
    this.getPowerInfo();
  }
}

</script>

<style scoped>

</style>
