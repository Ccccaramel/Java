<template>
  <div>
  <div class="container">
    <!--课程表单-->
    <form name="add_course" id="add_course">
      <div class="alert alert-dark" role="alert" style="width:100%;margin-bottom: 16px;elevation: deg;font-weight: bold">
        <span>学思网-添加课程</span>
      </div>

      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        课程基本信息
      </div>

      <!--课程名称-->
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text" id="basic-addon1">课程名称</span>
        </div>
        <input type="text" class="form-control" name="courseName" id="courseName" placeholder="课程名称">
      </div>
      <!--课程封面-->
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <span class="input-group-text" id="courseCover">课程封面</span>
        </div>
        <div class="custom-file">
          <!--accept="image/*" 设置文件类型仅为图片，允许所有图片格式-->
          <input type="file" class="custom-file-input" id="courseImg" name="files" accept="image/*">
          <label class="custom-file-label" for="courseImg" id="courseImg-label">Choose File</label>
          <input type="hidden" id="courseImg-hidden" name="courseImg-hidden" value="">
        </div>
      </div>

      <!--课程类型-->
      <div class="input-group mb-3">
        <div class="input-group-prepend">
          <label class="input-group-text" for="courseType">课程类型</label>
        </div>
        <select class="custom-select" id="courseType" name="courseType">
          <option :value="courseType.typeValue" v-for="(courseType,i) in courseTypeListCom" :selected="i===courseClassCom || courseClassCom===courseType.typeValue">{{courseType.typeName}}</option>
        </select>
      </div>

      <!--课程简介-->
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        课程简介
      </div>
      <div class="input-group mb-3" id="childNode-paragraph1">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text">课程简介(段落1)</span>
          </div>
          <textarea class="form-control" id="courseAbstract-1" name="courseAbstract-1"
                    placeholder="必须有一段课程介绍"></textarea>
        </div>
      </div>
      <div class="input-group mb-3" id="childNode-paragraph2">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text">课程简介(段落2)</span>
          </div>
          <textarea class="form-control" id="courseAbstract-2" name="courseAbstract-2"
                    placeholder="(选填)"></textarea>
        </div>
      </div>
      <div class="input-group mb-3" id="childNode-paragraph3">
        <div class="input-group">
          <div class="input-group-prepend">
            <span class="input-group-text">课程简介(段落3)</span>
          </div>
          <textarea class="form-control" id="courseAbstract-3" name="courseAbstract-3"
                    placeholder="(选填)"></textarea>
        </div>
      </div>

      <!--添加章节-->
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        章节内容
      </div>

      <div id="chapter">
        <!--第一章-->
        <div :id="'chapter-'+key" v-for="(value,key) in objCom">
          <!--章节标题-->
          <div class="input-group mb-3">
            <div class="input-group-prepend">
              <span class="input-group-text">第{{key}}章</span>
            </div>
            <input type="text" class="form-control" :id="'chapter-'+key+'-name'" :name="'chapter-'+key+'-name'" placeholder="请输入章名" :value="chapterNameListCom[key]==null ? '':chapterNameListCom[key]">
          </div>
          <!--小节内容-->
          <div :id="'chapter-'+key+'-section'" v-for="j in value">
            <div class="input-group mb-3" :id="'chapter-'+key+'-section-'+j">
              <div class="input-group-prepend">
                <span class="input-group-text">第{{j}}节</span>
              </div>
              <input type="text" class="form-control" :id="'chapter-'+key+'-section-'+j+'-name'" :name="'chapter-'+key+'-section-'+j+'-name'" placeholder="请输入小节名" :value="sectionNameListCom[key+'-'+j]==null ? '':sectionNameListCom[key+'-'+j]">
              <div class="input-group-prepend">
                <span class="input-group-text">教学视频</span>
              </div>
              <div class="custom-file">
                <input type="file" class="custom-file-input" name="files" :id="'chapter-'+key+'-section-'+j+'-video'" accept="video/*">
                <label class="custom-file-label" :for="'chapter-'+key+'-section-'+j+'-video'" :id="'chapter-'+key+'-section-'+j+'-video-label'">{{sectionMVNameListCom[key+'-'+j]}}</label>
                <input type="hidden" :id="'chapter-'+key+'-section-'+j+'-video-hidden'" :name="'chapter-'+key+'-section-'+j+'-video-hidden'" value="">
              </div>
            </div>
          </div>
          <!--小节添加按钮-->
          <button type="button" class="btn btn-outline-info" @click="addSection(key)">添加新小节</button>
          <!--小节移除按钮-->
          <button type="button" class="btn btn-outline-danger" @click="delSection(key)">移除小节</button>
          <hr>
        </div>
      </div>

      <!--<hr>-->

      <!--章节添加按钮-->
      <button type="button" class="btn btn-info" @click="addChapter();">添加新章节</button>
      <!--章节移除按钮-->
      <button type="button" class="btn btn-danger" @click="delChapter();">移除章节</button>

      <hr>

      <!--隐藏域:告诉后台本次保存是添加新课程还是修改已存在的课程-->
      <!--操作类别-->
      <!--true:修改已有课程-->
      <!--false:添加新课程-->
      <input type="hidden" id="edit" name="edit" value="false">
      <input type="hidden" id="courseId" name="courseId" value="">

      <hr>
      <input class="btn btn-primary" type="button" @click="saveCourse()" value="保存">


    </form>
  </div>
  <div id="result"></div>
  </div>
</template>

<script>
import global from './common.vue'
export default {
  name: "CourseManagement",
  data(){
    return{
      /**
       * key:章节
       * value:总小节数
       */
      obj:{
      },
      chapter:0,
      courseId:0,
      courseTypeList:[],
      /**
       * 课程类型
       * 默认值为0,该值决定课程类型下拉框选中第几个选项,默认第一个
       * 若教师在编辑课程，此值将会改变
       */
      courseClass:0,
      courseName:'',
      courseImgName:'',
      abs:[],
      structure:[],
      chapterNameList: {
      },
      sectionNameList:{
      },
      sectionMVNameList:{
      },
    }
  },
  computed:{
    objCom:function(){
      return this.obj;
    },
    courseTypeListCom:function (){
      return this.courseTypeList;
    },
    courseClassCom:function (){
      return this.courseClass;
    },
    chapterNameListCom:function (){
      return this.chapterNameList;
    },
    sectionNameListCom:function (){
      return this.sectionNameList;
    },
    sectionMVNameListCom:function (){
      return this.sectionMVNameList;
    }
  },
  methods:{
    //增添小节
    addSection(key) {
      this.obj[key]=this.obj[key]+1;
      console.log("key:"+key+","+this.obj[key]);
    },

    //增添章节
    addChapter() {
      this.chapter++;
      this.$set(this.obj,this.chapter,1);
    },

    //移除小节
    delSection(key) {
      if(this.obj[key]==1){
        alert("每章必须有一节!");
      }
      else {
        this.obj[key]=this.obj[key]-1;
      }
    },

    //移除章节
    delChapter() {
      if(this.chapter==1){
        alert("课程必须有一章!");
      }
      else {
        this.$delete(this.obj,this.chapter)
        this.chapter--;
      }
    },

    checkCourseStructure() {
      /**
       * 返回true:检查通过，将课程信息保存
       * 返回false:检查不通过，提示教师检查课程内容
       */

      //判断课程名称是否为空
      if ($("#courseName").val() == "") {
        alert("课程名称不可为空！")
        return false;
      }

      //判断课程名称是否为空
      if ($("#courseImg-label").html() == ""||$("#courseImg-label").html() == "Choose File") {
        alert("课程封面不可为空！")
        return false;
      }

      //判断课程简介是否为空
      if ($("#courseAbstract-1").val() == "") {
        alert("课程简介第一段不可为空！")
        return false;
      }
      if ($("#courseAbstract-3").val() != "" && $("#courseAbstract-2").val() == "") {
        alert("课程简介第二段不可为空！")
        return false;
      }

      for (var cha = 1; $("#chapter-" + cha + "-name").length > 0; cha++) {
        if ($("#chapter-" + cha + "-name").val() == "") {
          alert("第" + cha + "章章名不可为空!");
          return false;
        }
        for (var sec = 1; $("#chapter-" + cha + "-section-" + sec + "-name").length > 0; sec++) {
          if ($("#chapter-" + cha + "-section-" + sec + "-name").val() == "") {
            alert("第" + cha + "章第"+sec+"小节名不可为空!");
            return false;
          }
          if ($("#chapter-" + cha + "-section-" + sec + "-video-label").html() == ""||$("#chapter-" + cha + "-section-" + sec + "-video-label").html() == "Choose file") {
            alert("第" + cha + "章第"+sec+"小节视频资源不可为空!");
            return false;
          }
        }
      }
      return true;
    },

    //保存课程
    saveCourse() {
      /**
       * 检查课程结构是否规范
       * 无课程名、课程封面
       * 无课程简介或者【第三段课程简介】有内容而【第二段课程简介】却没有内容
       * 例如创建了小节或章节却没有内容(章节名、小节名、视频资源)
       **/
      if(!this.checkCourseStructure()){
        return ;
      }

      //获取课程名
      var courseName = $("#courseName").val();
      // alert(courseName);
      //获取课程封面名
      var file = $("#courseImg").val();
      //获取图片文件名
      var fileName = file.split("\\").pop();
      //为文件重新命名
      var newName = "";
      //提取图片名
      fileName = fileName.substring(0, fileName.lastIndexOf("."));
      //提取图片后缀
      var fileExt = file.substr(file.indexOf("."));
      $("#courseImgLabel").html(fileName);

      /**
       * 编辑课程时，课程资源的编辑不同于其他编辑，当一个旧资源被(删除或被替代)并保存编辑后，后台不知道是哪个旧资源被删除
       * 获取课程资源数据并整理成数组
       */
        //资源id(编辑课程)或特殊标识(-1:新添资源,编辑课程或新加课程)
      var sign = new Array();
      //资源状态(0:   1:   2:已被删除   3:无变化)
      var state = new Array();

      //表单提交
      var form = document.getElementById("add_course");
      var fd = new FormData(form);
      $.ajax({
        url: global.httpUrl+"/saveCourse",
        type: 'POST',
        data: fd, edit,
        processData: false,  // 告诉jQuery不要去处理发送的数据
        contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
        success: function (response) {
          alert("保存成功!");
        },
        error: function () {
          alert("上传失败!");
        }
      });
      return false;
    },
    /**
     * 判断用户是如何进入此页面
     * 1.登录后通过【教师中心】合法进入
     * 2.直接输入url进入---首先无法获取课程列表，提交时后台无法正常运行
     * 3.用户进入后修改url进入---会将其他教师的测试卷更改为自己的测试卷
     * 解决方法
     * 1.检查session，确认教师身份
     * 2.获取教师id，检查此testid是否属于此教师
     * 检查通过后才能继续操作
     * 否则关闭当前页面
     **/
    checkIdentitl(courseId) {
      console.log("checkIdentitl"+courseId);
      $.ajax({
        url: global.httpUrl+'/editCourseIdentitlCheck',
        type: 'post',
        dataType: 'json',
        data: {'courseId': courseId},
        success: function (json) {
          // console.log("checkIdentitl json:"+JSON.parse(json));
          if(json.state===false){
            alert(json.state+"非法进入!");
            window.open("/","_self").close();
          }else {
            alert("允许继续编辑课程!");
          }
        },
        error: function () {
          alert("账号检测错误");
        }
      });
    },
  },
  mounted() {
    // this.$set(this.obj,2,2);
    // this.$set(this.obj,3,1);
    // this.$set(this.obj,4,3);

    var that=this;
    $(function () {
      //加载课程类型信息
      //获取URL
      var url = document.URL;
      console.log("url:"+url);
      //分割URL,获取搜索框内的关键词
      that.courseId = url.split("?courseId=")[1];

      //如果有传进来课程id，则将该课程原有的所有相关信息填入表格，供教师修改、编辑
      if (typeof(that.courseId) !== 'undefined' ) {
        that.checkIdentitl(that.courseId);
        $("#edit").attr("value", "true");
        $("#courseId").attr("value", that.courseId);
        console.log("undefined?");
        $.ajax({
          url: global.httpUrl+'/getCourseIntroduction',
          type: 'post',
          dataType: 'json',
          data: {'courseId': that.courseId},
          success: function (json) {
            var course = json.course;
            // //获取course数组，取出课程名、课程封面名、课程类型
            that.courseName = course.courseName;
            that.courseImgName = course.courseImgName;
            that.courseClass = course.courseClass;

            console.log("courseClass:"+course.courseClass);

            $("#courseName").val(course.courseName);
            $("#courseImg-label").html(course.courseImgName);


            //获取数据库返回的数据，与课程简介相关的集合
            var abstractList = json.abstract;
            console.log("size:"+abstractList.length);
            //获取课程简介
            for(var i=1;i<=3;i++){
              $("#courseAbstract-" + i).val(abstractList[i-1].courseIntroduce);
            }

            /**
             * 统计【章节】与其【小节】数量
             * 创建组件
             */
            var courseStructureList = json.structure;
            for(var i in courseStructureList){
              var chapterId=courseStructureList[i].chapterId;
              var sectionId=courseStructureList[i].sectionId;

              // 创建组件
              if(typeof (that.obj[chapterId])=="undefined"){
                that.addChapter();
              }
              else {
                that.addSection(chapterId);
              }

              /**
               * 获取数据
                */
              // 获取章节
              if(typeof (that.chapterNameList[chapterId])=="undefined"){
                that.$set(that.chapterNameList,chapterId,courseStructureList[i].chapterName);
              }
              // 获取小节
              that.$set(that.sectionNameList,chapterId+"-"+sectionId,courseStructureList[i].sectionName);
              // 获取视频名称
              that.$set(that.sectionMVNameList,chapterId+"-"+sectionId,courseStructureList[i].mvAdd);
            }

            // //将章名、小节名、视频名添入
            // for(var i in courseStructureList){
            //   var chapterId=courseStructureList[i].chapterId;
            //   var sectionId=courseStructureList[i].sectionId;
            //   console.log(">>>"+chapterId+","+sectionId+","+i);
            //   $("#chapter-" + chapterId + "-name").val(courseStructureList[i].chapterName);
            //   $("#chapter-" + chapterId + "-section-"+sectionId+"-name").val(courseStructureList[i].sectionName);
            //   $("#chapter-" + chapterId + "-section-"+sectionId+"-video-label").html(courseStructureList[i].mvAdd);
            // }

            /**
             * 放入数据
             */


            /**
             * 将章节以及对应小节的数据放入各元素中，若没有相应元素则立即创建并添入
             */
            //   //获取数据库返回的数据，与课程目相关的集合
            // var str = json.structure;
            // // 获取课程目录
            // $.each(str, function (index, array) {
            //   //获取章节集的id
            //   var chapter = "chapter-" + array['chapterId'];
            //   //获取单个小节的id
            //   var section = "chapter-" + array['chapterId'] + "-section-" + array['sectionId'];
            //   //将章名、小节名、视频名添入
            //   $("#" + chapter + "-name").val(array['chapterName']);
            //   $("#" + section + "-name").val(array['sectionName']);
            //   $("#" + section + "-video-label").html(array['mvAdd']);
            // });
          },
          error: function () {
            alert("加载课程错误");
          }
        });
      }
      else{
        that.addChapter();
        that.courseId=-1;
        $("#courseId").attr("value", that.courseId);
        that.checkIdentitl(that.courseId);
      };
    });

    $(function () {
      // 由于只有type为file的input元素在确定文件后输入框内不显示文件名，所以只需处理此类元素即可
      //通过jQuery添加的元素无法触发上个事件，再次绑定事件使新添加的元素也触发事件
      $("body").on("change",".custom-file-input", function () {
        var file = this.value;
        var id = this.id;
        var fileName = file.split("\\").pop();
        fileName = fileName.substring(0, fileName.lastIndexOf("."));
        var fileExt = file.substr(file.indexOf("."));
        $("#" + id+"-label").html(fileName + fileExt);
        $("#" + id+"-hidden").val(fileName + fileExt);
      });
    });

    $(function () {
      //将课程类别从数据库中调出
      $.ajax({
        url: global.httpUrl+'/getAllAccounts',
        type: 'post',
        dataType: 'json',
        success: function (json) {
          that.courseTypeList = json.courseTypeList;
        },
        error: function () {
          alert("错误");
        }
      });


    });

  }


}
</script>

<style scoped>

</style>
