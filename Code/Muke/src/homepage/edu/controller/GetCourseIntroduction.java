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
 * Servlet implementation class GetCourseIntroduction
 */
@WebServlet("/GetCourseIntroduction")
public class GetCourseIntroduction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseIntroduction() {
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
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		int id=(int) session.getAttribute("id");
		boolean teacher=(boolean) session.getAttribute("teacher");
		boolean state = false;
		int courseId=Integer.parseInt(request.getParameter("courseId"));
		if(teacher) {
			state=true;
			CourseService courseService=new CourseService();
			data.put("course",courseService.getCourseMessage(courseId));
			data.put("abstract", courseService.getCourseAbstract(courseId));
			data.put("structure", courseService.getCourseStructure(courseId));
		}
		data.put("state", state);
		String dataJson=gson.toJson(data);
		System.out.println("GetCourseIntroduction/dataJson:"+dataJson);
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
