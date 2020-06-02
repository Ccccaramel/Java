package com.ding;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServletDemo4
 */
@WebServlet("/ServletDemo4")
public class ServletDemo4 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo4() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 多个 Servlet 通过 ServletContext 对象实现数据共享
		response.setContentType("text/html;charset=utf-8");
		ServletContext context=this.getServletContext(); 
		String data=(String)context.getAttribute("data");
		response.getWriter().append("data:"+data).append(request.getContextPath());
		
		// 获取 Web 应用的初始化参数
		ServletContext sc=this.getServletContext();
		String cp=sc.getInitParameter("url");
		response.getWriter().print(cp);
		response.getWriter().append(" 4s后实现用 ServletContext 实现请求转发");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
