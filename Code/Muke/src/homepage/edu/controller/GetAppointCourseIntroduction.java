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

import homepage.edu.dao.Course;
import homepage.edu.service.CourseService;
import homepage.edu.service.FavoriteService;
import homepage.edu.service.TeacherService;

/**
 * Servlet implementation class GetAppointCourseIntroduction
 */
@WebServlet("/GetAppointCourseIntroduction")
public class GetAppointCourseIntroduction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAppointCourseIntroduction() {
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
		
		int courseId=Integer.parseInt(request.getParameter("courseId"));
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		CourseService courseService=new CourseService();
		TeacherService teacherService=new TeacherService();
		FavoriteService favoriteService=new FavoriteService();
		Course course=new Course();
		course=courseService.getAppointCourseMessage(courseId);
		data.put("courseStatus", course.getCourseStatus()==1?true:false);
		data.put("courseImgName", course.getCourseImgName());
		data.put("courseName", course.getCourseName());
		data.put("teacherName",course.getTeacherName());
		data.put("abstract", courseService.getCourseAbstract(courseId));
		data.put("structure", courseService.getCourseStructure(courseId));
		if(session.getAttribute("id") != null) {
			data.put("pass", favoriteService.checkFavorite((int)session.getAttribute("id"), courseId));
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
