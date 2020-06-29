package homepage.edu.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class CookieExamine
 */
@WebServlet("/CookieExamine")
public class CookieExamine extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CookieExamine() {
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
		Cookie[] cookies=request.getCookies();
		boolean state=false;
		int id = 0;
		String password = null;
		boolean teacher = false;
		boolean loginRemenberMe = false;
		String message = null;
		if(cookies!=null) {
			for(Cookie c:cookies) {
				String name=c.getName();  //获取cookie名称
				if("id".equals(name)) { 
					id=Integer.parseInt(c.getValue());  //获取cookie值并存入
				}
				if("password".equals(name)) { 
					password = c.getValue();  //获取cookie值并存入
				}
				if("teacher".equals(name)) { 
					loginRemenberMe=Boolean.valueOf(c.getValue()).booleanValue();  //获取cookie值并存入
				}
				if("loginRemenberMe".equals(name)) { 
					loginRemenberMe=Boolean.valueOf(c.getValue()).booleanValue();  //获取cookie值并存入
				}
				if("state".equals(name)) { 
					state=Boolean.valueOf(c.getValue()).booleanValue();
				}
			}
		}else {
			state=false;
		}
		data.put("state", state);
		if(state==true) {
			data.put("id", id);
			data.put("password", password);
			data.put("teacher", teacher);
			data.put("loginRemenberMe", loginRemenberMe);
			data.put("message", "你的账号密码已保存在cookie中");			
		}else {
			data.put("message", "Cookie未记住密码!");	
		}

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
