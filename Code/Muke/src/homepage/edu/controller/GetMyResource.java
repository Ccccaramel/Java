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

import homepage.edu.service.CourseResourceService;

/**
 * Servlet implementation class GetMyResource
 */
@WebServlet("/GetMyResource")
public class GetMyResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMyResource() {
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
		CourseResourceService courseResourceService=new CourseResourceService();
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		int userId=(int) session.getAttribute("id");
//		int use
		int page=0;
		String pageStr=request.getParameter("pageNum");
		if(pageStr==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageStr);
		}
		int pageSize=12;
//		int row=courseResourceService.getUserCourseResourceRow(userId, userType);
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
