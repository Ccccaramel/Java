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
		
		String key=request.getParameter("key");  // ģ�������ؼ���
		String pageStr=request.getParameter("pageNum");  // �ڼ�ҳ
		int gmPower=Integer.parseInt(request.getParameter("gmPower"));  // �ù���Ա��Ȩ��
		int page;  // �ڼ�ҳ
		int pageSize=10;  // ÿҳ��ʾ�������������ֵ
		if(pageStr==null) {
			page = 1;
		}else {
			page=Integer.parseInt(pageStr);
		}
		int startPage=page*pageSize;  // ƫ����
		List<Admin> userList=adminService.getAllAccounts(gmPower, key, startPage, pageSize);  // ��ȡ����Ȩ�޵��ڴ˹���Ա�Ĺ���Ա��Ϣ
		int total=userList.size();  // ��ȡ�����ݵ�������  
		int totalPage=(int) Math.ceil(total*1.0/pageSize);  // ��ҳ��		
		int currentPageRows=userList.size();  // ��ǰҳ��������
		Map<Object,Object> data=new HashMap<Object,Object>();
		data.put("userList", userList);  // �����б�
		data.put("total", total);  // ��������
		data.put("totalPage", totalPage);  // ��ҳ��
		data.put("pageSize", pageSize);  // ÿҳ���ֵ
		data.put("currentPageRows", currentPageRows);  // ��ǰҳ��������
		
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
