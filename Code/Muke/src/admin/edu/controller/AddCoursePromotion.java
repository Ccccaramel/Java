package admin.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.edu.service.CoursePromotionService;
import homepage.edu.service.CourseService;

/**
 * Servlet implementation class AddCoursePromotion
 */
@WebServlet("/AddCoursePromotion")
public class AddCoursePromotion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCoursePromotion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		CoursePromotionService coursePromotionService=new CoursePromotionService();
		CourseService courseService=new CourseService();
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		int courseId=Integer.parseInt(request.getParameter("newPromotion"));
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
		String dataJson=gson.toJson(data);
		System.out.println("dataJson:"+dataJson);
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
