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
 * Servlet implementation class GMLogin
 */
@WebServlet("/GMLogin")
public class GMLogin extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public GMLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */ 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int loginMode=Integer.parseInt(request.getParameter("loginMode"));
		String gmPassword=request.getParameter("gmPassword");
		AdminService admindao=new AdminService();
		Boolean state = null;
		if(loginMode==1) {
			int gmId=Integer.parseInt(request.getParameter("gmAccount"));
			state=admindao.loginId(gmId,gmPassword);			
		}else if(loginMode==2) {
			int gmTel=Integer.parseInt(request.getParameter("gmAccount"));
			state=admindao.loginTel(gmTel,gmPassword);	
		}else if(loginMode==3) {
			String gmEmail=request.getParameter("gmAccount");
			state=admindao.loginEmail(gmEmail,gmPassword);			
		}
		System.out.println("state:"+state);  // �鿴��ѯ���

		Map<Object,Object> data=new HashMap<Object,Object>();
		data.put("state",state );
		data.put("GM",true);
		Admin user=admindao.getUser();// ��ȡ�û�����
		
		if(state) {  // �˺Ŵ���
			// �����ݴ������ session
			HttpSession session=request.getSession();  // ʹ�� resuest ����� getSession() ��ȡ session ,�� session �������򴴽�һ��
			Gson gson=new Gson();
			String userJson=gson.toJson(user); //  ����ת JSON
			System.out.println("userJson:"+userJson);
			session.setAttribute("userJson", userJson);
			if(session.isNew()){
				// session �����ɹ�
			}else {
				// �������Ѵ��ڸ� session  
			}	
		}
		data.put("sign", user.CheckAccountStatus());
		Gson gson=new Gson();
		String result=gson.toJson(data);
		System.out.println("result:"+result);

		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
