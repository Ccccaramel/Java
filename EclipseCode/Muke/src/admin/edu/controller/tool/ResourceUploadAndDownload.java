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
	
	public Map<Object,Object> resourceUpload(HttpServletRequest request,int userId){  //课程上传至本地并返回相关数据
		File imgFile=new File(saveImgPath);
		Map<Object,Object> result=new HashMap<Object,Object>();
		if(!imgFile.exists()&&imgFile.isDirectory()) {  //文件是否存在且为目录
			System.out.println("目录不存在,需要创建!");
			imgFile.mkdir();  //创建目录
		}
		String message="";
		DiskFileItemFactory factory=new DiskFileItemFactory();  //创建一个 DiskFileItemFactory 工厂
		ServletFileUpload upload=new ServletFileUpload(factory);  //创建一个文件上传解析器
		upload.setHeaderEncoding("utf-8");  //防止中文乱码
		if(!ServletFileUpload.isMultipartContent(request)) {  //判断提交的是否为表单数据
			return result;
		}
		try {
			//使用 ServletFileUpload 解析器解析上传数据,解析结果返回的是一个 List<FileItem> 集合,每个 FileItem 对应一个 From 表单的输入项
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list) {
				if(item.isFormField()) {  //如果 fileItem 中封装的是普通输入项的数据
					String name=item.getFieldName();
					String value=item.getString("utf-8");  //防止中文乱码
					if(value!=""&&value.length()>0) {
						result.put(name, value);
					}
					System.out.println(name+"="+value);
				}else {  //如果 fileItem 中封装的是上传文件
					String name=item.getFieldName();
					
					String fileName=item.getName();  //得到文件名称
					System.out.println(fileName);
					if(fileName==null||fileName.trim().equals("")) {  //判断文件名是否为空
						continue;
					}
					//不同浏览器提交的文件名是不一样的,有些浏览器提交上来的文件名是带有路径的,而有的浏览器没有带路径
					fileName=fileName.substring(fileName.lastIndexOf("\\")+1);  //只保留文件名部分
					Map<Object,Object> data=this.newName(fileName, userId);
					result.put(name,data.get("name"));  //只存资源名字,若不想一个文件夹内包含太多资源要使用 Hash 算法打散存储,暂不分流存储
					int type=(int) data.get("type");
					InputStream in=item.getInputStream();  //获取 item 中的上传文件的输入流
					FileOutputStream out;
					if(type==0) {  //图片
						out=new FileOutputStream(this.saveImgPath+"\\"+data.get("name"));  //创建一个文件输出流
//						result.put(name,this.saveImgPath+"\\"+data.get("name"));  //不存储绝对位置,只存储文件名
					}else if(type==1){  //视频
						out=new FileOutputStream(this.saveVideoPath+"\\"+data.get("name"));  //创建一个文件输出流
//						result.put(name,this.saveVideoPath+"\\"+data.get("name"));
					}else {
						System.out.println("非法类型!");
						continue;
					}
						
//					FileOutputStream out=new FileOutputStream(saveImgPath+"\\"+fileName);  //创建一个文件输出流
					byte buffer[]=new byte[1024];  //创建一个缓冲区
					int len=0;  //用来判断输入流中的数据是否已经读完的标识
					while((len=in.read(buffer))>0) {
						out.write(buffer, 0, len);  //使用 FileOutputStream 输出流将缓冲区的数据写入到指定的目录中
					}
					in.close();  //关闭输入流
					out.close();  //关闭输出流
					item.delete();  //删除处理文件上传时生成的临时文件
					message="文件上传成功";
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			message="文件上传失败";
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
	public Map<Object,Object> courseResourceUpload(HttpServletRequest request,int userId){  //课程资源上传至本地并返回相关数据
		File courseResourceFile=new File(this.saveCourseResourcePath);
		Map<Object,Object> result=new HashMap<Object,Object>();
		if(!courseResourceFile.exists()&&courseResourceFile.isDirectory()) {  //文件是否存在且为目录
			System.out.println("目录不存在,需要创建!");
			courseResourceFile.mkdir();  //创建目录
		}
		String message="";
		DiskFileItemFactory factory=new DiskFileItemFactory();  //创建一个 DiskFileItemFactory 工厂
		ServletFileUpload upload=new ServletFileUpload(factory);  //创建一个文件上传解析器
		upload.setHeaderEncoding("utf-8");  //防止中文乱码
		if(!ServletFileUpload.isMultipartContent(request)) {  //判断提交的是否为表单数据
			return result;
		}
		try {
			//使用 ServletFileUpload 解析器解析上传数据,解析结果返回的是一个 List<FileItem> 集合,每个 FileItem 对应一个 From 表单的输入项
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list) {
				if(item.isFormField()) {  //如果 fileItem 中封装的是普通输入项的数据
					String name=item.getFieldName();
					String value=item.getString("utf-8");  //防止中文乱码
					if(value!=""&&value.length()>0) {
						result.put(name, value);
					}
					System.out.println(name+"="+value);
				}else {  //如果 fileItem 中封装的是上传文件
					String name=item.getFieldName();
					
					String fileName=item.getName();  //得到文件名称
					System.out.println(fileName);
					if(fileName==null||fileName.trim().equals("")) {  //判断文件名是否为空
						continue;
					}
					//不同浏览器提交的文件名是不一样的,有些浏览器提交上来的文件名是带有路径的,而有的浏览器没有带路径
					fileName=fileName.substring(fileName.lastIndexOf("\\")+1);  //只保留文件名部分
					result.put(name+"-oldName", fileName);
					Map<Object,Object> data=this.newName(fileName, userId);
					result.put(name,data.get("name"));  //只存资源名字,若不想一个文件夹内包含太多资源要使用 Hash 算法打散存储,暂不分流存储
					int type=(int) data.get("type");
					InputStream in=item.getInputStream();  //获取 item 中的上传文件的输入流
					FileOutputStream out;
					if(type!=-1) {  //图片
						out=new FileOutputStream(this.saveCourseResourcePath+"\\"+data.get("name"));  //创建一个文件输出流
					}else {
						System.out.println("非法类型!");
						continue;
					}
					byte buffer[]=new byte[1024];  //创建一个缓冲区
					int len=0;  //用来判断输入流中的数据是否已经读完的标识
					while((len=in.read(buffer))>0) {
						out.write(buffer, 0, len);  //使用 FileOutputStream 输出流将缓冲区的数据写入到指定的目录中
					}
					in.close();  //关闭输入流
					out.close();  //关闭输出流
					item.delete();  //删除处理文件上传时生成的临时文件
					message="文件上传成功";
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			message="文件上传失败";
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
	public Map<Object,Object> testUpload(HttpServletRequest request){  //解析测试卷并返回数据
		Map<Object,Object> result=new HashMap<Object,Object>();
		String message="";
		DiskFileItemFactory factory=new DiskFileItemFactory();  //创建一个 DiskFileItemFactory 工厂
		ServletFileUpload upload=new ServletFileUpload(factory);  //创建一个文件上传解析器
		upload.setHeaderEncoding("utf-8");  //防止中文乱码
		if(!ServletFileUpload.isMultipartContent(request)) {  //判断提交的是否为表单数据
			return result;
		}
		try {
			//使用 ServletFileUpload 解析器解析上传数据,解析结果返回的是一个 List<FileItem> 集合,每个 FileItem 对应一个 From 表单的输入项
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list) {
				String name=item.getFieldName();
				String value=item.getString("utf-8");  //防止中文乱码
				if(value!=""&&value.length()>0) {
					result.put(name, value);
				}
				System.out.println(name+"="+value);
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			message="文件上传失败";
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
	public Map<Object,Object> resourceTable(HttpServletRequest request){  //解析表单并返回数据
		Map<Object,Object> result=new HashMap<Object,Object>();
		String message="";
		DiskFileItemFactory factory=new DiskFileItemFactory();  //创建一个 DiskFileItemFactory 工厂
		ServletFileUpload upload=new ServletFileUpload(factory);  //创建一个文件上传解析器
		upload.setHeaderEncoding("utf-8");  //防止中文乱码
		if(!ServletFileUpload.isMultipartContent(request)) {  //判断提交的是否为表单数据
			return result;
		}
		try {
			//使用 ServletFileUpload 解析器解析上传数据,解析结果返回的是一个 List<FileItem> 集合,每个 FileItem 对应一个 From 表单的输入项
			List<FileItem> list=upload.parseRequest(request);
			for(FileItem item:list) {
				String name=item.getFieldName();
				String value=item.getString("utf-8");  //防止中文乱码
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
	public void courseEntry(HttpServletRequest request,Map<Object,Object> data,String saveVideoPath) {//课程数据保存至数据库
		//foreach()并不合适取数据,需要"定点"取值
		HttpSession session=request.getSession();
		CourseService courseService=new CourseService();
		boolean edit=Boolean.parseBoolean((String)data.get("edit"));
		if(edit) {
			courseService.changeCourse((String)data.get("courseName"),(String)data.get("courseImg"),Integer.parseInt((String) data.get("courseType")), Integer.parseInt((String)data.get("courseId")));
		}else {
			courseService.addCourse((String)data.get("courseName"),
					(String)data.get("courseImg"),
					Integer.parseInt((String) data.get("courseType")), 
					(int) session.getAttribute("id"));  //课程基本信息			
		}

		for(int i=1;i<=3;i++) {  //课程简介
			String str="courseAbstract-"+i;
			String courseIntroduce=(String) data.get(str);
			if(courseIntroduce!=null&&courseIntroduce.length()>0) {
				System.out.println("courseIntroduce:"+courseIntroduce);
				courseService.saveCourseAbstract(i,courseIntroduce);
			}else {
				if(edit) {
					courseService.delCourseAbstract(i, Integer.parseInt((String)data.get("courseId")));
				}
				System.out.println("不存在第"+i+"段简介");
			}
		}
		int chapterId=1;
		for(;data.get("chapter-"+chapterId+"-name")!=null;chapterId++) {  //章节判断 
			int sectionId=1;
			for(;data.get("chapter-"+chapterId+"-section-"+sectionId+"-name")!=null;sectionId++) {  //小节判断
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
	public void testEntry(HttpServletRequest request,Map<Object,Object> data) {  //测试卷数据保存至数据库
		//foreach()并不合适取数据,需要"定点"取值
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
					teacherId);  //保存测试卷基本信息		
		}
		int questionNumber=1;
		int optionNumber=1;
		for(;questionNumber>0;questionNumber++) {  //测试卷题目分值正确选项等
			String questionContent=(String) data.get("question-"+questionNumber);
			if(questionContent==null||questionContent.length()==0) {  //不存在第i题,则表明数据已读取完毕
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
		if(edit) {  //先删选项再删问题
			itemBackService.delTestQuestion(testId, questionNumber);
			System.out.println("不存在第"+questionNumber+"题");	
		}

	}
	public void courseResourceEntry(HttpServletRequest request,Map<Object,Object> data) {//课程额外资源保存至数据库
		//foreach()并不合适取数据,需要"定点"取值
		HttpSession session=request.getSession();
		CourseResourceService courseResourceService=new CourseResourceService();
		int id=(int) session.getAttribute("id");
		boolean teacher= (boolean) session.getAttribute("teacher");
		int userType=0;
		if(teacher) {
			userType=1;
		}

		for(int i=1;;i++) {  //资源
			String linkAdd=(String) data.get("resource-"+i);
			String resourceName=(String) data.get("resource-"+i+"-oldName");
			int courseId=Integer.parseInt((String) data.get("courseId"));
			if(linkAdd!=null&&linkAdd.length()>0) {
				courseResourceService.addCourseResource(resourceName, courseId, linkAdd, id, userType);
			}else {
				System.out.println("不存在第"+i+"个资源");
				break;
			}
		}
		int chapterId=1;
		for(;data.get("chapter-"+chapterId+"-name")!=null;chapterId++) {  //章节判断 
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
		String suffix=fileName.substring(fileName.lastIndexOf(".")+1);  //只保留文件名部分
		int type=this.suffixJudgment(suffix);
		DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
		Calendar calendar=Calendar.getInstance();
		int num=1000+(int)(Math.random()*(9000));
		name=df.format(calendar.getTime())+userId+num+"."+suffix;
		data.put("name", name);  //新名字 xxx.xxx(2020.png 1234.mp4 ...)
		data.put("type", type);  //类型 0:图片 1:后缀
		data.put("suffix", suffix);  // 后缀 xx(png mp4 ...)
		return data;
	}
	public int suffixJudgment(String suffix) {  //判断资源类型并返回标识符
		int type=-1;  //-1:未知类型 0:图片 1:视频 2:压缩包 3:文档/表格
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