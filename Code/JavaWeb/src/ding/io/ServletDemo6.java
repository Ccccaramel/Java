package ding.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ServletDemo6
 */
@WebServlet("/ServletDemo6")
public class ServletDemo6 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDemo6() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		response.setHeader("content-type", "text/html;charset=utf-8");
		
		// �ڿͻ��˻��� Servlet �����
		String data="setDataHeader";
		response.setDateHeader("expires", System.currentTimeMillis()+24*3600*1000);
		response.getWriter().append(data);		
		
		String path=this.getServletContext().getRealPath("/WEB-INF/classes/resource/db1.properties");  // ͨ�� ServletContext ��ȡ Web ��Դ�ľ���·��
		InputStream in=new FileInputStream(path);
		
//		ʹ����װ������ȡ��Դ�ļ�(�������д������õ�Ч)
//		ClassLoader loader=ServletDemo6.class.getClassLoader();
//		InputStream in=loader.getResourceAsStream("resource/db1.properties");
		
		Properties prop=new Properties();
		prop.load(in);
		String driver=prop.getProperty("driver");
		String url=prop.getProperty("url");
		String user=prop.getProperty("user");
		String password=prop.getProperty("password");
		
		response.getWriter().append(MessageFormat.format("driver={0},url={1},user={2},password={3}",driver,url,user,password));

	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
