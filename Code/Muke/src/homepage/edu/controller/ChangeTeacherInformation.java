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
 * Servlet implementation class ChangeTeacherInformation
 */
@WebServlet("/ChangeTeacherInformation")
public class ChangeTeacherInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeTeacherInformation() {
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
		Map<Object,Object> data=new HashMap<Object,Object>();
		Gson gson=new Gson();
		boolean state=false;
		
		int id=(int) session.getAttribute("id");
		String teacherEmail=request.getParameter("teacherEmail");
		String teachersSchool=request.getParameter("teachersSchool");
		String teacherTel=request.getParameter("teacherTel");
		String teachersSchoolEmail=request.getParameter("teachersSchoolEmail");
		TeacherService teacherService=new TeacherService();
		Teacher teacher= new Teacher();
		state=teacherService.updateTeacherMessage(id, teacherEmail, teachersSchool, teacherTel, teachersSchoolEmail);
		teacher=teacherService.getTeacherMessage(id);
		data.put("teachersSchool", teacher.getTeachersSchool());
		data.put("teacherTel", teacher.getTeacherTel());
		data.put("teacherEmail", teacher.getTeacherEmail());
		data.put("teachersSchoolEmail", teacher.getTeachersSchoolEmail());
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
