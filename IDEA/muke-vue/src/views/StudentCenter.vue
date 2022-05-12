<template>
  <div>
    <Top></Top>
    <!--主体部分-->
    <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 16px;font-weight: bold">
        <span>个人中心</span>
      </div>
      <div class="row">
        <div class="col-2">
          <div class="list-group" id="v-pills-tab" role="tablist" aria-orientation="vertical">
            <a class="list-group-item list-group-item-action active" id="v-pills-home-tab" data-toggle="pill"
               href="#v-pills-home" role="tab"
               aria-controls="v-pills-home" aria-selected="true" style="border: 0px">我的信息</a>
            <a class="list-group-item list-group-item-action" id="v-pills-change-password-tab" data-toggle="pill"
               href="#v-pills-change-password" role="tab"
               aria-controls="v-pills-change-password" aria-selected="false" style="border: 0px">修改密码</a>
            <a class="list-group-item list-group-item-action" id="v-pills-collection-tab" data-toggle="pill"
               href="#v-pills-collection" role="tab"
               aria-controls="v-pills-collection" aria-selected="false" style="border: 0px"
               @click="loadingMyCourseCollection(1)">课程收藏</a>

            <a class="list-group-item list-group-item-action" id="v-pills-my-resource-tab" data-toggle="pill"
               href="#v-pills-my-resource" role="tab"
               aria-controls="v-pills-my-resource" aria-selected="false" style="border: 0px"
               @click="myResource()">我的资源</a>

            <a class="list-group-item list-group-item-action" id="v-pills-achievement-tab" data-toggle="pill"
               href="#v-pills-achievement" role="tab"
               aria-controls="v-pills-achievement" aria-selected="false" style="border: 0px"
               @click="myAchievement()">我的成绩</a>
          </div>
          <hr>
          <a class="btn list-group-item list-group-item-action" aria-selected="false" style="border: 0px"
             @click="userExitXuesi">安全退出</a>
        </div>
        <div class="col-10">
          <div class="tab-content" id="v-pills-tabContent">
            <!--我的信息-->
            <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
                 aria-labelledby="v-pills-home-tab">
              <form method="post" id="SUser">
                <div class="form-group row">
                  <label for="userName" class="col-sm-2 col-form-label">用户名</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="userName" id="userName"
                           placeholder="给自己起一个昵称吧" style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="userId" class="col-sm-2 col-form-label">Id</label>
                  <div class="col-sm-10">
                    <input type="text" readonly class="form-control-plaintext" id="userId"
                           placeholder="你还未登录！">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="userEmail" class="col-sm-2 col-form-label">Email</label>
                  <div class="col-sm-10">
                    <input type="email" class="form-control" name="userEmail" id="userEmail"
                           placeholder="email@example.com" style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="userTel" class="col-sm-2 col-form-label">电话号码</label>
                  <div class="col-sm-10">
                    <input type="tel" class="form-control" name="userTel" id="userTel"
                           placeholder="请补充你的电话号码" style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <div class="form-group row">
                  <label class="col-sm-2 col-form-label">性别</label>
                  <div class="col-sm-10 form-control" style="border: 0px">

                    <div class="custom-control custom-radio custom-control-inline">
                      <input type="radio" id="customRadioInline1" name="customRadioInline1"
                             class="custom-control-input" value="男">
                      <label class="custom-control-label" for="customRadioInline1">男</label>
                    </div>
                    <div class="custom-control custom-radio custom-control-inline">
                      <input type="radio" id="customRadioInline2" name="customRadioInline1"
                             class="custom-control-input" value="女">
                      <label class="custom-control-label" for="customRadioInline2">女</label>
                    </div>
                  </div>
                </div>
                <div class="form-group row">
                  <label for="userBirth" class="col-sm-2 col-form-label">出生日期</label>
                  <div class="col-sm-10">
                    <input type="date" class="form-control" id="userBirth" name="userBirth"
                           placeholder="2018-12-08" style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <hr>
                <input class="btn btn-primary" type="button" @click="saveChanges" value="保存修改">
              </form>
            </div>
            <!--密码修改-->
            <div class="tab-pane fade" id="v-pills-change-password" role="tabpanel"
                 aria-labelledby="v-pills-change-password-tab">
              <form method="post" id="change_password_form">
                <div class="form-group row">
                  <label for="userOldPassword" class="col-sm-2 col-form-label">原密码</label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="userOldPassword" id="userOldPassword"
                           placeholder="输入你的密码" style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="userNewPassword" class="col-sm-2 col-form-label">新密码</label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="userNewPassword" id="userNewPassword"
                           placeholder="你的新密码" style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="userNewPasswordR" class="col-sm-2 col-form-label">确认新密码</label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="userNewPasswordR"
                           id="userNewPasswordR" placeholder="请再输入一次新密码"
                           style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <hr>
                <input class="btn btn-primary" type="button" @click="saveChangePassword" value="保存修改">
              </form>
            </div>
            <!--课程收藏-->
            <div class="tab-pane fade" id="v-pills-collection" role="tabpanel"
                 aria-labelledby="v-pills-collection-tab" style="padding: 15px">
              <!--card-->
              <div id="my-course-collection-list" class="row">
                <div class="card col-3" style="width: 18rem;cursor:pointer" v-for="myCourseCollection in myCourseCollectionList">
                  <img class="card-img-top" :src="myCourseCollection.course.imageURL" alt="Card image cap">
                  <div class="card-body">
                    <span class="card-title" :id="myCourseCollection.courseId" @click="introduce(myCourseCollection.courseId)">{{ myCourseCollection.course.courseName }}</span>
                  </div>
                </div>
              </div>
              <hr>
              <!--页码-->
              <div id="my-course-collection-pagecount">
                <nav aria-label="Page navigation ">
                  <ul class="pagination">
                    <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                    <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingMyCourseCollection(1)">Previous</a></li>
                    <li class="page-item" aria-current="page" v-for="i in totalPage">
                      <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingMyCourseCollection(i)">{{i}}</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingMyCourseCollection(totalPage)">Last</a></li>
                  </ul>
                </nav>
              </div>
            </div>
            <!--我的资源-->
            <div class="tab-pane fade" id="v-pills-my-resource" role="tabpanel"
                 aria-labelledby="v-pills-my-resource-tab">

              <table class="table">
                <thead>
                <tr>
                  <th scope="col">资源id</th>
                  <th scope="col">资源名称</th>
                  <th scope="col">课程id</th>
                  <th scope="col">资源状态</th>
                  <th scope="col" style="text-align: center">操作</th>
                </tr>
                </thead>
                <tbody id="my-resource-list">
                  <tr v-for="(resource,i) in myResourceList">
                    <th scope="row">{{ i }}</th>
                    <td>{{ resource.resourceName }}</td>
                    <td>{{ resource.courseId }} </td>
                    <td :id="resource.resourceId">{{ resource.accountStatus.accountClass }}</td>
                    <td style="text-align: center">
                      <span class="btn badge badge-danger" @click="resourceOperation(resource.resourceId,3,7)">删除</span>
                      <span class="btn badge badge-light" style="margin-left: 6px;margin-right: 6px" @click="resourceOperation(resource.resourceId,2,7)">冻结</span>
                      <span class="btn badge badge-success" @click="resourceOperation(resource.resourceId,1,7)">恢复</span>
                    </td>
                  </tr>
                </tbody>
              </table>
              <hr>
              <div id="my-resource-pagecount">
                <nav aria-label="Page navigation ">
                  <ul class="pagination">
                    <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                    <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingMyResource(1)">Previous</a></li>
                    <li class="page-item" aria-current="page" v-for="i in totalPage">
                      <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingMyResource(i)">{{i}}</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingMyResource(totalPage)">Last</a></li>
                  </ul>
                </nav>
              </div>
            </div>
            <!--我的成绩-->
            <div class="tab-pane fade" id="v-pills-achievement" role="tabpanel"
                 aria-labelledby="v-pills-achievement-tab">
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">测试卷名</th>
                  <th scope="col">所属课程</th>
                  <th scope="col">得分</th>
                  <th scope="col">总分</th>
                  <th scope="col">测试评价</th>
                  <th scope="col">测试日期</th>
                  <th scope="col" style="text-align: center">操作</th>
                </tr>
                </thead>
                <tbody id="my-achievement-list">
                  <tr v-for="myAchievement in myAchievementList">
                    <th scope="row">{{ myAchievement.itemBack.testName }}</th>
                    <td>{{ myAchievement.course.courseName }}</td>
                    <td>{{ myAchievement.userAchievement }}</td>
                    <td>{{ myAchievement.totalScore }}</td>
                    <td>{{ myAchievement.evaluate.typeName }}</td>
                    <td>{{ myAchievement.submitTestDate }}</td>
                    <td style="text-align: center" v-if="myAchievement.effectiveness==1">
                      <a class="badge badge-light" style="cursor:pointer" @click="answerDetails(myAchievement.testId,myAchievement.answerSheetId)">详情&rsaquo;&rsaquo;</a>
                    </td>
                    <td style="text-align: center" v-if="myAchievement.effectiveness!=1">测试卷已失效</td>
                  </tr>
                </tbody>
              </table>
              <hr>
              <div id="my-achievement-pagecount">
                <nav aria-label="Page navigation ">
                  <ul class="pagination">
                    <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                    <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingMyResource(1)">Previous</a></li>
                    <li class="page-item" aria-current="page" v-for="i in totalPage">
                      <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingMyResource(i)">{{i}}</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingMyResource(totalPage)">Last</a></li>
                  </ul>
                </nav>
              </div>
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
import global from './common.vue'
export default {
  name: "StudentCenter",
  data(){
    return{
      myCourseCollectionList:[],
      myResourceList:[],
      myAchievementList:[],
      total:0,
      page:0,
      totalPage:0,
    }
  },
  methods:{

    //个人信息修改
    saveChanges() {
      $.ajax({
        url: global.httpUrl+'/changeUserInformation',
        type: 'POST',
        data:{"userName":$("#userName").val(),
          "userEmail":$("#userEmail").val(),
          "userTel":$("#userTel").val(),
          "userSex":$('input[name="customRadioInline1"]:checked').val(),
          "userBirth":$("#userBirth").val()
        },
        dataType:'json',
        success: function (response) {
          if(response.result){
            alert("修改成功!");
          }
        },
        error:function(xhr,status,error){
          alert("修改失败!");
        }
      });
    },
    //账号退出
    userExitXuesi() {
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/exit',
        success: function (json) {
          location.href = '/';
        },
        error: function () {
          alert("操作失败");
        }
      });
    },

    //密码修改
    saveChangePassword() {
      var userOldPassword = $("#userOldPassword").val();
      var userNewPassword = $("#userNewPassword").val();
      var userNewPasswordR = $("#userNewPasswordR").val();
      if(this.checkPassword(userOldPassword,userNewPassword,userNewPasswordR)){
        $.ajax({
          url: global.httpUrl+'/changePassword',
          type: 'POST',
          data:{"oldPassword":$("#userOldPassword").val(),
            "newPassword":$("#userNewPassword").val(),
          },
          dataType:'json',
          success: function (response) {
            alert(response.message);
            $("#userOldPassword").val('');
            $("#userNewPassword").val('');
            $("#userNewPasswordR").val('');
          },
          error:function(xhr,status,error){
            alert("修改密码操作失败!");
          }
        });
      }
    },

    //密码检查(php的写在后端)
    checkPassword(oldPassword,newPassword,newPasswordR){
      if(oldPassword==newPassword){
        alert("新密码不可与原密码相同!");
        return false;
      }
      if(newPassword!=newPasswordR){
        alert("两次密码不一致!");
        return false;
      }
      var ps=/^[0-9a-zA-Z]{6,12}$/;
      if(ps.test(newPassword)){
        return true;
      }else{
        alert("两次新密码不符合规范,请使用数字或字母且长度在6-12之间!");
        return false;
      }
    },

    //获取该用户的收藏课程
    myCourseCollection() {
      this.loadingMyCourseCollection(1);
    },
    //加载我的课程收藏
    loadingMyCourseCollection(page) {
      var that=this;
      $.ajax({
        //数据提交方式
        type: 'POST',
        //后端URL
        url: global.httpUrl+'/getUserCourseCollection',
        /**
         * 向后端传递的数据
         * 搜索的关键字以及当前页码
         **/
        data: {'pageNum': page - 1},
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
          //获取数据库返回的数据
          that.myCourseCollectionList = json.list;
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
    //获取该用户发布的资源
    myResource() {
      this.loadingMyResource(1);
    },
    //加载我发布的资源
    loadingMyResource(page) {
      var that=this;
      $.ajax({
        //数据提交方式
        type: 'POST',
        url: global.httpUrl+'/getCourseResource',
        data: {'pageNum': page - 1},
        //返回数据类型
        dataType: 'json',
        success: function (json) {
          console.log(json);
          //获取本页总数据的数量
          that.total = json.total;
          //获取当前页数
          that.page = page;
          //获取总页数
          that.totalPage = json.totalPage;
          //获取数据库返回的数据
          that.myResourceList = json.courseResouceList;
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },

    //用户对自己的课程资源状态更改
    resourceOperation(resourceId, sign,category) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/accountManagement',
        data: {'id': resourceId, 'sign': sign,'category':category},
        dataType: 'json',
        success: function (json) {
          that.loadingMyResource(1);
        },
        error: function () {
          alert("操作失败");
        }
      });
    },
    //获取该用户的成绩
    myAchievement() {
      this.loadingMyAchievement(1);
    },
    //加载我的考试成绩信息
    loadingMyAchievement(page) {
      var that=this;
      alert("page:"+page);
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getAppointUserAchievementInformation',
        data: {'pageNum': page - 1},
        dataType: 'json',
        beforeSend: function () {
        },
        success: function (json) {
          that.total = json.total;
          that.page = page;
          that.totalPage = json.totalPage;
          that.myAchievementList = json.list;
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
    //运行此函数将携带答题卡id进入test页面，并将答卷以及正确答案和解析重现给用户
    answerDetails(testId, answerSheetId) {
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/saveAnswerSheetId',
        data: {'testId': testId,'answerSheetId':answerSheetId},
        dataType: 'json',
        success: function () {
          window.open("/Test");
        },
        error: function () {
          alert("跳转失败");
        }
      });
    },
  },
  components: {
    'Top': top,  // 组件
    'Botton': botton,
  },
  mounted() {
    // 页面加载完后立即自动执行
    // 判断是否已登录
    $(function () {
      $.ajax({
        url: global.httpUrl+'/getUserMessage',
        type: 'post',
        dataType: 'json',
        success: function (response) {
          if(!response.state){
            alert("非法访问!");
            window.open("/","_self").close();
          }

          $("#userName").val(response.userName);
          $("#userId").val(response.userId);
          $("#userEmail").val(response.userEmail);
          $("#userTel").val(response.userTel);

          if (response.userSex == "男") {
            $("#customRadioInline1").prop("checked", true);
            $("#customRadioInline2").prop("checked", false);
          } else if (response.userSex == "女") {
            $("#customRadioInline1").prop("checked", false);
            $("#customRadioInline2").prop("checked", true);
          }
          $("#userBirth").val(response.userBirth);
        },
        error: function (err) {
          alert("判断是否已登录错误");
        }
      });
    });
  }

}
</script>

<style scoped>

</style>
