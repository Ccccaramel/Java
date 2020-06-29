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
import homepage.edu.service.CourseService;

/**
 * Servlet implementation class SearchResource
 */
@WebServlet("/SearchResource")
public class SearchResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchResource() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		CourseResourceService courseResourceService=new CourseResourceService();
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
		int row=courseResourceService.searchCourseResourceRow(startPage, pageSize, key);
		int totalPage=(int) Math.ceil(row*1.0/pageSize);
		
		data.put("list", courseResourceService.searchCourseResource(startPage, pageSize, key));
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
