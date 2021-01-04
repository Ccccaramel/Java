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

import admin.edu.dao.CoursePromotion;
import admin.edu.service.CoursePromotionService;
import homepage.edu.service.CourseResourceService;

/**
 * Servlet implementation class GetCoursePromotion
 */
@WebServlet("/GetCoursePromotion")
public class GetCoursePromotion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCoursePromotion() {
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
		CoursePromotionService coursePromotionService=new CoursePromotionService();
		List<CoursePromotion> coursePromotionList=new ArrayList<>();
		int page;  //第几页
		String pageStr=request.getParameter("pageNum");
		if(pageStr==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageStr);
		}
		int pageSize=12;  //每页显示数据数量
		int startPage=pageSize*page;  //偏移量
		
		int row=coursePromotionService.getCoursePromotionRow();
		int totalPage=(int)Math.ceil(row*1.0/pageSize);  //总页数
		coursePromotionList=coursePromotionService.getAllCoursePromotionMessage(startPage, pageSize);
		data.put("coursePromotionList", coursePromotionList);
		data.put("total", row);
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
