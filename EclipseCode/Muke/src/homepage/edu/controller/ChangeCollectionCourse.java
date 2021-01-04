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

import homepage.edu.service.FavoriteService;

/**
 * Servlet implementation class ChangeCollectionCourse
 */
@WebServlet("/ChangeCollectionCourse")
public class ChangeCollectionCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeCollectionCourse() {
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
		Map<Object,Object> data=new HashMap<>();
		FavoriteService favoriteService=new FavoriteService();
		Gson gson=new Gson();
		int userId,courseId,sign;
		if(session.getAttribute("state")!=null&&(boolean) session.getAttribute("state")) {
			if((boolean) session.getAttribute("teacher")) {
				data.put("pass", false);
				data.put("message", "抱歉，教师暂未开放此功能，您可以注册一个普通账号进行操作!");
			}else {
				data.put("pass", true);
				userId=(int) session.getAttribute("id");
				courseId=Integer.parseInt(request.getParameter("courseId"));
				sign=Integer.parseInt(request.getParameter("sign"));
				if(sign==0) {
					favoriteService.addFavorite(userId, courseId);
				}else if(sign==1) {
					favoriteService.delFavorite(userId, courseId);
				}else {
					data.put("result", true);
				}
				data.put("result", false);
			}
		}else {
			data.put("pass", false);
			data.put("message", "请登录后再操作!");
		}
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
