package ding.io;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo3
 */
@WebServlet("/ServletDemo3")
public class ServletDemo3 extends HttpServlet {
//	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo3() {
        super();
        // TODO Auto-generated constructor stub
    }
    
//    private ServletConfig config;
//    public void init(ServletConfig config) throws ServletException {
//    	this.config=config;
//    }
            
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=utf-8");  // 别忘了这个,否则你的 html 标签会失效以及中文乱码
		String ServletName=this.getServletConfig().getInitParameter("name");
		response.getWriter().println("<h3>"+ServletName+"</h3>");	
		
		Enumeration<String> e=this.getServletConfig().getInitParameterNames();
		while(e.hasMoreElements()) {
			String name=e.nextElement();
			String value=this.getServletConfig().getInitParameter(name);
			response.getWriter().println(name+":"+value+"<br/>");
		}
		String ServletAge=this.getServletConfig().getInitParameter("age");
		response.getWriter().println("<h3>"+ServletAge+"</h3>");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
