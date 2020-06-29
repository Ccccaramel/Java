package admin.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import homepage.edu.dao.Course;
import homepage.edu.service.CourseService;

/**
 * Servlet implementation class GetCourseMessage
 */
@WebServlet("/GetCourseMessage")
public class GetCourseMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		Map<Object,Object> data=new HashMap<Object,Object>();
		Gson gson=new Gson();
		CourseService courseService=new CourseService();
		String key=request.getParameter("key");  //搜索关键字
		String pageStr=request.getParameter("pageNum");  //页码
		int page;
		if(pageStr==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageStr);
		}
		int pageSize=12;  //规定每页显示数据条数
		int row=courseService.getCourseTableRow();  //获取总课程数
		int startPage=page*pageSize;  //偏移量
		int totalPage=(int)Math.ceil(row*1.0/pageSize);  //总页数
		
		List<Course> courseList=courseService.getCourseMessage(key,startPage,pageSize);
		data.put("courseList", courseList);
		data.put("total",row);
		data.put("pageSize", pageSize);
		data.put("totalPage", totalPage);
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
