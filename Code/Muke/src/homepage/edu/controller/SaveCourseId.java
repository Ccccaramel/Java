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

/**
 * Servlet implementation class SaveCourseId
 */
@WebServlet("/SaveCourseId")
public class SaveCourseId extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveCourseId() {
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
		if(session.getAttribute("state")!=null&&(boolean)session.getAttribute("state")) {
			if((boolean)session.getAttribute("teacher")) {
				data.put("sate", false);
				data.put("message", "教师上传资源请到教师中心操作!");				
			}else {
			int courseId=Integer.parseInt(request.getParameter("courseId"));
			session.setAttribute("courseId", courseId);
			data.put("state", true);					
			}
		}else {
			data.put("sate", false);
			data.put("message", "请先登录!");
		}

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
