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

import homepage.edu.service.NoteService;

/**
 * Servlet implementation class SaveNote
 */
@WebServlet("/SaveNote")
public class SaveNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveNote() {
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
		if(session.getAttribute("state")==null) {
			data.put("state", false);
			data.put("message", "请先登录!");
		}else {
			int userType=0;
			if((boolean) session.getAttribute("teacher")) {
				userType=1;
			}
			int userId=(int) session.getAttribute("id");
			String note=request.getParameter("note");
			int courseId=Integer.parseInt(request.getParameter("courseId"));
			NoteService noteService=new NoteService();
			noteService.addNote(courseId, userId, userType, note);
			data.put("State", true);
			data.put("message", "分享成功!");
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
