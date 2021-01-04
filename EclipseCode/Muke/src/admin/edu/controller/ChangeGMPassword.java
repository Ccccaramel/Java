package admin.edu.controller;

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

import admin.edu.dao.Admin;
import admin.edu.service.AdminService;

/**
 * Servlet implementation class ChangeGMPassword
 */
@WebServlet("/ChangeGMPassword")
public class ChangeGMPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeGMPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// �����ύ����
		String newPassword=request.getParameter("GMNewPassword");
		String oldPassword=request.getParameter("GMOldPassword");
		
		// ��ȡ session ����ȡ����
		HttpSession session=request.getSession();
		String userJson=(String) session.getAttribute("userJson");  // ��ȡ����� JSON
		Gson gson=new Gson();
		Admin user=gson.fromJson(userJson, Admin.class);  // �� JSON ת��Ϊ����
		AdminService admindao=new AdminService();
		
		admindao.setUser(user);  // ������װ�� service �Է��� sql ����
		boolean result = admindao.changePassword(newPassword, oldPassword);
		
		// ������ת���� JSON ������
		Map<Object,Object> data=new HashMap<Object,Object>();
		data.put("result", result);
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
