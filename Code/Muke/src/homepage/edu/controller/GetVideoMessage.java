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
 * Servlet implementation class GetVideoMessage
 */
@WebServlet("/GetVideoMessage")
public class GetVideoMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetVideoMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		Map<Object,Object> data=new HashMap<>();
		CourseService courseService=new CourseService();
		Gson gson=new Gson();
		int courseId=(int) session.getAttribute("courseId");
		int chapterId=(int) session.getAttribute("chapterId");
		int sectionId=(int) session.getAttribute("sectionId");
//		session.removeAttribute("courseId");
//		session.removeAttribute("chapterId");
//		session.removeAttribute("sectionId");
		String mvAdd=courseService.getMvAdd(courseId, chapterId, sectionId);
		data.put("courseId", courseId);
		data.put("chapterId", chapterId);
		data.put("sectionId", sectionId);
		data.put("mvAdd", mvAdd);
		String dataJson=gson.toJson(data);
		System.out.println("GetVideoMessage:"+dataJson);
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
