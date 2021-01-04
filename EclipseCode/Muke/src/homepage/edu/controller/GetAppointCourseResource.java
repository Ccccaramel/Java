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

import homepage.edu.service.CourseResourceService;

/**
 * Servlet implementation class GetAppointCourseResource
 */
@WebServlet("/GetAppointCourseResource")
public class GetAppointCourseResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAppointCourseResource() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		int courseId=Integer.parseInt(request.getParameter("courseId"));
		int share=Integer.parseInt(request.getParameter("share"));
		CourseResourceService courseResourceService=new CourseResourceService();
		int row=courseResourceService.getAppointCourseResourceRow(courseId, share);
		data.put("count",row);
		data.put("list", courseResourceService.getAppointCourseResource(courseId, share));
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
