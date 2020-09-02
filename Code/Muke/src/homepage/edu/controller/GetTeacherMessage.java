package homepage.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import homepage.edu.dao.Teacher;
import homepage.edu.service.TeacherService;

/**
 * Servlet implementation class GetTeacherMessage
 */
@WebServlet("/GetTeacherMessage")
public class GetTeacherMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeacherMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		TeacherService teacherService=new TeacherService();
		Map<Object,Object> data=new HashMap<Object,Object>();
		Gson gson=new Gson();
		
		int id=(int) session.getAttribute("id");
		System.out.println("id:"+id);
		boolean state = (boolean) session.getAttribute("state");
		boolean teacherIs =(boolean) session.getAttribute("teacher");
		if(state==true&&teacherIs==true) {
			Teacher teacher=teacherService.getTeacherMessage(id);
			data.put("teacherId", teacher.getTeacherId());
			data.put("teacherName", teacher.getTeacherName());
			data.put("teachersSchool", teacher.getTeachersSchool());
			data.put("teacherTel", teacher.getTeacherTel());
			data.put("teacherEmail", teacher.getTeacherEmail());
			data.put("teacherQualification", teacher.getTeacherQualification());
			data.put("teachersSchoolEmail", teacher.getTeachersSchoolEmail());
		}else {
			System.out.println("ÕËºÅ×´Ì¬Òì³£");
		}
		data.put("state", state);
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
