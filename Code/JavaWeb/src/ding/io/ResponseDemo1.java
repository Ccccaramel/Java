package ding.io;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ResponseDemo1
 */
@WebServlet("/ResponseDemo1")
public class ResponseDemo1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResponseDemo1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/** 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String s="中国";
		response.setHeader("content-type", "text/html;charset=utf-8");
		
		// OutputStream 输出
//		OutputStream os=response.getOutputStream();
//		byte[] dba=("OutputStream:"+s).getBytes("utf-8");
//		os.write(dba);  // 必须转换成字节否则报错
		
		// PrintWriter 输出
//		PrintWriter pw=response.getWriter();
//		// pw.write("<meta http-equiv='content-type' context='text/html;charset=utf-8'/>");  // 与上面等效
//		pw.write("PrintWriter:"+s+",int:"+1);
		
		// 文件下载
//		String realPath=this.getServletContext().getRealPath("/WEB-INF/classes/resource/bill.jpg");
////		String fileName=realPath.substring(realPath.lastIndexOf("\\"+1));
//		FileReader in=new FileReader(realPath);
//		int len=0;
//		char[] buffer=new char[1024];
//		PrintWriter out=response.getWriter();
//		while((len=in.read(buffer))>0) {
//			out.write(buffer,0,len);
//		}
//		in.close();
		
		
		// 生成验证码 --- 运行此功能前把其它代码都注释掉,否则此功能无效
		response.setHeader("refresh", "5");
		BufferedImage image=new BufferedImage(120,20,BufferedImage.TYPE_INT_RGB);
		Graphics2D g=(Graphics2D)image.getGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 120, 20);
		g.setColor(Color.BLUE);
		g.setFont(new Font(null,Font.BOLD,20));
		g.drawString(makeNum(), 0, 20);
		response.setContentType("image/jpeg");
		// 设置 Http 响应头控制浏览器禁止缓存当前文档内容(仅指以下三行代码)
		response.setDateHeader("expries", -1);
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		ImageIO.write(image,"jpg",response.getOutputStream());
		
		// 通过 Response 实现请求重定向
//		response.sendRedirect("1.jsp");
	}

	private String makeNum() {
		// TODO Auto-generated method stub
		Random random=new Random();
		String num=random.nextInt(99999999)+"";
		StringBuffer s=new StringBuffer();
		for(int i=0;i<7-num.length();i++) {
			s.append("0");
		}
		num=s.toString()+num;
		return num;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
