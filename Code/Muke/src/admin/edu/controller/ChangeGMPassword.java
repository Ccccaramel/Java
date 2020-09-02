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
		
		// 接收提交数据
		String newPassword=request.getParameter("GMNewPassword");
		String oldPassword=request.getParameter("GMOldPassword");
		
		// 获取 session 并提取对象
		HttpSession session=request.getSession();
		String userJson=(String) session.getAttribute("userJson");  // 获取对象的 JSON
		Gson gson=new Gson();
		Admin user=gson.fromJson(userJson, Admin.class);  // 将 JSON 转换为对象
		AdminService admindao=new AdminService();
		
		admindao.setUser(user);  // 将对象装入 service 以方便 sql 操作
		boolean result = admindao.changePassword(newPassword, oldPassword);
		
		// 将数据转换成 JSON 并返回
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
