package homepage.edu.controller;

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

import admin.edu.service.CourseTypeService;
import homepage.edu.dao.Course;
import homepage.edu.service.CourseService;

/**
 * Servlet implementation class AdoptCourseTypeSearchCourse
 */
@WebServlet("/AdoptCourseTypeSearchCourse")
public class AdoptCourseTypeSearchCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdoptCourseTypeSearchCourse() {
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
		Gson gson=new Gson();
		CourseTypeService courseTypeService=new CourseTypeService();

		int courseClass=Integer.parseInt(request.getParameter("courseTypeId"));  //�γ�����id
		session.removeAttribute("typeValue");
		System.out.println("courseClass:"+courseClass);
		CourseService courseService=new CourseService();
		String pageNum=request.getParameter("pageNum");  //ҳ��
		int page;
		if(pageNum==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageNum);
		}
		int pageSize=12;  //�涨ÿҳ��ʾ��������
		int row=0;  //�γ�����
		int startPage=page*pageSize;  //ƫ����
		
		List<Course> courseList=new ArrayList<Course>();
		if(courseClass==-1) {
			row=courseService.getAllCourseRow();  //�γ�����
			courseList=courseService.getAllTypeCourse(startPage,pageSize);
			data.put("typeName", "ȫ���γ�");
		}else {
			row=courseService.getAdoptCourseTypeRow(courseClass);  //�γ�����
			courseList=courseService.getAdoptTypeCourse(courseClass,startPage,pageSize);
			data.put("typeName", courseTypeService.getcourseType(courseClass));
		}
		int totalPage=(int) Math.ceil(row*1.0/pageSize);  //��ҳ��
		data.put("courseList", courseList);
		data.put("total", row);
		data.put("pageSize", pageSize);
		data.put("totalPage", totalPage);
		String dataJson=gson.toJson(data);
		System.out.println("dataJson:"+dataJson);
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
