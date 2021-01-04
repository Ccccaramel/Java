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

import admin.edu.dao.CourseType;
import admin.edu.service.CourseTypeService;

/**
 * Servlet implementation class GetCourseType
 */
@WebServlet("/GetCourseType")
public class GetCourseType extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final int CourseType = 0;
	private static final int List = 0;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseType() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		CourseTypeService courseTypeService=new CourseTypeService();
		int row = courseTypeService.getCourseTypeRow();  // 数据总条数
		int pageSize = 12;  // 规定每个页面显示课程数量的最大值
		int totalPage = (int) Math.ceil(row*1.0/pageSize); // 总页数
		String pageStr=request.getParameter("pageNum");  // 第几页
		int page;
		if(pageStr==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageStr);
		}
		int startPage = page *pageSize;  // 偏移量

		
		List<CourseType> courseTypeList =courseTypeService.getCourseType(startPage, pageSize);
		int currentPageRows=courseTypeList.size();  // 当前页数据条数
				
		Map<Object,Object> data=new HashMap<Object,Object>();
		data.put("courseTypeList", courseTypeList);  // 数据列表
		data.put("total", row);  // 数据总条数
		data.put("totalPage", totalPage); // 总页数
		data.put("pageSize", pageSize );  // 规定每个页面显示课程数量的最大值
		data.put("currentPageRows",currentPageRows);  // 当前页数据条数
		
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
