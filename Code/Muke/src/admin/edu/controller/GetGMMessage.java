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

/**
 * Servlet implementation class GetGMMessage
 */
@WebServlet("/GetGMMessage")
public class GetGMMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGMMessage() {
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
		Map<Object,Object> data=new HashMap<Object,Object>();
		
		HttpSession session=request.getSession();
		if(session.isNew()) {
			System.out.println("new");
		}else {
			System.out.println("old"+session);
		}
		String userJson=(String) session.getAttribute("userJson");
		System.out.println("userJson:"+userJson);
		Admin user=gson.fromJson(userJson, Admin.class);  // 把 JSON 转换成对象
		
		
		data.put("gmId",user.getGmId());
		data.put("gmEmail",user.getGmEmail());
		data.put("gmTel", user.getGmTel());
		data.put("gmPowerNo",user.getGmPower());
		data.put("gmPower", user.getPowerClass()); 
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
