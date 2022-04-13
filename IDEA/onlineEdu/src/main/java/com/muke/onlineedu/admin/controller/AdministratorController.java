package com.muke.onlineedu.admin.controller;

import com.google.gson.Gson;
import com.muke.onlineedu.admin.entity.*;
import com.muke.onlineedu.admin.service.*;
import com.muke.onlineedu.common.entity.CourseType;
import com.muke.onlineedu.common.entity.GMPower;
import com.muke.onlineedu.common.service.AccountStatusService;
import com.muke.onlineedu.common.service.CourseTypeService;
import com.muke.onlineedu.common.service.GMPowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdministratorController {

    @Autowired
    AdministratorService administratorService;
    @Autowired
    StudentService studentService;
    @Autowired
    TeacherService teacherService;
    @Autowired
    CourseService courseService;
    @Autowired
    CourseTypeService courseTypeService;
    @Autowired
    ItemBackService itemBackService;
    @Autowired
    CoursePromotionService coursePromotionService;
    @Autowired
    CourseResourceService courseResourceService;
    @Autowired
    GMPowerService gmPowerService;
    @Autowired
    AccountStatusService accountStatusService;
    @Autowired
    SuggestionsService suggestionsService;

    @ResponseBody
    @RequestMapping("/hi")
    public String hi(){
        System.out.println("123");
        return "hi!";
    }

    @ResponseBody
    @RequestMapping("/gmLogin")
    public String gmLogin(String sign, String account,String password, HttpSession session){
        boolean state=false;
        if(sign.equals("id")){
            state = loginById(Integer.parseInt(account), password);
        }else if(sign.equals("tel")){
            state= loginByTel(account,password);
        }else if(sign.equals("email")){
            state= loginByEmail(account,password);
        }
        Map<Object,Object> data= new HashMap<>();
        data.put("state",state );
        data.put("GM",true);
        Admin user=administratorService.getUser();// 获取用户对象
        Gson gson=new Gson();

        if(state) {  // 账号存在
            ServletContext servletContext = session.getServletContext();  // 使用 resuest 对象的 getSession() 获取 session ,若 session 不存在则创建一个
            servletContext.setAttribute("application1","a1");
            String userJson=gson.toJson(user); //  对象转 JSON
            servletContext.setAttribute("userJson", userJson);
        }
        data.put("sign", administratorService.CheckAccountStatus(user.getGmAccountStatus()));
        String result=gson.toJson(data);
        return result;
    }

    @ResponseBody
    @RequestMapping("/getGMMessage")
    public String getGMMessage(HttpSession session){
        Gson gson=new Gson();
        Map<Object,Object> data=new HashMap<>();

        if(session.isNew()) {
            System.out.println("new");
        }else {
            System.out.println("old"+session);
        }
        ServletContext servletContext = session.getServletContext();
        String userJson=(String) servletContext.getAttribute("userJson");
        Admin user=gson.fromJson(userJson, Admin.class);  // 把 JSON 转换成对象
        boolean state=false;
        if(user==null) {
            System.out.println("user is null");

        }else {
            state=true;
            data.put("gmId",user.getGmId());
            data.put("gmEmail",user.getGmEmail());
            data.put("gmTel", user.getGmTel());
            data.put("gmPowerNo",user.getGmPower());
            data.put("gmPower",  user.getGmPowerClass());
        }
        data.put("state", state);
        String result=gson.toJson(data);
        return result;
    }

    @ResponseBody
    @RequestMapping("/loginCheck")
    public String loginCheck(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String userJson=(String) servletContext.getAttribute("userJson");
        Gson gson=new Gson();
        Admin user=gson.fromJson(userJson, Admin.class);
        if(user==null){
            return gson.toJson(905);
        }
        int gmId=user.getGmId();
        int state=administratorService.stateCheck(gmId);
        return gson.toJson(state);
    }

    @ResponseBody
    @RequestMapping("/saveGMChanges")
    public String saveGMChanges(String gmEmail, String gmTel,HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String userJson=(String) servletContext.getAttribute("userJson");
        Gson gson=new Gson();
        Admin user=gson.fromJson(userJson, Admin.class);  // 将 JSON 转换为对象

        administratorService.setUser(user);  // 将对象装入 service ,因为 updateUser 需要 user 的 id
        administratorService.saveGMChanges(gmEmail,gmTel);
        administratorService.updateUser();
        user=administratorService.getUser();
        // 将数据转换成 JSON 并返回
        Map<Object,Object> data=new HashMap<>();
        data.put("result", true);
        data.put("gmEmail", user.getGmEmail());
        data.put("gmTel", user.getGmTel());
        String dataJson=gson.toJson(data);
        return gson.toJson(dataJson);
    }

    @ResponseBody
    @RequestMapping("/changeGMPassword")
    public String changeGMPassword(String oldPassword, String newPassword){
        administratorService.changePassword(newPassword,oldPassword);
        Gson gson=new Gson();
        Map<Object,Object> data=new HashMap<>();
        data.put("result", true);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getAppointTypeGMMessage")
    public String getAppointTypeGMMessage(String gmPower,String pageNum,String key){// 该管理员的权限 第几页 模糊搜索关键字
        int page;  // 第几页
        int pageSize=10;  // 每页显示数据数量的最大值
        if(pageNum==null) {
            page = 1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int startPage=page*pageSize;  // 偏移量
        List<Admin> userList=administratorService.getPartAccounts(Integer.parseInt(gmPower), key, startPage, pageSize);  // 获取所有权限低于此管理员的管理员信息
        int total=administratorService.getAllAccounts(Integer.parseInt(gmPower), key).size();  // 获取表数据的总条数
        int totalPage=(int) Math.ceil(total*1.0/pageSize);  // 总页数
        int currentPageRows=userList.size();  // 当前页数据条数
        Map<Object,Object> data=new HashMap<Object,Object>();
        data.put("userList", userList);  // 数据列表
        data.put("total", total);  // 数据总量
        data.put("totalPage", totalPage);  // 总页数
        data.put("pageSize", pageSize);  // 每页最大值
        data.put("currentPageRows", currentPageRows);  // 当前页数据条数
        Gson gson=new Gson();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/accountManagement")
    public String accountManagement(int id, int sign, int category,HttpSession session){
        String result = null;
        if(category== 0) {
            studentService.updateStudentAccountStatus(sign, id);
            result=accountStatusService.findBy(sign);
        }else if(category== 1) {  //对管教师用户账号状态编辑
            teacherService.updateTeacherAccountStatus(sign, id);
            result=accountStatusService.findBy(sign);
        }else if(category== 2) {  // 对管理员用户账号状态编辑
            administratorService.updatePower(sign, id);  // 更改低级权限的管理员账号状态
            result=accountStatusService.findBy(sign);
        }else if(category== 3) {  //管理员/教师对课程状态进行编辑
            courseService.updateCourseStatus(sign, id);
            result=accountStatusService.findBy(sign);
        }else if(category== 4) {  // 对所有课程类型编辑
            courseService.setAsDefault(id);  // 将所有类型为待删除的课程类型的课程更改为"未知"类型
            courseTypeService.delCourseType(id);  // 删除指定课程类型
        }else if(category== 5) {  //对所有课程测试卷状态编辑
            itemBackService.updateTestState(id, sign);
            result=accountStatusService.findBy(sign);
        }else if(category== 6) {  //对课程推广编辑
            coursePromotionService.delCoursePromotion(id);
            result="课程推广删除成功!";
        }else if(category== 7) {  //对所有课程资源状态编辑
            courseResourceService.updateUserCourseResource(sign, id);
            result=accountStatusService.findBy(sign);
        }else {
            result = null;
        }
        Map<Object,Object> data=new HashMap<Object,Object>();
        data.put("result", result);
        Gson gson=new Gson();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getGMPowerTabel")
    public String getGMPowerTabel(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        String userJson=(String) servletContext.getAttribute("userJson");
        Gson gson=new Gson();
        Admin user=gson.fromJson(userJson, Admin.class);  // 将 JSON 转换为对象
        List<GMPower> gmPowerList=new ArrayList<>();
        int powerNumber=user.getGmPower();
        gmPowerList=gmPowerService.getGMPowerMessage(powerNumber);
        Map<Object,Object> data=new HashMap<>();
        data.put("gmPowerList", gmPowerList);
        String dataJson=gson.toJson(data);
        return dataJson;
    }

    @ResponseBody
    @RequestMapping("/administratorAccountRegistration")
    public String administratorAccountRegistration(int quantity,int gmPower){
        List<Admin> adminList=administratorService.batchAddAdminAccount(quantity, gmPower);
        Map<Object,Object> data=new HashMap<>();
        data.put("adminList", adminList);
        Gson gson=new Gson();
        String dataJson=gson.toJson(data);
        return dataJson;
    }

    @ResponseBody
    @RequestMapping("/getAllTeacher")
    public String getAllTeacher(String pageNum,String key){
        Gson gson=new Gson();
        Map<Object,Object> data=new HashMap<>();
        int row=teacherService.getTeacherTableRow();
        int page;
        int pageSize=12;
        int totalPage=(int)Math.ceil(row*1.0/pageSize);
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int startPage=page*pageSize;
        List<Teacher> teacherList=teacherService.getPartTeacher(key,startPage,pageSize);
        data.put("teacherList", teacherList);
        data.put("total", row);
        data.put("totalPage", totalPage);
        data.put("pageSize", pageSize);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getAllUserMessage")
    public String getAllUserMessage(String pageNum,String key){
        Gson gson=new Gson();
        Map<Object,Object> data=new HashMap<>();
        int row=studentService.getStudentTableRow();
        int page;
        int pageSize=12;
        int totalPage=(int)Math.ceil(row*1.0/pageSize);
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int startPage=page*pageSize;
        List<Student> studentList=studentService.getPartStudent(key,startPage,pageSize);
        data.put("studentList", studentList);
        data.put("total", row);
        data.put("totalPage", totalPage);
        data.put("pageSize", pageSize);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/addCourseType")
    public String addCourseType(String newType){
        courseTypeService.addNewCourseType(newType);
        Map<Object,Object> data=new HashMap<>();
        data.put("result", true);
        Gson gson=new Gson();
        return gson.toJson(data);
    }


    @ResponseBody
    @RequestMapping("/getCourseType")
    public String getCourseType(String pageNum){
        int row = courseTypeService.getAllCourseType().size();  // 数据总条数
        int pageSize = 12;  // 规定每个页面显示课程数量的最大值
        int totalPage = (int) Math.ceil(row*1.0/pageSize); // 总页数
        int page;
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int startPage = page *pageSize;  // 偏移量

        List<CourseType> courseTypeList = courseTypeService.getCourseType(startPage, pageSize);
        int currentPageRows=courseTypeList.size();  // 当前页数据条数

        Map<Object,Object> data=new HashMap<>();
        data.put("courseTypeList", courseTypeList);  // 数据列表
        data.put("total", row);  // 数据总条数
        data.put("totalPage", totalPage); // 总页数
        data.put("pageSize", pageSize );  // 规定每个页面显示课程数量的最大值
        data.put("currentPageRows",currentPageRows);  // 当前页数据条数

        Gson gson=new Gson();
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getCourseMessage")
    public String getCourseMessage(String key,String pageNum){ // 搜索关键字,页码
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int page;
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int pageSize=12;  //规定每页显示数据条数
        int row=courseService.getAllCourse().size();  //获取总课程数
        int startPage=page*pageSize;  //偏移量
        int totalPage=(int)Math.ceil(row*1.0/pageSize);  //总页数

        List<Course> courseList=courseService.getCourseMessage(startPage,pageSize,key);
        data.put("courseList", courseList);
        data.put("total",row);
        data.put("pageSize", pageSize);
        data.put("totalPage", totalPage);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getCourseResourceMessage")
    public String getCourseResourceMessage(String key,String pageNum){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int pageSize=12;  //每页数据最大行数
        int row=courseResourceService.getUserCourseResourceRow(key);  //总数据量
        int page;  //第几页
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int startPage=page*pageSize;
        int totalPage=(int)Math.ceil(row*1.0/pageSize);  //总页数

        List<CourseResource> courseResouceList=courseResourceService.getAllCourseResource(startPage,pageSize,key);
        data.put("courseResouceList", courseResouceList);
        data.put("total",row);
        data.put("pageSize", pageSize);
        data.put("totalPage", totalPage);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getAllTestMessage")
    public String getAllTestMessage(String pageNum,String key){
        Gson gson=new Gson();
        Map<Object,Object> data=new HashMap<>();
        int page;
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int pageSize=12;
        int row=itemBackService.getAllTestRow(key);
        int startPage=page*pageSize;
        int totalPage=(int) Math.ceil(row*1.0/pageSize);
        data.put("total", row);
        data.put("pageSize", pageSize);
        data.put("totalPage", totalPage);

        List<ItemBack> listItemBacks=new ArrayList<ItemBack>();
        listItemBacks=itemBackService.getPartTestMessage(startPage, pageSize,key);
        data.put("testList", listItemBacks);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getCoursePromotion")
    public String getCoursePromotion(String pageNum){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        List<CoursePromotion> coursePromotionList=new ArrayList<>();
        int page;  //第几页
        if(pageNum==null) {
            page=1;
        }else {
            page=Integer.parseInt(pageNum);
        }
        int pageSize=12;  //每页显示数据数量
        int startPage=pageSize*page;  //偏移量

        int row=coursePromotionService.getAllCoursePromotionRow();
        int totalPage=(int)Math.ceil(row*1.0/pageSize);  //总页数
        coursePromotionList=coursePromotionService.getPartCoursePromotionMessage(startPage, pageSize);
        data.put("coursePromotionList", coursePromotionList);
        data.put("total", row);
        data.put("pageSize", pageSize);
        data.put("totalPage", totalPage);
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/addCoursePromotion")
    public String addCoursePromotion(String newPromotion){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int courseId=Integer.parseInt(newPromotion);
        if(courseService.checkCourseExist(courseId)) {
            if(coursePromotionService.courseExist(courseId)) {
                data.put("state", false);
                data.put("message", "该课程正在推广中!");
            }else {
                coursePromotionService.addCoursePromotion(courseId);
                data.put("state", true);
                data.put("message", "添加推广成功!");
            }
        }else {
            data.put("state", false);
            data.put("message", "该课程不存在或已被删除/冻结!");
        }
        return gson.toJson(data);
    }

    @ResponseBody
    @RequestMapping("/getSuggestions")
    public String getSuggestions(String pageNum){
        Map<Object,Object> data=new HashMap<>();
        Gson gson=new Gson();
        int page=0;
        if(pageNum==null) {
            page=0;
        }else{
            page=Integer.parseInt(pageNum);
        }
        int pageSize=12;
        int startPage=page*pageSize;
        int row=suggestionsService.getSuggestionsTableRow();
        int totalPage=(int)Math.ceil(row*1.0/pageSize);

        data.put("suggestionsList", suggestionsService.getPartSuggestions(startPage, pageSize));
        data.put("total", row);
        data.put("totalPage", totalPage);
        data.put("pageSize", pageSize);
        return gson.toJson(data);
    }

    private boolean loginById(int id,String gmPassword){
        return administratorService.loginById(id, gmPassword);
    }

    private boolean loginByTel(String gmTel,String gmPassword){
//        return administratorService.loginByTel(gmTel, gmPassword);
        return false;
    }

    private boolean loginByEmail(String gmEmail, String gmPassword){
//        return administratorService.loginByEmail(gmEmail, gmPassword);
        return false;
    }


}
