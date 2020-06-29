package homepage.edu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import admin.edu.controller.tool.ResourceUploadAndDownload;

/**
 * Servlet implementation class SaveCourse
 */
@WebServlet("/SaveCourse")
public class SaveCourse extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SaveCourse() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session=request.getSession();
		int TeacherId=(int) session.getAttribute("id");
		boolean teacher=(boolean) session.getAttribute("teacher");
		
		//����,���� Servers �ᵼ����Դ���
//		String saveImgPath=this.getServletContext().getRealPath("/Data/img");
//		String saveVideoPath=this.getServletContext().getRealPath("/Data/video");  //��ȡ�ϴ��ļ��ı���Ŀ¼
		
		String realPath = this.getServletContext().getRealPath("/");
		String realPathParent=(new File(realPath)).getParent();
		System.out.println("realPathParent:"+realPathParent);
		String saveImgPath=realPathParent+"/Data/img";
		String saveVideoPath=realPathParent+"/Data/video";  //��ȡ�ϴ��ļ��ı���Ŀ¼
		ResourceUploadAndDownload resourceUploadAndDownload=new ResourceUploadAndDownload(saveImgPath, saveVideoPath);
		Map<Object,Object> data=new HashMap<Object,Object>();
		data=resourceUploadAndDownload.resourceUpload(request,TeacherId);  //��������,������Դ���洢,������Ϣ���� Map �в�����
		System.out.println("data:"+data);
		resourceUploadAndDownload.courseEntry(request,data,saveVideoPath);  //���������صı���Ϣ��ȡ�������ݿ���			


		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
