package homepage.edu.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.edu.dao.CourseType;
import admin.edu.service.CourseTypeService;

/**
 * Servlet implementation class HomeGetCourseType
 */
@WebServlet("/HomeGetCourseType")
public class HomeGetCourseType extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeGetCourseType() {
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
		int amount=Integer.parseInt(request.getParameter("amount"));
		CourseTypeService courseTypeService=new CourseTypeService();
		List<CourseType> courseTypeList=new ArrayList<>();
		courseTypeList=courseTypeService.getCourseType(amount);
		data.put("courseTypeList", courseTypeList);
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
