package admin.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import admin.edu.service.AdminService;
import admin.edu.service.CourseTypeService;
import homepage.edu.service.TeacherService;

/**
 * Servlet implementation class AccountManagement
 */
@WebServlet("/AccountManagement")
public class AccountManagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AccountManagement() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		int category = Integer.parseInt(request.getParameter("category"));
		int sign= Integer.parseInt(request.getParameter("sign"));
		int id=Integer.parseInt(request.getParameter("id"));
		String result = null;
		if(category== 0) {
			
		}else if(category== 1) {  //对管教师用户账号状态编辑
			TeacherService teacherService=new TeacherService();
			teacherService.updateTeacherAccountStatus(sign, id);
			result=teacherService.getAccountStatusStr(id);
		}else if(category== 2) {  // 对管理员用户账号状态编辑
			AdminService adminService=new AdminService();
			adminService.updatePower(sign, id);  // 更改低级权限的管理员账号状态
			result=adminService.getAccountStatusStr(id);  // 删除指定课程类型
		}else if(category== 3) {
			
		}else if(category== 4) {  // 对所有课程类型编辑
			CourseTypeService courseTypeService=new CourseTypeService();
			courseTypeService.setAsDefault(id);  // 将所有类型为待删除的课程类型的课程更改为"未知"类型
			int flag=courseTypeService.delCourseType(id);  // 删除指定课程类型
			if(flag==1) {
				System.out.println("已删除1条数据");
			}else {
				System.out.println("未删除任何数据或删除数据超过1条");
			}
		}else if(category== 5) {
			
		}else if(category== 6) {
			
		}else if(category== 7) {
			
		}else {
			result = null;
		}
		Map<Object,Object> data=new HashMap<Object,Object>();
		data.put("result", result);
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
