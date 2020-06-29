package admin.edu.controller;

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

import homepage.edu.dao.ItemBack;
import homepage.edu.service.ItemBackService;

/**
 * Servlet implementation class GetAllTestMessage
 */
@WebServlet("/GetAllTestMessage")
public class GetAllTestMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllTestMessage() {
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
		Gson gson=new Gson();
		Map<Object,Object> data=new HashMap<>();
		ItemBackService itemBackService=new ItemBackService();
		String pageNum=request.getParameter("pageNum");
		String key=request.getParameter("key");
		int page;
		if(pageNum==null) {
			page=1;
		}else {
			page=Integer.parseInt(pageNum);
		}
		int pageSize=12;
		int row=itemBackService.getAllTestRow(key);
		int startPage=page*pageSize;
		int totalPage=(int) Math.ceil(row*1.0/pageSize);
		data.put("total", row);
		data.put("pageSize", pageSize);
		data.put("totalPage", totalPage);
		
		List<ItemBack> listItemBacks=new ArrayList<ItemBack>();
		listItemBacks=itemBackService.getAllTestMessage(startPage, pageSize,key);
		data.put("testList", listItemBacks);
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
