package admin.edu.controller;

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
import admin.edu.dao.Admin;
import admin.edu.dao.GMPower;
import admin.edu.service.GMPowerService;
/**
 * Servlet implementation class GetGMPowerTabel
 */
@WebServlet("/GetGMPowerTabel")
public class GetGMPowerTabel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetGMPowerTabel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		Map<Object,Object> data=new HashMap<>();
		HttpSession session=request.getSession();
		Gson gson=new Gson();
		GMPowerService gmPowerService=new GMPowerService();
		List<GMPower> gmPowerList=new ArrayList<>();
		String userJson=(String)session.getAttribute("userJson");  //从session取出用户信息
		Admin user=gson.fromJson(userJson, Admin.class);  //把信息转换成对象
		int powerNumber=user.getGmPower();
		gmPowerList=gmPowerService.getGMPowerMessage(powerNumber);
		data.put("gmPowerList", gmPowerList);
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
