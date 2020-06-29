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

import homepage.edu.service.StudentService;
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
		StudentService studentService=new StudentService();
		boolean teacher=(boolean) session.getAttribute("teacher");
		Gson gson=new Gson();
		Boolean state=false;
		String message=null;
		
		int id=(int) session.getAttribute("id");
		String userOldPassword=request.getParameter("oldPassword");
		String userNewPassword=request.getParameter("newPassword");
		if(teacher) {
			if(teacherService.IdLogin(id, userOldPassword)) {  //������������ȷ
				System.out.println("������������ȷ");
				if(teacherService.checkNewPassword(id, userNewPassword)) {  //��������Ƿ�����ʹ�ù�
					teacherService.updatePassword(id, userOldPassword, userNewPassword);
					state=true;
					System.out.println("�޸�����ɹ�");
					message="�޸�����ɹ�";
				}else{  //���������ϴ�ʹ�õ�����һ��
					message="���������ϴ�ʹ�õ�����һ��";
					System.out.println("������������ʹ�õ�����һ��,�뻻һ������");
				}
			}else {  //�������������
				message="�������������";
				System.out.println("�������������");
			}			
		}else {
			if(studentService.IdLogin(id, userOldPassword)) {  //������������ȷ
				System.out.println("������������ȷ");
				if(studentService.checkNewPassword(id, userNewPassword)) {  //��������Ƿ�����ʹ�ù�
					studentService.updatePassword(id, userOldPassword, userNewPassword);
					state=true;
					System.out.println("�޸�����ɹ�");
					message="�޸�����ɹ�";
				}else{  //���������ϴ�ʹ�õ�����һ��
					message="���������ϴ�ʹ�õ�����һ��";
					System.out.println("������������ʹ�õ�����һ��,�뻻һ������");
				}
			}else {  //�������������
				message="�������������";
				System.out.println("�������������");
			}	
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
