package admin.edu.controller.tool;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import homepage.edu.service.CourseResourceService;
import homepage.edu.service.CourseService;
import homepage.edu.service.ItemBackService;

public class ResourceUploadAndDownload {
	private String saveImgPath;
	private String saveVideoPath;
	private String saveCourseResourcePath;
	
	public ResourceUploadAndDownload() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ResourceUploadAndDownload(String saveImgPath, String saveVideoPath) {
		super();
		this.saveImgPath = saveImgPath;
		this.saveVideoPath = saveVideoPath;
	}
	
	public ResourceUploadAndDownload(String saveCourseResourcePath) {
		super();
		this.saveCourseResourcePath =saveCourseResourcePath;
	}
	
	public Map<Object,Object> resourceUpload(HttpServletRequest request,int userId){  //�γ��ϴ������ز������������
		File imgFile=new File(saveImgPath);
		Map<Object,Object> result=new HashMap<Object,Object>();
		if(!imgFile.exists()&&imgFile.isDirectory()) {  //�ļ��Ƿ������ΪĿ¼
			System.out.println("Ŀ¼������,��Ҫ����!");
			imgFile.mkdir();  //����Ŀ¼
		}
		String message="";
		DiskFileItemFactory factory=new DiskFileItemFactory();  //����һ�� DiskFileItemFactory ����
		ServletFileUpload upload=new ServletFileUpload(factory);  //����һ���ļ��ϴ�������
		upload.setHeaderEncoding("utf-8");  //��ֹ��������
		if(!ServletFileUpload.isMultipartContent(request)) {  //�ж��ύ���Ƿ�Ϊ������
			return result;
		}
		try {
			//ʹ�� ServletFileUpload �����������ϴ�����,����������ص���һ�� List<FileItem> ����,ÿ�� FileItem ��Ӧһ�� From ����������
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list) {
				if(item.isFormField()) {  //��� fileItem �з�װ������ͨ�����������
					String name=item.getFieldName();
					String value=item.getString("utf-8");  //��ֹ��������
					if(value!=""&&value.length()>0) {
						result.put(name, value);
					}
					System.out.println(name+"="+value);
				}else {  //��� fileItem �з�װ�����ϴ��ļ�
					String name=item.getFieldName();
					
					String fileName=item.getName();  //�õ��ļ�����
					System.out.println(fileName);
					if(fileName==null||fileName.trim().equals("")) {  //�ж��ļ����Ƿ�Ϊ��
						continue;
					}
					//��ͬ������ύ���ļ����ǲ�һ����,��Щ������ύ�������ļ����Ǵ���·����,���е������û�д�·��
					fileName=fileName.substring(fileName.lastIndexOf("\\")+1);  //ֻ�����ļ�������
					Map<Object,Object> data=this.newName(fileName, userId);
					result.put(name,data.get("name"));  //ֻ����Դ����,������һ���ļ����ڰ���̫����ԴҪʹ�� Hash �㷨��ɢ�洢,�ݲ������洢
					int type=(int) data.get("type");
					InputStream in=item.getInputStream();  //��ȡ item �е��ϴ��ļ���������
					FileOutputStream out;
					if(type==0) {  //ͼƬ
						out=new FileOutputStream(this.saveImgPath+"\\"+data.get("name"));  //����һ���ļ������
//						result.put(name,this.saveImgPath+"\\"+data.get("name"));  //���洢����λ��,ֻ�洢�ļ���
					}else if(type==1){  //��Ƶ
						out=new FileOutputStream(this.saveVideoPath+"\\"+data.get("name"));  //����һ���ļ������
//						result.put(name,this.saveVideoPath+"\\"+data.get("name"));
					}else {
						System.out.println("�Ƿ�����!");
						continue;
					}
						
//					FileOutputStream out=new FileOutputStream(saveImgPath+"\\"+fileName);  //����һ���ļ������
					byte buffer[]=new byte[1024];  //����һ��������
					int len=0;  //�����ж��������е������Ƿ��Ѿ�����ı�ʶ
					while((len=in.read(buffer))>0) {
						out.write(buffer, 0, len);  //ʹ�� FileOutputStream �������������������д�뵽ָ����Ŀ¼��
					}
					in.close();  //�ر�������
					out.close();  //�ر������
					item.delete();  //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
					message="�ļ��ϴ��ɹ�";
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			message="�ļ��ϴ�ʧ��";
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public Map<Object,Object> courseResourceUpload(HttpServletRequest request,int userId){  //�γ���Դ�ϴ������ز������������
		File courseResourceFile=new File(this.saveCourseResourcePath);
		Map<Object,Object> result=new HashMap<Object,Object>();
		if(!courseResourceFile.exists()&&courseResourceFile.isDirectory()) {  //�ļ��Ƿ������ΪĿ¼
			System.out.println("Ŀ¼������,��Ҫ����!");
			courseResourceFile.mkdir();  //����Ŀ¼
		}
		String message="";
		DiskFileItemFactory factory=new DiskFileItemFactory();  //����һ�� DiskFileItemFactory ����
		ServletFileUpload upload=new ServletFileUpload(factory);  //����һ���ļ��ϴ�������
		upload.setHeaderEncoding("utf-8");  //��ֹ��������
		if(!ServletFileUpload.isMultipartContent(request)) {  //�ж��ύ���Ƿ�Ϊ������
			return result;
		}
		try {
			//ʹ�� ServletFileUpload �����������ϴ�����,����������ص���һ�� List<FileItem> ����,ÿ�� FileItem ��Ӧһ�� From ����������
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list) {
				if(item.isFormField()) {  //��� fileItem �з�װ������ͨ�����������
					String name=item.getFieldName();
					String value=item.getString("utf-8");  //��ֹ��������
					if(value!=""&&value.length()>0) {
						result.put(name, value);
					}
					System.out.println(name+"="+value);
				}else {  //��� fileItem �з�װ�����ϴ��ļ�
					String name=item.getFieldName();
					
					String fileName=item.getName();  //�õ��ļ�����
					System.out.println(fileName);
					if(fileName==null||fileName.trim().equals("")) {  //�ж��ļ����Ƿ�Ϊ��
						continue;
					}
					//��ͬ������ύ���ļ����ǲ�һ����,��Щ������ύ�������ļ����Ǵ���·����,���е������û�д�·��
					fileName=fileName.substring(fileName.lastIndexOf("\\")+1);  //ֻ�����ļ�������
					result.put(name+"-oldName", fileName);
					Map<Object,Object> data=this.newName(fileName, userId);
					result.put(name,data.get("name"));  //ֻ����Դ����,������һ���ļ����ڰ���̫����ԴҪʹ�� Hash �㷨��ɢ�洢,�ݲ������洢
					int type=(int) data.get("type");
					InputStream in=item.getInputStream();  //��ȡ item �е��ϴ��ļ���������
					FileOutputStream out;
					if(type!=-1) {  //ͼƬ
						out=new FileOutputStream(this.saveCourseResourcePath+"\\"+data.get("name"));  //����һ���ļ������
					}else {
						System.out.println("�Ƿ�����!");
						continue;
					}
					byte buffer[]=new byte[1024];  //����һ��������
					int len=0;  //�����ж��������е������Ƿ��Ѿ�����ı�ʶ
					while((len=in.read(buffer))>0) {
						out.write(buffer, 0, len);  //ʹ�� FileOutputStream �������������������д�뵽ָ����Ŀ¼��
					}
					in.close();  //�ر�������
					out.close();  //�ر������
					item.delete();  //ɾ�������ļ��ϴ�ʱ���ɵ���ʱ�ļ�
					message="�ļ��ϴ��ɹ�";
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			message="�ļ��ϴ�ʧ��";
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public Map<Object,Object> testUpload(HttpServletRequest request){  //�������Ծ���������
		Map<Object,Object> result=new HashMap<Object,Object>();
		String message="";
		DiskFileItemFactory factory=new DiskFileItemFactory();  //����һ�� DiskFileItemFactory ����
		ServletFileUpload upload=new ServletFileUpload(factory);  //����һ���ļ��ϴ�������
		upload.setHeaderEncoding("utf-8");  //��ֹ��������
		if(!ServletFileUpload.isMultipartContent(request)) {  //�ж��ύ���Ƿ�Ϊ������
			return result;
		}
		try {
			//ʹ�� ServletFileUpload �����������ϴ�����,����������ص���һ�� List<FileItem> ����,ÿ�� FileItem ��Ӧһ�� From ����������
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list) {
				String name=item.getFieldName();
				String value=item.getString("utf-8");  //��ֹ��������
				if(value!=""&&value.length()>0) {
					result.put(name, value);
				}
				System.out.println(name+"="+value);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			message="�ļ��ϴ�ʧ��";
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public Map<Object,Object> resourceTable(HttpServletRequest request){  //����������������
		Map<Object,Object> result=new HashMap<Object,Object>();
		String message="";
		DiskFileItemFactory factory=new DiskFileItemFactory();  //����һ�� DiskFileItemFactory ����
		ServletFileUpload upload=new ServletFileUpload(factory);  //����һ���ļ��ϴ�������
		upload.setHeaderEncoding("utf-8");  //��ֹ��������
		if(!ServletFileUpload.isMultipartContent(request)) {  //�ж��ύ���Ƿ�Ϊ������
			return result;
		}
		try {
			//ʹ�� ServletFileUpload �����������ϴ�����,����������ص���һ�� List<FileItem> ����,ÿ�� FileItem ��Ӧһ�� From ����������
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list) {
				String name=item.getFieldName();
				String value=item.getString("utf-8");  //��ֹ��������
				if(value!=""&&value.length()>0) {
					result.put(name, value);
				}
				System.out.println(name+"="+value);
			}
		}catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	public void courseEntry(HttpServletRequest request,Map<Object,Object> data,String saveVideoPath) {//�γ����ݱ��������ݿ�
		//foreach()��������ȡ����,��Ҫ"����"ȡֵ
		HttpSession session=request.getSession();
		CourseService courseService=new CourseService();
		boolean edit=Boolean.parseBoolean((String)data.get("edit"));
		if(edit) {
			courseService.changeCourse((String)data.get("courseName"),(String)data.get("courseImg"),Integer.parseInt((String) data.get("courseType")), Integer.parseInt((String)data.get("courseId")));
		}else {
			courseService.addCourse((String)data.get("courseName"),
					(String)data.get("courseImg"),
					Integer.parseInt((String) data.get("courseType")), 
					(int) session.getAttribute("id"));  //�γ̻�����Ϣ			
		}

		for(int i=1;i<=3;i++) {  //�γ̼��
			String str="courseAbstract-"+i;
			String courseIntroduce=(String) data.get(str);
			if(courseIntroduce!=null&&courseIntroduce.length()>0) {
				System.out.println("courseIntroduce:"+courseIntroduce);
				courseService.saveCourseAbstract(i,courseIntroduce);
			}else {
				if(edit) {
					courseService.delCourseAbstract(i, Integer.parseInt((String)data.get("courseId")));
				}
				System.out.println("�����ڵ�"+i+"�μ��");
			}
		}
		int chapterId=1;
		for(;data.get("chapter-"+chapterId+"-name")!=null;chapterId++) {  //�½��ж� 
			int sectionId=1;
			for(;data.get("chapter-"+chapterId+"-section-"+sectionId+"-name")!=null;sectionId++) {  //С���ж�
				String chapterName=(String) data.get("chapter-"+chapterId+"-name");
				String sectionName=(String) data.get("chapter-"+chapterId+"-section-"+sectionId+"-name");
				String mvAdd=(String) data.get("chapter-"+chapterId+"-section-"+sectionId+"-video");
				courseService.saveCourseChapter(chapterId, chapterName, sectionId, sectionName, mvAdd);
			}
			if(edit) {
				courseService.delMV(Integer.parseInt((String)data.get("courseId")), chapterId, sectionId, saveVideoPath);
				courseService.delCourseSection(Integer.parseInt((String)data.get("courseId")), chapterId, sectionId);
			}
			
		}
		if(edit) {
			courseService.delMV(Integer.parseInt((String)data.get("courseId")), chapterId, saveVideoPath);
			courseService.delCourseChapter(Integer.parseInt((String)data.get("courseId")), chapterId);
		}
		
	}
	public void testEntry(HttpServletRequest request,Map<Object,Object> data) {  //���Ծ����ݱ��������ݿ�
		//foreach()��������ȡ����,��Ҫ"����"ȡֵ
		HttpSession session=request.getSession();
		int teacherId=(int) session.getAttribute("id");
		int testId = 0;
		ItemBackService itemBackService=new ItemBackService();
		boolean edit=Boolean.parseBoolean((String)data.get("edit"));
		if(edit) {
			testId=Integer.parseInt((String) data.get("testId"));
			itemBackService.updateItemBack(Integer.parseInt((String) data.get("courseId")), 
			(String)data.get("testName"),
			Integer.parseInt((String)data.get("totalScore")) , 
			Integer.parseInt((String)data.get("passLine")), 
			testId);
		}else {
			itemBackService.addItemBack(Integer.parseInt((String) data.get("courseId")), 
					(String)data.get("testName"),
					Integer.parseInt((String)data.get("totalScore")) , 
					Integer.parseInt((String)data.get("passLine")), 
					teacherId);  //������Ծ������Ϣ		
		}
		int questionNumber=1;
		int optionNumber=1;
		for(;questionNumber>0;questionNumber++) {  //���Ծ���Ŀ��ֵ��ȷѡ���
			String questionContent=(String) data.get("question-"+questionNumber);
			if(questionContent==null||questionContent.length()==0) {  //�����ڵ�i��,����������Ѷ�ȡ���
				break;
			}
			int rightKey=Integer.parseInt((String) data.get("question-"+questionNumber+"-right-key"));
			String analysis=(String) data.get("question-"+questionNumber+"-analysis");
			int score=Integer.parseInt((String) data.get("question-"+questionNumber+"-score"));
			int questionId=0;
			itemBackService.addTestQuestion(questionNumber, 
						questionContent, 
						rightKey, 
						analysis, 
						score);
			optionNumber=1;
			for(;optionNumber<=4;optionNumber++) {
				String optionContent=(String) data.get("question-"+questionNumber+"-option-"+optionNumber);
				itemBackService.saveTestOption(questionNumber, optionNumber, optionContent);
			}
		}
		if(edit) {  //��ɾѡ����ɾ����
			itemBackService.delTestQuestion(testId, questionNumber);
			System.out.println("�����ڵ�"+questionNumber+"��");	
		}

	}
	public void courseResourceEntry(HttpServletRequest request,Map<Object,Object> data) {//�γ̶�����Դ���������ݿ�
		//foreach()��������ȡ����,��Ҫ"����"ȡֵ
		HttpSession session=request.getSession();
		CourseResourceService courseResourceService=new CourseResourceService();
		int id=(int) session.getAttribute("id");
		boolean teacher= (boolean) session.getAttribute("teacher");
		int userType=0;
		if(teacher) {
			userType=1;
		}

		for(int i=1;;i++) {  //��Դ
			String linkAdd=(String) data.get("resource-"+i);
			String resourceName=(String) data.get("resource-"+i+"-oldName");
			int courseId=Integer.parseInt((String) data.get("courseId"));
			if(linkAdd!=null&&linkAdd.length()>0) {
				courseResourceService.addCourseResource(resourceName, courseId, linkAdd, id, userType);
			}else {
				System.out.println("�����ڵ�"+i+"����Դ");
				break;
			}
		}
		int chapterId=1;
		for(;data.get("chapter-"+chapterId+"-name")!=null;chapterId++) {  //�½��ж� 
			int sectionId=1;
		}

		
	}
	public String saveImg(String name) {
		String message=null;
		return message;
	}
	public String saveVideo(String name) {
		String message=null;
		return message;
	}
	public Map<Object,Object> newName(String fileName,int userId) {
		Map<Object,Object> data=new HashMap<Object,Object>();
		String name=null;
		String suffix=fileName.substring(fileName.lastIndexOf(".")+1);  //ֻ�����ļ�������
		int type=this.suffixJudgment(suffix);
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar=Calendar.getInstance();
		int num=1000+(int)(Math.random()*(9000));
		name=df.format(calendar.getTime())+userId+num+"."+suffix;
		data.put("name", name);  //������ xxx.xxx(2020.png 1234.mp4 ...)
		data.put("type", type);  //���� 0:ͼƬ 1:��׺
		data.put("suffix", suffix);  // ��׺ xx(png mp4 ...)
		return data;
	}
	public int suffixJudgment(String suffix) {  //�ж���Դ���Ͳ����ر�ʶ��
		int type=-1;  //-1:δ֪���� 0:ͼƬ 1:��Ƶ 2:ѹ���� 3:�ĵ�/���
		if(suffix.equalsIgnoreCase("png")||suffix.equalsIgnoreCase("jpeg")||suffix.equalsIgnoreCase("jpg")||suffix.equalsIgnoreCase("tif")) {
			type=0;
		}else if(suffix.equalsIgnoreCase("avi")||suffix.equalsIgnoreCase("mp4")||suffix.equalsIgnoreCase("wmv")||suffix.equalsIgnoreCase("mov")){
			type=1;
		}else if(suffix.equalsIgnoreCase("zip")||suffix.equalsIgnoreCase("rar")||suffix.equalsIgnoreCase("arj")||suffix.equalsIgnoreCase("z")){
			type=2;
		}else if(suffix.equalsIgnoreCase("txt")||suffix.equalsIgnoreCase("doc")||suffix.equalsIgnoreCase("docx")||suffix.equalsIgnoreCase("rtf")||suffix.equalsIgnoreCase("wps")||suffix.equalsIgnoreCase("xls")||suffix.equalsIgnoreCase("xlsx")||suffix.equalsIgnoreCase("pdf")){
			type=3;
		}
		return type;
	}
}