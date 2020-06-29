package admin.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import admin.edu.controller.tool.ResourceUploadAndDownload;
import admin.edu.service.AdminService;

/**
 * Servlet implementation class AdministratorAccountRegistration
 */
@WebServlet("/AdministratorAccountRegistration")
public class AdministratorAccountRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdministratorAccountRegistration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		Gson gson=new Gson();
		ResourceUploadAndDownload resourceUploadAndDownload=new ResourceUploadAndDownload();
		Map<Object,Object> data=resourceUploadAndDownload.resourceTable(request);
		AdminService adminService=new AdminService();
		
		int lv=Integer.parseInt((String) data.get("administrator-level"));
		int quantity=Integer.parseInt((String) data.get("administrator-number"));
		
		data.put("adminList", adminService.batchAddAdminAccount(quantity, lv));
		
		String dataJson=gson.toJson(data);
		System.out.println("data:"+dataJson);
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
