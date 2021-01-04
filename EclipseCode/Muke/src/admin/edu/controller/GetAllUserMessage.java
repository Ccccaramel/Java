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

import homepage.edu.dao.Student;
import homepage.edu.service.StudentService;

/**
 * Servlet implementation class GetAllUserMessage
 */
@WebServlet("/GetAllUserMessage")
public class GetAllUserMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllUserMessage() {
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
		Gson gson=new Gson();
		StudentService studentService=new StudentService();
		int pageSize=12;  //每页最大值
		int page;  //第几页
		String pageStr=request.getParameter("pageNum");
		String key=request.getParameter("key");
		if(pageStr==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageStr); 
		}
		int startPage=page*pageSize;  //偏移量
		int row=studentService.getStudentTableRow(startPage, pageSize, key);  //数据总数
		int totalPage=(int)Math.ceil(row*1.0/pageSize);  //总页数
		List<Student> studentList=studentService.getAllStudentMessage(startPage, pageSize, key);
		data.put("studentList", studentList);
		data.put("total", row);
		data.put("pageSize", pageSize);
		data.put("totalPage", totalPage);
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
