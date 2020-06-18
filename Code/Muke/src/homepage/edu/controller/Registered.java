package homepage.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import homepage.edu.service.TeacherService;

/**
 * Servlet implementation class Registered
 */
@WebServlet("/Registered")
public class Registered extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registered() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String userEmail=request.getParameter("userEmail");
		String userPassword=request.getParameter("userPassword");
		boolean result=false;
		Map<Object,Object> data=new HashMap<Object,Object>();
		System.out.println("profession:"+request.getParameter("profession"));
		boolean profession=Boolean.parseBoolean(request.getParameter("profession"));
		if(profession) {  // 教师注册
			System.out.println("teacher registered");
			String teacherName = request.getParameter("teacherName");
			String teachersSchool = request.getParameter("teachersSchool");
			String teacherIDcard = request.getParameter("teacherIDcard");
			String teacherQualification = request.getParameter("teacherQualification");
			String teacherTel = request.getParameter("teacherTel");
			String teachersSchoolEmail = request.getParameter("teachersSchoolEmail");
			TeacherService teacherService=new TeacherService();
			result=teacherService.registered(teacherName, teachersSchool, teacherIDcard, teacherTel, userEmail, teacherQualification, userPassword, teachersSchoolEmail);			
		}else {  // 学生注册
			System.out.println("student registered");

		}
		System.out.println("Registered-result:"+result);
		data.put("result", result);
		Gson gson=new Gson();
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
