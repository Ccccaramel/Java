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

import homepage.edu.dao.Teacher;
import homepage.edu.service.TeacherService;

/**
 * Servlet implementation class GetAllTeacher
 */
@WebServlet("/GetAllTeacher")
public class GetAllTeacher extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllTeacher() {
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
		TeacherService teacherService=new TeacherService();
		int row=teacherService.getTeacherTableRow();
		int page;
		int pageSize=12;
		int totalPage=(int)Math.ceil(row*1.0/pageSize);
		String pageStr=request.getParameter("pageNum");
		String key=request.getParameter("key");
		if(pageStr==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageStr);
		}
		int startPage=page*pageSize;
		List<Teacher> teacherList=teacherService.getAllTeacher(key,startPage,pageSize);
		data.put("teacherList", teacherList);
		data.put("total", row);
		data.put("totalPage", totalPage);
		data.put("pageSize", pageSize);
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
