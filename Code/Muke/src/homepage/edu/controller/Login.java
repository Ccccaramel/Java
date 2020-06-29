package homepage.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import homepage.edu.dao.Teacher;
import homepage.edu.service.StudentService;
import homepage.edu.service.TeacherService;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		int loginMode =Integer.parseInt(request.getParameter("loginMode"));
		int teacher = Integer.parseInt(request.getParameter("teacher"));
		int loginRemenberMe = Integer.parseInt(request.getParameter("loginRemenberMe"));
		Map<Object,Object> data=new HashMap<Object,Object>();
		Gson gson=new Gson();
		HttpSession session=request.getSession();
		Boolean state = false;
		if(teacher==1) {  //教师登录 
			data.put("type", 1);
			TeacherService teacherService=new TeacherService();
			if(loginMode==1) {  //Email登录
				state = teacherService.EmailLogin(account, password);
				if(state) {  //存在此账号
					if(teacherService.getAccountStatus()==1) {  //帐号状态
						data.put("state", true);
						session.setAttribute("teacher", true);
						session.setAttribute("id", teacherService.getTeacher().getTeacherId());
						session.setAttribute("state", true);
						System.out.println("账号以及密码正确");
						if(loginRemenberMe==1) {  //记住密码	
							Cookie cookieId= new Cookie("id",teacherService.getTeacher().getTeacherId()+"");
							Cookie cookiePassword=new Cookie("password",password);
							Cookie cookieState=new Cookie("state","true");
							Cookie cookieTeacher=new Cookie("teacher","1");
							Cookie cookieLoginRemenberMe=new Cookie("loginRemenberMe","true");
							cookieId.setMaxAge(30);
							cookiePassword.setMaxAge(30);
							cookieState.setMaxAge(30);
							cookieTeacher.setMaxAge(30);
							cookieLoginRemenberMe.setMaxAge(30);
							response.addCookie(cookieId);
							response.addCookie(cookiePassword);
							response.addCookie(cookieState);
							response.addCookie(cookieTeacher);
							response.addCookie(cookieLoginRemenberMe);
//							for(Cookie c:cookies) {
//								String name=c.getName();  //获取cookie名称
//								System.out.println(name+":"+c.getValue());
//							}
							System.out.println("已勾选记住密码");
						}
					}else {
						System.out.println("账号以及密码正确但账号状态不正常");
						data.put("state", false);
					}
				}else {
					System.out.println("账号或密码不正确");
				}
			}else if(loginMode==2) {  //Tel登录
				state = teacherService.TelLogin(account, password);
				if(state) {  //存在此账号
					if(teacherService.getAccountStatus()==1) {  //帐号状态
						data.put("state", true);
						session.setAttribute("teacher", true);
						session.setAttribute("id", teacherService.getTeacher().getTeacherId());
						session.setAttribute("state", true);
						System.out.println("账号以及密码正确");
						if(loginRemenberMe==1) {  //记住密码	
							Cookie cookieId= new Cookie("id",teacherService.getTeacher().getTeacherId()+"");
							Cookie cookiePassword=new Cookie("password",password);
							Cookie cookieState=new Cookie("state","true");
							Cookie cookieTeacher=new Cookie("teacher","1");
							Cookie cookieLoginRemenberMe=new Cookie("loginRemenberMe","true");
							cookieId.setMaxAge(30);
							cookiePassword.setMaxAge(30);
							cookieState.setMaxAge(30);
							cookieTeacher.setMaxAge(30);
							cookieLoginRemenberMe.setMaxAge(30);
							response.addCookie(cookieId);
							response.addCookie(cookiePassword);
							response.addCookie(cookieState);
							response.addCookie(cookieTeacher);
							response.addCookie(cookieLoginRemenberMe);
							System.out.println("已勾选记住密码");
						}
					}else {
						System.out.println("账号以及密码正确但账号状态不正常");
						data.put("state", false);
					}
				}else {
					System.out.println("Tel账号或密码不正确");
				}
			}else if(loginMode==3) {  //Id登录
				state = teacherService.IdLogin(Integer.parseInt(account), password);
				if(state) {  //存在此账号
					if(teacherService.getAccountStatus()==1) {  //帐号状态
						data.put("state", true);
						session.setAttribute("teacher", true);
						session.setAttribute("id", teacherService.getTeacher().getTeacherId());
						session.setAttribute("state", true);
						System.out.println("账号以及密码正确");
						if(loginRemenberMe==1) {  //记住密码	
							Cookie cookieId= new Cookie("id",teacherService.getTeacher().getTeacherId()+"");
							Cookie cookiePassword=new Cookie("password",password);
							Cookie cookieState=new Cookie("state","true");
							Cookie cookieTeacher=new Cookie("teacher","1");
							Cookie cookieLoginRemenberMe=new Cookie("loginRemenberMe","true");
							cookieId.setMaxAge(30);
							cookiePassword.setMaxAge(30);
							cookieState.setMaxAge(30);
							cookieTeacher.setMaxAge(30);
							cookieLoginRemenberMe.setMaxAge(30);
							response.addCookie(cookieId);
							response.addCookie(cookiePassword);
							response.addCookie(cookieState);
							response.addCookie(cookieTeacher);
							response.addCookie(cookieLoginRemenberMe);
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
		}else {  //学生登录
			data.put("type", 0);
			StudentService studentService=new StudentService();
			if(loginMode==1) {  //Email登录
				state = studentService.EmailLogin(account,password);
				if(state) {  //存在此账号
					if(studentService.getAccountStatus()==1) {  //帐号状态
						data.put("state", true);
						session.setAttribute("teacher", false);
						session.setAttribute("id", studentService.getUser().getUserId());
						session.setAttribute("state", true);
						System.out.println("账号以及密码正确");
						if(loginRemenberMe==1) {  //记住密码	
							Cookie cookieId= new Cookie("id",studentService.getUser().getUserId()+"");
							Cookie cookiePassword=new Cookie("password",password);
							Cookie cookieState=new Cookie("state","true");
							Cookie cookieTeacher=new Cookie("teacher","0");
							Cookie cookieLoginRemenberMe=new Cookie("loginRemenberMe","true");
							cookieId.setMaxAge(30);
							cookiePassword.setMaxAge(30);
							cookieState.setMaxAge(30);
							cookieTeacher.setMaxAge(30);
							cookieLoginRemenberMe.setMaxAge(30);
							response.addCookie(cookieId);
							response.addCookie(cookiePassword);
							response.addCookie(cookieState);
							response.addCookie(cookieTeacher);
							response.addCookie(cookieLoginRemenberMe);
							System.out.println("已勾选记住密码");
						}
					}else {
						System.out.println("账号以及密码正确但账号状态不正常");
						data.put("state", false);
					}
				}else {
					System.out.println("Email-账号或密码不正确");
				}
			}else if(loginMode==2) {  //Tel登录
				state = studentService.TelLogin(account, password);
				if(state) {  //存在此账号
					if(studentService.getAccountStatus()==1) {  //帐号状态
						data.put("state", true);
						session.setAttribute("teacher", false);
						session.setAttribute("id", studentService.getUser().getUserId());
						session.setAttribute("state", true);
						System.out.println("账号以及密码正确");
						if(loginRemenberMe==1) {  //记住密码	
							Cookie cookieId= new Cookie("id",studentService.getUser().getUserId()+"");
							Cookie cookiePassword=new Cookie("password",password);
							Cookie cookieState=new Cookie("state","true");
							Cookie cookieTeacher=new Cookie("teacher","0");
							Cookie cookieLoginRemenberMe=new Cookie("loginRemenberMe","true");
							cookieId.setMaxAge(30);
							cookiePassword.setMaxAge(30);
							cookieState.setMaxAge(30);
							cookieTeacher.setMaxAge(30);
							cookieLoginRemenberMe.setMaxAge(30);
							response.addCookie(cookieId);
							response.addCookie(cookiePassword);
							response.addCookie(cookieState);
							response.addCookie(cookieTeacher);
							response.addCookie(cookieLoginRemenberMe);
							System.out.println("已勾选记住密码");
						}
					}else {
						System.out.println("账号以及密码正确但账号状态不正常");
						data.put("state", false);
					}
				}else {
					System.out.println("Tel-账号或密码不正确");
				}
			}else if(loginMode==3) {  //Id登录
				state = studentService.IdLogin(Integer.parseInt(account), password);
				if(state) {  //存在此账号
					if(studentService.getAccountStatus()==1) {  //帐号状态
						data.put("state", true);
						session.setAttribute("teacher", false);
						session.setAttribute("id", studentService.getUser().getUserId());
						session.setAttribute("state", true);
						System.out.println("账号以及密码正确");
						if(loginRemenberMe==1) {  //记住密码	
							Cookie cookieId= new Cookie("id",studentService.getUser().getUserId()+"");
							Cookie cookiePassword=new Cookie("password",password);
							Cookie cookieState=new Cookie("state","true");
							Cookie cookieTeacher=new Cookie("teacher","0");
							Cookie cookieLoginRemenberMe=new Cookie("loginRemenberMe","true");
							cookieId.setMaxAge(30);
							cookiePassword.setMaxAge(30);
							cookieState.setMaxAge(30);
							cookieTeacher.setMaxAge(30);
							cookieLoginRemenberMe.setMaxAge(30);
							response.addCookie(cookieId);
							response.addCookie(cookiePassword);
							response.addCookie(cookieState);
							response.addCookie(cookieTeacher);
							response.addCookie(cookieLoginRemenberMe);
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
		String dataJson=gson.toJson(data);
		response.getWriter().print(dataJson);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
