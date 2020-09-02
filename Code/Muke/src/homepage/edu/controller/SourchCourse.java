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

import homepage.edu.service.CourseService;

/**
 * Servlet implementation class SourchCourse
 */
@WebServlet("/SourchCourse")
public class SourchCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SourchCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		CourseService courseService=new CourseService();
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		
		String pageStr=request.getParameter("pageNum");
		int page=0;
		if(pageStr==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageStr);
		}
		int pageSize=12;
		String key=request.getParameter("key");
		int startPage=page*pageSize;
		int row=courseService.getSourchCourseRow(key, startPage, pageSize);
		int totalPage=(int) Math.ceil(row*1.0/pageSize);
		
		data.put("list", courseService.sourchCourse(key, startPage, pageSize));
		data.put("total", row);
		data.put("totalPage", totalPage);
		data.put("page", page);
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
