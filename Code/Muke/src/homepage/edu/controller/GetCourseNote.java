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

import homepage.edu.service.NoteService;

/**
 * Servlet implementation class GetCourseNote
 */
@WebServlet("/GetCourseNote")
public class GetCourseNote extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetCourseNote() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		NoteService noteService=new NoteService();
		int courseId=Integer.parseInt(request.getParameter("courseId"));
		String pageStr=request.getParameter("pageNum");
		int page=0;
		if(pageStr==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageStr);
		}
		int pageSize=12;
		int row=noteService.getNoteRow(courseId);
		int totalPage=(int) Math.ceil(row*1.0/pageSize);
		int startPage=page*pageSize;
		Map<Object,Object> data=new HashMap<>();
		data.put("list",noteService.getNote(courseId, startPage, pageSize));
		data.put("total", row);
		data.put("pageSize", pageSize);
		data.put("totalPage", totalPage);
		Gson gson=new Gson();
		String dataJson=gson.toJson(data);
		System.out.println("dataJson:"+dataJson);
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
