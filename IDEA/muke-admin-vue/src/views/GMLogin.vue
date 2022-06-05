<template>
  <div id="aa">
    <div class="container" style="padding-left: 0px; padding-right: 0px">
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content" style="border: 0px">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalCenterTitle">MuKe-后台管理系统</h5>
          </div>
          <div class="modal-body">
            <form action="#" method="post" name="GMlogin_from" id="login_from">
              <div class="form-group">
                <label for="GMAccount">账号</label>
                <input type="email" class="form-control" id="GMAccount" placeholder="手机号码/Id/邮箱">
              </div>
              <div class="form-group">
                <label for="GMPassWord">密码</label>
                <input type="password" class="form-control" id="GMPassWord" placeholder="Password">
              </div>
              <div class="modal-footer">
                <input type="button" class="btn btn-primary" name="submit_GMlogin_form" id="submit_GMlogin_form" value="登录" @click="submitGMLogin">
              </div>
            </form>
          </div>
        </div>
      </div>
    </div>
  </div>

</template>

<!-- src="../assets/canvas" -->
<script>
import global from './Common.vue'
function check() {
  var id = /^[0-9]{4}$/;
  var te = /^[0-9]{11}$/;
  var em = /^[0-9a-zA-Z]{4,12}@[0-9a-zA-Z]{1,8}.[a-zA-Z]{2,6}$/;
  if ($("#GMAccount").val() == "") {
    alert("账号不可为空！");
    exit(0);
  }
  if ($("#GMPassWord").val() == "") {
    alert("密码不可为空！");
    exit(0);
  }
  if (id.test($("#GMAccount").val())) {
    return "id";
  } else if (te.test($("#GMAccount").val())) {
    return "tel";
  } else if (em.test($("#GMAccount").val())) {
    return "email";
  } else {
    alert("账号格式错误！");
    exit(0);
  }
}

export default {
  name: 'GMLogin',
  methods:{
    submitGMLogin() {
      var that=this;
      $.ajax({
        type : 'POST',
        url : global.httpUrl+"/gmLogin",
        data : {
          'account' : $("#GMAccount").val(),
          'password' : $("#GMPassWord").val(),
          'sign': check(),
        },
        dataType : 'json',
        success : function(json) {
          console.log("success");
          console.log("result>sign:" + json.sign+",state:"+json.state);
          that.GMStatHandler(json.sign);
        },
        error : function(xhr, status, error) {
          console.log("状态码:" + xhr.status + ",状态:" + xhr.readyState
            + ",错误信息:" + xhr.statusText + ",请求状态:" + status
            + ",error:" + error);
        }
      });
    },
    GMStatHandler(msg) {
      if (msg == 901) {
        alert("账号密码不可为空！");
      } else if (msg == 902) {
        alert("请检查你的账号是否正确!");
      } else if (msg == 903) {
        alert("登录失败，请检查账号或密码是否正确!");
      }else if (msg == 905) {
        alert("非法访问请登录!");
        // location.href = '/';
        this.$router.push("/");
      } else if (msg == 906) {
        alert("检测到可能session异常，请尝试重新登录!");
        // location.href = '/';
        this.$router.push("/");
      } else if (msg == 907) {
        //程序an无需额外操作
      } else if (msg == 908) {
        alert("已安全退出!");
        // location.href = '/';
        this.$router.push("/");
      } else if (msg == 910) {
        alert("修改失败！");
      } else if (msg == 911) {
        alert("账号待审核中");
      } else if (msg == 912) {
        alert("请尽快修改初始密码！");
        // location.href = '/ManagementSystem';
        this.$router.push("/ManagementSystem");
      } else if (msg == 913) {
        alert("账号已被冻结");
      } else if (msg == 914) {
        alert("账号已被注销");
      } else if (msg == 915) {
        alert("登陆成功!");
        // location.href = '/ManagementSystem';
        this.$router.push("/ManagementSystem");
      } else if (msg == 916) {
        alert("登录失败,请检查账号或密码是否正确!");
      } else {
        alert("请使用正常的账号登录");
        alert(msg);
      }
    },
  },
}
$(function () {
  $('[data-toggle="popover"]').popover()
})
//数据返回后端处理结果反馈给用户，便于指导用户正确操作
//100-199:状态相关(登陆/注册成功)
//200-299：账号相关(Id/邮箱/电话号码检测)
//300-399：密码相关(密码检测)
//400-499:个人信息相关(信息修改)
//*************************************************************
// 可调参数
var BACKGROUND_COLOR = "rgb(255,255,255)";   // 背景颜色
var POINT_NUM = 100;                        // 星星数目
var POINT_COLOR = "#66CCFF";  // 点的颜色
var LINE_LENGTH = 10000;                    // 点之间连线长度(的平方)

// 创建背景画布
var cvs = document.createElement("canvas");
cvs.width = window.innerWidth;
cvs.height = window.innerHeight;
cvs.style.cssText = "\
    position:fixed;\
    top:0px;\
    left:0px;\
    z-index:-1;\
    opacity:1.0;\
    ";
document.body.appendChild(cvs);

var ctx = cvs.getContext("2d");

var startTime = new Date().getTime();

//随机数函数
function randomInt(min, max) {
  return Math.floor((max - min + 1) * Math.random() + min);
}

function randomFloat(min, max) {
  return (max - min) * Math.random() + min;
}

//构造点类
function Point() {
  this.x = randomFloat(0, cvs.width);
  this.y = randomFloat(0, cvs.height);

  var speed = randomFloat(0.3, 1.4);
  var angle = randomFloat(0, 2 * Math.PI);

  this.dx = Math.sin(angle) * speed;
  this.dy = Math.cos(angle) * speed;

  this.r = 1.5;

  this.color = POINT_COLOR;
}

Point.prototype.move = function () {
  this.x += this.dx;
  if (this.x < 0) {
    this.x = 0;
    this.dx = -this.dx;
  } else if (this.x > cvs.width) {
    this.x = cvs.width;
    this.dx = -this.dx;
  }
  this.y += this.dy;
  if (this.y < 0) {
    this.y = 0;
    this.dy = -this.dy;
  } else if (this.y > cvs.height) {
    this.y = cvs.height;
    this.dy = -this.dy;
  }
}

Point.prototype.draw = function () {
  ctx.fillStyle = this.color;
  ctx.beginPath();
  ctx.arc(this.x, this.y, this.r, 0, Math.PI * 2);
  ctx.closePath();
  ctx.fill();
}

var points = [];

function initPoints(num) {
  for (var i = 0; i < num; ++i) {
    points.push(new Point());
  }
}

var p0 = new Point(); //鼠标
p0.dx = p0.dy = 0;
var degree = 2.5;
document.onmousemove = function (ev) {
  p0.x = ev.clientX;
  p0.y = ev.clientY;
}
document.onmousedown = function (ev) {
  degree = 5.0;
  p0.x = ev.clientX;
  p0.y = ev.clientY;
}
document.onmouseup = function (ev) {
  degree = 2.5;
  p0.x = ev.clientX;
  p0.y = ev.clientY;
}
window.onmouseout = function () {
  p0.x = null;
  p0.y = null;
}

function drawLine(p1, p2, deg) {
  var dx = p1.x - p2.x;
  var dy = p1.y - p2.y;
  var dis2 = dx * dx + dy * dy;
  if (dis2 < 2 * LINE_LENGTH) {
    if (dis2 > LINE_LENGTH) {
      if (p1 === p0) {
        p2.x += dx * 0.03;
        p2.y += dy * 0.03;
      } else return;
    }
    var t = (1.05 - dis2 / LINE_LENGTH) * 0.2 * deg;
    ctx.strokeStyle = "rgba(240,248,255," + t + ")";
    ctx.beginPath();
    ctx.lineWidth = 1.5;
    ctx.moveTo(p1.x, p1.y);
    ctx.lineTo(p2.x, p2.y);
    ctx.closePath();
    ctx.stroke();
  }
  return;
}

//绘制每一帧
function drawFrame() {
  cvs.width = window.innerWidth;
  cvs.height = window.innerHeight;
  ctx.fillStyle = BACKGROUND_COLOR;
  ctx.fillRect(0, 0, cvs.width, cvs.height);

  var arr = (p0.x == null ? points : [p0].concat(points));
  for (var i = 0; i < arr.length; ++i) {
    for (var j = i + 1; j < arr.length; ++j) {
      drawLine(arr[i], arr[j], 1.0);
    }
    arr[i].draw();
    arr[i].move();
  }

  window.requestAnimationFrame(drawFrame);
}

initPoints(POINT_NUM);
drawFrame();

</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
