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

/**
 * Servlet implementation class GetUserAppointTestAnswer
 */
@WebServlet("/GetUserAppointTestAnswer")
public class GetUserAppointTestAnswer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetUserAppointTestAnswer() {
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
		ItemBackService itemBackService=new ItemBackService();
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		int userId=(int) session.getAttribute("id");
		int testId=Integer.parseInt(request.getParameter("testId"));
		String answerSheetId=request.getParameter("answerSheetId");
		data.put("results", itemBackService.getTestQuestionAndAnswer(testId,userId,answerSheetId));
		String dataJson=gson.toJson(data);
		System.out.println("GetUserAppointTestAnswer:"+dataJson);
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
