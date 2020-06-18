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
 * Servlet implementation class SaveGMChanges
 */
@WebServlet("/SaveGMChanges")
public class SaveGMChanges extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveGMChanges() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		String userJson=(String) session.getAttribute("userJson");  // �� session ��ȡ user ����� json ֵ
		Gson gson=new Gson(); 
		Admin user=gson.fromJson(userJson, Admin.class);  // �� json ת���� ����
		String gmEmail=request.getParameter("GMEmail");  // ��ȡ Email
		int gmTel=Integer.parseInt(request.getParameter("GMTel"));  // ��ȡ Tel
		AdminService admindao=new AdminService();
		admindao.setUser(user);
		boolean result = admindao.saveGMchanges(gmEmail, gmTel);  // ���²���ø��½��
		Map<Object,Object> data=new HashMap<Object,Object>();
		data.put("result", result);
		if(result) {
			user=admindao.getUser();
			data.put("gmEmail", user.getGmEmail());
			data.put("gmTel", user.getGmTel());
			userJson=gson.toJson(user);  // �� session ��ȡ���º�� user ����� json ֵ
			session.setAttribute("userJson", userJson);  // �����º�� userJson ���� session
		}else {
			System.out.println("����ʧ��.");
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
