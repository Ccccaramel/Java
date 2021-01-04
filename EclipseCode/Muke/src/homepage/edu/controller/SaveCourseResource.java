package homepage.edu.controller;

import java.io.File;
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

/**
 * Servlet implementation class SaveCourseResource
 */
@WebServlet("/SaveCourseResource")
public class SaveCourseResource extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveCourseResource() {
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
		Gson gson=new Gson();
		int id=(int) session.getAttribute("id");
		boolean teacher= (boolean) session.getAttribute("teacher");
		
		String realPath = this.getServletContext().getRealPath("/");
		String realPathParent=(new File(realPath)).getParent();
		String saveCourseResourcePath=realPathParent+"/Data/courseResource";
		ResourceUploadAndDownload resourceUploadAndDownload=new ResourceUploadAndDownload(saveCourseResourcePath);
		data=resourceUploadAndDownload.courseResourceUpload(request, id);
		System.out.println("data:"+data);
		resourceUploadAndDownload.courseResourceEntry(request, data);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
