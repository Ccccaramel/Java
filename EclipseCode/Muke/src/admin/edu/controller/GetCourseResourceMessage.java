package admin.edu.controller;

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
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import homepage.edu.dao.CourseResource;
import homepage.edu.service.CourseResourceService;

/**
 * Servlet implementation class GetCourseResourceMessage
 */
@WebServlet("/GetCourseResourceMessage")
public class GetCourseResourceMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseResourceMessage() {
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
		CourseResourceService courseResourceService=new CourseResourceService();
		String key=request.getParameter("key");
		int pageSize=12;  //每页数据最大行数
		int row=courseResourceService.getUserCourseResourceRow(key);  //总数据量
		int page;  //第几页
		String pageNum=request.getParameter("pageNum");
		if(pageNum==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageNum);
		}
		int startPage=page*pageSize;
		int totalPage=(int)Math.ceil(row*1.0/pageSize);  //总页数


		List<CourseResource> courseResouceList=new ArrayList<>();
		courseResouceList=courseResourceService.getAllCourseResource(startPage,pageSize,key);
		data.put("courseResouceList", courseResouceList);
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
