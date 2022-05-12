<template>
  <div class="container">
    <!--课程测试卷表单-->
    <form name="add_test" id="add_test">
      <div class="alert alert-dark" role="alert"
           style="width:100%;margin-bottom: 16px;elevation: deg;font-weight: bold">
        <span>学思网-编辑测试卷</span>
      </div>

      <!--选择添加课程测试卷的课程id-->
      <div class="input-group mb-3">
        <!--选择课程项-->
        <div class="input-group-prepend">
          <button class="btn btn-dark" for="courseId" disabled>选择课程</button>
        </div>
        <select class="custom-select" id="courseId" name="courseId">
          <option :selected="p===0 || courseId===coursseInfo.courseId" :value="coursseInfo.courseId" :name="coursseInfo.courseId" v-for="(coursseInfo,p) in courseListCom">{{coursseInfo.courseId}}:&nbsp;{{coursseInfo.courseName}}</option>
        </select>
      </div>
      <!--为测试卷取名-->
      <div class="input-group" style="margin-bottom: 16px">
        <!--测试卷名称项-->
        <div class="input-group-prepend">
          <button class="btn btn-dark" disabled>测试卷名称</button>
        </div>
        <!--测试卷名-->
        <input type="text" aria-label="测试卷名称" id="testName" name="testName" class="form-control"
               placeholder="测试卷名称" :value="testName">
      </div>

      <!--上传测试卷表单控件-->
<!--      <div class="input-group" style="margin-bottom: 16px">-->
<!--        <div id="drop">请将已编辑好的测试卷(.xlsx文件)拖入此处(注：编辑表格内容时请在所有“,”前加入“~”)</div>-->

<!--        <input type="checkbox" name="useworker" hidden>-->
<!--        <input type="checkbox" name="userabs" hidden>-->
<!--      </div>-->

      <!--编辑测试卷-->
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px;font-weight: bold">
        编辑测试卷
      </div>
      <div id="problem">
        <div :id="'problem-'+questionNo" v-for="questionNo in questionTotalCom">
          <!--题目-->
          <div class="input-group" style="margin-bottom: 8px">
            <!--序号-->
            <div class="input-group-prepend">
              <button class="btn btn-dark" disabled>第{{questionNo}}题</button>
            </div>
            <!--题目文本-->
            <input type="text" aria-label="题目内容" :id="'question-'+questionNo" :name="'question-'+questionNo" class="form-control"
                   placeholder="题目内容" :value="questionListCom[questionNo-1]==null?'':questionListCom[questionNo-1].questionContent">
            <!--题目分值-->
            <div class="input-group-append">
              <button class="btn btn-outline-primary" disabled>分值</button>
            </div>
            <div class="input-group-append">
              <select class="custom-select" :id="'question-'+questionNo+'-score'" :name="'question-'+questionNo+'-score'"
                      @change="changeTotalScore()">
                <option v-for="j in 10" :value="j" :selected="j===1 || (questionListCom[questionNo-1]!=null && questionListCom[questionNo-1].score===j)">{{j}}</option>
              </select>
            </div>
          </div>
          <!--选项-->
          <div class="input-group" v-bind:class="chooseClass(j)" v-for="j in 4">
            <div class="input-group-prepend">
              <button class="btn btn-outline-dark" disabled>选项{{j}}</button>
            </div>
            <input type="text" aria-label="选项内容" :id="'question-'+questionNo+'-option-'+j" :name="'question-'+questionNo+'-option-'+j"
                   class="form-control" placeholder="请填写选项内容" :value="questionListCom[questionNo-1]==null?'':questionListCom[questionNo-1].testOptions[j-1].optionContent">
          </div>

          <!--正确选项-->
          <div class="input-group" style="margin-bottom: 6px">
            <div class="input-group-prepend">
              <button class="btn btn-outline-primary" disabled>正确选项</button>
            </div>
            <div class="input-group-append">
              <select class="custom-select" :id="'question-'+questionNo+'-right-key'" :name="'question-'+questionNo+'-right-key'">
                <option :value="n" :selected="questionListCom[questionNo-1]!=null?questionListCom[questionNo-1].rightKey==n:n===1" v-for="n in 4">{{ n }}</option>
              </select>
            </div>
          </div>

          <!--解析-->
          <div class="input-group">
            <div class="input-group-prepend">
              <button class="btn btn-outline-primary" disabled>解析</button>
            </div>
            <input type="text" aria-label="选项内容" :id="'question-'+questionNo+'-analysis'" :name="'question-'+questionNo+'-analysis'"
                   class="form-control" placeholder="请填写题目解析" :value="questionListCom[questionNo-1]==null?'':questionListCom[questionNo-1].analysis">
          </div>

          <hr>
        </div>

      </div>

      <!--总分-->
      <div class="form-group row">

        <label class="col-sm-1 col-form-label">总分:</label>

        <div class="input-group-prepend">
          <input type="text" id="totalScore" name="totalScore" class="form-control-plaintext" readonly
                 :value="totalScoreCom">
        </div>
      </div>

      <!--及格线-->
      <div class="input-group" style="margin-bottom: 6px">

        <div class="input-group-prepend">
          <button class="btn btn-outline-success" disabled>及格线</button>
        </div>
        <div class="input-group-append">
          <input type="text" id="passLine" name="passLine" class="form-control" value="1">
        </div>
      </div>

      <button type="button" class="btn btn-outline-info" @click="addQuestion()">再出一题</button>
      <button type="button" class="btn btn-outline-danger" @click="delQuestion()">删除最后一题</button>

      <!--隐藏域:告诉后台本次保存是添加新测试卷还是修改已存在的测试卷-->
      <!--操作类别-->
      <!--true:修改已有测试卷-->
      <!--false:添加新测试卷-->
      <input type="hidden" id="edit" name="edit" value="false">
      <input type="hidden" id="testId" name="testId" value="">

      <hr>
      <input class="btn btn-primary" type="button" @click="saveTest()" value="保存">
    </form>
  </div>
</template>

<script>
import global from './common.vue'
export default {
  name: "CourseTest",
  data(){
    return{
      edit:false,
      courseId:0,
      testName:'',
      totalScore:0,
      passLine:0,
      questionList:[],
      questionTotal:0,

      testId:0,
      courseList:{
      },
    }
  },
  computed:{
    editCom:function () {
      return this.edit;
    },
    testNameCom:function (){
      return this.testName;
    },
    questionTotalCom:function (){
      return this.questionTotal;
    },
    chooseClass(){
      return function (c) {
        if (c === 4) {
          return 'sty1';
        } else {
          return "sty2";
        }
      }
    },
    courseListCom:function () {
      return this.courseList;
    },
    questionListCom:function (){
      return this.questionList;
    },
    totalScoreCom:function (){
      return this.totalScore;
    }
  },
  methods:{
    //增添题目
    addQuestion() {
      this.questionTotal++;
      this.totalScore++;
      // this.changeTotalScore();
    },

    //删除最后一个小题
    delQuestion() {
      var s=parseInt($("#question-" + this.questionTotal + "-score").val());
      console.log("s"+s);
      this.totalScore=this.totalScore-s;
      this.questionTotal--;
      // this.changeTotalScore();
    },

    // 增删小题或修改小题分数时
    changeTotalScore(){

      var totalScore = 0;
      for (var n = 1; n<=this.questionTotal; n++) {
        console.log("totalScore:"+totalScore);
        totalScore += parseInt($("#question-" + n + "-score").val());
      }
      this.totalScore=totalScore;
      // $("#totalScore").val(totalScore);

      //更新推荐及格分
      var res = totalScore * 0.6;
      $("#passLine").val(res.toFixed(0));
      console.log("res:"+res);
    },

    //加载课程菜单下拉框
    addCourseMenu() {
      var that=this;
      //将课程id和中文名放入下拉菜单中
      $.ajax({
        url: global.httpUrl+'/getCourseIdName',
        type: 'post',
        dataType: 'json',
        success: function (json) {
          that.courseList=json.courseList;
        },
        error: function () {
          alert("课程下拉框错误");
        }
      });
    },

    //点击保存测试卷按钮
    saveTest() {
      /**
       * 检查表单，表单不可有空
       **/
      if (!this.check()) {
        return;
      }

      //有测试id传入，编辑测试卷
      if (this.testId !== -1) {
        //再次确认
        if (window.confirm("确认保存更改吗?这将会导致已测试的学生无法查看测试卷结果以及题目解析")) {
          //表单提交
          this.submitTest();
        }
      } else {
        this.submitTest();
      }
    },

    //保存测试卷
    submitTest() {
      var form = document.getElementById("add_test");
      var fd = new FormData(form);
      $.ajax({
        url: global.httpUrl+"/saveTest",
        type: 'POST',
        data: fd,
        processData: false,  // 告诉jQuery不要去处理发送的数据
        contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
        success: function () {
          alert("保存成功!");
        },
        error: function () {
          alert("上传失败!");
        }
      });
      return false;
    },


    //提交前对数据进行检查
    check() {
      //判断测试卷名称是否为空
      if ($("#testName").val() === "") {
        alert("课程名称不可为空！")
        return false;
      }

      //检测题目是否为空
      for (var que = 1; $("#question-" + que).length > 0; que++) {
        if ($("#question-" + que).val() === "") {
          alert("第" + que + "小题题目不可为空!");
          return false;
        }
        for (var opt = 1; opt <= 4; opt++) {
          if ($("#question-" + que + "-option-" + opt).val() === "") {
            alert("第" + que + "题第" + opt + "选项不可为空!");
            return false;
          }
        }
        if ($("#question-" + que + "-analysis").val() === "") {
          alert("第" + que + "小题解析不可为空!");
          return false;
        }
      }

      //判断及格线是否合乎要求
      if (parseInt($("#passLine").val()) >= parseInt($("#totalScore").val())) {
        alert("及格线设置不规范，请参考总分以及试卷难易度进行设置");
        return false;
      }

      return true;

    },

    /**
     * 判断用户是如何进入此页面
     * 1.登录后通过【教师中心】合法进入
     * 2.直接输入url进入---首先无法获取课程列表，提交时后台无法正常运行
     * 3.用户进入后修改url进入---会将其他教师的测试卷更改为自己的测试卷
     * 解决方法
     * 1.检查session，确认教师身份
     * 2.获取教师id，检查此testid是否属于此教师
     * 检查通过后才能继续操作
     * 否则关闭当前页面
     **/
    checkIdentitl(testId) {
      $.ajax({
        url: global.httpUrl+'/editTestIdentitlCheck',
        type: 'post',
        dataType: 'json',
        data: {'testId': testId},
        success: function (json) {
          if (json.status === false) {
            window.open("/", "_self").close();
          }
        },
        error: function () {
          alert("账号检测错误");
        }
      });
    },

    // 加载原测试卷内容
    loadingTest(testId) {
      var that=this;
      $.ajax({
        url: global.httpUrl+'/getTestIntroduction',
        type: 'post',
        dataType: 'json',
        data: {'testId': testId},
        success: function (json) {
          console.log(json);

          var test = json.test;
          //获取test数组，取出课程id、测试卷名称、总分、及格分
          // $.each(test, function (index, array) {
            //获取课程id
            that.courseId = test.courseId;
            // alert("-"+courseId);
            //将课程名称填入表单
            that.testName=test.testName;
            // $("#testName").val(array['testName']);
            //获取总分
            that.totalScore = test.totalScore;
            //将总分填入表单
            // $("#totalScore").val(test.totalScore);
            //获取及格分
            // that.passLine = test.passLine;
            //将及格分填入表单
            $("#passLine").val(test.passLine);
          // });
          //获取question数组，取出题目序号、问题内容、正确答案、解析、分值
          that.questionList = json.question;
          // 题目数量
          that.questionTotal = json.questionTotal;
          // for(var i=1;i<=that.questionTotal;i++){
          //   console.log(">>"+i+","+json.question[i-1].questionContent);
          //   $("#question-" + i).val(questionInfoList[i-1].questionContent);
          //   $("#question-" + i + "-analysis").val(questionInfoList[i-1].analysis);
          //   $("#question-" + i + "-score").val(questionInfoList[i-1].score);
          //   $("#question-" + i + "-right-key").val(questionInfoList[i-1].rightKey);
          // }
          // $.each(that.questionList, function (index, array) {
          //   var num = array['questionNumber'];
          //   //判断该小题元素是否存在
          //   if ($("#problem").length < num) {
          //     //不存在该小题的元素，则创建
          //     create_topic_components(num, "problem");
          //
          //   }
          //   $("#question-" + num).val(array['questionContent']);
          //   $("#question-" + num + "-analysis").val(array['analysis']);
          //   $("#question-" + num + "-score").val(array['score']);
          //   $("#question-" + num + "-right-key").val(array['rightKey']);
          //
          // });

          //获取option数组，取出选项序号、选项内容,将其添入对应元素中
          that.option = json.option;
          //获取课程简介
          // $.each(option, function (index, array) {
          //   $("#question-" + array['questionNumber'] + "-option-" + array['questionOption']).val(array['optionContent']);
          // });

        },
        error: function () {
          alert("测试卷内容加载错误");
        }
      });
    },
  },
  mounted() {
    var that=this;
    //进入此页面前检测url是否有testid传入，用以判断当前操作是修改测试卷还是添加新测试卷
    $(function () {

      //获取URL
      var url = document.URL;
      //分割URL,获取搜索框内的关键词
      that.testId = url.split("?testId=")[1];


      //如果有传进来测试卷id，则将该测试卷原有的所有相关信息填入表格，供教师修改、编辑
      if (typeof(that.testId) !== 'undefined') {

        that.checkIdentitl(that.testId);

        that.edit=true;

        //更改隐藏域相应的值，
        $("#edit").attr("value", "true");
        $("#testId").attr("value", that.testId);

        that.loadingTest(that.testId);
      }
      /**
       * 若是新添测试卷，【选择课程】选项会默认选第一个课程
       * 但若是编辑一个已存在的课程则需要获取这个测试卷的所属课程id，所以必须在获取该课程id后再加载【选择课程】列表并选定对应课程
       * 如果将这两步都异步同时加载有时【选择课程】会选中第一项，因为后者可能先运行而前者还未获取到课程id
       **/
      that.addCourseMenu();
    });
  }
}
</script>

<style scoped>
.sty1{
  margin-bottom: 6px;
}
.sty2{
  margin-bottom: 10px;
}
</style>
