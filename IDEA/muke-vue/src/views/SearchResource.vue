<template>
  <div>
    <Top></Top>
    <div class="container" id="resource" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        资源搜索
      </div>
      <table class="table table-sm table-hover table-striped" id="resource-table" v-if="total!==0">
        <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">资源名称</th>
          <th scope="col">所属课程</th>
          <th scope="col">课程名称</th>
          <th scope="col">来源</th>
          <th scope="col" style="text-align: center">操作</th>
        </tr>
        </thead>
        <tbody id="resource-list">
        <tr v-for="(resource,i) in resourceList">
          <th scope="row">{{i}}</th>
          <td>{{resource.resourceName}}</td>
          <td>{{resource.courseId}}</td>
          <td>{{resource.course.courseName}}</td>
          <td>{{resource.userClass.typeName}}</td>
          <td style="text-align: center">
            <a class="badge badge-danger" style="margin-left: 6px;margin-right: 6px" :href="url+resource.resourceId" download="">下载</a>
            <a class="btn badge badge-light" v-if="resource.resourceName.indexOf('.doc') !== -1 || resource.resourceName.indexOf('.ppt') !== -1 || resource.resourceName.indexOf('.pptx') !== -1 || resource.resourceName.indexOf('.xlsx') !== -1" style="margin-left: 6px;margin-right: 6px" :href="'https://view.officeapps.live.com/op/view.aspx?src='+resource.linkURL" target="view_window">预览</a>
          </td>
        </tr>
        </tbody>
      </table>
      <div class="card-body" v-if="total===0">
<!--        <img class="card-img-top" src="../Data/img/no_found.png" alt="Card image cap">-->
        <h3 class="card-title">未找到相关资源</h3>
        <p class="card-text">可能关键词不准确，请重新输入关键词再进行搜索</p>
        <a href="/" class="btn btn-primary">去首页看看</a>
      </div>
      <hr>
      <div id="resource-list-pagecount">
        <nav aria-label="Page navigation ">
          <ul class="pagination">
            <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
            <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
            <li class="page-item"><a class="page-link" href="#" @click="loadingResource(1)">Previous</a></li>
            <li class="page-item" aria-current="page" v-for="i in totalPage">
              <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingResource(i)">{{i}}</a>
            </li>
            <li class="page-item"><a class="page-link" href="#" @click="loadingResource(totalPage)">Last</a></li>
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
  name: "SearchResource",
  components:{
    'Top': top,  // 组件
    'Botton': botton,
  },
  data(){
    return{
      key:'',
      total:0,
      page:0,
      totalPage:0,
      resourceList:[],
      url:'',
    }
  },
  methods:{
    download(resourceId){
      console.log(resourceId);
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/download',
        data: {'resourceId': resourceId},
        //返回数据类型
        dataType: 'json',
        success: function () {
        },
        error: function (XMLHttpRequest, textStatus, e) {
          alert("下载失败");
          console.log("error:" + e);
        }
      });
    },

    //加载资源列表
    loadingResource(page) {
      var that=this;
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/searchResource',
        /**
         * 向后端传递的数据
         * 搜索的关键字以及当前页码
         **/
        data: {'key': that.key, 'pageNum': page - 1},
        //返回数据类型
        dataType: 'json',
        //数据完成加载前提示文字(一般很快看不到)
        beforeSend: function () {
        },
        success: function (json) {
          //获取本页总数据的数量
          that.total = json.total;
          //获取当前页数
          that.page = page;
          //获取总页数
          that.totalPage = json.totalPage;
          that.resourceList = json.resourceList;
          that.url=json.url;
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
  },
  computed:{
  },
  mounted() {
    var that=this;
    //页码相关
    $(function () {
      console.log("123");

      //获取URL
      var url = document.URL;
      //分割URL,获取搜索框内的关键词
      var keyStr = url.split("?key=")[1];
      this.key = decodeURI(keyStr);
      if (typeof(this.key)== "undefined") {
        alert("非法访问!");
        // window.open("/", "_self").close();
        const { href } = that.$router.resolve({
          path: "/",
        });
        window.open(href, '_self').close();
      }
      //此页面第一次加载将会显示第一页的搜索结果
      that.loadingResource(1);
    });
  },
}
</script>

<style scoped>

</style>
