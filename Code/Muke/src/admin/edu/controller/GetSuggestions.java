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

import com.google.gson.Gson;

import admin.edu.dao.Suggestions;
import admin.edu.service.SuggestionsService;

/**
 * Servlet implementation class GetSuggestions
 */
@WebServlet("/GetSuggestions")
public class GetSuggestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetSuggestions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");
		SuggestionsService suggestionsService=new SuggestionsService();
		Map<Object,Object> data=new HashMap<>();
		Gson gson=new Gson();
		int page=0;
		String pageStr=request.getParameter("pageNum");
		if(pageStr==null) {
			page=0;
		}else{
			page=Integer.parseInt(pageStr);
		}
		int pageSize=12;
		int startPage=page*pageSize;
		int row=suggestionsService.getSuggestionsTableRow();
		int totalPage=(int)Math.ceil(row*1.0/pageSize);
		
		
		List<Suggestions> suggestionsList=new ArrayList<Suggestions>();
		data.put("suggestionsList", suggestionsService.getAllSuggestions());
		data.put("total", row);
		data.put("totalPage", totalPage);
		data.put("pageSize", pageSize);
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
