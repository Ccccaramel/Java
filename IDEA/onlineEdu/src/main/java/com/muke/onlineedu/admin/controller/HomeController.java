package com.muke.onlineedu.admin.controller;

import com.google.gson.Gson;
import com.muke.onlineedu.admin.entity.*;
import com.muke.onlineedu.admin.service.*;
import com.muke.onlineedu.common.service.CourseTypeService;
import com.muke.onlineedu.common.tool.CommonConfig;
import com.muke.onlineedu.common.tool.ResourceUploadAndDownload;
import com.muke.onlineedu.common.tool.ServerConfig;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.math.BigInteger;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@RestController
public class HomeController {

    @Autowired
    TeacherService teacherService;
    @Autowired
    StudentService studentService;
    @Autowired
    SuggestionsService suggestionsService;
    @Autowired
    CoursePromotionService coursePromotionService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseTypeService courseTypeService;
    @Autowired
    private ServerConfig serverConfig;
    @Autowired
    CourseResourceService courseResourceService;
    @Autowired
    FavoriteService favoriteService;
    @Autowired
    ItemBackService itemBackService;
    @Autowired
    CourseStructureService courseStructureService;
    @Autowired
    NoteService noteService;
    @Autowired
    TestQuestionService testQuestionService;
    @Autowired
    UserAchievementService userAchievementService;
    @Autowired
    UserResultsService userResultsService;
    @Autowired
    CommonConfig commonConfig;

    public String getUrl() {
        return  serverConfig.getUrl();
    }

    @RequestMapping("/test")
    public String test(){
//        return "/homepage/home"; // 要想使用此方法访问页面那么控制器类的注解必须为 @Controller
        return "/homepage/home";
    }

    // Thymeleaf 视图解析器默认配置的位置:templates,所以你的 html 文件必须放在该文件夹下
    @RequestMapping("/")
    public ModelAndView home(){
//        return "/homepage/home"; // 要想使用此方法访问页面那么控制器类的注解必须为 @Controller
        return new ModelAndView("/homepage/home");
    }

    @ResponseBody
    @RequestMapping("/userState")
    public String userState(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        if(servletContext.getAttribute("state")!=null && (boolean)servletContext.getAttribute("state")) {
            boolean teacher=(boolean) servletContext.getAttribute("teacher");
            boolean state=(boolean) servletContext.getAttribute("state");
            data.put("teacher", teacher);
            data.put("state", state);
        }else {
            data.put("state", false);
        }

        Gson gson=new Gson();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/registered")
    public String registered(String userEmail,String userPassword,String profession,
                             String teacherName,String teachersSchool,String teacherIDcard,
                             Integer teacherQualification,String teacherTel,String teachersSchoolEmail){
        boolean result=false;
        Map<Object,Object> data=new HashMap<>();
        if(Boolean.parseBoolean(profession)) {  // 教师注册
            result=teacherService.registered(teacherName, teachersSchool, teacherIDcard, teacherTel, userEmail, teacherQualification, md5(userPassword), teachersSchoolEmail);
        }else {  // 学生注册
            result=studentService.registered(userEmail, md5(userPassword));
        }
        data.put("result", result);
        Gson gson=new Gson();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/login")
    public String login(String account, String  password, Integer loginMode, boolean loginRememberMe, boolean userClass, HttpSession session,HttpServletRequest request, HttpServletResponse response){
        ServletContext servletContext = session.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        Boolean state = false;
        //教师登录
        if(userClass) {
            data.put("type", 1);
            //Email登录
            if(loginMode==1) {
                Teacher user = teacherService.emailLogin(account, md5(password));
                if(user!=null){
                    state=true;
                }
                if(state) {  //存在此账号
                    if(user.getTeacherAccountStatus()==1) {  //帐号状态
                        data.put("state", true);
                        servletContext.setAttribute("teacher", true);
                        servletContext.setAttribute("id", user.getTeacherId());
                        servletContext.setAttribute("state", true);
                        System.out.println("账号以及密码正确");
                        if(loginRememberMe) {  //记住密码
                            Cookie cookieId= new Cookie("account",account);
                            Cookie cookiePassword=new Cookie("password",password);
                            Cookie cookieState=new Cookie("state","true");
                            Cookie cookieTeacher=new Cookie("teacher","true");
                            Cookie cookieLoginRememberMe=new Cookie("loginRememberMe","true");
                            cookieId.setMaxAge(30);
                            cookiePassword.setMaxAge(30);
                            cookieState.setMaxAge(30);
                            cookieTeacher.setMaxAge(30);
                            cookieLoginRememberMe.setMaxAge(30);
                            response.addCookie(cookieId);
                            response.addCookie(cookiePassword);
                            response.addCookie(cookieState);
                            response.addCookie(cookieTeacher);
                            response.addCookie(cookieLoginRememberMe);
                            System.out.println("已勾选记住密码");
                        }
                    }else {
                        System.out.println("账号以及密码正确但账号状态不正常");
                        data.put("state", false);
                    }
                }else {
                    System.out.println("账号或密码不正确");
                }
            }
            // Tel 登录
            else if(loginMode==2) {
                Teacher user = teacherService.telLogin(account, md5(password));
                if(user!=null){
                    state=true;
                }
                if(state) {  //存在此账号
                    if(user.getTeacherAccountStatus()==1) {  //帐号状态
                        data.put("state", true);
                        servletContext.setAttribute("teacher", true);
                        servletContext.setAttribute("id", user.getTeacherId());
                        servletContext.setAttribute("state", true);
                        System.out.println("账号以及密码正确");
                        if(loginRememberMe) {  //记住密码
                            Cookie cookieId= new Cookie("account",account);
                            Cookie cookiePassword=new Cookie("password",password);
                            Cookie cookieState=new Cookie("state","true");
                            Cookie cookieTeacher=new Cookie("teacher","true");
                            Cookie cookieLoginRememberMe=new Cookie("loginRememberMe","true");
                            cookieId.setMaxAge(30);
                            cookiePassword.setMaxAge(30);
                            cookieState.setMaxAge(30);
                            cookieTeacher.setMaxAge(30);
                            cookieLoginRememberMe.setMaxAge(30);
                            response.addCookie(cookieId);
                            response.addCookie(cookiePassword);
                            response.addCookie(cookieState);
                            response.addCookie(cookieTeacher);
                            response.addCookie(cookieLoginRememberMe);
                            System.out.println("已勾选记住密码");
                        }
                    }else {
                        System.out.println("账号以及密码正确但账号状态不正常");
                        data.put("state", false);
                    }
                }else {
                    System.out.println("Tel账号或密码不正确");
                }
            }
            // Id 登录
            else if(loginMode==3) {
                Teacher user = teacherService.idLogin(Integer.parseInt(account), md5(password));
                if(user!=null){
                    state=true;
                }
                if(state) {  //存在此账号
                    if(user.getTeacherAccountStatus()==1) {  //帐号状态
                        data.put("state", true);
                        servletContext.setAttribute("teacher", true);
                        servletContext.setAttribute("id", user.getTeacherId());
                        servletContext.setAttribute("state", true);
                        System.out.println("账号以及密码正确");
                        if(loginRememberMe) {  //记住密码
                            Cookie cookieId= new Cookie("account",account);
                            Cookie cookiePassword=new Cookie("password",password);
                            Cookie cookieState=new Cookie("state","true");
                            Cookie cookieTeacher=new Cookie("teacher","true");
                            Cookie cookieLoginRememberMe=new Cookie("loginRememberMe","true");
                            cookieId.setMaxAge(30);
                            cookiePassword.setMaxAge(30);
                            cookieState.setMaxAge(30);
                            cookieTeacher.setMaxAge(30);
                            cookieLoginRememberMe.setMaxAge(30);
                            response.addCookie(cookieId);
                            response.addCookie(cookiePassword);
                            response.addCookie(cookieState);
                            response.addCookie(cookieTeacher);
                            response.addCookie(cookieLoginRememberMe);
                            System.out.println("已勾选记住密码");
                        }
                    }else {
                        System.out.println("账号以及密码正确但账号状态不正常");
                        data.put("state", false);
                    }
                }else {
                    System.out.println("Id-账号或密码不正确");
                }
            }
        }
        //学生登录
        else {
            data.put("type", 0);
            //Email登录
            if(loginMode==1) {
                Student user = studentService.emailLogin(account, md5(password));
                if(user!=null){
                    state=true;
                }
                if(state) {  //存在此账号
                    if(user.getUserAccountStatus()==1) {  //帐号状态
                        data.put("state", true);
                        servletContext.setAttribute("teacher", false);
                        servletContext.setAttribute("id", user.getUserId());
                        servletContext.setAttribute("state", true);
                        System.out.println("账号以及密码正确");
                        if(loginRememberMe) {  //记住密码
                            Cookie cookieId= new Cookie("account",account);
                            Cookie cookiePassword=new Cookie("password",password);
                            Cookie cookieState=new Cookie("state","true");
                            Cookie cookieTeacher=new Cookie("teacher","false");
                            Cookie cookieLoginRememberMe=new Cookie("loginRememberMe","true");
                            cookieId.setMaxAge(30);
                            cookiePassword.setMaxAge(30);
                            cookieState.setMaxAge(30);
                            cookieTeacher.setMaxAge(30);
                            cookieLoginRememberMe.setMaxAge(30);
                            response.addCookie(cookieId);
                            response.addCookie(cookiePassword);
                            response.addCookie(cookieState);
                            response.addCookie(cookieTeacher);
                            response.addCookie(cookieLoginRememberMe);
                            System.out.println("已勾选记住密码"+cookiePassword.getValue());
//                            for(Cookie c:response.) {
//								String name=c.getName();  //获取cookie名称
//								System.out.println(name+":"+c.getValue());
//							}
                        }
                    }else {
                        System.out.println("账号以及密码正确但账号状态不正常");
                        data.put("state", false);
                    }
                }else {
                    System.out.println("Email-账号或密码不正确");
                }
            }
            //Tel登录
            else if(loginMode==2) {
                Student user = studentService.telLogin(account, md5(password));
                if(user!=null){
                    state=true;
                }
                if(state) {  //存在此账号
                    if(user.getUserAccountStatus()==1) {  //帐号状态
                        data.put("state", true);
                        servletContext.setAttribute("teacher", false);
                        servletContext.setAttribute("id", user.getUserId());
                        servletContext.setAttribute("state", true);
                        System.out.println("账号以及密码正确");
                        if(loginRememberMe) {  //记住密码
                            Cookie cookieId= new Cookie("account",account);
                            Cookie cookiePassword=new Cookie("password",password);
                            Cookie cookieState=new Cookie("state","true");
                            Cookie cookieTeacher=new Cookie("teacher","false");
                            Cookie cookieLoginRememberMe=new Cookie("loginRememberMe","true");
                            cookieId.setMaxAge(30);
                            cookiePassword.setMaxAge(30);
                            cookieState.setMaxAge(30);
                            cookieTeacher.setMaxAge(30);
                            cookieLoginRememberMe.setMaxAge(30);
                            response.addCookie(cookieId);
                            response.addCookie(cookiePassword);
                            response.addCookie(cookieState);
                            response.addCookie(cookieTeacher);
                            response.addCookie(cookieLoginRememberMe);
                            System.out.println("已勾选记住密码");
                        }
                    }else {
                        System.out.println("账号以及密码正确但账号状态不正常");
                        data.put("state", false);
                    }
                }else {
                    System.out.println("Tel-账号或密码不正确");
                }
            }
            //Id登录
            else if(loginMode==3) {
                Student user = studentService.idLogin(Integer.parseInt(account), md5(password));
                if(user!=null){
                    state=true;
                }
                if(state) {  //存在此账号
                    if(user.getUserAccountStatus()==1) {  //帐号状态
                        data.put("state", true);
                        servletContext.setAttribute("teacher", false);
                        servletContext.setAttribute("id", user.getUserId());
                        servletContext.setAttribute("state", true);
                        System.out.println("账号以及密码正确");
                        if(loginRememberMe) {  //记住密码
                            Cookie cookieId= new Cookie("account",account);
                            Cookie cookiePassword=new Cookie("password",password);
                            Cookie cookieState=new Cookie("state","true");
                            Cookie cookieTeacher=new Cookie("teacher","false");
                            Cookie cookieLoginRememberMe=new Cookie("loginRememberMe","true");
                            cookieId.setMaxAge(300);
                            cookiePassword.setMaxAge(300);
                            cookieState.setMaxAge(300);
                            cookieTeacher.setMaxAge(300);
                            cookieLoginRememberMe.setMaxAge(300);
                            response.addCookie(cookieId);
                            response.addCookie(cookiePassword);
                            response.addCookie(cookieState);
                            response.addCookie(cookieTeacher);
                            response.addCookie(cookieLoginRememberMe);
                            System.out.println("已勾选记住密码");
                        }
                    }else {
                        System.out.println("账号以及密码正确但账号状态不正常");
                        data.put("state", false);
                    }
                }else {
                    System.out.println("Id-账号或密码不正确");
                }
            }
        }

        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        // 创建一个 cookie对象
        Cookie cookie = new Cookie("username", "Jovan");
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        //将cookie对象加入response响应
        response.addCookie(cookie);

        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/cookieExamine")
    public String cookieExamine(@CookieValue(value = "username", defaultValue = "Atta") String username,HttpServletRequest request, HttpServletResponse response){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        Cookie[] cookies=request.getCookies();

        Cookie mycookies[] = request.getCookies();
        if(mycookies!=null){
            for(Cookie c:mycookies) {
                String name=c.getName();  //获取cookie名称
                System.out.println(name+":"+c.getValue());
            }
        }


        System.out.println(">"+username);

        boolean state=false;
        String account = null;
        boolean teacher = false;
        boolean RememberMe = false;
        String password = null;
        if(cookies!=null) {
            for(Cookie c:cookies) {
                String name=c.getName();  //获取cookie名称
                if("account".equals(name)) {
                    account=c.getValue();  //获取cookie值并存入
                }
                if("password".equals(name)) {
                    password = c.getValue();  //获取cookie值并存入
                }
                if("teacher".equals(name)) {
                    teacher=Boolean.valueOf(c.getValue()).booleanValue();  //获取cookie值并存入
                }
                if("RememberMe".equals(name)) {
                    RememberMe=Boolean.valueOf(c.getValue()).booleanValue();  //获取cookie值并存入
                }
                if("state".equals(name)) {
                    state=Boolean.valueOf(c.getValue()).booleanValue();
                }
            }
        }else {
            state=false;
        }
        data.put("state", state);
        if(state==true) {
            data.put("account", account);
            data.put("password", password);
            data.put("teacher", teacher);
            data.put("loginRememberMe", RememberMe);
            data.put("message", "你的账号密码已保存在cookie中");
        }else {
            data.put("message", "Cookie未记住密码!");
        }
        System.out.println("获取cookie"+data);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/saveFeedBack")
    public String saveFeedBack(String messageText,HttpServletRequest request){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();

        ServletContext servletContext = request.getServletContext();
        int userId=-1;
        if(servletContext.getAttribute("state")!=null) {
            userId=(int) servletContext.getAttribute("id");
        }
        suggestionsService.addSuggestions(userId,messageText);

        data.put("status",true);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getPromotionCourse")
    public String getPromotionCourse(){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        data.put("promotionCourseList",coursePromotionService.getAllCoursePromotionMessage(commonConfig.getPhotoPath()));
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/homeGetCourseType")
    public String homeGetCourseType(String amount){
        int amountNum;
        if(amount==null) {
            amountNum=1;
        }else {
            amountNum=Integer.parseInt(amount);
        }
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        data.put("partCourseTypeList",courseTypeService.homeGetCourseType(amountNum));
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getPopularCourses")
    public String getPopularCourses(){
        int amount=6;
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        data.put("popularCourseList",courseService.getPopularCourses(commonConfig.getPhotoPath(),amount));
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getNewCourses")
    public String getNewCourses(){
        int amount=6;
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        data.put("popularCourseList",courseService.getNewCourses(commonConfig.getPhotoPath(),amount));
        return gson.toJson(data);
    }
    @ResponseBody
    @RequestMapping("/getITCourses")
    public String getITCourses(){
        int amount=6;
        int conurseType=1006;
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        data.put("itCourseList",courseService.getITCourses(commonConfig.getPhotoPath(),conurseType,amount));
        return gson.toJson(data);
    }
    @ResponseBody
    @RequestMapping("/getLiteraryHistoryCourses")
    public String getLiteraryHistoryCourses(){
        int amount=6;
        int conurseType=1020;
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        data.put("historyCourseList",courseService.getITCourses(commonConfig.getPhotoPath(),conurseType,amount));
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/searchResource")
    public String searchResource(String pageNum,String key){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();

        int page=0;
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int pageSize=12;
        int startPage=page*pageSize;
        int row=courseResourceService.searchCourseResourceRow(key);
        int totalPage=(int) Math.ceil(row*1.0/pageSize);

        data.put("resourceList", courseResourceService.searchCourseResource(startPage, pageSize, key));
        data.put("total", row);
        data.put("totalPage", totalPage);
        data.put("page", page);
        data.put("url", commonConfig.getUrl()+"/download/");
        return gson.toJson(data);
    }

    @RequestMapping("/download/{resourceId}")
    public Object downloadFile(@PathVariable("resourceId")int resourceId, HttpServletResponse response, HttpServletRequest request){
        OutputStream os = null;
        InputStream is= null;

        CourseResource courseResource=courseResourceService.findBy(resourceId);
        String resourceName=courseResource.getResourceName();
        String suffix=resourceName.substring(resourceName.lastIndexOf(".")+1);  //只保留文件名部分resourceId
        int type= ResourceUploadAndDownload.suffixJudgment(suffix);
        System.out.println("resourceName:"+resourceName);
        String realPath = null;
        if(type==0){ // 图片
            realPath= commonConfig.getRealPhotoPath();
        }else if(type==1){ // 视频
            realPath= commonConfig.getRealVideoPath();
        }else if(type==2){ // 压缩文件
            realPath= commonConfig.getRealFilePath();
        }else if(type==3){ // 文本
            realPath= commonConfig.getRealFilePath();
        }
        try {
            // 取得输出流
            os = response.getOutputStream();
            // 清空输出流
            response.reset();
            response.setHeader("content-type", "application/octet-stream");
            response.setContentType("application/octet-stream");
            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode(resourceName,"UTF-8"));

            //读取流
            File f = new File(realPath+courseResource.getLinkAdd());
            is = new FileInputStream(f);
            if (is == null) {
                System.out.println("下载附件失败，请检查文件\"" + resourceName + "\"是否存在");
            }
            //复制
            IOUtils.copy(is, response.getOutputStream());
            response.getOutputStream().flush();
        } catch (IOException e) {
            System.out.println("下载附件失败,error:"+e.getMessage());
        }
        //文件的关闭放在finally中
        finally
        {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
//                logger.error(ExceptionUtils.getFullStackTrace(e));
            }
            try {
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
//                logger.error(ExceptionUtils.getFullStackTrace(e));
            }
        }
        return null;
    }

    @ResponseBody
    @RequestMapping("/sourchCourse")
    public String sourchCourse(String pageNum,String key){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int page=0;
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int pageSize=12;
        int startPage=page*pageSize;
        int row=courseService.getSearchCourseRow(key);
        int totalPage=(int) Math.ceil(row*1.0/pageSize);

        data.put("courseList", courseService.searchCourse(commonConfig.getPhotoPath(),key, startPage, pageSize));
        data.put("total", row);
        data.put("totalPage", totalPage);
        data.put("page", page);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/heatsUp")
    public String heatsUp(int courseId){
        Map<Object,Object> data=new HashMap<>();
        courseService.heatsUp(courseId);
        Gson gson=new Gson();
        data.put("result",true);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getAppointCourseIntroduction")
    public String getAppointCourseIntroduction(int courseId,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        Course course=new Course();
        course=courseService.getAppointCourseMessage(commonConfig.getPhotoPath(),courseId);
        data.put("course", course);
        Map<Integer,Map<Integer,Object>> chapterList=new HashMap<>();

        if(servletContext.getAttribute("id") != null) {
            data.put("pass", favoriteService.checkFavorite((int)servletContext.getAttribute("id"), courseId));
        }
        for(CourseStructure courseStructure :course.getCourseStructures()){
            if(courseStructure.getSectionId()==1){ // 第?章第1节
                Map<Integer,Object> sectionList=new HashMap<>(); // 存放小节
                sectionList.put(courseStructure.getSectionId(),courseStructure);
                chapterList.put(courseStructure.getChapterId(),sectionList);
            }
            else {
                chapterList.get(courseStructure.getChapterId()).put(courseStructure.getSectionId(),courseStructure);
            }
        }
        data.put("courseStatus", course!=null);
        data.put("chapterList", chapterList);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getAppointCourseResource")
    public String getAppointCourseResource(int courseId,int userType){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        List<CourseResource> courseResourceList = courseResourceService.getAppointCourseResource(courseId, userType);
        data.put("count",courseResourceList.size());
        data.put("courseResourceList", courseResourceList);
        data.put("url", commonConfig.getUrl()+"/download/");
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getAppointTestInformation")
    public String getAppointTestInformation(int courseId){
        List<ItemBack> itemBackList=new ArrayList<>();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        itemBackList=itemBackService.getAppointTestInformation(courseId);
        data.put("count", itemBackList.size());
        data.put("itemBackList", itemBackList);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/changeCollectionCourse")
    public String changeCollectionCourse(boolean sign,int courseId,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int userId;
        if(servletContext.getAttribute("state")!=null&&(boolean) servletContext.getAttribute("state")) {
            if((boolean) servletContext.getAttribute("teacher")) {
                data.put("pass", false);
                data.put("message", "抱歉，教师暂未开放此功能，您可以注册一个普通账号进行操作!");
            }else {
                data.put("pass", true);
                userId=(int) servletContext.getAttribute("id");
                if(!sign) {
                    favoriteService.addFavorite(userId, courseId);
                    data.put("result", true);
                    data.put("pass", true);
                    data.put("message", "收藏成功!");
                }else if(sign) {
                    favoriteService.delFavorite(userId, courseId);
                    data.put("result", true);
                    data.put("pass", false);
                    data.put("message", "收藏取消!");
                }else {
                    data.put("result", false);
                }
            }
        }else {
            data.put("pass", false);
            data.put("message", "请登录后再操作!");
        }
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/saveVideoMessage")
    public String saveVideoMessage(int courseId,int chapterId,int sectionId,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        servletContext.setAttribute("courseId", courseId);
        servletContext.setAttribute("chapterId", chapterId);
        servletContext.setAttribute("sectionId", sectionId);
        Gson gson=new Gson();
        Map<Object,Object> data=new HashMap<>();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getVideoMessage")
    public String getVideoMessage(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        if(servletContext.getAttribute("courseId")==null||servletContext.getAttribute("chapterId")==null||servletContext.getAttribute("sectionId")==null){
            data.put("state", false);
            return gson.toJson(data);
        }
        int courseId=(int) servletContext.getAttribute("courseId");
        int chapterId=(int) servletContext.getAttribute("chapterId");
        int sectionId=(int) servletContext.getAttribute("sectionId");
        String mvAdd=courseStructureService.getSectionInfo(courseId,chapterId,sectionId).getMvAdd();
        data.put("courseId", courseId);
        data.put("chapterId", chapterId);
        data.put("sectionId", sectionId);
        data.put("mvAdd", mvAdd);
        data.put("state", true);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/learnDataImport")
    public String learnDataImport(int courseId, int chapterId,int sectionId){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        Course course=courseService.findBy(courseId);
        CourseStructure courseStructure=courseStructureService.getSectionInfo(courseId, chapterId, sectionId);
        String mvAdd= commonConfig.getVideoPath()+courseStructure.getMvAdd();

        int previousChapterId=0;
        int previousSectionId=0;
        if(sectionId==1){
            if(chapterId==1) {
                //无 <上一节>
                previousChapterId=0;
                previousSectionId=0;
            }else {
                previousChapterId=chapterId-1;
                previousSectionId=courseStructureService.getChaptersSectionRow(courseId, previousChapterId);
            }
        }else {
            previousChapterId=chapterId;
            previousSectionId=sectionId-1;
        }

        int nextChapterId=0;
        int nextSectionId=0;
        if(courseStructureService.getChaptersSectionRow(courseId, chapterId)==sectionId) {  //若本小节为本章的最后一小节
            if(courseStructureService.checkChapterExist(courseId, chapterId+1)) {
                nextChapterId=chapterId+1;
                nextSectionId=1;
            }else {
                // 无<下一节>
                nextChapterId=0;
                nextSectionId=0;

            }
        }else {
            nextChapterId=chapterId;
            nextSectionId=sectionId+1;
        }

        data.put("course",course);
        data.put("courseStructure",courseStructure);


        data.put("mvAdd", mvAdd);

        data.put("previousChapterId",previousChapterId);
        data.put("previousSectionId",previousSectionId);
        data.put("nextChapterId",nextChapterId);
        data.put("nextSectionId",nextSectionId);

        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getCourseNote")
    public String getCourseNote(int courseId,String pageNum){

        int page=0;
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int pageSize=6;
        int row=noteService.getNoteRow(courseId);
        int totalPage=(int) Math.ceil(row*1.0/pageSize);
        int startPage=page*pageSize;
        Map<Object,Object> data=new HashMap<>();
        data.put("list",noteService.getNote(courseId, startPage, pageSize));
        data.put("total", row);
        data.put("pageSize", pageSize);
        data.put("totalPage", totalPage);
        Gson gson=new Gson();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/saveNote")
    public String saveNote(String note,int courseId,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        if(servletContext.getAttribute("state")==null) {
            data.put("state", false);
            data.put("message", "请先登录!");
        }else {
            int userType=0;
            if((boolean) servletContext.getAttribute("teacher")) {
                userType=1;
            }
            int userId=(int) servletContext.getAttribute("id");
            noteService.addNote(courseId, userId, userType, note);
            data.put("state", true);
            data.put("message", "分享成功!");
        }

        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/saveCourseId")
    public String saveCourseId(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        if(servletContext.getAttribute("state")!=null&&(boolean)servletContext.getAttribute("state")) {
            if((boolean)servletContext.getAttribute("teacher")) {
                data.put("sate", false);
                data.put("message", "教师上传资源请到教师中心操作!");
            }else {
                int courseId=Integer.parseInt(request.getParameter("courseId"));
                servletContext.setAttribute("courseId", courseId);
                data.put("state", true);
            }
        }else {
            data.put("sate", false);
            data.put("message", "请先登录!");
        }

        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getCourseId")
    public String getCourseId(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int courseId=(int) servletContext.getAttribute("courseId");
        servletContext.removeAttribute("courseId");
        data.put("courseId", courseId);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/userSaveCourseResource")
    public String userSaveCourseResource(HttpServletRequest request,@RequestParam("files") MultipartFile[] files){
        ServletContext servletContext = request.getServletContext();
        int id=(int) servletContext.getAttribute("id");
        boolean teacher= (boolean) servletContext.getAttribute("teacher");
        Map<String,String> data=new HashMap<>();
        Gson gson=new Gson();


        // 表单数据
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name=parameterNames.nextElement();
            System.out.println("*"+name+"***"+request.getParameter(name));
            data.put(name,request.getParameter(name));
        }

        // 上传文件数据
        Map<String, MultipartFile> fileMap = new HashMap<>();
        for (MultipartFile file : files) {
            System.out.println("fileOldName:" + file.getOriginalFilename()+",:"+file);
            fileMap.put(file.getOriginalFilename(),file);
        }
        File photoDirFile= commonConfig.getPhotoDirFile();
        File vedioDirFile= commonConfig.getVideoDirFile();
        File fileDirFile= commonConfig.getFileDirFile();
        Map<Object,Object> result=new HashMap<>();
        ResourceUploadAndDownload resourceUploadAndDownload=new ResourceUploadAndDownload(photoDirFile, vedioDirFile,fileDirFile);
        data.putAll(resourceUploadAndDownload.resourceUpload(fileMap,id));  //将表单解析,整理资源并存储,将表单信息放入 Map 中并返回
        System.out.println("resource:"+data);
        courseResourceService.courseResourceEntry(id,teacher,data);  //将上述返回的表单信息提取存入数据库中
        result.put("result", true);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/accountChecking")
    public String accountChecking(int testId,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        boolean pass=false;
        if(servletContext.getAttribute("state")!=null&&(boolean) servletContext.getAttribute("state") && !(boolean) servletContext.getAttribute("teacher")) {
            pass=true;
            servletContext.setAttribute("testId",testId);
            servletContext.setAttribute("isTest",true);
        }else {
            data.put("message", "仅限已登录的学生测试!");
        }
        data.put("pass", pass);
        data.put("isTest", true);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getTest")
    public String getTest(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();

        boolean status=false;
        if(servletContext.getAttribute("state")==null||servletContext.getAttribute("testId")==null) {
            data.put("status", status);
        }else {
            int testId= (int) servletContext.getAttribute("testId");
            int userId=(int) servletContext.getAttribute("id");
            boolean teacher=(boolean) servletContext.getAttribute("teacher");
            boolean state=(boolean) servletContext.getAttribute("state");

            if(!teacher&&state) {
                status=true;
            }
            data.put("status", status);

            data.put("userId", userId);
            data.put("student", studentService.getUserMessage(userId));

            ItemBack itemBack = itemBackService.findBy(testId);
            data.put("test", itemBack);
            data.put("course", courseService.findBy(itemBack.getCourseId()));

            String answerSheetId = (String) servletContext.getAttribute("answerSheetId");
            boolean isTest = (boolean) servletContext.getAttribute("isTest");
            if(answerSheetId==null&&isTest){
                data.put("question", testQuestionService.getTestQuestion(testId));
                data.put("isTest", true);
            }else {
                data.put("question", userResultsService.getTestQuestionAndAnswer(answerSheetId,testQuestionService.getTestQuestion(testId)));
                data.put("isTest", false);
            }

            data.put("testId", testId);
            data.put("answerSheetId", (String)servletContext.getAttribute("answerSheetId"));
            servletContext.removeAttribute("testId");
            servletContext.removeAttribute("answerSheetId");
            servletContext.removeAttribute("isTest");
        }
        System.out.println("data:"+data);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/savePapers")
    public String savePapers(HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        Map<String,String> data=new HashMap<>();
        Gson gson=new Gson();

        // 表单数据
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String name=parameterNames.nextElement();
            data.put(name,request.getParameter(name));
        }

        int userId=(int) servletContext.getAttribute("id");
        userAchievementService.saveUserTest(data,userId);

        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getUserCourseCollection")
    public String getUserCourseCollection(String pageNum,HttpServletRequest request){
        ServletContext servletContext = request.getServletContext();
        int userId=(int) servletContext.getAttribute("id");
        Map<Object,Object> data=new HashMap<>();
        int pageSize=12;  //每页数据最大行数
        int row=favoriteService.getFavoriteCourseRow(userId);
        int page;  //第几页
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int startPage=page*pageSize;
        int totalPage=(int)Math.ceil(row*1.0/pageSize);  //总页数

        data.put("list", favoriteService.getFavoriteCourseMessage(commonConfig.getPhotoPath(),startPage,pageSize,userId));
        data.put("totalPage", totalPage);
        data.put("total", row);
        data.put("pageSize", pageSize);
        Gson gson=new Gson();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/saveCourseTypeId")
    public String saveCourseTypeId(String courseTypeId,HttpSession session){
        ServletContext servletContext = session.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        servletContext.setAttribute("courseTypeId",courseTypeId);
        data.put("result", "success");
        Gson gson=new Gson();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getCourseTypeId")
    public String getCourseTypeId(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        Map<Object,Object> data=new HashMap<>();
        String courseTypeId=(String)servletContext.getAttribute("courseTypeId");
        servletContext.removeAttribute("courseTypeId");
        System.out.println(">>>"+courseTypeId);
        data.put("courseTypeId", courseTypeId);
        Gson gson=new Gson();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/adoptCourseTypeSearchCourse")
    public String adoptCourseTypeSearchCourse(String courseTypeId,String pageNum){
        int courseClass = Integer.parseInt(courseTypeId);  //课程类型id
        Map<Object,Object> data=new HashMap<>();
        int page;
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int pageSize=12;  //规定每页显示数据条数
        int row=0;  //课程总数
        int startPage=page*pageSize;  //偏移量

        List<Course> courseList;
        if(courseClass==-1) {
            row=courseService.getAllValidCourse().size();  //课程总数
            courseList=courseService.getPartValidCourse(commonConfig.getPhotoPath(),startPage,pageSize);
            data.put("typeName", "全部课程");
        }else {
            row=courseService.findAllByAdoptCourseType(courseClass).size();  //课程总数
            courseList=courseService.findByAdoptCourseType(commonConfig.getPhotoPath(),courseClass,startPage,pageSize);
            data.put("typeName", courseTypeService.getTypeName(courseClass));
        }
        int totalPage=(int) Math.ceil(row*1.0/pageSize);  //总页数
        data.put("courseList", courseList);
        data.put("total", row);
        data.put("pageSize", pageSize);
        data.put("totalPage", totalPage);

        Gson gson=new Gson();
        return gson.toJson(data);
    }

    private static String md5(String str) {  // md5 加密
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
}
