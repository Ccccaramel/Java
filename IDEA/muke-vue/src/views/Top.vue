<template>
  <div>
  <!--顶部栏-->
  <nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-bottom: 16px">
    <img src="../assets/logo.png">
    <a class="navbar-brand" href="/">Muke</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav mr-auto">
        <li class="nav-item active">
          <a class="nav-link" href="course_type">课程分类 <span class="sr-only">(current)</span></a>
        </li>

        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
             aria-haspopup="true" aria-expanded="false">
            帮助反馈
          </a>
          <div class="dropdown-menu" aria-labelledby="navbarDropdown">
            <a class="dropdown-item" href="#">帮助中心</a>
            <a class="btn dropdown-item" data-toggle="modal" data-target="#feed-back"
               data-whatever="@mdo">意见反馈</a>
            <div class="dropdown-divider"></div>
            <a class="dropdown-item" href="#">加入我们</a>

          </div>
        </li>
        <li class="nav-item">
          <a class="nav-link" style="cursor:pointer" data-toggle="modal" data-target=".bd-example-modal-sm">联系我们</a>
          <div class="modal fade bd-example-modal-sm" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-sm">
              <div class="modal-content" style="background: rgba(66,66,66,0.6);color:#ffffff">
                <span>官方QQ群725099517</span>
                <span>701855339</span>
                <span>725107181</span>
                <span>725107119</span>
                <span>客服QQ：800059038</span>
                <span>电话：4001782233</span>
              </div>
            </div>
          </div>
        </li>

        <!--搜索-->
        <form action="#" class="form-inline my-2 my-lg-0" style="margin-right: 20px">
          <input class="form-control mr-sm-2" type="search" name="searchCourse" id="searchCourse"
                 placeholder="课程搜索" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="button" @click="searchCourse()">搜索
          </button>
        </form>
        <!--搜索-->
        <form action="#" class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="search" name="searchResource" id="searchResource"
                 placeholder="资源搜索" aria-label="Search">
          <button class="btn btn-outline-success my-2 my-sm-0" type="button" @click="searchResource()">搜索
          </button>
        </form>
      </ul>

      <button type="button" class="btn btn-outline-primary" id="login_button" data-toggle="modal"
              data-target="#login" style="margin-right: 8px" @click="loginRememberMe">登录
      </button>
      <button type="button" class="btn btn-outline-primary" id="registered_button" data-toggle="modal"
              data-target="#registered">注册
      </button>
      <!--被隐藏的个人中心按钮，登录成功后会显示-->
      <button type="button" class="btn btn-outline-primary" id="user_center" style="display: none"
              @click="userCenter">个人中心
      </button>
      <!--被隐藏的教师中心按钮，登陆成功后会显示-->
      <button type="button" class="btn btn-outline-primary" id="teacher_center" style="display: none"
              @click="teacherCenter">教师中心
      </button>
      <!--</a>-->

    </div>
  </nav>
  <div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
       aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalCenterTitle">登录-学思网</h5>
          <button type="button" id="lClose" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form action="#" method="post" name="login_from" id="login_from">
            <div class="form-group">
              <label for="lAccount">账号</label>
              <input type="email" class="form-control" id="lAccount"
                     placeholder="手机号码/id/邮箱">
            </div>
            <div class="form-group">
              <label for="lPassWord">密码</label>
              <input type="password" class="form-control" id="lPassWord"
                     placeholder="Password">
            </div>

            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" id="loginRememberMe" name="loginRememberMe">
              <label class="custom-control-label" for="loginRememberMe" data-toggle="collapse"
                     aria-expanded="false" id="lrm">记住我</label>
            </div>
            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" id="loginTeacher" name="loginTeacher">
              <label class="custom-control-label" for="loginTeacher" data-toggle="collapse"
                     aria-expanded="false" id="lt">我是教师</label>
            </div>

            <div class="modal-footer">
              <input type="button" class="btn btn-primary" name="submit_login_form" id="submit_login_form"
                     value="登录" @click="submitLogin()">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <!--注册模块-->
  <div class="modal fade" id="registered" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle"
       aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title">注册-学思网</h5>
          <button type="button" id="rClose" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form action="#" method="post" id="registered_from">

            <div class="collapse"  id="collapseExample">
              <div class="form-group">
                <label for="rTeacherNmae">你的姓名</label>
                <input type="email" class="form-control" id="rTeacherNmae"
                       placeholder="请填写你的真实姓名">
              </div>

            <div class="form-group">
                <label for="rTeachersSchool">任职学校</label>
                <input type="email" class="form-control" id="rTeachersSchool"
                       placeholder="请填写你的任职的学校">
              </div>

            <div class="form-group">
                <label for="rTeacherIDcard">身份证号</label>
                <input type="email" class="form-control" id="rTeacherIDcard"
                       placeholder="请填写你的身份证号">
              </div>

            <div class="form-group">
                <label for="rTeacherQualification">教师资格证号</label>
                <input type="email" class="form-control" id="rTeacherQualification"
                       placeholder="请填写你的教师资格证号">
              </div>

            <div class="form-group">
                <label for="rTeacherTel">电话</label>
                <input type="email" class="form-control" id="rTeacherTel"
                       placeholder="请填写你的联系电话">
              </div>

            <div class="form-group">
                <label for="rTeacherSchoolEmail">所属学校邮箱</label>
                <input type="email" class="form-control" id="rTeacherSchoolEmail"
                       placeholder="请填写你所属学校的邮箱">
              </div>
            </div>

            <div class="form-group">
              <label for="rUserEmail">你的邮箱</label>
              <input type="email" class="form-control" name="rUserEmail" id="rUserEmail"
                     placeholder="请填写你自己的邮箱">
            </div>

            <div class="form-group">
              <label for="rUserPassWord">密码</label>
              <input type="password" class="form-control" name="rUserPassWord" id="rUserPassWord"
                     placeholder="Password">
            </div>

            <div class="form-group">
              <label for="rUserPassWordR">密码确认</label>
              <input type="password" class="form-control" name="rUserPassWordR" id="rUserPassWordR"
                     placeholder="Password">
            </div>

            <div class="custom-control custom-checkbox">
              <input type="checkbox" class="custom-control-input" id="registeredTeacher"
                     name="registeredTeacher">
              <label class="custom-control-label" for="registeredTeacher" data-toggle="collapse"
                     href="#collapseExample" aria-expanded="false"
                     aria-controls="collapseExample">我是教师</label>
            </div>

            <div class="modal-footer">
              <input type="button" class="btn btn-primary" name="submit_registered_form"
                     id="submit_registered_form" value="注册" @click="submitRegistered()">
            </div>
          </form>
        </div>
      </div>
    </div>
  </div>
  <!--【意见反馈】窗口-->
  <div class="modal fade" id="feed-back" tabindex="-1" role="dialog" aria-labelledby="feed-back" aria-hidden="true">
    <div class="modal-dialog" role="document">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">意见反馈</h5>
          <button type="button" class="close" data-dismiss="modal" aria-label="Close" id="FeedbackColse">
            <span aria-hidden="true">&times;</span>
          </button>
        </div>
        <div class="modal-body">
          <form id="Feedback" name="Feedback">
            <div class="form-group">
              <label for="message-text" class="col-form-label">告诉我们的不足:</label>
              <textarea class="form-control" name="message-text" id="message-text"></textarea>
            </div>
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
          <button type="button" class="btn btn-primary" @click="feedBack()">提交反馈</button>
        </div>
      </div>
    </div>
  </div>
  </div>
</template>

<script>
import global from './common.vue'
export default {
  name: "Top",
  methods:{
    //注册数据检查
    registeredCheck() {
      var em = /^[0-9a-zA-Z]{4 ,12}@[0-9a-zA-Z]{1,8}.[a-zA-Z]{2,6}$/;
      var te = /^[0-9a-zA-Z]{11}$/;
      if ($("#registeredTeacher")[0].checked) {
        if ($("#rTeacherNmae").val() == "") {
          alert("教师姓名不可为空！");
          return false;
        }
        if ($("#rTeachersSchool").val() == "") {
          alert("所属学校不可为空！");
          return false;
        }
        if ($("#rTeacherIDcard").val() == "") {
          alert("身份证号码不可为空！");
          return false;
        }
        if ($("#rTeacherTel").val() == "") {
          alert("电话号码不可为空！");
          return false;
        }
        if (!(te.test($("#rTeacherTel").val()))) {
          alert("电话号码格式错误！");
          return false;
        }
        if ($("#rTeacherQualification").val() == "") {
          alert("教师资格证号不可为空！");
          return false;
        }
        if ($("#rTeacherSchoolEmail").val() == "") {
          alert("所属学校邮箱不可为空！");
          return false;
        }
        //判断邮箱是否合法
        if (!(em.test($("#rTeacherSchoolEmail").val()))) {
          alert("所属学校邮箱格式不正确!");
          return false;
        }
      }
      //判断邮箱是否为空
      if ($("#rUserEmail").val() == "") {
        alert("邮箱不可为空");
        return false;
      }
      //判断邮箱是否合法
      if (!(em.test($("#rUserEmail").val()))) {
        alert("你的邮箱格式不正确!");
        return false;
      }
      //判断密码是否为空
      if ($("#rUserPassWord").val() == "") {
        alert("密码不可为空!");
        return false;
      }
      //判断确认密码是否为空
      if ($("#rUserPassWordR").val() == "") {
        alert("确认密码不可为空!");
        return false;
      }
      //判断两次密码输入是否一致
      if ($("#rUserPassWord").val() != $("#rUserPassWordR").val()) {
        alert("两次密码输入是否一致!");
        return false;
      }
      return true;
    },
    //登录按钮响应
    submitLogin() {
      console.log(">>>"+global.httpUrl);

      //数据检查
      var loginMode = loginCheck();
      var that = this;

      //用户登录类别标记
      var userClass = false;
      //判断是否为教师
      if ($("#loginTeacher")[0].checked) {
        userClass = true;
      }
      //判断是否选中【记住密码】
      var remember = false;
      if ($("#loginRememberMe")[0].checked) {
        remember = true;
      }
      //数据处理
      var post_method = {"account" : $("#lAccount").val() ,
        "password" : $("#lPassWord").val() ,
        "loginMode" : loginMode ,
        "loginRememberMe" : remember ,
        "userClass" : userClass};
      $.ajax({
        type : 'POST',
        xhrFields: {
          withCredentials: true
        },
        crossDomain:true, //设置跨域为true
        url : global.httpUrl+"/login",
        data :post_method,
        dataType : 'json',
        success : function(json) {
          if(json.state){
            console.log("登录成功");
            if(json.type==1){
              that.teacher_login_registered_successful();
            }else if(json.type==0){
              that.user_login_registered_successful();
            }
          }else{
            alert("登录失败,请检查账号和密码再试!")
          }
          //GMStatHandler(json.sign);
        },
        error : function(xhr, status, error) {
          console.log("状态码:" + xhr.status + ",状态:" + xhr.readyState
            + ",错误信息:" + xhr.statusText + ",请求状态:" + status
            + ",error:" + error);
        }
      });
    },
    //注册按钮响应
    submitRegistered() {
      /**
       * 对注册用户信息进行检查，判断是否符合要求
       **/
      if(this.registeredCheck()){
        /**
         * 处理注册信息
         **/
        var post_method = {"userEmail" : $("#rUserEmail").val(),
          "userPassword" : $("#rUserPassWord").val()};

        if ($("#registeredTeacher")[0].checked) {
          console.log("go?");
          post_method = {"userEmail" : $("#rUserEmail").val(),
            "userPassword" : $("#rUserPassWord").val(),
            "teacherName" : $("#rTeacherNmae").val() ,
            "teachersSchool" : $("#rTeachersSchool").val() ,
            "teacherIDcard" : $("#rTeacherIDcard").val() ,
            "teacherQualification": $("#rTeacherQualification").val() ,
            "teacherTel" : $("#rTeacherTel").val() ,
            "teachersSchoolEmail" : $("#rTeacherSchoolEmail").val() ,
            "profession" : $("#registeredTeacher")[0].checked};
        }
        console.log("checked:"+$("#registeredTeacher")[0].checked+",post_method:"+post_method);
        $.ajax({
          url: global.httpUrl+"/registered",
          data:post_method,
          type: 'post',
          dataType:'json',
          success: function (json) {
            console.log("!"+json.result);
            if(json.result){
              alert("注册成功!");
            }else{
              alert("注册失败!");
            }
            user_registered_successful();
          },
          error: function (err) {
            alert("退出错误!");
          }
        });
      }
    },
    /**
     * 点击首页上的登录按钮时立即检测cookie，是否之前勾选了记住密码
     * 若勾选了则将cookie储存的id、密码、是否勾选【教师】选项等信息填入表单和session中
     */
    loginRememberMe() {
      $.ajax({
        xhrFields: {
          withCredentials: true
        },
        crossDomain:true, //设置跨域为true
        url: global.httpUrl+"/cookieExamine",
        type: 'POST',
        dataType: 'json',
        success: function (json) {
          if (json.state) {
            // alert("你记住了密码");
            $("#lAccount").val(json.id);
            $("#lPassWord").val(json.password);
            $("#loginRememberMe").click();
            if (json.teacher) {
              $("#loginTeacher").click();
            }
          } else {
            alert(json.message);
          }
        },
        error: function () {
          alert("记住密码检测失败!");
        }
      });
    },

    //数据返回后端处理结果反馈给用户，便于指导用户正确操作
    //100-199:状态相关(登陆/注册成功)
    //200-299：账号相关(Id/邮箱/电话号码检测)
    //300-399：密码相关(密码检测)
    //400-499:个人信息相关(信息修改)
    StatHandler() {
      if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
        var msg = xmlHttp.responseText;
        if (msg == 101) {
          this.user_login_registered_successful();
        } else if (msg == 102) {
          alert("注册成功！");
          //清空并关闭注册弹窗

          //学生注册成功时即为已登录状态
          user_registered_successful();
        } else if (msg == 103) {
          alert("你已安全退出！");
          location.href = '/Home';
        } else if (msg == 104) {
          // alert("账号存在不用登录!");
          this.user_login_registered_successful();
        } else if (msg == 105) {
          // alert("请登录或注册!");
        } else if (msg == 106) {
          alert("账号异常,请登录!");
        } else if (msg == 107) {
          alert("注册提交成功，请等待审核！");
          user_registered_successful();
        } else if (msg == 201) {
          alert("邮箱不可为空!");
        } else if (msg == 202) {
          alert("邮箱格式不正确!");
        } else if (msg == 203) {
          alert("该邮箱已注册，请登录!");
        } else if (msg == 204) {
          alert("不存在该账号，请检查账号或注册!");
        } else if (msg == 205) {
          alert("账号格式有误，请检查账号!");
        } else if (msg == 301) {
          alert("密码不可为空!");
        } else if (msg == 302) {
          alert("确认密码不可为空!");
        } else if (msg == 303) {
          alert("密码不一致!");
        } else if (msg == 304) {
          alert("请按要求输入密码!");
        } else if (msg == 305) {
          alert("新密码与旧密码不可以相同!");
        } else if (msg == 306) {
          alert("新密码不可以使用曾今使用过的密码!");
        } else if (msg == 401) {
          alert("信息保存失败!");
        } else if (msg == 403) {
          alert("密码修改成功!");
          exit();
        } else if (msg == 404) {
          alert("密码修改失败!");
        } else if (msg == 501) {
          alert("你的账号正在审核中，请耐心等待!");
          user_registered_successful();
        } else if (msg == 502) {
          alert("欢迎回来!");
          this.user_login_registered_successful();
        } else if (msg == 503) {
          alert("你的账号已被冻结，请联系管理员!");
        } else if (msg == 504) {
          alert("你的账号已注销!");
        } else if (msg == 505) {
          alert("未知错误!" + msg);
        } else if (msg == 506) {
          alert("你的账号已注销!");
        } else if (msg == 507) {
          alert("此教师号!");
          this.teacher_login_registered_successful();
        } else if (msg == 0) {
          alert("修改成功!");
        } else {
          alert("未知错误!" + "6666" + msg);
        }
      } else {
      }
    },

    //普通登录成功后清除账号登录信息关闭登录窗口以及灰色底层，然后进入exist()函数
    user_login_registered_successful() {
      $("#lAccount").val('');
      $("#lPassWord").val('');
      close_window();//登录或注册成功后关闭注册窗口和灰色底层，并将登录按钮和注册按钮隐藏
      document.getElementById("user_center").style = "display:";//按钮替换
    },

    //教师登录成功后关闭登录窗口以及灰色底层，然后进入exist()函数
    teacher_login_registered_successful() {
      close_window();//登录或注册成功后关闭注册窗口和灰色底层，并将登录按钮和注册按钮隐藏
      document.getElementById("teacher_center").style = "display:";//按钮替换
    },

    //连接到用户类别对应的个人中心
    userCenter() {
      location.href = '/StudentCenter';
    },

    teacherCenter() {
      location.href = '/TeacherCenter';
    },

    //提交反馈意见
    feedBack() {
      //反馈意见表单提交
      // var form = document.getElementById("Feedback");
      // var fd = new FormData(form);
      var messageText=$("#message-text").val();
      console.log("messageText:"+messageText);
      $.ajax({
        url: global.httpUrl+"/saveFeedBack",
        type: 'POST',
        data: {'messageText': messageText},
        // processData: false,  // 告诉jQuery不要去处理发送的数据    它会使 注解方式获取数据 失效!
        // contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
        success: function (response, status, xhr) {
          alert("意见反馈成功!");
          console.log(response);
          var json = $.parseJSON(response);
          console.log(json);
          $("#FeedbackColse").click();
        },
        error: function () {
          alert("意见反馈失败!");
        }
      });
    },

    //课程搜索
    searchCourse() {
      var key = $("#searchCourse").val();
      location.href = encodeURI("/SearchCourse?key=" + key);
    },

    //资源搜索
    searchResource() {
      var key = $("#searchResource").val();
      location.href = encodeURI("/SearchResource?key=" + key);
    },
  },

  mounted() {
    var that = this;
    // 页面加载完后立即自动执行
// 判断是否已登录
    $(function () {
      $.ajax({
        url: global.httpUrl+'/userState',
        type: 'POST',
        dataType:'json',
        success: function (response) {
          if(response.state){
            alert("登录成功!");
            if(response.teacher){
              that.teacher_login_registered_successful();
            }else{
              that.user_login_registered_successful();
            }
          }else{
            alert("未登录!");
          }
        },
        error:function(xhr,status,error){
          alert("判断是否已登录校验失败!"+global.httpUrl);
        }
      });
    });
  },

}

$(function () {
  $('[data-toggle="popover"]').popover()
})


//课程搜索
function search_course() {
  var key = $("#searchCourse").val();
  location.href = encodeURI("search_course.html?key=" + key);
}

//资源搜索
function search_resource() {
  var key = $("#searchResource").val();
  location.href = encodeURI("search_resource.html?key=" + key);
}

// 登录数据检查
function loginCheck() {
  var em = /^[0-9a-zA-Z]{4,12}@[0-9a-zA-Z]{1,8}.[a-zA-Z]{2,6}$/;
  var te = /^[0-9a-zA-Z]{11}$/;
  var id = /^[0-9]{4,8}$/;
  if ($("#lAccount").val() == "") {
    alert("账号不可为空！");
    return false;
  }

  if (em.test($("#lAccount").val())) {
    return 1;
  } else if (te.test($("#lAccount").val())) {
    return 2;
  } else if (id.test($("#lAccount").val())) {
    return 3;
  } else {
    alert("账号格式错误！");
    return false;
  }

  if ($("#lPassWord").val() == "") {
    alert("密码不可为空！");
    return false;
  }

}

//学生注册成功后清除账号注册信息关闭注册窗口以及灰色底层，然后进入exist()函数
function user_registered_successful() {
  close_registered_window();//登录或注册成功后关闭注册窗口和灰色底层，并将登录按钮和注册按钮隐藏
}

//注册成功后关闭窗口和灰色底层，并将登录按钮和注册按钮隐藏
function close_registered_window() {

  /**
   * 调用方法虽能一次性清除表单内容，但也会使注册窗口发生异常，当教师注册后再点击注册弹出注册窗口，模式注册教师模式但【我是教师】已被取消
   * 解决办法：在清除表单之前获取【我是教师】状态，清除之后再恢复之前状态
   **/
  var sign=false;
  if ($("#registeredTeacher")[0].checked) {
    sign=true;
  }
  document.getElementById("registered_from").reset();//重置窗口数据

  if(sign==true){
    $("#registeredTeacher")[0].checked=true;
  }
  document.getElementById("rClose").click();//关闭登录灰色底层
}

//登录或注册成功后关闭窗口和灰色底层，并将登录按钮和注册按钮隐藏
function close_window() {

  document.getElementById("login_from").reset();//重置窗口数据
  document.getElementById("lClose").click();//关闭登录灰色底层
  document.getElementById("login_button").style = "display:none;";//隐藏登录按钮
  document.getElementById("registered_button").style = "display:none;";//隐藏注册按钮
}



</script>

<style scoped>

</style>
