<template>
  <div class="container">
    <form name="add_course_resource" id="add_course_resource">
      <div class="alert alert-dark" role="alert" style="width:100%;margin-bottom: 16px;elevation: deg">
        <span>学思网-添加课程资源</span>
      </div>

      <!--课程与课件资源各自为一个体系,分开管理-->
      <!--添加此课程相关资源，例如word、ppt等文件供学生下载或在线浏览-->
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px">
        课程资源
      </div>
      <div id="resource">
        <div class="input-group mb-3" :id="'res-'+i" v-for="i in resourceTotal">
          <div class="custom-file">
            <input type="file" class="custom-file-input" name="files" :id="'resource-'+i">
            <label class="custom-file-label" :for="'resource-'+i" :id="'resource-'+i+'-label'">选择课件或相关资料</label>
            <input type="hidden" :id="'resource-'+i+'-label-hidden'" :name="'resource-'+i+'-label-hidden'">
          </div>
        </div>
      </div>


      <!--上传组件添加按钮-->
      <button type="button" class="btn btn-outline-info" @click="addResource()">添加</button>
      <!--上传组件移除按钮-->
      <button type="button" class="btn btn-outline-danger" @click="delResource()">删除</button>

      <!--隐藏域:存放课程id-->
      <input type="hidden" id="courseId" name="courseId" :value="courseId">
      <!--ppt类文件无法上传，只好在提交表单前计算file控件个数一并上传-->
      <input type="hidden" id="resourceNum" name="resourceNum" value="1">
      <hr>
      <input class="btn btn-primary" type="button" @click="saveCourseResource()" value="保存">
    </form>
  </div>
</template>

<script>
import global from './common.vue'
export default {
  name: "AddCourseResource",
  data(){
    return{
      courseId:0,
      resourceTotal:1,
    }
  },
  methods:{
    addResource(){
      this.resourceTotal++;
    },
    delResource(){
      if(this.resourceTotal==1){
        alert("上传文件数量不可低于1!");
      }else {
        this.resourceTotal--;
      }
    },
    //保存课程资源
    saveCourseResource() {

      var len = $("#resource").children().length;
      //更改隐藏域相应的值
      $("#resourceNum").attr("value", len);

      //表单提交
      var form = document.getElementById("add_course_resource");
      var fd = new FormData(form);
      $.ajax({
        url: global.httpUrl+"/userSaveCourseResource",
        type: 'POST',
        data: fd,
        processData: false,  // 告诉jQuery不要去处理发送的数据
        contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
        success: function (response, status, xhr) {
          alert("课程资源保存成功!");
          window.open('',"_self").close();
        },
        error: function () {
          alert("课程资源上传失败!");
        }
      });
      return false;
    },
  },
  mounted() {
    var that=this;
    $(function () {
      // 获取课程id，此id只有通过已登录的学生账号进入course.html点击【我要上传】并接受检测后才能将课程id存入session
      // 若直接进入将无法获取id，则可判断为非法进入
      $.ajax({
        url: global.httpUrl+"/getCourseId",
        type: 'POST',
        dataType: 'json',
        success: function (json) {
          if(json.courseId==null){
            alert("非法进入!");
            window.open("/","_self").close();
          }
          that.courseId=json.courseId;
        },
        error: function () {
          alert("获取课程id失败,非法进入!");
          window.open("/","_self").close();
        }
      });

      //通过jQuery添加的元素无法触发上个事件，再次绑定事件使新添加的元素也触发事件
      $("body").on("change", ".custom-file-input", function () {
        var file = this.value;
        var id = this.id;
        var fileName = file.split("\\").pop();
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        var fileExt = file.substr(file.indexOf("."));
        $("#" + id + "-label").html(fileName + fileExt);
        $("#" + id + "-label-hidden").val(fileName + fileExt);
      });
    });
  },
}
</script>

<style scoped>

</style>
