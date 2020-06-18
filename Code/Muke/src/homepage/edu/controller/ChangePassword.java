package homepage.edu.controller;

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

import homepage.edu.service.TeacherService;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		HttpSession session=request.getSession();
		Map<Object,Object> data=new HashMap<Object,Object>();
		TeacherService teacherService=new TeacherService();
		Gson gson=new Gson();
		Boolean state=false;
		String message=null;
		
		int id=(int) session.getAttribute("id");
		String userOldPassword=request.getParameter("oldPassword");
		String userNewPassword=request.getParameter("newPassword");
		if(teacherService.IdLogin(id, userOldPassword)) {  //旧密码输入正确
			System.out.println("旧密码输入正确");
			if(teacherService.checkNewPassword(id, userNewPassword)) {  //检查密码是否曾今使用过
				teacherService.updatePassword(id, userOldPassword, userNewPassword);
				state=true;
				System.out.println("修改密码成功");
				message="修改密码成功";
			}else{  //新密码与上次使用的密码一致
				message="新密码与上次使用的密码一致";
				System.out.println("新密码与曾经使用的密码一致,请换一个密码");
			}
		}else {  //旧密码输入错误
			message="旧密码输入错误";
			System.out.println("旧密码输入错误");
		}
		
		data.put("state", state);
		data.put("message", message);
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
