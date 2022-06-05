<template>
  <div>
    <Top></Top>
    <!--视频播放-->
    <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 4px">

      <nav aria-label="breadcrumb">
        <ol class="breadcrumb" style="margin-bottom: 0px " id="learn-head">
          <li class="breadcrumb-item"><a class='btn' @click="goCourse(course['courseId'])">{{ course['courseName'] }}</a></li>
          <li class="btn breadcrumb-item active" aria-current="page">第{{ courseStructure.chapterId }}章:{{ courseStructure.chapterName }}</li>
          <li class="btn breadcrumb-item active" aria-current="page">第{{ courseStructure.sectionId }}小节:{{ courseStructure.sectionName }}</li>
        </ol>
      </nav>

      <!--视频播放主体-->
      <div class="container" style="padding-right: 0px;padding-left: 0px;width: 100%;height: 100%">
        <video :src="mvAdd" poster="../assets/image/play.png" preload controls width="100%" id="courseVideo"
               @click="playing()"></video>
      </div>

      <!--前后课程链接-->
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 8px" id="link">
        <span style="float: right;cursor:pointer" v-if="nextChapterId != 0 && nextSectionId != 0">
          <a @click="dataImport(courseId,nextChapterId, nextSectionId)">下一节-&gt;</a>
        </span>
        <span style="cursor:pointer" v-if="previousChapterId != 0 && previousSectionId != 0">
          <a @click="dataImport(courseId,previousChapterId,previousSectionId )">&lt;-上一节</a>
        </span>
        <span v-if="previousChapterId == 0 || previousSectionId == 0">&nbsp;</span>
      </div>

      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        课堂笔记
      </div>
      <!--课程笔记输入框-->

      <div class="input-group mb-3">
        <textarea class="form-control" placeholder="和同学们分享你的学习心得吧!" id="note">{{noteDataCom}}</textarea>

        <div class="input-group-append">
          <button class="btn btn-outline-secondary" type="button" id="button-addon2" @click="sendNote()">分享我的笔记</button>
        </div>
      </div>

      <!--笔记栏-->
      <div id="note-list">
        <span v-if="totalCom===0">暂无笔记，快来抢沙发吧!</span>
        <div class="card" v-if="totalCom!==0" v-for="note in noteListCom">
          <div class="card-header">
            <span v-if="note.userTypeInfo['typeName']=='普通用户'">{{ note.student['userName'] }}</span>
            <span v-if="note.userTypeInfo['typeName']=='教师'">{{ note.teacher['teacherName'] }}</span>
            <span class="badge badge-pill badge-primary" v-if="note.userTypeInfo['typeName']=='教师'">教师</span>
            <span style="float: right">{{ note.sendingDate }}</span>
            </div>
          <div class="card-body">
            <blockquote class="blockquote mb-0">
            <p>{{ note.note }}</p>
            </blockquote>
            </div>
          </div>
      </div>
      <hr>
      <!--页码-->
      <div id="note-pagecount">
        <nav aria-label="Page navigation ">
          <ul class="pagination">
            <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
            <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
            <li class="page-item"><a class="page-link" href="#" @click="loadingNote(1)">Previous</a></li>
            <li class="page-item" aria-current="page" v-for="i in totalPage">
              <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingNote(i)">{{i}}</a>
            </li>
            <li class="page-item"><a class="page-link" href="#" @click="loadingNote(totalPage)">Last</a></li>
          </ul>
        </nav>
      </div>
    </div>
    <Botton></Botton>
  </div>
</template>

<script>
import top from '@/views/Top.vue'
import botton from '@/views/Botton.vue'
import global from './Common.vue'
export default {
  name: "Learn",
  data(){
    return{
      courseId:0,
      chapterId:0,
      sectionId:0,
      mvAdd:'',
      course:{},
      previousChapterId:0,
      previousSectionId:0,
      nextChapterId:0,
      nextSectionId:0,
      courseStructure:{},
      total:0,
      page:0,
      totalPage:0,
      noteList:[],
      noteData:'',
    }
  },
  computed:{
    totalCom:function () {
      return this.total;
    },
    noteListCom:function (){
      return this.noteList;
    },
    noteDataCom:function () {
      return this.noteData;
    }
  },
  methods:{
    dataImport(courseId, chapterId, sectionId) {
      var that=this;
      //将课程课程id、对应章节id、对应小节id以及【上一节】、【下一节】数据取出放入前端元素内
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/learnDataImport',
        dataType: 'json',
        data: {'courseId': courseId, 'chapterId': chapterId, 'sectionId': sectionId},
        success: function (json) {
          that.course=json.course;
          that.mvAdd=json.mvAdd;
          console.log("mv:"+that.mvAdd);
          that.courseStructure=json.courseStructure;
          that.previousChapterId=json.previousChapterId;
          that.previousSectionId=json.previousSectionId;
          that.nextChapterId=json.nextChapterId;
          that.nextSectionId=json.nextSectionId;
        },
        error: function () {
          alert("系统错误");
        }
      });
    },
    //加载课堂笔记
    note() {
      this.loadingNote(1);
    },

    //加载课堂笔记
    loadingNote(page) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getCourseNote',
        data: {'courseId': that.courseId,'pageNum': page - 1},
        dataType: 'json',
        success: function (json) {
          that.total = json.total;
          console.log("total:"+that.total);
          that.page = page;
          that.totalPage = json.totalPage;
          that.noteList = json.list;
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },

    //提交课程笔记
    sendNote() {
      var that=this;
      //反馈意见表单提交
      var note=$("#note").val();
      // alert(courseId);
      if(note==""){
        alert("笔记不可为空!");
        return;
      }
      // alert(note);
      $.ajax({
        url: global.httpUrl+"/saveNote",
        type: 'POST',
        data: {"note":note,"courseId":that.courseId},
        dataType: 'json',
        success: function (json) {
          if(json.state){
            alert("sendNote success!");
            that.loadingNote(1);
            that.noteData='';
          }
          alert(json.message);
        },
        error: function () {
          alert("笔记分享失败!");
        }
      });
    },
    //点击视频即可实现播放与暂停
    playing() {
      var vi = document.getElementById("courseVideo");
      if (vi.paused) {
        vi.pause();
      } else {
        vi.play();
      }
    },

    //回到课程简介
    goCourse(courseId) {
      // location.href = "/Course?courseId=" + courseId;
      this.$router.push("/Course?courseId=" + courseId);
    },
  },
  mounted() {
    var that=this;
    $(function () {
      //将课程课程id、对应章节id、对应小节id从session中取出
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/getVideoMessage',
        dataType: 'json',
        success: function (json) {
          if(json.state==false){
            alert("非法进入!");
            // window.open("/","_self").close();
            const { href } = that.$router.resolve({
              path: "/",
            });
            window.open(href, '_self').close();
            return;
          }else {
            that.courseId = json.courseId;
            that.chapterId = json.chapterId;
            that.sectionId = json.sectionId;
            // that.mvAdd = json.mvAdd;
            // console.log("mv:"+that.mvAdd);
            that.dataImport(that.courseId, that.chapterId, that.sectionId,that);
            that.note();
          }
        },
        error: function () {
          alert("系统错误");
        }
      });
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
