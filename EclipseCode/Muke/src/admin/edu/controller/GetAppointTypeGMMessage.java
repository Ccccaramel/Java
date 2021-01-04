package admin.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.edu.dao.Admin;
import admin.edu.service.AdminService;

/**
 * Servlet implementation class GetAppointTypeGMMessage
 */
@WebServlet("/GetAppointTypeGMMessage")
public class GetAppointTypeGMMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAppointTypeGMMessage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		AdminService adminService=new AdminService();
		
		String key=request.getParameter("key");  // 模糊搜索关键字
		String pageStr=request.getParameter("pageNum");  // 第几页
		int gmPower=Integer.parseInt(request.getParameter("gmPower"));  // 该管理员的权限
		int page;  // 第几页
		int pageSize=10;  // 每页显示数据数量的最大值
		if(pageStr==null) {
			page = 1;
		}else {
			page=Integer.parseInt(pageStr);
		}
		int startPage=page*pageSize;  // 偏移量
		List<Admin> userList=adminService.getAllAccounts(gmPower, key, startPage, pageSize);  // 获取所有权限低于此管理员的管理员信息
		int total=userList.size();  // 获取表数据的总条数  
		int totalPage=(int) Math.ceil(total*1.0/pageSize);  // 总页数		
		int currentPageRows=userList.size();  // 当前页数据条数
		Map<Object,Object> data=new HashMap<Object,Object>();
		data.put("userList", userList);  // 数据列表
		data.put("total", total);  // 数据总量
		data.put("totalPage", totalPage);  // 总页数
		data.put("pageSize", pageSize);  // 每页最大值
		data.put("currentPageRows", currentPageRows);  // 当前页数据条数
		
		Gson gson=new Gson();
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
