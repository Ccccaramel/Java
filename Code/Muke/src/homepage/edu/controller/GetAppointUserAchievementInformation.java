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

import homepage.edu.service.AchievementService;

/**
 * Servlet implementation class GetAppointUserAchievementInformation
 */
@WebServlet("/GetAppointUserAchievementInformation")
public class GetAppointUserAchievementInformation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAppointUserAchievementInformation() {
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
		AchievementService achievementService=new AchievementService();
		int page=0;
		int pageSize=12;
		String pageStr=(String) request.getParameter("pageNum");
		if(pageStr==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageStr);
		}
		int startPage=page*pageSize;
		int userId=(int) session.getAttribute("id");
		int row=achievementService.getAppointUserAchievementRow(userId);
		int totalPage=(int) Math.ceil(row*1.0/pageSize);
		Map<Object,Object> data=new HashMap<>();
		data.put("list",achievementService.getAppointUserAchievement(userId,startPage,pageSize));
		data.put("totalPage", totalPage);
		data.put("total", row);
		data.put("pageSize", pageSize);
		Gson gson=new Gson();
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
