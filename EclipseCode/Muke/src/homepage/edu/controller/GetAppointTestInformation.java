package homepage.edu.controller;

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

import homepage.edu.dao.ItemBack;
import homepage.edu.service.ItemBackService;

/**
 * Servlet implementation class GetAppointTestInformation
 */
@WebServlet("/GetAppointTestInformation")
public class GetAppointTestInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAppointTestInformation() {
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
		int courseId=Integer.parseInt(request.getParameter("courseId"));
		ItemBackService itemBackService=new ItemBackService();
		List<ItemBack> itemBackList=new ArrayList<>();
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		int count=itemBackService.getAppointTestInformationRow(courseId);
		itemBackList=itemBackService.getAppointTestInformation(courseId);
		data.put("count", count);
		data.put("itemBackList", itemBackList);
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
