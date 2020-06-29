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
 * Servlet implementation class GetUserCourseCollection
 */
@WebServlet("/GetUserCourseCollection")
public class GetUserCourseCollection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserCourseCollection() {
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
		CourseService courseService=new CourseService();
		int userId=(int) session.getAttribute("id");
		Map<Object,Object> data=new HashMap<>();
		int pageSize=12;  //每页数据最大行数
		int row=courseService.getFavoriteCourseRow(userId);
		int page;  //第几页
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageNum);
		}
		int startPage=page*pageSize;
		int totalPage=(int)Math.ceil(row*1.0/pageSize);  //总页数
		
		data.put("list", courseService.getFavoriteCourseMessage(startPage,totalPage,userId));
		data.put("totalPage", totalPage);
		data.put("total", row);
		data.put("pageSize", pageSize);
		Gson gson=new Gson();
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
