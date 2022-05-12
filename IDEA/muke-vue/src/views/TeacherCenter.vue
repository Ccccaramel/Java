<template>
  <div>
    <Top></Top>
    <!--主体部分-->
    <div class="container" style="padding-left:0px;max-width:1800px;padding-right:0px;margin-bottom: 16px">
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 16px;elevation: deg;font-weight: bold">
        <span>学思网-教师个人中心</span>
      </div>
      <div class="row">
        <div class="col-1">
          <div class="list-group" id="list-tab" role="tablist">
            <a class="list-group-item list-group-item-action active" id="list-home-list" data-toggle="list"
               href="#v-pills-home" role="tab" aria-controls="home" style="font-weight: bold">教师信息</a>
            <a class="list-group-item list-group-item-action" id="list-profile-list" data-toggle="list"
               href="#v-pills-changeTeacherPassword" role="tab" aria-controls="profile" style="font-weight: bold">修改密码</a>
            <a class="list-group-item list-group-item-action" id="list-course-list" data-toggle="list"
               href="#v-pills-course" role="tab" aria-controls="messages" style="font-weight: bold"
               @click="course()">课程管理</a>
            <a class="list-group-item list-group-item-action" id="list-test-list" data-toggle="list"
               href="#v-pills-test" role="tab" aria-controls="settings" style="font-weight: bold"
               @click="test()">题库管理</a>
            <a class="list-group-item list-group-item-action" id="list-resources-list" data-toggle="list"
               href="#v-pills-curriculum-resource" role="tab" aria-controls="settings" style="font-weight: bold"
               @click="resource()">资源管理</a>
            <a class="list-group-item list-group-item-action" id="list-exit-list" data-toggle="list" href="#"
               role="tab" aria-controls="settings" style="font-weight: bold"
               @click="teacherExitXuesi()">安全退出</a>
          </div>
        </div>
        <div class="col-11">
          <div class="tab-content" id="v-pills-tabContent">
            <!--教师个人信息-->
            <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
                 aria-labelledby="v-pills-home-tab">
              <form method="post" id="teacher_message">

                <div class="form-group row">
                  <label for="teacherId" class="col-sm-2 col-form-label">Id</label>
                  <div class="col-sm-10">
                    <input type="text" readonly class="form-control-plaintext" id="teacherId"
                           placeholder="你还未登录！">
                  </div>
                </div>

                <div class="form-group row">
                  <label for="teacherName" class="col-sm-2 col-form-label">姓名</label>
                  <div class="col-sm-10">
                    <input type="text" readonly class="form-control-plaintext" id="teacherName"
                           placeholder="你还未登录！">
                  </div>
                </div>

                <div class="form-group row">
                  <label for="teachersSchool" class="col-sm-2 col-form-label">任职学院</label>
                  <div class="col-sm-10">
                    <input type="text" class="form-control" name="userEmail" id="teachersSchool"
                           placeholder="未知" style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="teacherTel" class="col-sm-2 col-form-label">电话号码</label>
                  <div class="col-sm-10">

                    <input type="tel" class="form-control" name="userTel" id="teacherTel"
                           placeholder="请补充你的电话号码"
                           style="border: 0px;padding-left: 0px">

                  </div>
                </div>

                <div class="form-group row">
                  <label for="teacherEmail" class="col-sm-2 col-form-label">个人邮箱</label>
                  <div class="col-sm-10">
                    <input type="email" readonly class="form-control-plaintext" id="teacherEmail"
                           placeholder="未知">
                  </div>
                </div>

                <div class="form-group row">
                  <label for="teacherQualification" class="col-sm-2 col-form-label">教师资格证</label>
                  <div class="col-sm-10">
                    <input type="text" readonly class="form-control-plaintext" id="teacherQualification"
                           placeholder="未知">
                  </div>
                </div>

                <div class="form-group row">
                  <label for="teachersSchoolEmail" class="col-sm-2 col-form-label">学院邮箱</label>
                  <div class="col-sm-10">
                    <input type="text" readonly class="form-control-plaintext" id="teachersSchoolEmail"
                           placeholder="未知">
                  </div>
                </div>

                <hr>
                <input class="btn btn-primary" type="button" @click="saveTeacherChanges()" value="保存修改">
              </form>
            </div>
            <!--密码修改-->
            <div class="tab-pane fade" id="v-pills-changeTeacherPassword" role="tabpanel"
                 aria-labelledby="v-pills-profile-tab">
              <form method="post" id="change_password_form">
                <div class="form-group row">
                  <label for="teacherOldPassword" class="col-sm-2 col-form-label">原密码</label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="teacherOldPassword"
                           id="teacherOldPassword"
                           placeholder="输入你的密码"
                           style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="teacherNewPassword" class="col-sm-2 col-form-label">新密码</label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="teacherNewPassword"
                           id="teacherNewPassword"
                           placeholder="6-12位的字母或数字"
                           style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <div class="form-group row">
                  <label for="teacherNewPasswordR" class="col-sm-2 col-form-label">确认新密码</label>
                  <div class="col-sm-10">
                    <input type="password" class="form-control" name="teacherNewPasswordR"
                           id="teacherNewPasswordR"
                           placeholder="请再输入一次新密码"
                           style="border: 0px;padding-left: 0px">
                  </div>
                </div>
                <hr>
                <input class="btn btn-primary" type="button" @click="saveTeacherChangePassword()"
                       value="保存修改">
              </form>
            </div>

            <!--课程管理-->
            <div class="tab-pane fade" id="v-pills-course" role="tabpanel" aria-labelledby="v-pills-home-tab">
              <div class="container">

                <div class="input-group mb-3 align-self-center">
                  <button type="button" @click="addCourse()" class="btn btn-success">添加课程
                  </button>
                </div>
              </div>
              <br>
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">课程ID</th>
                  <th scope="col">课程名</th>
                  <th scope="col">课程类别</th>
                  <th scope="col">课程状态</th>
                  <th scope="col" style="text-align: center">操作</th>
                </tr>
                </thead>
                <tbody id="course-list">
                  <tr v-for="courseInfo in courseListCom">
                    <th scope="row">{{courseInfo.courseId}}</th>
                    <td>{{courseInfo.courseName}}</td>
                    <td>{{courseInfo.courseType.typeName}}</td>
                    <td :id="'course-'+courseInfo.courseId">{{courseInfo.accountStatus.accountClass}}</td>
                    <td style="text-align: center">
                      <span class="btn badge badge-danger" @click="operation(courseInfo.courseId,3,3)">删除</span>
                      <span class="btn badge badge-light" style="margin-left: 6px;margin-right: 6px" @click="operation(courseInfo.courseId,2,3)">冻结</span>
                      <span class="btn badge badge-success" @click="operation(courseInfo.courseId,1,3)">恢复</span>
                      <span class="btn badge badge-info" style="margin-left: 24px" @click="changeCourse(courseInfo.courseId)">编辑</span>
                    </td>
                  </tr>
                </tbody>
              </table>
              <hr>
              <div id="course-pagecount">
                <nav aria-label="Page navigation ">
                  <ul class="pagination">
                    <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                    <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingCourse(1)">Previous</a></li>
                    <li class="page-item" aria-current="page" v-for="i in totalPage">
                      <a class="page-link" href="#" v-if="curPage+3>i && curPage-3<i" @click="loadingCourse(i)">{{i}}</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingCourse(totalPage)">Last</a></li>
                  </ul>
                </nav>
              </div>
            </div>

            <!--题库管理-->
            <div class="tab-pane fade" id="v-pills-test" role="tabpanel" aria-labelledby="v-pills-messages-tab">
              <div class="container">

                <div class="input-group mb-3 align-self-center">
                  <button type="button" @click="addCourseTest()" class="btn btn-success">编辑新的测试卷
                  </button>
                </div>
              </div>
              <br>
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">测试卷ID</th>
                  <th scope="col">测试卷名称</th>
                  <th scope="col">课程id</th>
                  <th scope="col">测试卷状态</th>
                  <th scope="col" style="text-align: center">操作</th>
                </tr>
                </thead>
                <tbody id="test-list">
                  <tr v-for="test in testListCom">
                    <th scope="row">{{test.testId}}</th>
                    <td>{{test.testName}}</td>
                    <td>{{test.courseId}}</td>
                    <td :id="'test-'+test.testId">{{test.accountStatus.accountClass}}</td>
                    <td style="text-align: center">
                      <span class="btn badge badge-danger" @click="testOperation(test.testId,3,5)">删除</span>
                      <span class="btn badge badge-light" style="margin-left: 6px;margin-right: 6px" @click="testOperation(test.testId,2,5)">冻结</span>
                      <span class="btn badge badge-success" @click="testOperation(test.testId,1,5)">恢复</span>
                      <span class="btn badge badge-info" style="margin-left: 24px" @click="changeTest(test.testId,test.courseId)">编辑</span>
                    </td>
                  </tr>
                </tbody>
              </table>
              <hr>
              <div id="test-pagecount">
                <nav aria-label="Page navigation ">
                  <ul class="pagination">
                    <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                    <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingTest(1)">Previous</a></li>
                    <li class="page-item" aria-current="page" v-for="i in totalPage">
                      <a class="page-link" href="#" v-if="curPage+3>i && curPage-3<i" @click="loadingTest(i)">{{i}}</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingTest(totalPage)">Last</a></li>
                  </ul>
                </nav>
              </div>
            </div>

            <!--课程资源管理-->
            <div class="tab-pane fade" id="v-pills-curriculum-resource" role="tabpanel"
                 aria-labelledby="v-pills-messages-tab">
              <div class="container">

                <div class="input-group mb-3 align-self-center">
                  <button type="button" @click="addCourseResources()" class="btn btn-success">添加课程资源
                  </button>
                </div>
              </div>
              <br>
              <table class="table">
                <thead>
                <tr>
                  <th scope="col">资源ID</th>
                  <th scope="col">资源名称</th>
                  <th scope="col">课程ID</th>
                  <th scope="col">资源状态</th>
                  <th scope="col" style="text-align: center">操作</th>
                </tr>
                </thead>
                <tbody id="resource-list">
                  <tr v-for="resource in resourceList">
                    <th scope="row">{{resource.resourceId}}</th>
                    <td>{{resource.resourceName}}</td>
                    <td>{{resource.courseId}}</td>
                    <td :id="resource.resourceId">{{resource.accountStatus.accountClass}}</td>
                    <td style="text-align: center">
                      <span class="btn badge badge-danger" @click="resourceOperation(resource.resourceId,3,7)">删除</span>
                      <span class="btn badge badge-light" style="margin-left: 6px;margin-right: 6px" @click="resourceOperation(resource.resourceId,2,7)">冻结</span>
                      <span class="btn badge badge-success" @click="resourceOperation(resource.resourceId,1,7)">恢复</span>
                    </td>
                  </tr>
                </tbody>
              </table>
              <hr>
              <div id="resource-pagecount"></div>
                <nav aria-label="Page navigation ">
                  <ul class="pagination">
                    <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                    <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                    <li class="page-item"><span class="page-link" @click="loadingResource(1)">Previous</span></li>
                    <li class="page-item" aria-current="page" v-for="i in totalPage">
                      <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingResource(i)">{{i}}</a>
                    </li>
                    <li class="page-item"><a class="page-link" href="#" @click="loadingResource(totalPage)">Last</a></li>
                  </ul>
                </nav>
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
  name: "TeacherCenter",
  components: {
    'Top': top,  // 组件
    'Botton': botton,
  },
  data(){
    return{
      // page: 1,
      total:0,
      curPage :0,
      totalPage :0,
      courseList:[],
      testList:[],
      resourceList:[],

    }
  },
  computed:{
    totalCom:function (){
      return this.total;
    },
    curPageCom:function (){
      return this.curPage;
    },
    totalPageCom:function (){
      return this.totalPage;
    },
    courseListCom:function (){
      return this.courseList;
    },
    testListCom:function (){
      return this.testList;
    },
  },
  methods:{
    //教师个人信息修改
    saveTeacherChanges() {
      var that=this;
        $.ajax({
          url: global.httpUrl+'/changeTeacherInformation',
          type: 'post',
          data: {
            "teacherEmail" : $("#teacherEmail").val() ,
            "teachersSchool" : $("#teachersSchool").val() ,
            "teacherTel" : $("#teacherTel").val() ,
            "teachersSchoolEmail" : $("#teachersSchoolEmail").val()
          },
          dataType: 'json',
          success: function (response) {
            alert("修改成功!");
            $("#teachersSchool").val(response.teachersSchool);
            $("#teacherTel").val(response.teacherTel);
            $("#teacherEmail").val(response.teacherEmail);
            $("#teachersSchoolEmail").val(response.teachersSchoolEmail);
          },
          error: function (err) {
            alert("错误");
          }
        });
    },
    //密码检查
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
    //密码修改
    saveTeacherChangePassword() {
      var oldPassword=$("#teacherOldPassword").val();
      var newPassword=$("#teacherNewPassword").val();
      var newPasswordR=$("#teacherNewPasswordR").val();
      if(this.checkPassword(oldPassword,newPassword,newPasswordR)){
        var dataStr={
          "oldPassword" : oldPassword,
          "newPassword" : newPassword};
        $.ajax({
          url: global.httpUrl+'/changePassword',
          type: 'post',
          data: dataStr,
          dataType: 'json',
          success: function (response) {
            alert(response.message);
            $("#teacherOldPassword").val("");
            $("#teacherNewPassword").val("");
            $("#teacherNewPasswordR").val("");
          },
          error: function (err) {
            alert("错误");
          }
        });
      }
    },
    //添加课程
    addCourse() {
      window.open('/CourseManagement');
    },
    //教师退出
    teacherExitXuesi() {
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

    //课程管理
    course() {
      this.loadingCourse(1);
    },
    //加载课程信息
    loadingCourse(page) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getTeacherCourseMessage',
        data: {'pageNum': page - 1},
        dataType: 'json',
        beforeSend: function () {
          // $("#course-list").append("加载中...");
        },
        success: function (json) {
          // $("#course-list").empty();
          that.total = json.total;
          that.curPage = page;
          that.totalPage = json.totalPage;
          that.courseList = json.courseList;
          console.log(">"+JSON.stringify(json.courseList));
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
    //状态更改
    //id：对象id
    //sign:操作指令代号
    //   0：待审核
    //   1：正常
    //   2：冻结
    //   3：注销
    //课程状态更改
    operation(courseId, sign,category) {
      var that=this;
      // alert("你确定要对"+id+"执行"+sign+"操作吗?");
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/accountManagement',
        data: {'id': courseId, 'sign': sign,'category':category},
        dataType: 'json',
        success: function (json) {
          alert(json.result);
          if (sign === 3) {
            //如果是删除则更新列表
            that.course();
          } else {
            $("#course-" + courseId).html(json.result);
          }

        },
        error: function () {
          alert("操作失败");
        }
      });
    },
    //编辑或修改课程
    changeCourse(courseId) {
      window.open("/CourseManagement?courseId=" + courseId);
    },

    //添加课程测试卷
    addCourseTest() {
      //在此之前判断该教师是否已发布过课程
      $.ajax({
        url: global.httpUrl+'/checkTeacherCourse',
        type: 'post',
        dataType: 'json',
        success: function (json) {
          if(json.total>0){
            window.open('/CourseTest');
          }else{
            alert("你还未发布过课程或无正常状态的课程，请添加课程或将课程状态恢复!");
          }

        },
        complete: function(XMLHttpRequest, textStatus) {
        },
        error: function (err) {
          alert("添加课程时出错!");
        }
      });

    },
    //测试卷管理
    test() {
      this.loadingTest(1);
    },

    //加载测试卷信息
    loadingTest(page) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getTestMessage',
        data: {'pageNum': page - 1},
        dataType: 'json',
        beforeSend: function () {
          // $("#test-list").append("加载中...");
        },
        success: function (json) {
          // $("#test-list").empty();
          that.total = json.total;
          that.curPage = page;
          that.totalPage = json.totalPage;
          that.testList = json.testList;
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },

    //课程测试卷状态更改
    testOperation(testId, sign,category) {
      // alert("你确定要对" + testId + "执行" + sign + "操作吗?");
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/accountManagement',
        data: {'id': testId, 'sign': sign,'category':category},
        dataType: 'json',
        success: function (json) {
          if (sign == 3) {
            //如果是删除则更新列表
            that.test();
          } else {
            $("#test-" + testId).html(json.result);
          }

        },
        error: function () {
          alert("操作失败");
        }
      });
    },

    //编辑或修改测试卷
    changeTest(testId) {
      window.open("/CourseTest?testId=" + testId);
    },

    //添加课程资源
    addCourseResources() {
      //在此之前判断该教师是否已发布过课程
      $.ajax({
        url: global.httpUrl+'/checkTeacherCourse',
        type: 'post',
        dataType: 'json',
        success: function (json) {
          if(json.total>0){
            window.open('/CourseResource');
          }else{
            alert("你还未发布过课程或无正常状态的课程，请添加课程或将课程状态恢复!");
          }

        },
        complete: function(XMLHttpRequest, textStatus) {
        },
        error: function (err) {
          alert("添加课程时出错!");
        }
      });

    },

    //资源管理
    resource() {
      this.loadingResource(1);
    },

    //加载课程资源信息
    loadingResource(page) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getCourseResource',
        data: {'pageNum': page - 1},
        dataType: 'json',
        beforeSend: function () {
        },
        success: function (json) {
          that.total = json.total;
          that.curPage = page;
          that.totalPage = json.totalPage;
          that.resourceList = json.courseResouceList;
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },

    //课程资源状态更改
    resourceOperation(resourceId, sign,category) {
      // alert("你确定要对"+id+"执行"+sign+"操作吗?");
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/accountManagement',
        data: {'id': resourceId, 'sign': sign,'category':category},
        dataType: 'json',
        success: function (json) {
          // alert(json.state);
          if (sign === 3) {
            //如果是删除则更新列表
            resource();
          } else {
            $("#resource-list #" + resourceId).html(json.result);
          }
        },
        error: function () {
          alert("操作失败");
        }
      });
    },
  },
  mounted() {
    var that=this;
    $(function () {
      // 页面加载完后立即自动执行
      // 判断是否已登录
      $.ajax({
        url: global.httpUrl+'/getTeacherMessage',
        type: 'post',
        dataType: 'json',
        success: function (response) {
          if(response.state){
            $("#teacherId").val(response.teacherId);
            $("#teacherName").val(response.teacherName);
            $("#teachersSchool").val(response.teachersSchool);
            $("#teacherTel").val(response.teacherTel);
            $("#teacherEmail").val(response.teacherEmail);
            $("#teacherQualification").val(response.teacherQualification);
            $("#teachersSchoolEmail").val(response.teachersSchoolEmail);
          }else{
            alert("非法进入!");
            window.open("/", "_self").close();
          }
        },
        error: function (err) {
          alert("错误");
          that.$router.push("/");
        }
      });
    });
  }
}

</script>

<style scoped>

</style>
