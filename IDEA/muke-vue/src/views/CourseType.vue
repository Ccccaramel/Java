<template>
  <!--顶部导航栏-->
  <div>
    <Top></Top>
    <!--课程类型列表-->
    <div class="container">
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        课程分类
      </div>
      <div class="btn container-fluid" style="padding-top: 0px;border-top: 0px">
        <div class="row align-items-start" id="course-type" style="background-color:rgb(247,247,247) ">
          <div class="col-2" v-for="courseType in courseTypeList" @click="courseTypeSearch(1,courseType.typeValue)">{{courseType.typeName}}</div>
        </div>
      </div>
    </div>
    <!--课程搜索结果-->
    <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        搜索结果
        <span id="typeName">&nbsp;&gt;&gt;{{typeName}}</span>
      </div>
      <!--搜索结果-->
      <div class="container">
        <div id="list" class="row">
          <div class="card-body" v-if="total===0">
            <h3 class="card-title">未找到相关资源</h3>
            <p class="card-text">可能关键词不准确,请重新输入关键词再进行搜索</p>
          </div>

          <div class="card col-4" style="width: 18rem;cursor:pointer"  v-for="course in courseList" v-if="total>0"  @click="introduce(course.courseId);">
            <img class="card-img-top" :src="course.imageURL" alt="Card image cap">
            <div class="card-body">
              <span class="card-title">{{course.courseName}}</span>
            </div>
          </div>
        </div>
      </div>
      <hr>
      <!--页码-->
      <div id="pagecount">
        <nav aria-label="Page navigation ">
          <ul class="pagination">
            <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
            <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
            <li class="page-item"><a class="page-link" href="#" @click="courseTypeSearch(1,courseTypeId)">Previous</a></li>
            <li class="page-item" aria-current="page" v-for="i in totalPage">
              <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="courseTypeSearch(i,courseTypeId)">{{i}}</a>
            </li>
            <li class="page-item"><a class="page-link" href="#" @click="courseTypeSearch(totalPage, courseTypeId)">Last</a></li>
          </ul>
        </nav>
      </div>
    </div>
    <!--底部-->
    <Botton></Botton>
  </div>
</template>

<script>
import top from '@/views/Top.vue'
import botton from '@/views/Botton.vue'
import global from './Common.vue'
export default {
  name: "CourseType",
  components: {
    'Top':top,
    'Botton':botton,
  },
  data(){
    return{
      total:0,
      page:0,
      totalPage:0,
      typeName:'',
      courseTypeId:0,
      courseTypeList:[],
      courseList:[],
    }
  },
  methods:{
    //通过课程类型id获取相关课程
    courseTypeSearch(page, typeValue) {
      var that=this;
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/adoptCourseTypeSearchCourse',
        data: {'courseTypeId': typeValue, 'pageNum': page - 1},
        //返回数据类型
        dataType: 'json',
        //数据完成加载前提示文字(一般很快看不到)
        beforeSend: function () {
        },
        success: function (json) {
          console.log(json);
          //更改【查询结果】标题
          that.typeName=json.typeName;
          //获取本页总数据的数量
          that.total = json.total;
          //获取当前页数
          that.page = page;
          //获取总页数
          that.totalPage = json.totalPage;
          //获取本页的数据数量(最后一页可能不为pageSize)
          var count = json.count;
          //获取数据库返回的数据
          that.courseList = json.courseList;
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },

    //点击课程名获取课程名称，跳转到课程介绍页面
    introduce(courseId) {
      var that=this;
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/heatsUp',
        data: {'courseId': courseId},
        success: function () {
          that.$router.push("/Course?courseId=" + courseId);
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
  },
  mounted() {
    var that=this;
    $(function () {
      //加载课程分类---数据库中前24个课程类型，
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/homeGetCourseType',
        //返回数据类型
        data:{'amount':24},
        dataType: 'json',
        success: function (json) {
          console.log(json);
          //获取数据库返回的数据
          that.courseTypeList = json.partCourseTypeList;
        },
        error: function () {
          alert("课程分类加载失败");
        }
      });

      /**
       * 进入此页面只有两种方式
       * 1.直接点击首页顶部的【课程分类】，此时没有搜索条件，显示所有课程
       * 2.通过首页的子菜单栏点击进入，此时session保存有课程类型的id，显示此id对应的课程类型的所有课程
       * 接下来根据判断是否有session来决定如何运行
       **/
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/getCourseTypeId',
        //返回数据类型
        dataType: 'json',
        success: function (json) {
          console.log(json);
          that.courseTypeId = json.courseTypeId;
          if (that.courseTypeId == null) {
            that.courseTypeId = -1;
          }
          that.courseTypeSearch(1, that.courseTypeId);
        },
        error: function () {
          alert("课程分类加载失败");
        }
      });
    });
  }
}
</script>

<style scoped>

</style>
