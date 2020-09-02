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

import homepage.edu.service.ItemBackService;
import homepage.edu.service.StudentService;

/**
 * Servlet implementation class GetTest
 */
@WebServlet("/GetTest")
public class GetTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetTest() {
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
		
		boolean status=false;
		if(session.getAttribute("state")==null) {
			data.put("status", status);
		}else {
			int userId=(int) session.getAttribute("id");
			int testId=Integer.parseInt(request.getParameter("testId"));
			boolean teacher=(boolean) session.getAttribute("teacher");
			boolean state=(boolean) session.getAttribute("state");
			
			if(!teacher&&state) {
				status=true;
			}
			data.put("status", status);
			
			StudentService studentService=new StudentService();
			data.put("userId", userId);
			data.put("userName", studentService.getStudentName(userId));
			
			ItemBackService itemBackService=new ItemBackService();
			data.put("test", itemBackService.getTeacherTestMessage(testId));
			data.put("question", itemBackService.getTestQuestion(testId));
			data.put("option", itemBackService.getTestOption());			
		}

		
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
