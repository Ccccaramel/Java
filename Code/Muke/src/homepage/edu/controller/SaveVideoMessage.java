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
 * Servlet implementation class SaveVideoMessage
 */
@WebServlet("/SaveVideoMessage")
public class SaveVideoMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveVideoMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<Object,Object> data=new HashMap<>();
		HttpSession session=request.getSession();
		int courseId=Integer.parseInt(request.getParameter("courseId"));
		int chapterId=Integer.parseInt(request.getParameter("chapterId"));
		int sectionId=Integer.parseInt(request.getParameter("sectionId"));
		session.setAttribute("courseId", courseId);
		session.setAttribute("chapterId", chapterId);
		session.setAttribute("sectionId", sectionId);
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
