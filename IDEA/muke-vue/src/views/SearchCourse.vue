<template>
  <div>
    <Top></Top>
    <!--课程搜索结果-->
    <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        搜索结果
      </div>
      <!--搜索结果-->
      <div class="container">
        <div id="list" class="row">

          <!---->
          <!--搜索显示部分-->
          <!---->
          <div class="card-body" v-if="total===0">
<!--            <img class="card-img-top" src="../Data/img/no_found.png" alt="Card image cap">-->
            <h3 class="card-title">未找到相关资源</h3>
            <p class="card-text">可能关键词不准确,请重新输入关键词再进行搜索</p>
<!--            <a href="/" class="btn btn-primary">去首页看看</a>-->
          </div>

          <div class="card col-4" style="width: 18rem;cursor:pointer" :id="course.courseId" @click="introduce(course.courseId)" v-if="total>0" v-for="course  in courseList">
            <img class="card-img-top" :src="course.imageURL" alt="Card image cap">
            <div class="card-body">
              <span class="card-title">{{ course.courseName }}</span>
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
            <li class="page-item"><a class="page-link" href="#" @click="loadingCourse(1)">Previous</a></li>
            <li class="page-item" aria-current="page" v-for="i in totalPage">
              <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingCourse(i)">{{i}}</a>
            </li>
            <li class="page-item"><a class="page-link" href="#" @click="loadingCourse(totalPage)">Last</a></li>
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
  name: "SearchCourse",
  data(){
    return{
      key:'',
      total:0,
      totalPage:0,
      page:0,
      courseList:[],
    }
  },
  methods:{
    loadingCourse(page) {
      var that=this;
      console.log("key:"+that.key);
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/sourchCourse',
        data: {'key': that.key, 'pageNum': page - 1},
        //返回数据类型
        dataType: 'json',
        //数据完成加载前提示文字(一般很快看不到)
        beforeSend: function () {
        },
        success: function (json) {
          console.log(json);
          //获取本页总数据的数量
          that.total = json.total;
          //获取当前页数
          that.page = page;
          //获取总页数
          that.totalPage = json.totalPage;
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
          // location.href = "/Course?courseId=" + courseId;
          that.$router.push("/Course?courseId=" + courseId);
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
  },
  components:{
    'Top': top,  // 组件
    'Botton': botton,
  },
  computed:{

  },
  mounted() {
    var that=this;
    $(function () {
      //获取URL
      var url = document.URL;
      //分割URL,获取搜索框内的关键词
      var keyStr = url.split("?key=")[1];
      that.key = decodeURI(keyStr);
      if(typeof (that.key)==="undefined"){
        alert("非法访问!");
        // window.open("/","_self").close();
        const { href } = that.$router.resolve({
          path: "/",
        });
        window.open(href, '_self').close();
      }
      //此页面第一次加载将会显示第一页的搜索结果
      that.loadingCourse(1);
    });
  },
}
</script>

<style scoped>

</style>
