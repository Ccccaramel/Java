<template>
  <div>
    <Top></Top>
    <!--课程介绍-->
    <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        课程介绍
      </div>
      <div class="container" style="padding-right: 0px;padding-left: 0px;width: 100%;height: 100%">
        <img :src="courseImgURL==''?'../assets/image/play.png':courseImgURL" style="width: 100%;height: 100%">
      </div>
      <div class="container" style="padding-right: 0px;padding-left: 0px;width: 100%;height: 100%">
        <div class="alert alert-success" role="alert">
          <h2 class="alert-heading">{{courseName}}</h2>
          <hr>
          <h4 class="alert-heading">{{teacherName}}</h4>

          <div id="courseAbstract">
            <p style="text-indent: 2em" v-for="courseAbstract in courseAbstracts">{{ courseAbstract.courseIntroduce }}</p>
          </div>
          <hr>
          <button type="button" class="btn btn-outline-success" name="collection-course" id="collection-course"
                  @click="collectionCourse()" :value="passVal">{{ passMessage }}
          </button>
        </div>
      </div>
    </div>
    <!--课程目录-->
    <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        课程目录
      </div>
      <!--目录-->
      <div class="container" style="padding-right: 0px;padding-left: 0px;width: 100%;height: 100%">
        <div class="accordion" id="accordionExample">
          <div class="card" :id="'chapter-'+chapterId" v-for="(chapterInfo,chapterId) in chapterList">

            <!-- 章节名-->
            <div class="card-header" :id="'heading-' + chapterId">
              <h5 class="mb-0">
                <button class="btn btn-link" type="button" data-toggle="collapse" :data-target="'#collapse-'+chapterId"
                  :aria-expanded="chapterId===1" :aria-controls="'collapse-' +chapterId" style="text-decoration: none;color:#0a0a0a">
                  第{{ chapterId }}章&nbsp;&nbsp;&nbsp;{{ chapterInfo[1]['chapterName'] }}</button>
              </h5>
            </div>
            <!-- 小节-->
            <div :id="'collapse-' + chapterId" :class="chapterId==1?'collapse show':'collapse'" :aria-labelledby="'heading-'+chapterId" data-parent="#accordionExample" v-for="(sectionInfo,sectionId) in chapterInfo">
              <div class="card-body">
                <ul class="list-group list-group-flush" :id="sectionId">
                  <li class="list-group-item">
                    <button class="btn btn-link" @click="videoPlayback(chapterId,sectionId)" style="text-decoration: none;color: #6a6a6a">第{{ sectionId }}小节:&nbsp;&nbsp;&nbsp;{{ sectionInfo['sectionName'] }}</button>
                  </li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>

    </div>
    <!--课程资源-->
    <div class="container" id="course-resource" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        课程资源
      </div>
      <div class="alert alert-danger" role="alert"  v-if="resourceTotal==0">暂无课程资源!</div>
      <table id="course-resource-table" class="table table-sm table-hover table-striped" v-if="resourceTotal!=0">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">资源名称</th>
          <th scope="col" style="text-align: center">操作</th>
        </tr>
        </thead>
        <tbody id="course-resource-list">
          <tr v-for="(courseResource,i) in courseResourceList">
            <th scope="row">{{ i }}</th>
            <td>{{ courseResource.resourceName }}</td>
            <td style="text-align: center">
              <a class="badge badge-danger" style="margin-left: 6px;margin-right: 6px" :href="url+courseResource.resourceId" download="">下载</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <!--共享资源-->
    <div class="container" id="share-resource" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">

      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        共享资源
        <span href="#" style="float:right" class="btn badge badge-pill badge-success"
              @click="userUploadCourseResource()">我要上传</span>
      </div>
      <div class="alert alert-danger" role="alert" v-if="shareResourceTotal==0">暂无共享资源!</div>
      <table id="share-resource-table" class="table table-sm table-hover table-striped" v-if="shareResourceTotal!=0">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">资源名称</th>
          <th scope="col" style="text-align: center">操作</th>
        </tr>
        </thead>
        <tbody id="share-resource-list">
          <tr v-for="(shareCourseResource,i) in shareCourseResourceList">
            <th scope="row">{{ i }}</th>
            <td>{{ shareCourseResource.resourceName }}</td>
            <td style="text-align: center">
              <a class="badge badge-danger" style="margin-left: 6px;margin-right: 6px" :href="url+shareCourseResource.resourceId" download="">下载</a>
              <a class="btn badge badge-light" style="margin-left: 6px;margin-right: 6px" href="https://view.officeapps.live.com/op/view.aspx?src=http://www.xiawasi.cn/Data/course_resource/array['linkAdd']">预览</a>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <!--课程测试-->
    <div class="container" id="test" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">

      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        课程测试
      </div>
      <div class="alert alert-danger" role="alert" v-if="testTotal==0">此课程暂无测试资源!</div>
      <ul class="list-group" id="test-list">
        <li class="list-group-item d-flex justify-content-between align-items-center" v-for="test in testList">
          {{ test.testName }}
          <span class="btn badge badge-primary badge-pill" @click="goTest(test.testId)">开始测试</span>
        </li>
      </ul>

    </div>
    <Botton></Botton>
  </div>
</template>

<script>
import top from '@/views/Top.vue'
import botton from '@/views/Botton.vue'
import global from './common.vue'
export default {
  name: "Course",
  data(){
    return{
      caurse:{},
      courseId:0,
      courseImgURL:'',
      courseName:'',
      teacherName:'',
      courseAbstracts:{},
      chapterList:{},
      sectionList:{},
      pass:false,
      resourceTotal:0,
      courseResourceList:[],
      shareResourceTotal:0,
      shareCourseResourceList:[],
      testTotal:0,
      testList:[],
      url:'',
    }
  },
  computed:{
    passMessage:function (){
      if(this.pass){
        return "已收藏";
      }
      else {
        return "收藏";
      }
    },
    passVal:function () {
      if(this.pass){
        return 1;
      }
      else {
        return 0;
      }
    }
  },
  methods:{
    // 加载课程简介
    loadingIntroduction(courseId) {
      var that=this;
      $.ajax({
        url: global.httpUrl+'/getAppointCourseIntroduction',
        type: 'post',
        dataType: 'json',
        data: {"courseId": courseId},
        success: function (json) {
          //判断该课程是否合法
          if(!json.courseStatus){
            alert("非法进入!");
            window.open("/","_self").close();
          }
          //控制台打印出数据
          console.log(json);
          var course=json.course;
          that.course=course;
          //获取课程简介
          that.courseImgURL=course.imageURL;

          that.courseAbstracts=course.courseAbstracts;
          //修改h标签的文本内容
          that.courseName=course.courseName;
          //获取教师姓名并显示
          that.teacherName="讲师:" + course.teacher.teacherName;

          //获取数据库返回的数据，与课程简介相关的集合
          that.chapterList = json.chapterList;

          //更改课程收藏状态
          //更改按钮文本
          that.pass=json.pass;
        },
        error: function (err) {
          alert("加载课程简介错误");
        }
      });
    },

    //加载课程资源
    loadingCourseResource(courseId) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getAppointCourseResource',
        data: {'courseId': courseId, 'userType': 1},
        dataType: 'json',
        success: function (json) {
          console.log(json);
          that.resourceTotal=json.count;
          that.courseResourceList=json.courseResourceList;
          that.url=json.url;
        },
        error: function () {
          alert("数据加载失败");
        },
      });
    },
    //加载公共资源
    loadingShareResource(courseId){
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getAppointCourseResource',
        data: {'courseId': courseId, 'userType': 0},
        dataType: 'json',
        success: function (json) {
          console.log(json);
          that.shareResourceTotal=json.count;
          that.shareCourseResourceList=json.courseResourceList;
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },

    //加载课程测试卷
    loadingCourseTest(courseId) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getAppointTestInformation',
        data: {'courseId': courseId},
        dataType: 'json',
        beforeSend: function () {
        },
        success: function (json) {
          console.log(json);
          that.testTotal=json.count;
          that.testList = json.itemBackList;
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },

    /**
     * 课程收藏
     * 0:显示【收藏】---表示用户未收藏获已取消收藏
     * 1:显示【已收藏】---表示用户已收藏
     */
    collectionCourse() {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/changeCollectionCourse',
        data: {'sign': that.pass, "courseId": that.courseId},
        dataType: 'json',
        success: function (json) {
          if(json.result){
            that.pass=json.pass;
          }
          alert(json.message);
        },
        error: function () {
          alert("收藏更改失败,可能你未登录");
        }
      });
    },

    //点击单个小节进入小节视频播放界面
    videoPlayback(chapterId, sectionId) {
      var that=this;
      //将课程课程id、对应章节id、对应小节id存储到session中，进入learn页面后取出这些数据
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/saveVideoMessage',
        /**
         * 向后端传递的数据
         * 搜索的关键字以及当前页码
         **/
        data: {'courseId': that.courseId, 'chapterId': chapterId, 'sectionId': sectionId},
        dataType: 'json',
        success: function (json) {
          location.href = "/Learn";
        },
        error: function () {
          alert("系统错误");
        }
      });

    },

    //学生添加课程资源
    userUploadCourseResource() {
      var that=this;
      $.ajax({
        url: global.httpUrl+'/saveCourseId',
        type: 'post',
        dataType: 'json',
        data: {"courseId": that.courseId},
        success: function (json) {
          //返回结果
          if (json.state) {
            //用户已登陆
            window.open("/AddCourseResource");
          } else {
            alert(json.message);
          }
        },
        error: function (err) {
          alert("请先登录!");
        }
      });
    },

    //点击进入测试
    goTest(testId) {
      $.ajax({
        url: global.httpUrl+'/accountChecking',
        type: 'post',
        dataType: 'json',
        success: function (json) {
          //返回结果
          // alert(json.pass);
          if (json.pass) {
            //用户已登陆
            window.open("/Test?testId=" + testId);
          } else{
            alert(json.message);
          }
        },
        error: function (err) {
          alert("请先登录!!!");
        }
      });
    },
  },
  mounted() {
    var that=this;
    /**
     * 进入此页面后将通过URL传递的课程id取出并加载课程简介、课程目录、章节测试、课程资源等等
     */
    $(function () {
      //获取URL
      var url = document.URL;
      //分割URL,获取课程id
      that.courseId = url.split("?courseId=")[1];
      if(typeof (that.courseId)==='undefined'){
        alert("非法进入!");
        window.open("/","_self").close();
      }
      //加载课程简介
      that.loadingIntroduction(that.courseId);
      //加载课程资源
      that.loadingCourseResource(that.courseId);
      //加载公共资源
      that.loadingShareResource(that.courseId);
      //加载课程测试卷
      that.loadingCourseTest(that.courseId);
    });
  },
  components:{
    'Top': top,  // 组件
    'Botton': botton,
  },
}
</script>

<style scoped>

</style>
