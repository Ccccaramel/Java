<template>
  <div>
  <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
    <div class="jumbotron">
      <h1 class="display-4" id="testName">{{test.testName}}</h1>
      <hr class="my-4">
      <div class="container">
        <div class="row">
          <div class="col-sm" id="courseName">
            课程: {{course.courseName}}
          </div>
          <div class="col-sm" id="userId">
            用户id: {{ student.userId }}
          </div>
          <div class="col-sm" id="userName">
            姓名: {{ student.userName }}
          </div>
          <div class="col-sm" id="totalScore">
            总分: {{ test.totalScore }}分
          </div>
          <div class="col-sm" id="passLine">
            及格线:{{ test.passLine }}分
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container" style="padding-left:0px;padding-right:0px;margin-bottom: 16px">
    <form name="submits_test" id="submits_test">
      <div class="alert alert-info" role="alert" style="width:100%;margin-bottom: 0px">
        测试题目
      </div>
      <div id="problem">

        <div :id="'problem-' + (i+1)" v-for="(question,i) in questionList">

          <!-- 题目问题,分值 -->
          <div class="input-group" disabled style="margin-bottom: 8px">
            <div class="input-group-prepend">
              <button class="btn btn-dark" disabled>第{{ (i+1) }}题</button>
            </div>
            <input type="hidden" :id="'problemId-' + (i+1)" :name="'problemId-' + (i+1)" :value="question.questionId">
            <div class="form-control" :id="'question-' + (i+1)" :name="'question-' + (i+1) ">{{ question.questionContent }}</div>

            <div class="input-group-append">
              <button class="btn btn-secondary" disabled>分值</button>
            </div>
            <div class="input-group-append">
              <button class="btn btn-outline-secondary" disabled :id="'question-' + (i+1) + '-score'" :name="'question-' + (i+1) + '-score'">{{question.score}}</button>
            </div>
          </div>

          <!-- 题目选项 -->
          <div class="input-group" style="margin-bottom: 6px" v-for="j in 4">
            <div class="input-group-prepend">
              <button class="btn btn-outline-dark" disabled>{{ letter(j) }}</button>
            </div>
            <div class="form-control" :id="'question-' + (i+1) + '-option-'+j" :name="'question-' + (i+1) + '-option-'+j">
              {{ question.testOptions[j-1].optionContent }}
            </div>
          </div>


          <!-- 考生选项 -->
          <div class="input-group" style="margin-bottom: 6px">
            <div class="input-group-prepend">
              <button class="btn btn-outline-primary" disabled>你的选择</button>
            </div>
            <div class="input-group-append">
              <select class="custom-select" :id="'question-' + (i+1) + '-user-key'" :name="'question-' + (i+1) + '-user-key'">
                <option :value="k" :selected="isTest?k==1:k==question.userKey" v-for="k in 4">{{ letter(k) }}</option>
              </select>
            </div>
          </div>
          <div class="input-group" v-if="question.userIsRight && !isTest">
            <div class="alert alert-success" role="alert">回答正确!</div>
          </div>
          <div class="input-group" style="margin-bottom: 6px" v-if="!question.userIsRight && !isTest">
            <div class="input-group-prepend">
              <button class="btn btn-outline-danger" disabled>正确答案</button>
            </div>
            <div class="input-group-prepend">
              <button class="btn btn-outline-danger" disabled :id="'question-' + (i+1) + '-rightKey'" :name="'question-' + (i+1) + '-rightKey'">{{ letter(question.rightKey) }}</button>
            </div>
          </div>
          <div class="input-group" v-if="!isTest">
            <div class="input-group-prepend">
              <button class="btn btn-outline-primary" disabled>解析</button>
              </div>
            <div class="form-control" :id="'question-' + (i+1) + '-analysis'" :name="'question-' + (i+1) + '-analysis'">{{question.analysis}}</div>
          </div>
          <hr/>
        </div>
        <hr>
      </div>

      <!--隐藏域-->
      <input type="hidden" id="testId" name="testId" :value="test.testId">
      <input type="hidden" id="courseId" name="courseId" :value="course.courseId">
      <input type="hidden" id="hTotalScore" name="hTotalScore" :value="test.totalScore">
      <input type="hidden" id="hPassLine" name="hPassLine" :value="test.passLine">

    </form>
    <input class="btn btn-primary" type="button" id="submitsPapers" name="submitsPapers" @click="submitsPapers()"
           value="提交" v-if="isTest">
  </div>
  </div>
</template>

<script>
export default {
  name: "Test",
  data(){
    return{
      course:{},
      testId:0,
      answerSheetId:'',
      test:{},
      student:{},
      opt:{
        1:'A',
        2:'B',
        3:'C',
        4:'D',
      },
      questionList:[],
      isTest:true,
    }
  },
  computed:{
    letter() {
      return function (i) {
        if (i == 1) {
          return 'A';
        } else if (i == 2) {
          return 'B';
        } else if (i == 3) {
          return 'C';
        } else if (i == 4) {
          return 'D';
        }
      }
    },
  },
  methods:{
    //保存考试
    submitsPapers() {
      //表单提交
      var form = document.getElementById("submits_test");
      var fd = new FormData(form);
      $.ajax({
        url: 'http://localhost:8080/savePapers',
        type: 'post',
        dataType: 'json',
        data: fd, testId, courseId, totalScore,
        processData: false,  // 告诉jQuery不要去处理发送的数据
        contentType: false,   // 告诉jQuery不要去设置Content-Type请求头
        success: function (json) {
          //提交成功后提示“交卷成功，请前往个人中心查看成绩”，然后自动关闭当前页面
          alert("试卷提交成功，请前往个人中心查看成绩");
          window.close();
        },
        error: function () {
          alert("试卷提交失败");
        },
      });
    },

    checkAnswerSheetId() {
      var that=this;
      //存在答题卡id，用户想要查看答卷和题目解析
      if (typeof (that.answerSheetId) != 'undefined') {
        that.isTest=false;
        $.ajax({
          url: 'http://localhost:8080/getUserAppointTestAnswer',
          type: 'post',
          dataType: 'json',
          data: {'testId': that.testId, 'answerSheetId': that.answerSheetId},
          success: function (json) {
            console.log(json);

            //获取result数组，取出题目序号、正确答案、解析、用户答案、结果是否正确(0:错误，1:正确)、小题id
            var question = json.results;
            $.each(question, function (index, array) {
              var num = array['questionNumber'];
              // alert("小题=" + num);
              // alert("userKey=" + array['userKey']);

              //将选择菜单的选项更改为用户的选项
              $("#question-" + array['questionNumber'] + "-user-key").val(array['userKey']);

              //创建
              var data = "";

              if (array['userAnswerResult'] == 1) {
              } else {
                console.log("userAnswerResult-0:"+array['userAnswerResult']);
                data += "<div class=\"input-group\" style=\"margin-bottom: 6px\">" +

                  "<div class=\"input-group-prepend\">" +
                  "<button class=\"btn btn-outline-danger\" disabled>正确答案</button>" +
                  "</div>" +

                  "<div class=\"input-group-prepend\">" +
                  "<button class=\"btn btn-outline-danger\" disabled id=\"question-" + num + "-rightKey\" name=\"question-" + num + "-rightKey\">" +
                  "</button>" +
                  "</div>" +
                  "</div>";
              }
              data += "<div class=\"input-group\">" +
                "<div class=\"input-group-prepend\">" +
                "<button class=\"btn btn-outline-primary\" disabled>解析</button>" +
                "</div>" +
                "<div class=\"form-control\" id=\"question-" + num + "-analysis\" name=\"question-" + num + "-analysis\">" +
                //array['analysis'] +
                "</div>" +
                "</div>";

              $("#problem-" + num).append(data);

              $("#question-" + num + "-rightKey").text(number_to_letter(array['rightKey']));
              $("#question-" + num + "-analysis").text(array['analysis']);
            });
          },
          error: function () {
            alert("还原答题卡失败，非法进入!");
            window.open("home.html","_self").close();
          }
        });
      }
    },
  },
  mounted() {
    var that=this;
    //加载测试卷的所有信息
    $(function () {
      //testId存有测试卷id，立即获取测试卷所有信息---课程id、课程名、测试卷总分值、测试卷及格线、测试卷的所有小题以及小题相应的选项、分值、正确答案、解析
      $.ajax({
        url: 'http://localhost:8080/getTest',
        type: 'post',
        dataType: 'json',
        data: {},
        success: function (json) {
          console.log(json);
          //判断是否已登录
          if(!json.status){
            alert("你未登录，非法进入!");
            window.open("/","_self").close();
          }

          that.testId = json.testId;
          that.answerSheetId = json.answerSheetId;
          that.test = json.test;
          that.student = json.student;
          that.course = json.course;
          that.questionList = json.question;
          that.isTest = json.isTest;
          //获取test数组，取出课程id、测试卷名称、总分、及格分
          if(that.test.testStatus!=1){
            alert("试卷已失效，非法进入!");
            window.open("/","_self").close();
          }

          // that.checkAnswerSheetId();  //再接着判断是否存在答题卡

        },
        error: function () {
          alert("测试卷加载错误");
        }
      });

    });
  },
}
</script>

<style scoped>

</style>
