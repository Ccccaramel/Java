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
import homepage.edu.service.FeedBackService;

/**
 * Servlet implementation class SaveFeedBack
 */
@WebServlet("/SaveFeedBack")
public class SaveFeedBack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveFeedBack() {
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
		FeedBackService feedBackService=new FeedBackService();
		Map<Object,Object> data=new HashMap<>();
		data=resourceUploadAndDownload.testUpload(request);
		int userId=-1;
		if(session.getAttribute("state")!=null) {
			userId=(int) session.getAttribute("id");
		}
		Gson gson=new Gson();
		feedBackService.addFeedBackService(userId, (String)data.get("message-text"));
		
		String dataJson=gson.toJson(data);
		response.getWriter().print(dataJson);;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
