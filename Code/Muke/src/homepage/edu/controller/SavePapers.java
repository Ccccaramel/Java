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

import admin.edu.controller.tool.ResourceUploadAndDownload;
import homepage.edu.service.UserResultsService;

/**
 * Servlet implementation class SavePapers
 */
@WebServlet("/SavePapers")
public class SavePapers extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SavePapers() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		ResourceUploadAndDownload resourceUploadAndDownload=new ResourceUploadAndDownload();
		UserResultsService userResultsService=new UserResultsService();
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		int userId=(int) session.getAttribute("id");
		data=resourceUploadAndDownload.testUpload(request);
		userResultsService.saveUserTest(data,userId);
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
