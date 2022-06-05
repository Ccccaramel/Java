<template>
  <!--主体部分 style="width:100%;margin-bottom: 16px;elevation: deg;font-weight: bold"-->
  <div class="container" style="padding-left:10px;max-width:1800px;padding-right:10px;margin-bottom: 16px">
    <div class="alert alert-info" role="alert">
      <span>学思网-后台管理中心</span>
    </div>
    <div class="row">
      <div class="col-1">
        <div class="list-group" id="gm-tab" role="tablist" aria-orientation="vertical">
          <a class="list-group-item list-group-item-action active" id="v-pills-home-tab" data-toggle="pill"
             href="#v-pills-home" role="tab"
             aria-controls="v-pills-home" aria-selected="true" style="border: 0px">管理员信息</a>
          <a class="list-group-item list-group-item-action" id="v-pills-changePassword-tab" data-toggle="pill"
             href="#v-pills-changePassword"
             role="tab"
             aria-controls="v-pills-profile" aria-selected="false" style="border: 0px">修改密码</a>
          <hr>

          <!--课程管理-->
          <a class="list-group-item list-group-item-action" id="v-pills-course-tab" data-toggle="pill"
             href="#v-pills-course" role="tab"
             aria-controls="collapseExample" aria-selected="false" style="border: 0px"
             @click="course(0);">课程管理</a>
          <!--课程资源管理-->
          <a class="list-group-item list-group-item-action" data-toggle="pill"
             href="#v-pills-course-resource" role="tab"
             aria-controls="collapseExample" aria-selected="false" style="border: 0px"
             @click="courseResource(0);">课程资源管理</a>
          <!--课程类型管理-->
          <a class="list-group-item list-group-item-action" id="v-pills-course-type-tab" data-toggle="pill"
             href="#v-pills-course_type" role="tab"
             aria-controls="collapseExample" aria-selected="false" style="border: 0px" @click="courseType()">课程类型管理</a>
          <!--题库管理-->
          <a class="list-group-item list-group-item-action" id="v-pills-test-tab" data-toggle="pill"
             href="#v-pills-test" role="tab"
             aria-controls="collapseExample" aria-selected="false" style="border: 0px" @click="test(0)">题库管理</a>

          <hr>

          <a class="list-group-item list-group-item-action" id="v-pills-suggestion-tab" data-toggle="pill"
             href="#v-pills-suggestion" role="tab"
             aria-controls="collapseExample" aria-selected="false" style="border: 0px" @click="loadingSuggestions(1)">意见箱</a>

          <a class="list-group-item list-group-item-action" id="v-pills-userManagement-tab" data-toggle="pill"
             href="#v-pills-userManagement" role="tab"
             aria-controls="collapseExample" aria-selected="false" style="border: 0px" @click="user(0)">用户管理</a>

          <a class="list-group-item list-group-item-action" id="v-pills-teacherManagement-tab" data-toggle="pill"
             href="#v-pills-teacherManagement" role="tab"
             aria-controls="collapseExample" aria-selected="false" style="border: 0px"
             @click="teacher(0)">教师管理</a>
          <a class="list-group-item list-group-item-action" id="v-pills-course-promotion-tab" data-toggle="pill"
             href="#v-pills-homePage" role="tab"
             aria-controls="collapseExample" aria-selected="false" style="border: 0px"
             @click="loadingCoursePromotion(1)">首页管理</a>

          <a v-if="visible1" class="list-group-item list-group-item-action" data-toggle="pill" id="v-pills-course-gmManagement-tab"
             href="#v-pills-gm-account-management" role="tab"
             aria-controls="collapseExample" aria-selected="false" style="border: 0px"
             @click="gm(power,0)">管理员账号管理</a>
        </div>
        <div class="list-group" role="tablist" aria-orientation="vertical">
          <hr>
          <a class="list-group-item list-group-item-action" data-toggle="pill" role="tab"
             href="#" aria-controls="collapseExample" aria-selected="false" style="border: 0px"
             @click="GMExitXuesi">安全退出</a>
        </div>
      </div>
      <div class="col-11">
        <div class="tab-content" id="v-pills-tabContent">
          <!--个人信息-->
          <div class="tab-pane fade show active" id="v-pills-home" role="tabpanel"
               aria-labelledby="v-pills-home-tab">
            <form method="post" id="GM_message">
              <div class="form-group row">
                <label class="col-sm-2 col-form-label">Id</label>
                <div class="col-sm-10">
                  <input type="text" readonly class="form-control-plaintext" id="GMId" name="GMId"
                         placeholder="你还未登录！">
                </div>
              </div>
              <div class="form-group row">
                <label class="col-sm-2 col-form-label">Email</label>
                <div class="col-sm-10">
                  <input type="email" class="form-control" name="GMEmail" id="GMEmail"
                         placeholder="email@example.com" style="border: 0px;padding-left: 0px">
                </div>
              </div>
              <div class="form-group row">
                <label class="col-sm-2 col-form-label">电话号码</label>
                <div class="col-sm-10">

                  <input type="tel" class="form-control" name="GMTel" id="GMTel"
                         placeholder="请补充你的电话号码"
                         style="border: 0px;padding-left: 0px">

                </div>
              </div>
              <div class="form-group row">
                <label class="col-sm-2 col-form-label">管理员级别</label>
                <div class="col-sm-10">
                  <input type="text" readonly class="form-control-plaintext" id="GMPower"
                         placeholder="未知">
                </div>
              </div>

              <hr>
              <input class="btn btn-primary" type="button" @click="saveGMChanges" value="保存修改">
            </form>
          </div>
          <!--密码修改-->
          <div class="tab-pane fade" id="v-pills-changePassword" role="tabpanel"
               aria-labelledby="v-pills-profile-tab">
            <form method="post" id="change_password_form">
              <div class="form-group row">
                <label class="col-sm-2 col-form-label">原密码</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" name="GMOldPassword" id="gmOldPassword"
                         placeholder="输入你的密码"
                         style="border: 0px;padding-left: 0px">
                </div>
              </div>
              <div class="form-group row">
                <label class="col-sm-2 col-form-label">新密码</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" name="GMNewPassword" id="gmNewPassword"
                         placeholder="6-12位的字母或数字"
                         style="border: 0px;padding-left: 0px">
                </div>
              </div>
              <div class="form-group row">
                <label class="col-sm-2 col-form-label">确认新密码</label>
                <div class="col-sm-10">
                  <input type="password" class="form-control" name="GMNewPasswordR" id="gmNewPasswordR"
                         placeholder="请再输入一次新密码"
                         style="border: 0px;padding-left: 0px">
                </div>
              </div>
              <hr>
              <input class="btn btn-primary" type="button" @click="save_GM_change_password" value="保存修改">
            </form>
          </div>

          <!--课程管理-->
          <div class="tab-pane fade" id="v-pills-course" role="tabpanel" aria-labelledby="v-pills-messages-tab">
            <div class="container">

              <div class="input-group mb-3 align-self-center">

                <input type="text" class="form-control" placeholder="输入关键字" id="courseKey"
                       aria-label="Recipient's username" aria-describedby="button-addon2">

                <div class="input-group-append">
                  <button class="btn btn-secondary" type="button"
                          @click="course(1)">搜索
                  </button>
                </div>

              </div>
            </div>
            <br>
            <table class="table">
              <thead>
              <tr>
                <th scope="col">Id</th>
                <th scope="col">课程名</th>
                <th scope="col">作者id</th>
                <th scope="col">作者姓名</th>
                <th scope="col">课程类型</th>
                <th scope="col">课程状态</th>
                <th scope="col" style="text-align: center">操作</th>
              </tr>
              </thead>
              <tbody id="course-list">
                <tr v-for="course in courseListCom">
                  <th scope="row">{{course.courseId}}</th>
                  <td>{{course.courseName}}</td>
                  <td>{{course.teacherId}}</td>
                  <td>{{course.teacher.teacherName}}</td>
                  <td>{{course.courseType.typeName}}</td>
                  <td :id="course.courseId">{{course.accountStatus.accountClass}}</td>
                  <td style="text-align: center"><span class="btn badge badge-danger" @click="operation(course.courseId ,3,3)">删除</span>
                    <span class="btn badge badge-light" style="margin-left: 6px;margin-right: 6px" @click="operation(course.courseId,2,3);">冻结</span>
                    <span class="btn badge badge-success" @click="operation(course.courseId,1,3)">恢复</span>
                  </td>
                </tr>
              </tbody>
            </table>
            <hr>
            <div id="course-pagecount"></div>
              <nav aria-label="Page navigation">
                <ul class="pagination">
                  <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                  <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingCourse(1,keyCom)">Previous</a></li>
                  <li class="page-item" aria-current="page" v-for="i in totalPage">
                    <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingCourse(i,keyCom)">{{i}}</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingCourse(totalPage,keyCom)">Last</a></li>
                </ul>
              </nav>
          </div>
          <!--课程资源管理-->
          <div class="tab-pane fade" id="v-pills-course-resource" role="tabpanel"
               aria-labelledby="v-pills-messages-tab">
            <div class="container">

              <div class="input-group mb-3 align-self-center">

                <input type="text" class="form-control" placeholder="输入关键字" id="resourceKey"
                       aria-label="Recipient's username"
                       aria-describedby="button-addon2">

                <div class="input-group-append">
                  <button class="btn btn-secondary" type="button" id="button-teacher"
                          @click="courseResource(1);">搜索
                  </button>
                </div>
              </div>
            </div>
            <br>
            <table class="table">
              <thead>
              <tr>
                <th scope="col">资源Id</th>
                <th scope="col">资源名称</th>
                <th scope="col">所属课程Id</th>
                <th scope="col">课程名称</th>
                <th scope="col">作者id</th>
                <th scope="col">作者姓名</th>
                <th scope="col">用户类型</th>
                <th scope="col">资源状态</th>
                <th scope="col" style="text-align: center">操作</th>
              </tr>
              </thead>
              <tbody id="course-resource-list">
                <tr v-for="courseResource in courseResourceList">
                  <th scope="row">{{courseResource.resourceId}}</th>
                  <td>{{courseResource.resourceName}}</td>
                  <td>{{courseResource.courseId}}</td>
                  <td>{{courseResource.course.courseName}}</td>
<!--                  <td>{{courseResource.teacher.teacherId}}</td>-->
                  <td v-if="courseResource.userClass.typeName==='教师'">{{courseResource.teacher.teacherName}}</td>
                  <td v-if="courseResource.userClass.typeName!=='教师'">{{courseResource.student}}</td>
                  <td>{{courseResource.userClass.typeName}}</td>
                  <td :id="courseResource.resourceId">{{courseResource.accountStatus.accountClass}}</td>
                  <td style="text-align: center">
                    <span class="btn badge badge-danger" onclick="operation(array['resourceId'],3,7)">删除</span>
                    <span class="btn badge badge-light" style="margin-left: 6px;margin-right: 6px" onclick="operation(array['resourceId'],2,7)">冻结</span>
                    <span class="btn badge badge-success" onclick="operation(array['resourceId'],1,7)">恢复</span>
                  </td>
                </tr>
              </tbody>
            </table>
            <hr>
            <div id="course-resource-pagecount">
              <nav aria-label="Page navigation">
                <ul class="pagination">
                  <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                  <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingCourseResource(1,keyCom)">Previous</a></li>
                  <li class="page-item" aria-current="page" v-for="i in totalPage">
                    <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingCourseResource(i,keyCom)">{{i}}</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingCourseResource(totalPage,keyCom)">Last</a></li>
                </ul>
              </nav>
            </div>
          </div>
          <!--课程类型管理-->
          <div class="tab-pane fade" id="v-pills-course_type" role="tabpanel"
               aria-labelledby="v-pills-messages-tab">
            <!--添加栏-->
            <div class="input-group mb-3 ">
              <form method="post" class="container" style="max-width: 400px">
                <div class="input-group-append">
                  <input type="text" class="form-control" placeholder="添加新课程类型"
                         aria-label="Recipient's username" aria-describedby="button-addon2" id="NewType">
                  <button class="btn btn-outline-secondary" type="button" id="button-addon2"
                          @click="addCourseType()">添加
                  </button>
                </div>
              </form>
            </div>
            <br>
            <!--显示列表-->
            <table class="table">
              <thead>
              <tr>
                <th scope="col">Id</th>
                <th scope="col">课程类型</th>
                <th scope="col" style="text-align: center">操作</th>
              </tr>
              </thead>
              <tbody id="course-type-list">
              <tr v-for="courseType in courseTypeListCom" :id="'course-type-list-'+courseType.typeValue">
                <th scope="row">#{{courseType.typeValue}}</th>
                <td>{{courseType.typeName}}</td>
                <td style="text-align: center">
                  <span class="btn badge badge-danger" @click="operation(courseType.typeValue,3,4)">删除</span>
                </td>
              </tr>
              </tbody>
            </table>
            <hr>
            <div id="course-type-pagecount">
              <nav aria-label="Page navigation">
                <ul class="pagination">
                  <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                  <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingCourseType(1)">Previous</a></li>
                  <li class="page-item" aria-current="page" v-for="i in totalPage">
                    <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingCourseType(i)">{{i}}</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingCourseType(totalPage)">Last</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <!--题库管理-->
          <div class="tab-pane fade" id="v-pills-test" role="tabpanel" aria-labelledby="v-pills-messages-tab">
            <div class="container">

              <div class="input-group mb-3 align-self-center">

                <input type="text" class="form-control" placeholder="输入关键字" id="testKey"
                       aria-label="Recipient's username"
                       aria-describedby="button-addon2">

                <div class="input-group-append">
                  <button class="btn btn-secondary" type="button" id="button-test" @click="test(1);">搜索
                  </button>
                </div>

              </div>
            </div>
            <br>
            <table class="table">
              <thead>
              <tr>
                <th scope="col">测试卷id</th>
                <th scope="col">测试卷名称</th>
                <th scope="col">课程id</th>
                <th scope="col">测试卷状态</th>
                <th scope="col" style="text-align: center">操作</th>
              </tr>
              </thead>
              <tbody id="test-list">
                <tr v-for="test in testList">
                  <th scope="row">{{ test.testId }}</th>
                  <td>{{test.testName}}</td>
                  <td>{{test.courseId}}</td>
                  <td :id="test.testId">{{test.accountStatus.accountClass}}</td>
                  <td style="text-align: center">
                    <span class="btn badge badge-danger" @click="operation(test.testId,3,5)">删除</span>
                    <span class="btn badge badge-light" style="margin-left: 6px;margin-right: 6px" @click="operation(test.testId,2,5)">冻结</span>
                    <span class="btn badge badge-success" @click="operation(test.testId,1,5)">恢复</span>
                  </td>
                </tr>
              </tbody>
            </table>
            <hr>
            <div id="test-pagecount">
              <nav aria-label="Page navigation">
                <ul class="pagination">
                  <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                  <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingTest(1,key)">Previous</a></li>
                  <li class="page-item" aria-current="page" v-for="i in totalPage">
                    <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingTest(i,key)">{{i}}</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingTest(totalPage,key)">Last</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <!--意见箱-->
          <div class="tab-pane fade" id="v-pills-suggestion" role="tabpanel"
               aria-labelledby="v-pills-messages-tab">
            <table class="table">
              <thead>
              <tr>
                <th scope="col">No.</th>
                <th scope="col">用户Id</th>
                <th scope="col">意见内容</th>
                <th scope="col">发送日期</th>
              </tr>
              </thead>
              <tbody id="list">
              <!--意见内容将加载在此处-->
                <tr v-for="suggestions in suggestionsList">
                  <th scope="row">{{suggestions.ideaId}}</th>
                  <td v-if="suggestions.userId===-1">游客</td>
                  <td v-if="suggestions.userId!==-1">{{suggestions.userId}}</td>
                  <td>{{suggestions.ideaContent}}</td>
                  <td>{{suggestions.sendingDate}}</td>
                  </tr>
              </tbody>
            </table>
            <hr>
            <div id="pagecount">
              <nav aria-label="Page navigation ">
                <ul class="pagination">
                  <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                  <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingSuggestions(1)">Previous</a></li>
                  <li class="page-item" aria-current="page" v-for="i in totalPage">
                    <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingSuggestions(i)">{{i}}</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingSuggestions(totalPage)">Last</a></li>
                </ul>
              </nav>
            </div>
          </div>
          <!--用户管理-->
          <div class="tab-pane fade" id="v-pills-userManagement" role="tabpanel"
               aria-labelledby="v-pills-messages-tab">
            <div class="input-group mb-3 container">
              <input type="text" class="form-control" placeholder="输入关键字" id="userKey"
                     aria-label="Recipient's username"
                     aria-describedby="button-addon2">
              <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button" id="button-user" @click="user(1)">
                  搜索
                </button>
              </div>
            </div>
            <br>
            <table class="table">
              <thead>
              <tr>
                <th scope="col">用户Id</th>
                <th scope="col">用户昵称</th>
                <th scope="col">用户邮箱</th>
                <th scope="col">联系电话</th>
                <th scope="col">性别</th>
                <th scope="col">出生日期</th>
                <th scope="col">状态</th>
                <th scope="col" style="text-align: center">操作</th>
              </tr>
              </thead>
              <tbody id="user-list">
                <tr v-for="userInfo in userInfoList">
                  <th scope="row">{{userInfo.userId}}</th>
                  <td>{{userInfo.userName}}</td>
                  <td>{{userInfo.userEmail}}</td>
                  <td>{{userInfo.userTel}}</td>
                  <td>{{userInfo.userSex}}</td>
                  <td>{{userInfo.userBirth}}</td>
                  <td :id="'user'+userInfo.userId">{{userInfo.accountClass}}</td>
                  <td style="text-align: center">
                    <span class="btn badge badge-danger" @click="operation(userInfo.userId,3,0)">注销</span>
                    <span class="badge badge-light" style="margin-left: 6px;margin-right: 6px" @click="operation(userInfo.userId,2,0)">冻结</span>
                    <span class="badge badge-success" @click="operation(userInfo.userId,1,0);">恢复</span>
                  </td>
                </tr>
              </tbody>
            </table>
            <hr>
            <div id="user-pagecount">
              <nav aria-label="Page navigation ">
                <ul class="pagination">
                  <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                  <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingUser(1, keyCom)">Previous</a></li>
                  <li class="page-item" aria-current="page" v-for="i in totalPage">
                    <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingUser(i, keyCom)">{{i}}</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingUser(totalPage, keyCom)">Last</a></li>
                </ul>
              </nav>
            </div>
          </div>
          <!--教师管理-->
          <div class="tab-pane fade" id="v-pills-teacherManagement" role="tabpanel"
               aria-labelledby="v-pills-messages-tab">
            <div class="input-group mb-3 container">
              <input type="text" class="form-control" placeholder="输入关键字" id="teacherKey"
                     aria-label="Recipient's username"
                     aria-describedby="button-addon2">
              <div class="input-group-append">
                <button class="btn btn-outline-secondary" type="button"
                        @click="teacher(1)">搜索
                </button>
              </div>
            </div>
            <br>
            <table class="table">
              <thead>
              <tr>
                <th scope="col">教师Id</th>
                <th scope="col">教师姓名</th>
                <th scope="col">联系电话</th>
                <th scope="col">教师邮箱</th>
                <th scope="col">身份证</th>
                <th scope="col">资格证号</th>
                <th scope="col">所属院校</th>
                <th scope="col">院校邮箱</th>
                <th scope="col">账号状态</th>
                <th scope="col" style="text-align: center">操作</th>
              </tr>
              </thead>
              <tbody id="teacher-list">
                <tr v-for="(teacherInfo,i) in teacherInfoList">
                  <th scope="row">{{teacherInfo.teacherId}}</th>
                  <td>{{teacherInfo.teacherName}}</td>
                  <td>{{teacherInfo.teacherTel}}</td>
                  <td>{{teacherInfo.teacherEmail}}</td>
                  <td>{{teacherInfo.teacherIDcard}}</td>
                  <td>{{teacherInfo.teacherQualification}}</td>
                  <td>{{teacherInfo.teachersSchool}}</td>
                  <td>{{teacherInfo.teachersSchoolEmail}}</td>
                  <td :id="'teacher'+teacherInfo.teacherId">{{teacherInfo.accountClass}}</td>
                  <td style='text-align: center'>
                    <span class="btn badge badge-danger" @click="operation(teacherInfo.teacherId,3,1)">注销</span>
                    <span class="btn badge badge-light" style='margin-left: 6px;margin-right: 6px' @click="operation(teacherInfo.teacherId,2,1)">冻结</span>
                    <span class="btn badge badge-success" @click="operation(teacherInfo.teacherId,1,1)">恢复/通过</span>
                  </td>
                </tr>
              </tbody>
            </table>
            <hr>
            <div id="teacher-pagecount">
              <nav aria-label="Page navigation ">
                <ul class="pagination">
                  <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                  <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                  <li class="page-item"><a class="page-link" href="#" @click="loading_teacher(1, keyCom)">Previous</a></li>
                  <li class="page-item" aria-current="page" v-for="i in totalPage">
                    <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loading_teacher(i, keyCom)">{{i}}</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#" @click="loading_teacher(totalPage, keyCom)">Last</a></li>
                </ul>
              </nav>
            </div>
          </div>
          <!--首页管理-->
          <div class="tab-pane fade" id="v-pills-homePage" role="tabpanel" aria-labelledby="v-pills-messages-tab">
            <!--添加栏-->
            <div class="input-group mb-3 ">
              <form method="post" class="container" style="max-width: 400px">
                <div class="input-group-append">
                  <input type="text" class="form-control" placeholder="填写需要推广的课程id"
                         aria-label="Recipient's username" aria-describedby="button-addon3"
                         id="NewPromotion">
                  <button class="btn btn-outline-secondary" type="button" id="button-addon3"
                          @click="addCoursePromotion()">添加
                  </button>
                </div>
              </form>
            </div>
            <br>
            <!--显示列表-->
            <table class="table">
              <thead>
              <tr>
                <th scope="col">课程Id</th>
                <th scope="col">课程名称</th>
                <th scope="col" style="text-align: center">操作</th>
              </tr>
              </thead>
              <tbody id="course-promotion-list">
              <tr :id="'course-type-list-' + coursePromotion.courseId" v-for="coursePromotion in coursePromotionList">
                <td>{{ coursePromotion.courseId }}</td>
                <td>{{ coursePromotion.course.courseName }}</td>
                <td style="text-align: center">
                  <span class="btn badge badge-danger" @click="operation(coursePromotion.courseId ,3,6)">删除</span>
                </td>
              </tr>
              </tbody>
            </table>
            <hr>
            <div id="course-promotion-pagecount">
              <nav aria-label="Page navigation ">
                <ul class="pagination">
                  <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                  <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingCoursePromotion(1)">Previous</a></li>
                  <li class="page-item" aria-current="page" v-for="i in totalPage">
                    <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loadingCoursePromotion(page)">{{i}}</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#" @click="loadingCoursePromotion(totalPage)">Last</a></li>
                </ul>
              </nav>
            </div>
          </div>

          <!-- 管理 管理员 操作 -->
          <div class="tab-pane fade" id="v-pills-gm-account-management" role="tabpanel" aria-labelledby="v-pills-messages-tab">
            <div class="input-group mb-3 container">
            <input type="text" class="form-control" placeholder="输入关键字" id="gmKey" aria-label="Recipient's username" aria-describedby="button-addon2">
            <div class="input-group-append">
              <button class="btn btn-outline-secondary" type="button" @click="gm(power,1)">搜索</button>
            </div>
            <div class="input-group-append" v-if="visible2">
              <button type="button" @click="addGM()" class="btn btn-success">管理员账号生成</button>
            </div>
            </div>
            <br>
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">#</th>
                  <th scope="col">管理员Id</th>
                  <th scope="col">邮箱</th>
                  <th scope="col">联系电话</th>
                  <th scope="col">权限级别</th>
                  <th scope="col">账号状态</th>
                  <th scope="col" style="text-align: center">操作</th>
                </tr>
              </thead>
              <tbody>
               <tr v-for="(gmInfo,i) in gmInfoList" v-bind:key="i">
                 <th scope="row">{{i+1}}</th>
                 <th scope="row">{{gmInfo.gmId}}</th>
                 <th scope="row">{{gmInfo.gmEmail}}</th>
                 <th scope="row">{{gmInfo.gmTel}}</th>
                 <th scope="row">{{gmInfo.gmPowerClass}}</th>
                 <th scope="row" :id="'gm'+gmInfo.gmId">{{gmInfo.accountStatus}}</th>
                 <td style="text-align: center" v-if="gmInfo.gmPower<power">
                   <span class="btn badge badge-danger" @click="operation(gmInfo.gmId,3,2)">注销</span>
                   <span class="btn badge badge-light" style="margin-left: 6px;margin-right: 6px" @click="operation(gmInfo.gmId,2,2)">冻结</span>
                   <span class="btn badge badge-success" @click="operation(gmInfo.gmId,1,2)">恢复</span>
                 </td>
                 <td style="text-align: center" v-else>同级别不可操作</td>
               </tr>
              </tbody>
            </table>
            <hr>
            <div id="gm-pagecount">
              <nav aria-label="Page navigation ">
                <ul class="pagination">
                  <li class="page-item active"><span class="page-link">结果总数:{{total}}</span></li>
                  <li class="page-item active"><span class="page-link">总页数:{{totalPage}}</span></li>
                  <li class="page-item"><span class="page-link" @click="loading_gm(power, 1, keyCom)">Previous</span></li>
                  <li class="page-item" aria-current="page" v-for="i in totalPage">
                    <a class="page-link" href="#" v-if="page+3>i && page-3<i" @click="loading_gm(power, i, keyCom)">{{i}}</a>
                  </li>
                  <li class="page-item"><a class="page-link" href="#" @click="loading_gm(power, totalPage, keyCom)">Last</a></li>
                </ul>
              </nav>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import global from './Common.vue'
export default {
  name: "ManagementSystem",
  data(){
    return{
      visible1: false,
      visible2: false,
      key: "", //用于模糊匹配,当使用默认值去 select 匹配时相当获得所有匹配数据
      power: 0,
      total: 0, // 数据总条数
      curPage: 0, // 起始页
      totalPage: 0, // 总页数
      page: 1,
      gmList:[],
      teacherList:[],
      userList:[],
      courseTypeList:[],
      courseList:[],
      courseResourceList:[],
      testList:[],
      coursePromotionList:[],
      suggestionsList:[],
    }
  },
  computed: {
    gmInfoList:function (){
      return this.gmList;
    },
    totalCom:function (){
      return this.total;
    },
    keyCom:function (){
      return this.key;
    },
    teacherInfoList:function () {
      return this.teacherList;
    },
    userInfoList:function() {
      return this.userList;
    },
    courseTypeListCom:function (){
      return this.courseTypeList;
    },
    courseListCom:function (){
      return this.courseList;
    },
  },
  methods:{
    //管理员个人信息修改
    saveGMChanges() {
      $.ajax({
        type : 'POST',
        url : global.httpUrl+"/saveGMChanges",
        data : {
          'gmEmail' : $("#GMEmail").val(),
          'gmTel': $("#GMTel").val()
        },
        dataType : 'json',
        success : function(response) {
          var json = JSON.parse(response);
          if(json.result){
            alert("基本信息更新成功");
            $("#GMEmail").val(json.gmEmail);
            $("#GMTel").val(json.gmTel);
          }else{
            alert("基本信息更新失败"+json.result);
          }
        },
        error : function(xhr, status, error) {
          console.log("saveGMChanges error");
        }
      });
    },
    gmLoginCheck() {
      var that=this;
      $.ajax({
        type : 'POST',
        url : global.httpUrl+"/loginCheck",
        dataType : 'json',
        success : function(json) {
          console.log("账号状态检查");
          that.GMStatHandler(json);
        },
        error : function(xhr, status, error) {
          console.log("***>状态码:" + xhr.status + ",状态:" + xhr.readyState
            + ",错误信息:" + xhr.statusText + ",请求状态:" + status
            + ",error:" + error);
          // location.href="/";
          that.$router.push("/");
        }
      });
    },
    //密码修改
    save_GM_change_password() {
      var GMopw = $("#gmOldPassword").val();
      var GMnpw = $("#gmNewPassword").val();
      var GMnpwr = $("#gmNewPasswordR").val();
      if(GMopw==GMnpw){
        alert("新密码不可与旧密码相同");
      }else if(GMnpw!=GMnpwr){
        alert("新密码与确认新密码不一致");
      }else{
        $.ajax({
          type : 'POST',
          url : global.httpUrl+"/changeGMPassword",
          data : {
            'oldPassword' : GMopw,
            'newPassword': GMnpw
          },
          dataType : 'json',
          success : function(response) {
            $("#gmOldPassword").val('');
            $("#gmNewPassword").val('');
            $("#gmNewPasswordR").val('');
            if(response.result){
              alert("密码更新成功");
            }else{
              alert("密码更新失败");
            }
          },
          error : function(xhr, status, error) {
            console.log("saveGMChanges error");
          }
        });
      }
    },
    /**
     * 扩展不同级别管理员的功能
     * 所有不同级别管理员共有的基础管理功能---网站维护、用户账号管理、课程管理、资源管理
     * 0:基层管理员---只含基础功能
     * 1:中层管理员---对基层管理员的账号管理
     * 2:高层管理员---对基层、中层管理员的账号管理、可查看后台管理员操作记录、管理员账号批量生成
     */
    add_fun(gmPower) {
      this.power=gmPower;
      if (gmPower == 0) {
        this.visible1=false;
        alert("你是基层管理员");
      } else if (gmPower == 1) {
        alert("你是中层管理员");
        this.visible1=true;
      } else if (gmPower == 2) {
        alert("你是高层管理员");
        this.visible1=true;
        this.visible2=true;
      } else {
        alert("系统错误，请联系网站维护人员");
      }
    },
    //管理员账号管理,点击界面"管理员账号管理"时触发,该元素是进入界面时自动生成且添加了 onclick 以及参数
    gm(gmPower, sign) {  //权限等级(数字),标记(0:所有数据,1:模糊搜索)
      this.key="";
      if (sign == 1) {  //模糊搜索时获取 key
        this.key = $("#gmKey").val();
      }
      this.loading_gm(gmPower, 1, this.key);  //请求数据(用户权限等级,第几页,匹配项)
      $("#gm-pagecount").on("click", "a", function () {
        var rel = $(this).attr("rel");
        if (rel) {
          this.loading_gm(gmPower, rel, this.key);
        }
      });
    },
    //加载管理员信息
    loading_gm(gmPower, page, key) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getAppointTypeGMMessage',
        data: {'pageNum': page - 1, 'gmPower': gmPower, 'key': key},
        dataType: 'json',
        beforeSend: function () {
          $("#gm-list").append("加载中...");
        },
        success: function (json) {
          console.log("loading_gm"+json);
          $("#gm-list").empty();
          pageSize = json.pageSize;
          curPage = page;
          totalPage = json.totalPage;
          that.page=page;
          that.gmList = json.userList;
          that.total=json.total;
          that.totalPage=json.totalPage;
          console.log(that.total);
        },
        complete: function () {
          // $("#gm-pagecount").html(getPageBar());
        },
        error: function () {
          alert("数据加载失败");
          that.$router.push({path:'/'});
        }
      });
    },
    //管理员退出
    GMExitXuesi() {
      var that=this;
      $.ajax({
        url: global.httpUrl+"/gmExit",
        type: 'post',
        success: function () {
          alert("成功退出!");
          // location.href = '/';
          that.$router.push("/");
        },
        error: function () {
          alert("退出错误!");
        }
      });
    },
    //添加管理员
    addGM() {
      // window.open('/AddGM');
      // this.$router.resolve("/AddGM");
      const { href } = this.$router.resolve({
        path: "/AddGM",
      });
      window.open(href, '_blank');
    },
    //加载课程推广信息
    loadingCoursePromotion(page) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getCoursePromotion',
        data: {'pageNum': page - 1},
        dataType: 'json',
        success: function (json) {
          that.total = json.total;
          that.Page = page;
          that.totalPage = json.totalPage;
          that.coursePromotionList = json.coursePromotionList;
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
    /**
     * 账号操作
     * id：对象id
     * sign:操作指令代号
     *    0：待审核
     *    1：正常
     *    2：冻结
     *    3：注销/删除
     * category:执行功能代号
     *    0：对普通用户账号状态编辑
     *    1：对教师用户账号状态编辑
     *    2：对管理员用户账号状态编辑
     *    3：对所有课程状态编辑
     *    4：删除指定课程类型
     *    5：对测试卷状态编辑
     *    6：对课程推广状态编辑
     *    7：对课程资源状态编辑
     **/
    operation(id, sign, category) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+"/accountManagement",
        data: {'id': id, 'sign': sign, 'category': category},
        dataType: 'json',
        success: function (json) {
          if (category === 0) {
            $("#user" + id).html(json.result);
          } else if (category === 1) {
            $("#teacher" + id).html(json.result);
          } else if (category === 2) {
            $("#gm" + id).html(json.result);
          } else if (category === 3) {
            $("#" + id).html(json.result);
          } else if (category === 4) {
            that.loadingCourseType(1);
          } else if (category === 5) {
            $("#" + id).html(json.result);
          } else if (category === 6) {
            that.loadingCoursePromotion(1);
          } else if (category === 7) {
            $("#" + id).html(json.result);
          } else {
            alert("操作错误！");
          }
        },
        error: function () {
          alert("操作失败");
        }
      });
    },
    //用户管理
    user(sign) {
      this.key = "";
      if (sign == 1) {
        this.key = $("#userKey").val();
      }else {
        $("#userKey").val("");
      }
      this.loadingUser(1, this.key);
      $("#user-pagecount").on("click", "a", function () {
        var rel = $(this).attr("rel");
        if (rel) {
          this.loadingUser(rel, this.key);
        }
      });
    },
    //加载用户信息
    loadingUser(page, key) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getAllUserMessage',
        data: {'pageNum': page - 1, 'key': key},
        dataType: 'json',
        // beforeSend: function () {
        //   $("#user-list").append("加载中...");
        // },
        success: function (json) {
          // $("#user-list").empty();
          pageSize = json.pageSize;
          curPage = page;
          that.total=json.total;
          that.totalPage=json.totalPage;
          that.page=page;
          that.userList = json.studentList;
        },
        complete: function () {
          // $("#user-pagecount").html(getPageBar());
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
    //教师管理
    teacher(sign) {
      this.key = "";
      if (sign == 1) {
        this.key = $("#teacherKey").val();
      }

      this.loading_teacher(1, this.key);
      $("#teacher-pagecount").on("click", "a", function () {
        var rel = $(this).attr("rel");
        if (rel) {
          this.loading_teacher(rel, this.key);
        }
      });
    },
    //加载教师信息
    loading_teacher(page, key) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getAllTeacher',
        data: {'pageNum': page-1 , 'key': key},
        dataType: 'json',
        beforeSend: function () {
          // $("#teacher-list").append("加载中...");
        },
        success: function (json) {
          // $("#teacher-list").empty();
          pageSize = json.pageSize;
          that.teacherList = json.teacherList;
          that.totalPage=json.totalPage;
          that.total= json.total;
          that.page = page;
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
    //添加新课程类型
    addCourseType() {
      var that=this;
      var NewType = $("#NewType").val();
      if (NewType.length == 0) {
        alert("不可为空！");
      } else {
        $.ajax({
          url: global.httpUrl+"/addCourseType",
          type: 'post',
          dataType: 'json',
          data: {"newType": NewType},
          success: function (json) {
            if (json.result) {
              alert("新课程添加成功!");
              that.loadingCourseType(1);
            }
            else {
              alert("新课程添加失败");
            }
          },
          error: function (err) {
            alert("新课程添加错误！");
          }
        });
      }
    },
    //课程类型管理
    courseType() {
      this.loadingCourseType(1);
      $("#course-type-pagecount").on("click", "a", function () {
        var rel = $(this).attr("rel");
        if (rel) {
          this.loadingCourseType(rel);
        }
      });
    },
    //加载课程类型信息
    loadingCourseType(page) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getCourseType',
        data: {'pageNum': page - 1},
        dataType: 'json',
        // beforeSend: function () {
        //   $("#course-type-list").append("加载中...");
        // },
        success: function (json) {
          console.log(json,page);
          // $("#course-type-list").empty();
          that.page = page;
          that.total = json.total;
          that.totalPage = json.totalPage;
          that.courseTypeList = json.courseTypeList;
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
    //课程资源管理
    courseResource(sign) {
      this.key = "";
      if (sign === 1) {
        this.key = $("#resourceKey").val();
      }
      this.loadingCourseResource(1, this.key);
      $("#course-resource-pagecount").on("click", "a", function () {
        var rel = $(this).attr("rel");
        if (rel) {
          this.loadingCourseResource(rel,this. key);
        }
      });
    },
    //加载课程资源信息
    loadingCourseResource(page, key) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getCourseResourceMessage',
        data: {'pageNum': page - 1, 'key': key},
        dataType: 'json',
        beforeSend: function () {
          // $("#course-resource-list").append("加载中...");
        },
        success: function (json) {
          // console.log(json);
          // $("#course-resource-list").empty();
          that.total = json.total;
          pageSize = json.pageSize;
          that.curPage = page;
          that.totalPage = json.totalPage;
          that.courseResourceList=json.courseResouceList;
        },
        complete: function () {
          // $("#course-resource-pagecount").html(getPageBar());
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
    //课程管理
    course(sign) {
      this.key = "";
      if (sign == 1) {
        key = $("#courseKey").val();
      }

      this.loadingCourse(1,this.key);
      $("#course-pagecount").on("click", "a", function () {
        var rel = $(this).attr("rel");
        if (rel) {
          this.loadingCourse(rel, this.key);
        }
      });
    },

    //加载测试卷信息
    loadingTest(page, key) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getAllTestMessage',
        data: {'pageNum': page - 1, 'key': key},
        dataType: 'json',
        beforeSend: function () {
          // $("#test-list").append("加载中...");
        },
        success: function (json) {
          // $("#test-list").empty();
          that.total = json.total;
          that.page = page;
          that.totalPage = json.totalPage;
          that.testList=json.testList;
        },
        complete: function () {
          // $("#test-pagecount").html(getPageBar());
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },

    //加载课程信息
    loadingCourse(page, key) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getCourseMessage',
        data: {'pageNum': page - 1, 'key': key},
        dataType: 'json',
        beforeSend: function () {
          // $("#course-list").append("加载中...");
        },
        success: function (json) {
          // $("#course-list").empty();
          that.totalPage=json.totalPage;
          that.courseList=json.courseList;
          that.total=json.total;
          that.page=page;

        },
        complete: function () {
          // $("#course-pagecount").html(getPageBar());
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },
    //测试卷管理
    test(sign) {
      this.key = "";
      if (sign === 1) {
        this.key = $("#testKey").val();
      }
      this.loadingTest(1, this.key);

    },
    //添加课程推广
    addCoursePromotion() {
      var that=this;
      var newPromotion = $("#NewPromotion").val();
      alert("courseId:"+newPromotion);
      if (NewPromotion.length == 0) {
        alert("不可为空！");
      } else {
        $.ajax({
          url: global.httpUrl+"/addCoursePromotion",
          data:{"newPromotion":newPromotion},
          type: 'post',
          dataType:'json',
          success: function (json) {
            if(json.state){
              alert(json.message);
              that.loadingCoursePromotion(1);
            }else{
              alert(json.message);
            }

          },
          error: function (err) {
            alert("添加推广错误!");
          }
        });
      }
    },


    //加载建议
    loadingSuggestions(page) {
      var that=this;
      $.ajax({
        type: 'POST',
        url: global.httpUrl+'/getSuggestions',
        data: {'pageNum': page - 1},
        dataType: 'json',
        beforeSend: function () {
          // $("#list").append("加载中...");
        },
        success: function (json) {
          // $("#list").empty();
          total = json.total;
          that.total=total;
          that.page=page;
          that.totalPage=totalPage;
          if (total === 0) {
            alert("暂无留言");
          } else {
            that.suggestionsList = json.suggestionsList;
          }
        },
        complete: function () {
        },
        error: function () {
          alert("数据加载失败");
        }
      });
    },

    //数据返回后端处理结果反馈给用户，便于指导用户正确操作
    //100-199:状态相关(登陆/注册成功)
    //200-299：账号相关(Id/邮箱/电话号码检测)
    //300-399：密码相关(密码检测)
    //400-499:个人信息相关(信息修改)
    GMStatHandler(msg) {
      if (msg == 901) {
        alert("账号密码不可为空！");
      } else if (msg == 902) {
        alert("请检查你的账号是否正确!");
      } else if (msg == 903) {
        alert("登录失败，请检查密码是否正确!");
      } else if (msg == 904) {
        alert("登陆成功!");
        // location.href = 'ManagementSystem.html';
        this.$router.push("/ManagementSystem");
      } else if (msg == 905) {
        alert("非法访问!");
        // location.href = '/';
        this.$router.push("/");
      } else if (msg == 906) {
        alert("已安全退出!");
        // location.href = 'GMLogin.html';
        this.$router.push("/");
      } else if (msg == 908) {
        alert("已成功修改！");
      } else if (msg == 909) {
        alert("密码不可为空！");
      } else if (msg == 910) {
        alert("新密码不一致！");
      } else if (msg == 911) {
        alert("密码不符合要求！");
      } else if (msg == 912) {
        alert("不可以与原密码相同！");
      } else if (msg == 913) {
        alert("新密码不可以使用曾今使用过的密码！");
      } else if (msg == 914) {
        alert("密码修改失败！");
      } else if (msg == 915) {
        alert("密码修改成功！");
        $("#GMOldPassword").val('');
        $("#GMNewPassword").val('');
        $("#GMNewPasswordR").val('');
      } else if (msg == 916) {
        alert("空！");
      } else if (msg == 917) {
        alert("新类型添加成功!");
      } else if (msg == 918) {
        alert("不存在该课程，请检查后重新输入!");
      } else if (msg == 919) {
        alert("该课程正在推广中!");
      } else if (msg == 920) {
        $("#v-pills-course-promotion-tab").click();
        alert("添入成功!");
      }else if (msg == 1) {
        // 账号正常 无需其它操作
      } else if (msg == 2) {
        alert("此号已被冻结!");
        GMExitXuesi();//清除账号session信息
      } else if (msg == 3) {
        alert("此号已被权限更高的管理员注销!");
        GMExitXuesi();//清除账号session信息
      }else {
        alert("失败！" + msg);
      }
    },
  },

  mounted() {
    var that = this;
    $(function () {
      $('[data-toggle="popover"]').popover()
    })
    setInterval(this.gmLoginCheck, "20000");  // 每10秒进行一次账号状态检查
    $(function(){  // 获取管理员的基本信息
      $.ajax({
        url: global.httpUrl+'/getGMMessage',
        type: 'post',
        dataType: 'json',
        success: function (response) {
          if(response.state){
            $("#GMId").val(response.gmId);
            $("#GMEmail").val(response.gmEmail);
            $("#GMTel").val(response.gmTel);
            $("#GMPower").val(response.gmPower);
            console.log("response.gmPower-"+response.gmPower);
            that.add_fun(response.gmPowerNo);
          }else{
            console.log("信息获取失败");
            // location.href="/";
            that.$router.push("/");
          }

        },
        error: function (err) {
          alert("错误");
        }
      });
    });
    // 判断是否已登录并获取登录信息
    $(function () {
      $.ajax({
        type : 'POST',
        url : global.httpUrl+"/loginCheck",
        dataType : 'json',
        success : function(json) {
          console.log("loginCheck>result:" + json);
          that.GMStatHandler(json);
        },
        error : function(xhr, status, error) {
          console.log("状态码:" + xhr.status + ",状态:" + xhr.readyState
            + ",错误信息:" + xhr.statusText + ",请求状态:" + status
            + ",error:" + error);
          that.$router.push({path:'/'});
        }
      });
    });
  }
}

//获取数据
var curPage = 1; //当前页码
var total, pageSize, totalPage;//总记录数，每页记录数，总页数
//意见箱
function suggestions() {

  loading_suggestions(1);
  $("#pagecount").on("click", "a", function () {
    var rel = $(this).attr("rel");
    if (rel) {
      loading_suggestions(rel);
    }
  });
}












//题库搜索
function test_search() {
  key = $("#testKey").val();
}


//普通用户/学生搜索
function user_search() {
  key = $("#userKey").val();
}

//教师搜索
function teacher_search() {
  key = $("#teacherKey").val();
}

//管理员搜索
function gm_search() {
  key = $("#gmKey").val();
  alert(key);
}


//触发账号检查事件
function click() {
  check();
}
</script>

<style scoped>

</style>
