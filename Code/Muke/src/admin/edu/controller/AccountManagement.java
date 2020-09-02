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

import admin.edu.service.AdminService;
import admin.edu.service.CoursePromotionService;
import admin.edu.service.CourseTypeService;
import homepage.edu.service.CourseResourceService;
import homepage.edu.service.CourseService;
import homepage.edu.service.ItemBackService;
import homepage.edu.service.StudentService;
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
		HttpSession session=request.getSession();
		int category = Integer.parseInt(request.getParameter("category"));
		int sign= Integer.parseInt(request.getParameter("sign"));
		int id=Integer.parseInt(request.getParameter("id"));
		String result = null;
		if(category== 0) {
			StudentService studentService=new StudentService();
			studentService.updateStudentAccountStatus(sign, id);
			result=studentService.getStudentAccountStr(id);
		}else if(category== 1) {  //�Թܽ�ʦ�û��˺�״̬�༭
			TeacherService teacherService=new TeacherService();
			teacherService.updateTeacherAccountStatus(sign, id);
			result=teacherService.getAccountStatusStr(id);
		}else if(category== 2) {  // �Թ���Ա�û��˺�״̬�༭
			AdminService adminService=new AdminService();
			adminService.updatePower(sign, id);  // ���ĵͼ�Ȩ�޵Ĺ���Ա�˺�״̬
			result=adminService.getAccountStatusStr(id);  // ɾ��ָ���γ�����
		}else if(category== 3) {  //����Ա/��ʦ�Կγ�״̬���б༭
			CourseService courseService=new CourseService();
			courseService.updateCourseStatus(sign, id);
			result=courseService.getCourseStatusStr(id);
		}else if(category== 4) {  // �����пγ����ͱ༭
			CourseTypeService courseTypeService=new CourseTypeService();
			courseTypeService.setAsDefault(id);  // ����������Ϊ��ɾ���Ŀγ����͵Ŀγ̸���Ϊ"δ֪"����
			int flag=courseTypeService.delCourseType(id);  // ɾ��ָ���γ�����
			if(flag==1) {
				System.out.println("��ɾ��1������");
			}else {
				System.out.println("δɾ���κ����ݻ�ɾ�����ݳ���1��");
			}
		}else if(category== 5) {  //�����пγ̲��Ծ�״̬�༭
			ItemBackService itemBackService=new ItemBackService();
			itemBackService.updateTestState(id, sign);
			result=itemBackService.getTestStateStr(id);
		}else if(category== 6) {  //�Կγ��ƹ�༭
			CoursePromotionService coursePromotionService=new CoursePromotionService();
			if(coursePromotionService.delCoursePromotion(id)) {
				System.out.println("�γ��ƹ�ɾ���ɹ�!");
				result="�γ��ƹ�ɾ���ɹ�!";
			}
			System.out.println("");
		}else if(category== 7) {  //�����пγ���Դ״̬�༭
//			boolean teacher=(boolean) session.getAttribute("teacher");
//			int userId=(int) session.getAttribute("id");
//			int userType=0;
//			if(teacher) {
//				userType=1;
//			}
			CourseResourceService courseResourceService=new CourseResourceService();
			courseResourceService.updateUserCourseResourceRow(sign, id);
			result=courseResourceService.getUserCourseResourceresourceAccountStr(id);
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
