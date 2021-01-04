package homepage.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

/**
 * Servlet implementation class GetTeacherCourseMessage
 */
@WebServlet("/GetTeacherCourseMessage")
public class GetTeacherCourseMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTeacherCourseMessage() {
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
		int id=(int) session.getAttribute("id");
		CourseService courseService=new CourseService();
		String pageNum=request.getParameter("pageNum");  //页码
		int page;
		if(pageNum==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageNum);
		}
		int pageSize=12;  //规定每页显示数据条数
		int row=courseService.getCourseTableRow(id);  //课程总数
		int startPage=page*pageSize;  //偏移量
		int totalPage=(int) Math.ceil(row*1.0/pageSize);  //总页数
		List<Course> courseList=courseService.getCourseMessage(startPage, pageSize, id);
		data.put("courseList", courseList);
		data.put("total", row);
		data.put("pageSize", pageSize);
		data.put("totalPage", totalPage);
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
