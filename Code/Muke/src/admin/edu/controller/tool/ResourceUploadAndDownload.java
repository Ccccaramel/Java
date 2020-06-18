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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ResourceUploadAndDownload {
	String saveImgPath;
	String saveVideoPath;
	
	public ResourceUploadAndDownload(String saveImgPath, String saveVideoPath) {
		super();
		this.saveImgPath = saveImgPath;
		this.saveVideoPath = saveVideoPath;
	}
	
	public Map<Object,Object> resourceUpload(HttpServletRequest request,int userId,boolean userType){
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
					result.put(name, value);
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
	public void databaseEntry(Map<Object,Object> data) {
		
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
	public int suffixJudgment(String suffix) {
		int type=-1;  //-1:未知类型 0:图片 1:后缀
		if(suffix.equalsIgnoreCase("png")||suffix.equalsIgnoreCase("jpeg")||suffix.equalsIgnoreCase("jpg")||suffix.equalsIgnoreCase("tif")) {
			type=0;
		}else if(suffix.equalsIgnoreCase("avi")||suffix.equalsIgnoreCase("mp4")||suffix.equalsIgnoreCase("wmv")||suffix.equalsIgnoreCase("mov")){
			type=1;
		}
		return type;
	}
}