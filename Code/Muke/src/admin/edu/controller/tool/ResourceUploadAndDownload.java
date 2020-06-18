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
					result.put(name, value);
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
	public int suffixJudgment(String suffix) {
		int type=-1;  //-1:δ֪���� 0:ͼƬ 1:��׺
		if(suffix.equalsIgnoreCase("png")||suffix.equalsIgnoreCase("jpeg")||suffix.equalsIgnoreCase("jpg")||suffix.equalsIgnoreCase("tif")) {
			type=0;
		}else if(suffix.equalsIgnoreCase("avi")||suffix.equalsIgnoreCase("mp4")||suffix.equalsIgnoreCase("wmv")||suffix.equalsIgnoreCase("mov")){
			type=1;
		}
		return type;
	}
}