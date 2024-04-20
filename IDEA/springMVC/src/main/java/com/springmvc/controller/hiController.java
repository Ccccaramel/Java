package com.springmvc.controller;

import com.springmvc.bean.User;
import com.springmvc.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

@Controller
public class hiController { // 类名前新增了一个图标,表示该类交给 AOC 容器管理

    // @RequestMapping("/") 与下等价
//    @RequestMapping(value = "/")
//    public String index() {
//        return "index";
//    }

    @RequestMapping(value = {"/target1", "/target2"})
    public String toTarget() {
        return "target";
    }  // 返回视图或页面

    @RequestMapping(value = "/test", method = {RequestMethod.GET})
    public String test() {
        return "test";
    }

    @GetMapping("/getTest")
    public String getTest() {
        return "test";
    }

    @PostMapping("/postTest")
    public String postTest() {
        return "test";
    }

    @RequestMapping(value = "/paramsTest", params = {"admin=root", "pw=123"})
    public String paramsTest() {
        return "test";
    }

    @RequestMapping(value = "/paramsTest2", params = {"admin"})
    public String paramsTest2() {
        return "test";
    }

    @RequestMapping(value = "/headerTest1", headers = {"Host=localhost:8081"})
    public String headerTest1() {
        return "test";
    }

    @RequestMapping(value = "/headerTest1", headers = {"Host=localhost:8080"})
    public String headerTest2() {
        return "test";
    }

    @RequestMapping("/a*/antTest")
    public String antTest() {
        return "test";
    }

    @RequestMapping("/a**a/antTest")
    public String antTest1() {
        return "test";
    }

    @RequestMapping("/**/antTest")
    public String antTest2() {
        return "test";
    }

    @RequestMapping("/pathTest/{id}/{name}") // 占位符
    public String pathTest(@PathVariable("id") int id,  @PathVariable("name")String name) { // 如果去除注解则值为 null
        System.out.println("id:" + id + ",name:" + name);
        return "test";
    }

    @RequestMapping("/servletAPI")
    public String servletAPI(HttpServletRequest request) { // 原生方法,少用
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println("name:" + name + ",password:" + password);
        return "test";
    }

    @RequestMapping("/testParam1")
    public String testParam1(String name, String password) {
        System.out.println("testParam1,name:" + name + ",password:" + password);
        return "test";
    }

    @RequestMapping("/testParam2")
    public String testParam2(
            String name,
            @RequestParam(value = "pw", required = false, defaultValue = "234") String password,
            String[] hobby, // hobby 不是数组类型,会将多个数据进行拼接操作
            @RequestHeader("Host") String host,
            @RequestHeader(value = "Referer", required = true, defaultValue = "RefererDef") String Referer,
            @RequestHeader(value = "header1", required = true, defaultValue = "header1Def") String header1,
//            @CookieValue(value = "Idea-32f345af")String JSESSIONID){
            @CookieValue(value = "JSESSIONID") String JSESSIONID) { // 先点击"原生servletAPI"再提交表单,否则"JSESSIONID"参数不存在
        System.out.println("testParam2,name:" + name + ",password:" + password + ",hobby:" + Arrays.toString(hobby) + ",host:" + host + ",Referer:" + Referer + ",header1:" + header1 + ",JSESSIONID:" + JSESSIONID);
        return "test";
    }

    @RequestMapping("/testBean1")
    public String testBean(User user) {
        System.out.println("user:"+user.toString());
        return "test";
    }

    @RequestMapping("/testRequestByServletAPI") // 原生
    public String testRequestByServletAPI(HttpServletRequest request){
        request.setAttribute("scope1","123");
        return "test";
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("scope1","aaa"); // 处理模型数据,即向请求域 request 共享数据
        modelAndView.setViewName("test"); // 设置视图名称
        return modelAndView;
    }

    @RequestMapping("/testMode")
    public String testMode(Model model){
        model.addAttribute("scope1","bbb");
        return "test";
    }

    @RequestMapping("/testMap")
    public String testMap(Map<String,Object> map){
        map.put("scope1","ccc");
        return "test";
    }

    @RequestMapping("/testModeMap")
    public String testModeMap(ModelMap modelMap){
        modelMap.addAttribute("scope1","ddd");
        return "test";
    }

    @RequestMapping("/testSession")
    public String testSession(HttpSession session){
        session.setAttribute("session1","s1");
        return "test";
    }

    @RequestMapping("/testApplication")
    public String testApplication(HttpSession session){
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("application1","a1");
        return "test";
    }

    @RequestMapping("/testForward")
    public String testForward(){
        return "forward:/test";
    }

    @RequestMapping("/testRedirect")
    public String testRedirect(){
        return "redirect:/test";
    }

    @RequestMapping(value = "/testRESTFulUser/{id}",method = RequestMethod.GET)
    public String testRESTFulGetUser(@PathVariable("id")String id){
        System.out.println("查询用户信息,id:"+id);
        return "test";
    }
    @RequestMapping(value = "/testRESTFulUser",method = RequestMethod.POST)
    public String testRESTFulPostUser(User user){
        System.out.println("新增用户信息,user:"+user.toString());
        return "test";
    }
    @RequestMapping(value = "/testRESTFulUser",method = RequestMethod.PUT)
    public String testRESTFulPutUser(User user){
        System.out.println("更新用户信息,user:"+user.toString());
        return "test";
    }

    @Autowired
    private UserDao userDao;
    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getAllUser(Model model){
        Collection<User> userList=userDao.getAll();
        System.out.println("userList:"+userList.toString());
        model.addAttribute("userList",userList);
        return "test";
    }
    @RequestMapping(value = "/user/{id}",method = RequestMethod.DELETE)
    public String testRESTFulDeleteUser(@PathVariable("id")Integer id){
        System.out.println("删除用户信息,id:"+id);
        userDao.delete(id);
        return "redirect:/user";
    }

    @RequestMapping(value = "/updateUser/{id}",method = RequestMethod.GET)
    public String getUser(@PathVariable("id")Integer id,Model model){
        User user = userDao.get(id);
        System.out.println("user:"+user.toString());
        model.addAttribute("user",user);
        return "updateUser";
    }
    @RequestMapping(value = "updateUser",method = RequestMethod.PUT)
    public String updateUser(User user){
        userDao.save(user);
        return "redirect:/user";
    }

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    public String addUser(User user){
        userDao.save(user);
        return "redirect:/user";
    }

    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String requestity){
        System.out.println("requestity:"+requestity);
        return "target";
    }

    @RequestMapping("/testRequestEntity")
    public String testRequestEntity(RequestEntity<String> requestity){
        System.out.println("requestity:"+requestity);
        System.out.println("getBody:"+requestity.getBody());
        System.out.println("getHeaders:"+requestity.getHeaders());
        return "target";
    }

    @RequestMapping("/testResponse")
    public void testResponse(HttpServletResponse res) throws IOException {
        res.getWriter().println("6666");
    }

    @RequestMapping("/testResponseBody")
    @ResponseBody
    public String testResponseBody(){
        return "target";
    }

    @RequestMapping("/testResponseBodyUser")
    @ResponseBody
    public User testResponseBodyUser(){
        return new User("t","123",1010,"男",12,"234@123.com",new String[]{"篮球","足球"});
    }

    // springMVC 处理 ajax
    @RequestMapping("/testAjax")
    @ResponseBody
    public String testAjax(String name,String password){
        System.out.println("name:"+name+",password:"+password);
        return "hi!!";
    }

    @RequestMapping("/testExceptionHandler")
    public String testExceptionHandler(){
        System.out.println(1/0);
        return "test";
    }

    @RequestMapping("/hi")
    public String hi(){
        return "hi";
    }
}