package com.muke.onlineedu.admin.controller;

import com.google.gson.Gson;
import com.muke.onlineedu.admin.entity.*;
import com.muke.onlineedu.admin.service.*;
import com.muke.onlineedu.common.tool.CommonConfig;
import com.muke.onlineedu.common.tool.ResourceUploadAndDownload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

@RestController
public class UserController {


    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseAbstractService courseAbstractService;
    @Autowired
    CourseStructureService courseStructureService;
    @Autowired
    ItemBackService itemBackService;
    @Autowired
    TestQuestionService testQuestionService;
    @Autowired
    TestOptionsService testOptionsService;
    @Autowired
    CourseResourceService courseResourceService;
    @Autowired
    UserAchievementService userAchievementService;
    @Autowired
    UserResultsService userResultsService;
    @Autowired
    CommonConfig commonConfig;

    @ResponseBody
    @RequestMapping("/getUserMessage")
    public String getUserMessage(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();

        if(servletContext.getAttribute("state")==null || !(boolean)servletContext.getAttribute("state")){
            data.put("state", false);
            return gson.toJson(data);
        }

        int userId=(int) servletContext.getAttribute("id");

        Student student=studentService.getUserMessage(userId);
        data.put("userName", student.getUserName());
        data.put("userId", student.getUserId());
        data.put("userEmail", student.getUserEmail());
        data.put("userTel", student.getUserTel());
        data.put("userSex", student.getUserSex());
        data.put("userBirth", student.getUserBirth());
        data.put("state", servletContext.getAttribute("state"));
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/changeUserInformation")
    public String changeUserInformation(String userName,String userEmail,String userTel,String userSex,String userBirth,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        int userId=(int) servletContext.getAttribute("id");
        studentService.changeUserInformation(userName,userEmail,userTel,userSex,userBirth,userId);
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        data.put("result", true);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/exit")
    public String exit(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        servletContext.removeAttribute("state");
        servletContext.removeAttribute("id");
        servletContext.removeAttribute("teacher");
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        data.put("result", true);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/changePassword")
    public String changePassword(String newPassword,String oldPassword,HttpServletRequest request){
        Map<Object,Object> data=new HashMap<>();
        ServletContext servletContext = request.getServletContext();
        boolean teacher=(boolean) servletContext.getAttribute("teacher");
        Gson gson=new Gson();
        Boolean state=false;
        String message=null;

        int id=(int) servletContext.getAttribute("id");
        if(teacher) {
            if(teacherService.idLogin(id, md5(oldPassword))!=null) { // 旧密码输入正确
                System.out.println("旧密码输入正确");
                if(teacherService.checkNewPassword(id, md5(newPassword))) { // 检查密码是否曾今使用过
                    teacherService.updatePassword(id, md5(oldPassword), md5(newPassword));
                    state=true;
                    System.out.println("修改密码成功");
                    message="修改密码成功";
                }else{  //新密码与上次使用的密码一致
                    message="新密码与上次使用的密码一致";
                    System.out.println("新密码与曾经使用的密码一致,请换一个密码");
                }
            }else {  //旧密码输入错误
                message="旧密码输入错误";
                System.out.println("旧密码输入错误");
            }
        }else {
            if(studentService.idLogin(id, md5(oldPassword))!=null) {  //旧密码输入正确
                System.out.println("旧密码输入正确");
                if(studentService.checkNewPassword(id, md5(newPassword))) {  //检查密码是否曾今使用过
                    studentService.updatePassword(id, md5(oldPassword), md5(newPassword));
                    state=true;
                    System.out.println("修改密码成功");
                    message="修改密码成功";
                }else{  //新密码与上次使用的密码一致
                    message="新密码与上次使用的密码一致";
                    System.out.println("新密码与曾经使用的密码一致,请换一个密码");
                }
            }else {  //旧密码输入错误
                message="旧密码输入错误";
                System.out.println("旧密码输入错误");
            }
        }
        data.put("state", state);
        data.put("message", message);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getTeacherMessage")
    public String getTeacherMessage(HttpServletRequest request){
        Map<Object,Object> data=new HashMap<Object,Object>();
        Gson gson=new Gson();
        ServletContext servletContext = request.getServletContext();
        int teacherId=(int) servletContext.getAttribute("id");
        System.out.println("id:"+teacherId);
        boolean state = (boolean) servletContext.getAttribute("state");
        boolean teacherIs =(boolean) servletContext.getAttribute("teacher");
        if(state==true&&teacherIs==true) {
            Teacher teacher=teacherService.getTeacherMessage(teacherId);
            data.put("teacherId", teacher.getTeacherId());
            data.put("teacherName", teacher.getTeacherName());
            data.put("teachersSchool", teacher.getTeachersSchool());
            data.put("teacherTel", teacher.getTeacherTel());
            data.put("teacherEmail", teacher.getTeacherEmail());
            data.put("teacherQualification", teacher.getTeacherQualification());
            data.put("teachersSchoolEmail", teacher.getTeachersSchoolEmail());
        }else {
            System.out.println("非法访问!");
        }
        data.put("state", state);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/changeTeacherInformation")
    public String changeTeacherInformation(String teacherEmail,String teachersSchool,String teacherTel,String teachersSchoolEmail,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        int teacherId=(int) servletContext.getAttribute("id");
        Map<Object,Object> data=new HashMap<Object,Object>();
        Gson gson=new Gson();
        boolean state=false;

        teacherService.updateTeacherMessage(teacherId, teacherEmail, teachersSchool, teacherTel, teachersSchoolEmail);
        Teacher teacher=teacherService.getTeacherMessage(teacherId);
        data.put("teachersSchool", teacher.getTeachersSchool());
        data.put("teacherTel", teacher.getTeacherTel());
        data.put("teacherEmail", teacher.getTeacherEmail());
        data.put("teachersSchoolEmail", teacher.getTeachersSchoolEmail());
        data.put("state", state);
        return gson.toJson(data);
    }
    public static String md5(String str) {  // md5 加密
        byte[] digest = null;
        MessageDigest md5;
        String md5Str;
        try {
            md5 = MessageDigest.getInstance("md5");
            digest = md5.digest(str.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        md5Str = new BigInteger(1, digest).toString(16);
        return md5Str;
    }

    @ResponseBody
    @RequestMapping("/saveCourse")
    public String saveCourse(HttpServletRequest request,@RequestParam("files") MultipartFile[] files){
        ServletContext servletContext = request.getServletContext();
        int teacherId=(int) servletContext.getAttribute("id");
        boolean teacher=(boolean) servletContext.getAttribute("teacher");

        File photoDirFile= commonConfig.getPhotoDirFile();
        File videoDirFile= commonConfig.getVideoDirFile();
        File fileDirFile= commonConfig.getFileDirFile();

        Map<String,String> data=new HashMap<>();

        /**
         * 表单数据
         */
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name=parameterNames.nextElement();
            System.out.println("*"+name+"***"+request.getParameter(name));
            data.put(name,request.getParameter(name));
        }


        /**
         * 上传文件数据
         */
        Map<String,MultipartFile> fileMap = new HashMap<>();
        for (MultipartFile file : files) {
            System.out.println("fileOldName:" + file.getOriginalFilename()+",:"+file);
            fileMap.put(file.getOriginalFilename(),file);
        }

        Map<Object,Object> result=new HashMap<>();
        Gson gson=new Gson();
        if(teacher){
            ResourceUploadAndDownload resourceUploadAndDownload=new ResourceUploadAndDownload(photoDirFile, videoDirFile,fileDirFile);
            data.putAll(resourceUploadAndDownload.resourceUpload(fileMap,teacherId));  //将表单解析,整理资源并存储,将表单信息放入 Map 中并返回
            System.out.println("resource:"+data);
            courseEntry(teacherId,data,videoDirFile.getPath());  //将上述返回的表单信息提取存入数据库中
            result.put("result", true);
        }
        else{
            result.put("result", false);
        }

        return gson.toJson(result);
    }

    @ResponseBody
    @RequestMapping("/editCourseIdentitlCheck")
    public String editCourseIdentitlCheck(int courseId,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        int teacherId=(int) servletContext.getAttribute("id");
        boolean teacher=(boolean) servletContext.getAttribute("teacher");
        boolean teacherState=(boolean) servletContext.getAttribute("state");
        boolean state=false;
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();

        System.out.println("check:"+teacherId+","+courseId+","+teacher+","+teacherState);
        if(courseId==-1) {
            if(teacher&&teacherState) {
                state=true;
            }
        }else {
            if(courseService.checkTeachersCourse(teacherId, courseId)&&teacher&&teacherState) {
                state=true;
            }
        }

        data.put("state", state);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getTeacherCourseMessage")
    public String getTeacherCourseMessage(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<Object,Object>();
        Gson gson=new Gson();
        int teacherId=(int) servletContext.getAttribute("id");
        String pageNum=request.getParameter("pageNum");  //页码
        int page;
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int pageSize=12;  //规定每页显示数据条数
        int row=courseService.getCourseTableRow(teacherId);  //课程总数
        int startPage=page*pageSize;  //偏移量
        int totalPage=(int) Math.ceil(row*1.0/pageSize);  //总页数
        List<Course> courseList=courseService.getCourseMessage(startPage, pageSize, teacherId);
        data.put("courseList", courseList);
        data.put("total", row);
        data.put("pageSize", pageSize);
        data.put("totalPage", totalPage);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getCourseIntroduction")
    public String getCourseIntroduction(int courseId,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int id=(int) servletContext.getAttribute("id");
        boolean teacher=(boolean) servletContext.getAttribute("teacher");
        boolean state = false;
        if(teacher) {
            state=true;
            data.put("course",courseService.findBy(courseId));
            data.put("abstract", courseAbstractService.findBy(courseId));
            data.put("structure", courseStructureService.findBy(courseId));
        }
        data.put("state", state);
        System.out.println("data:"+data);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/checkTeacherCourse")
    public String checkTeacherCourse(HttpServletRequest servletRequest){
        ServletContext servletContext = servletRequest.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int teacherId=(int) servletContext.getAttribute("id");
        int total=courseService.getTeacherAllCourse(teacherId).size();
        data.put("total", total);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getCourseIdName")
    public String getCourseIdName(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        int teacherId=(int) servletContext.getAttribute("id");
        Gson gson=new Gson();
        Map<Object,Object> data=new HashMap<>();
        List<Course> courseList=courseService.getTeacherAllCourse(teacherId);
        data.put("state", servletContext.getAttribute("state"));
        data.put("courseList", courseList);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/saveTest")
    public String saveTest(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        int teacherId=(int) servletContext.getAttribute("id");
        Gson gson=new Gson();
        Map<String,String> data = new HashMap<>();

        /**
         * 表单数据
         */
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name=parameterNames.nextElement();
            data.put(name,request.getParameter(name));
        }
        testEntry(teacherId, data);

        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getTestMessage")
    public String getTestMessage(int pageNum,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Gson gson=new Gson();
        Map<Object,Object> data=new HashMap<>();
        int teacherId=(int) servletContext.getAttribute("id");
        int pageSize=12;
        int row=itemBackService.getTeacherTestRow(teacherId);
        int startPage=pageNum*pageSize;
        int totalPage=(int) Math.ceil(row*1.0/pageSize);
        data.put("total", row);
        data.put("pageSize", pageSize);
        data.put("totalPage", totalPage);
        List<ItemBack> listItemBacks=itemBackService.getTeacherTestMessage(startPage, pageSize,teacherId);
        data.put("testList", listItemBacks);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/editTestIdentitlCheck")
    public String editTestIdentitlCheck(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int testId=Integer.parseInt(request.getParameter("testId"));
        int teacherId=(int)servletContext.getAttribute("id");
        boolean status=itemBackService.checkTeachersTest(testId, teacherId);
        data.put("status", status);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getTestIntroduction")
    public String getTestIntroduction(int testId){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        data.put("test", itemBackService.findBy(testId));
        List<TestQuestion> testQuestionList = testQuestionService.getTestQuestion(testId);
        data.put("question",testQuestionList );
        data.put("questionTotal", testQuestionList.size());
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/saveCourseResource")
    public String saveCourseResource(HttpServletRequest request,@RequestParam("files") MultipartFile[] files){
        ServletContext servletContext = request.getServletContext();
        Map<String,String> data=new HashMap<>();
        Gson gson=new Gson();
        int id=(int) servletContext.getAttribute("id");
        boolean teacher= (boolean) servletContext.getAttribute("teacher");

        File photoDirFile= commonConfig.getPhotoDirFile();
        File vedioDirFile= commonConfig.getVideoDirFile();
        File fileDirFile= commonConfig.getFileDirFile();

        // 表单数据
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name=parameterNames.nextElement();
            System.out.println("*"+name+"***"+request.getParameter(name));
            data.put(name,request.getParameter(name));
        }

        // 上传文件数据
        Map<String,MultipartFile> fileMap = new HashMap<>();
        for (MultipartFile file : files) {
            System.out.println("fileOldName:" + file.getOriginalFilename()+",:"+file);
            fileMap.put(file.getOriginalFilename(),file);
        }

        Map<Object,Object> result=new HashMap<>();
        ResourceUploadAndDownload resourceUploadAndDownload=new ResourceUploadAndDownload(photoDirFile, vedioDirFile,fileDirFile);
        data.putAll(resourceUploadAndDownload.resourceUpload(fileMap,id));  //将表单解析,整理资源并存储,将表单信息放入 Map 中并返回
        System.out.println("resource:"+data);
        courseResourceService.courseResourceEntry(id,teacher,data);  //将上述返回的表单信息提取存入数据库中
        result.put("result", true);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getCourseResource")
    public String getCourseResource(String pageNum,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int userType=0;
        int userId=(int) servletContext.getAttribute("id");
        boolean teacher=(boolean) servletContext.getAttribute("teacher");
        if(teacher) {
            userType=1;
        }

        int pageSize=12;  //每页数据最大行数
        int row=courseResourceService.getUserCourseResourceRow(userId, userType);  //总数据量
        int page;  //第几页
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int startPage=page*pageSize;
        int totalPage=(int)Math.ceil(row*1.0/pageSize);  //总页数

        List<CourseResource> courseResouceList=courseResourceService.getCourseResource(userId, userType,startPage,pageSize);
        data.put("courseResouceList", courseResouceList);
        data.put("total",row);
        data.put("pageSize", pageSize);
        data.put("totalPage", totalPage);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getAppointUserAchievementInformation")
    public String getAppointUserAchievementInformation(String pageNum,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        int page=0;
        int pageSize=12;
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int startPage=page*pageSize;
        int userId=(int) servletContext.getAttribute("id");
        int row=userAchievementService.getAppointUserAchievementRow(userId);
        int totalPage=(int) Math.ceil(row*1.0/pageSize);
        Map<Object,Object> data=new HashMap<>();
        data.put("list",userAchievementService.getPartUserAchievement(userId,startPage,pageSize));
        data.put("totalPage", totalPage);
        data.put("total", row);
        data.put("pageSize", pageSize);
        Gson gson=new Gson();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getUserAppointTestAnswer")
    public String getUserAppointTestAnswer(int testId,String answerSheetId,HttpServletRequest request){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int userId=(int) request.getAttribute("id");
        List<TestQuestion> testQuestionList = testQuestionService.getTestQuestionInfo(testId);
        data.put("results", userResultsService.getTestQuestionAndAnswer(answerSheetId,testQuestionList));
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/saveAnswerSheetId")
    public String saveAnswerSheetId(int testId,String answerSheetId,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("testId",testId);
        servletContext.setAttribute("answerSheetId",answerSheetId);
        servletContext.setAttribute("isTest",false);
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        data.put("results", true);
        return gson.toJson(data);
    }

    private void courseEntry(int teacherId,Map<String,String> data,String saveVideoPath) { //课程数据保存至数据库
        int courseId=0;
        boolean edit=Boolean.parseBoolean((String)data.get("edit"));
        if(edit) {
            courseId=Integer.parseInt((String)data.get("courseId"));
            courseService.changeCourse((String)data.get("courseName"),data.get(data.get("courseImg-hidden")),Integer.parseInt((String) data.get("courseType")), courseId);
        }else {
            Course course=new Course(data.get("courseName"),data.get(data.get("courseImg-hidden")),Integer.parseInt((String) data.get("courseType")),teacherId);
            courseService.addCourse(course);  //课程基本信息
            courseId=course.getCourseId();
            System.out.println("courseId:"+courseId);
        }

        for(int i=1;i<=3;i++) {  //课程简介
            String str="courseAbstract-"+i;
            String courseIntroduce=(String) data.get(str);
            if(courseIntroduce!=null&&courseIntroduce.length()>0) {
                System.out.println("courseIntroduce:"+courseIntroduce);
                courseAbstractService.saveCourseAbstract(i,courseIntroduce,courseId);
            }else {
                if(edit) {
                    courseAbstractService.delCourseAbstract(i, courseId);
                }
                System.out.println("不存在第"+i+"段简介");
            }
        }
        int chapterId=1;
        for(;data.get("chapter-"+chapterId+"-name")!=null;chapterId++) {  //章节判断
            int sectionId=1;
            for(;data.get("chapter-"+chapterId+"-section-"+sectionId+"-name")!=null;sectionId++) {  //小节判断
                String chapterName=(String) data.get("chapter-"+chapterId+"-name");
                String sectionName=(String) data.get("chapter-"+chapterId+"-section-"+sectionId+"-name");
                String mvAdd=(String) data.get("chapter-"+chapterId+"-section-"+sectionId+"-video");
                courseStructureService.saveCourseChapter(courseId,chapterId, chapterName, sectionId, sectionName, data.get(data.get("chapter-"+chapterId+"-section-"+sectionId+"-video-hidden")));
            }
            if(edit) {
                courseStructureService.delMV(Integer.parseInt((String)data.get("courseId")), chapterId, sectionId, saveVideoPath);
                courseStructureService.delCourseSection(Integer.parseInt((String)data.get("courseId")), chapterId, sectionId);
            }

        }
        if(edit) {
            courseStructureService.delMV(Integer.parseInt((String)data.get("courseId")), chapterId, saveVideoPath);
            courseStructureService.delCourseChapter(Integer.parseInt((String)data.get("courseId")), chapterId);
        }

    }

    private void testEntry(int teacherId,Map<String,String> data) {  //测试卷数据保存至数据库
        int testId = 0;
        boolean edit=Boolean.parseBoolean(data.get("edit"));
        if(edit) {
            testId=Integer.parseInt(data.get("testId"));
            itemBackService.updateItemBack(Integer.parseInt( data.get("courseId")),
                    data.get("testName"),
                    Integer.parseInt(data.get("totalScore")) ,
                    Integer.parseInt(data.get("passLine")),
                    testId);
        }else {
            ItemBack itemBack=new ItemBack(Integer.parseInt(data.get("courseId")),data.get("testName"),Integer.parseInt(data.get("totalScore")),Integer.parseInt(data.get("passLine")),teacherId);
            itemBackService.addItemBack(itemBack);  //保存测试卷基本信息
            testId=itemBack.getTestId();
        }
        int questionNumber=1;
        int optionNumber=1;
        for(;questionNumber>0;questionNumber++) {  //测试卷题目分值正确选项等
            String questionContent=data.get("question-"+questionNumber);
            if(questionContent==null||questionContent.length()==0) {  //不存在第i题,则表明数据已读取完毕
                break;
            }
            int rightKey=Integer.parseInt(data.get("question-"+questionNumber+"-right-key"));
            String analysis=data.get("question-"+questionNumber+"-analysis");
            int score=Integer.parseInt(data.get("question-"+questionNumber+"-score"));
            testQuestionService.addTestQuestion(testId,questionNumber,questionContent,rightKey,analysis,score);
            optionNumber=1;
            for(;optionNumber<=4;optionNumber++) {
                String optionContent=data.get("question-"+questionNumber+"-option-"+optionNumber);
                int questionId=testQuestionService.findBy(testId, questionNumber);
                testOptionsService.saveTestOption(questionId, optionNumber, optionContent);
            }
        }
        if(edit) {  //先删选项再删问题

            List<Integer> questionIdList=testQuestionService.findAfterBy(testId, questionNumber);
            testOptionsService.delTestOption(questionIdList);
            testQuestionService.delTestQuestion(testId, questionNumber);
            System.out.println("不存在第"+questionNumber+"题");
        }

    }
}
