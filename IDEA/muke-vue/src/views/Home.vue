<template xmlns:C="http://www.w3.org/1999/xhtml">
  <div>
  <Top></Top>
  <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
    <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
      课程分类
    </div>

    <div class="btn container-fluid" style="padding-top: 0px;border-top: 0px">
      <div class="row">
<!--      <div>-->

        <!--右侧菜单栏 -->
        <div class="col-2" style="padding-right: 0px;padding-left: 0px">
          <div class="btn-group-vertical carousel slide carousel-fade" role="group"
               aria-label="Button group with nested dropdown"
               style="width: 100%;height: 100%;" id="course-type">
            <button type="button" class="btn btn-secondary" style="width: 100%;height: 100%;" :id="CourseType.typeValue" @click="saveCourseType(CourseType.typeValue)" v-for="CourseType in partCourseTypeList">{{CourseType.typeName}}</button>
          </div>
        </div>

        <div id="carouselExampleCaptions" class="col-10 carousel slide carousel-fade" data-ride="carousel" style="padding:0px">
          <ol class="carousel-indicators">
            <li data-target="#carouselExampleCaptions" :data-slide-to="i" :class="[i===0?'active':'']"  v-for="(promotionCourse,i) in promotionCourseList"></li>
          </ol>
          <div class="carousel-inner">
            <div :class="[i===0?'carousel-item active':'carousel-item']" v-for="(promotionCourse,i) in promotionCourseList">
              <img :src="promotionCourse.course.imageURL" class="d-block w-100">
              <div class="carousel-caption d-none d-md-block">
                <h5 style="background: rgba(0,0,0,0.4)">{{promotionCourse.course.courseName}}</h5>
              </div>
            </div>
          </div>
<!--          <button class="carousel-control-prev" type="button" data-target="#carouselExampleCaptions" data-slide="prev">-->
<!--            <span class="carousel-control-prev-icon" aria-hidden="true"></span>-->
<!--            <span class="sr-only">Previous</span>-->
<!--          </button>-->
<!--          <button class="carousel-control-next" type="button" data-target="#carouselExampleCaptions" data-slide="next">-->
<!--            <span class="carousel-control-next-icon" aria-hidden="true"></span>-->
<!--            <span class="sr-only">Next</span>-->
<!--          </button>-->
        </div>
        <!--左侧轮播图-->
<!--        <div id="carouselExampleFade" class="col-10 carousel slide carousel-fade" data-ride="carousel" style="padding-right: 0px;padding-left: 0px">-->
<!--          <div class="carousel-inner">-->
<!--            <div class="carousel-item active" v-if="i===1" v-for="(promotionCourse,i) in promotionCourseList">-->
<!--              <img src="/upload/photo/2022033120293710005051.jpg" class="d-block w-100" alt="1">-->
<!--            </div>-->
<!--            <div class="carousel-item">-->
<!--              <img src="http://localhost:8080/upload/photo/2022040109585410006443.jpg" class="d-block w-100" alt="2">-->
<!--            </div>-->
<!--            <div class="carousel-item">-->
<!--              <img src="../assets/image/play.png" class="d-block w-100" alt="3">-->
<!--            </div>-->
<!--          </div>-->
<!--          <button class="carousel-control-prev" type="button" data-target="#carouselExampleFade" data-slide="prev">-->
<!--            <span class="carousel-control-prev-icon" aria-hidden="true"></span>-->
<!--            <span class="sr-only">Previous</span>-->
<!--          </button>-->
<!--          <button class="carousel-control-next" type="button" data-target="#carouselExampleFade" data-slide="next">-->
<!--            <span class="carousel-control-next-icon" aria-hidden="true"></span>-->
<!--            <span class="sr-only">Next</span>-->
<!--          </button>-->
<!--        </div>-->

      </div>
    </div>
  </div>
  <!--热门推荐-->
  <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
    <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
      热门推荐
    </div>
    <!--卡片-->
    <div class="container">
      <div class="row" id="popularRecommendation">
        <div class="card col-4" style="width: 18rem;" v-for="course in popularCourseList">
          <img class="card-img-top" :src="course.imageURL" alt="Card image cap">
          <div class="card-body">
            <h5 class="card-title">{{ course.courseName }}</h5>
            <p class="card-text">{{ course.courseFirstAbstract }}</p>
          </div>
          <div class="card-footer bg-transparent border-success">
            <button class="btn btn-primary" :id="course.courseId" onclick="introduce()">立即学习</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--最新课程-->
  <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
    <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
      <span>最新课程</span>
      <!--<a style="float: right" onclick="save_course_type(this)">&gt;&gt;更多</a>-->
    </div>
    <!--卡片-->
    <div class="container">
      <div class="row" id="latestCourse">
        <div class="card col-4" style="width: 18rem;" v-for="course in popularCourseList">
          <img class="card-img-top" :src="course.imageURL" alt="Card image cap">
          <div class="card-body">
            <h5 class="card-title">{{ course.courseName }}</h5>
            <p class="card-text">{{ course.courseFirstAbstract }}</p>
          </div>
          <div class="card-footer bg-transparent border-success">
            <button class="btn btn-primary" :id="course.courseId" onclick="introduce()">立即学习</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--IT&计算机-->
  <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
    <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
      <span>IT&计算机</span>
      <a style="float: right;text-decoration:none;" id="1" href="#" onclick="save_course_type(this)">&gt;&gt;更多</a>
    </div>
    <!--卡片-->
    <div class="container">
      <div class="row" id="IT">
        <div class="card col-4" style="width: 18rem;" v-for="course in itCourseList">
          <img class="card-img-top" :src="course.imageURL" alt="Card image cap">
          <div class="card-body">
            <h5 class="card-title">{{ course.courseName }}</h5>
            <p class="card-text">{{ course.courseFirstAbstract }}</p>
          </div>
          <div class="card-footer bg-transparent border-success">
            <button class="btn btn-primary" :id="course.courseId" onclick="introduce()">立即学习</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <!--文学历史-->
  <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
    <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
      <span>文学历史</span>
      <a style="float: right;text-decoration:none;" id="4" href="#" onclick="save_course_type(this)">&gt;&gt;更多</a>
    </div>
    <!--卡片-->
    <div class="container">
      <div class="row" id="literaryHistory">
        <div class="card col-4" style="width: 18rem;" v-for="course in historyCourseList">
          <img class="card-img-top" :src="course.imageURL" alt="Card image cap">
          <div class="card-body">
            <h5 class="card-title">{{ course.courseName }}</h5>
            <p class="card-text">{{ course.courseFirstAbstract }}</p>
          </div>
          <div class="card-footer bg-transparent border-success">
            <button class="btn btn-primary" :id="course.courseId" onclick="introduce()">立即学习</button>
          </div>
        </div>
      </div>
    </div>
  </div>
  <Botton></Botton>
  </div>
</template>

<script>
import top from '@/views/Top.vue'
import botton from '@/views/Botton.vue'

$(function () {
  $('[data-toggle="popover"]').popover()
})
export default {
  name: "Home",
  data () {
    return {
      partCourseTypeList:[],
      promotionCourseList:[],
      popularCourseList:[],
      newCourseList:[],
      itCourseList:[],
      historyCourseList:[],
    }
  },
  methods: {
    //点击课程名获取课程名称，跳转到课程介绍页面
    introduce(str) {
      //获取课程id
      // var courseId = $(str).attr("id");
      // heats_up(courseId);
    },
  },
  mounted () {
    var that=this;
    $(function () {
      //加载课程分类---数据库中前13个课程类型，
      $.ajax({
        //数据提交方式
        type: 'POST',
        // data: {"amount": 13},
        //后端URL
        url: 'http://localhost:8080/homeGetCourseType',
        //返回数据类型
        dataType: 'json',
        success: function (json) {
          console.log(json);
          //获取数据库返回的数据
          that.partCourseTypeList = json.partCourseTypeList;
        },
        error: function () {
          //alert("课程分类加载失败");
        },
      });

      //加载首页滚动轮播图推广课程
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: 'http://localhost:8080/getPromotionCourse',
        //返回数据类型
        dataType: 'json',
        success: function (json) {
          //获取数据库返回的数据
          that.promotionCourseList = json.promotionCourseList;
        },
        error: function () {
        }
      });

      //加载热门课程
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: 'http://localhost:8080/getPopularCourses',
        //返回数据类型
        dataType: 'json',
        success: function (json) {
          console.log(json);
          that.popularCourseList= json.popularCourseList;
        },
        error: function () {
          //alert("热门课程加载失败");
        }
      });

      //加载最新课程
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: 'http://localhost:8080/getNewCourses',
        //返回数据类型
        dataType: 'json',
        //数据完成加载前提示文字(一般很快看不到)
        beforeSend: function () {
        },
        success: function (json) {
          console.log(json);
          that.newCourseList = json.newCourseList;
        },
        error: function () {
          //alert("最新课程加载失败");
        }
      });

      //加载IT课程
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: 'http://localhost:8080/getITCourses',
        //返回数据类型
        dataType: 'json',
        //数据完成加载前提示文字(一般很快看不到)
        beforeSend: function () {
        },
        success: function (json) {
          console.log(json);
          that.itCourseList = json.itCourseList;
        },
        error: function () {
          //alert("IT课程加载失败");
        }
      });

      //加载文学历史课程
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: 'http://localhost:8080/getLiteraryHistoryCourses',
        //返回数据类型
        dataType: 'json',
        //数据完成加载前提示文字(一般很快看不到)
        beforeSend: function () {
        },
        success: function (json) {
          console.log(json);
          //获取数据库返回的数据
          that.historyCourseList = json.historyCourseList;
        },
        error: function () {
          //alert("文学历史课程加载失败");
        }
      });
    });
  },
  components: {
    'Top': top,  // 组件
    'Botton': botton,
  },
}
</script>

<style scoped>

</style>
