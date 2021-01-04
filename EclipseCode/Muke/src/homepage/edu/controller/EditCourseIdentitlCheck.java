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

import homepage.edu.service.CourseService;

/**
 * Servlet implementation class EditCourseIdentitlCheck
 */
@WebServlet("/EditCourseIdentitlCheck")
public class EditCourseIdentitlCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditCourseIdentitlCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		int teacherId=(int) session.getAttribute("id");
		int courseId=Integer.parseInt(request.getParameter("courseId"));
		boolean teacher=(boolean) session.getAttribute("teacher");
		boolean teacherState=(boolean) session.getAttribute("state");
		boolean state=false;
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		CourseService courseService=new CourseService();
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
		String dataJson=gson.toJson(data);
		System.out.println("state:"+state);
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
