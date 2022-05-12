<template>
  <div class="container">
    <form name="add_course_resource" id="add_course_resource">
      <div class="alert alert-dark" role="alert" style="width:100%;margin-bottom: 16px;elevation: deg;font-weight: bold">
        <span>学思网-添加课程资源</span>
      </div>

      <!--选择添加课程资源的课程id-->
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <label class="input-group-text">选择课程</label>
        </div>
        <select class="custom-select" id="courseId" name="courseId">
          <option :selected="i===0" :value="course.courseId" :name="course.courseId" v-for="(course,i) in courseList">{{course.courseId}}:&nbsp;{{course.courseName}}</option>
        </select>
      </div>

      <!--课程与课件资源各自为一个体系,分开管理-->
      <!--添加此课程相关资源，例如word、ppt等文件供学生下载或在线浏览-->
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0;font-weight: bold">
        课程资源
      </div>
      <div id="resource">
        <div class="input-group mb-3" id="res-1" v-for="i in resourseNo">
          <div class="custom-file">
            <input type="file" class="custom-file-input" name="files" :id="'resource-'+i">
            <label class="custom-file-label" :for="'resource-'+i" :id="'resource-'+i+'-label'">选择课件或相关资料</label>
            <input type="hidden" :id="'resource-'+i+'-label-hidden'" :name="'resource-'+i+'-label-hidden'" value="">
          </div>
        </div>
      </div>



      <!--上传组件添加按钮-->
      <button type="button" class="btn btn-outline-info" @click="addResource()">添加</button>
      <!--上传组件移除按钮-->
      <button type="button" class="btn btn-outline-danger" @click="delResource()">删除</button>

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
  name: "CourseResource",
  data(){
    return{
      courseList:[],
      resourseNo:1,
    }
  },
  methods:{
    addResource(){
      this.resourseNo++;
    },
    delResource(){
      if(this.resourseNo===1){
        alert("至少有一个上传组件!");
      }
      this.resourseNo--;
    },

    //保存课程资源
    saveCourseResource() {
      if(!this.check()){
        return ;
      }
      var len = $("#resource").children().length;
      //更改隐藏域相应的值
      $("#resourceNum").attr("value", len);

      //表单提交
      var form = document.getElementById("add_course_resource");
      var fd = new FormData(form);
      $.ajax({
        url: global.httpUrl+"/saveCourseResource",
        type: 'POST',
        data: fd,
        processData: false,  // 告诉jQuery不要去处理发送的数据
        contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
        success: function (response, status, xhr) {
          alert("课程资源保存成功!");
        },
        error: function () {
          alert("课程资源上传失败!");
        }
      });
      return false;
    },

    check() {
      for (var sign = 1; $("#resource-" + sign).length > 0; sign++) {
        if ($("#resource-" + sign + "-label").html() === ""|| $("#resource-" + sign + "-label").html() === "选择课件或相关资料") {
          alert("第" + sign + "个资源不可为空!");
          return false;
        }
        return true;
      }
    },
  },
  mounted() {
    var that=this;
    $(function () {
      //将课程id和中文名放入下拉菜单中
      $.ajax({
        url: global.httpUrl+'/getCourseIdName',
        type: 'post',
        dataType: 'json',
        success: function (json) {
          if(json.state){
            that.courseList=json.courseList;
          }else{
            alert("非法进入!");
            window.open("/","_self").close();
          }
        },
        error: function () {
          alert("错误");
        }
      });

      //绑定事件使新添加的元素也触发事件
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
  }
}
</script>

<style scoped>

</style>
