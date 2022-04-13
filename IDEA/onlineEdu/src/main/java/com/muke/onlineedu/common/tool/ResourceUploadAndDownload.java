package com.muke.onlineedu.common.tool;

import com.muke.onlineedu.admin.entity.Course;
import com.muke.onlineedu.admin.service.CourseAbstractService;
import com.muke.onlineedu.admin.service.CourseService;
import com.muke.onlineedu.admin.service.CourseStructureService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResourceUploadAndDownload {
    private File photoDirFile;
    private File vedioDirFile;
    private File fileDirFile;

    public ResourceUploadAndDownload() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResourceUploadAndDownload(File photoDirFile, File vedioDirFile,File fileDirFile) {
        super();
        this.photoDirFile = photoDirFile;
        this.vedioDirFile = vedioDirFile;
        this.fileDirFile = fileDirFile;
    }

    public Map<String,String> resourceUpload(Map<String, MultipartFile> fileMap, int userId){  //资源上传至本地并返回相关数据
        Map<String,String> result= new HashMap<>();
        for(String fileName:fileMap.keySet()){
            MultipartFile a = fileMap.get(fileName);
            if("".equals(fileName)){
                continue;
            }
            System.out.println("fileName:"+fileName);
            Map<String,Object> data=this.newName(fileName, userId);
            String newName = (String) data.get("name");
            result.put(fileName,newName);
            String finalPath = null;
            int type= (int) data.get("type");//-1:未知类型 0:图片 1:视频 2:压缩包 3:文档/表格
            if(type==-1){
                finalPath=this.fileDirFile+File.separator+newName;
            }
            else if(type==0){
                finalPath=this.photoDirFile+File.separator+newName;
            }
            else if(type==1){
                finalPath=this.vedioDirFile+File.separator+newName;
            }
            else if(type==2){
                finalPath=this.fileDirFile+File.separator+newName;
            }
            else if(type==3){
                finalPath=this.fileDirFile+File.separator+newName;
            }
            try {
                fileMap.get(fileName).transferTo(new File(finalPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
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

    public Map<String,Object> newName(String fileName,int userId) {
        Map<String,Object> data=new HashMap<>();
        String name=null;
        String suffix=fileName.substring(fileName.lastIndexOf(".")+1);  //只保留文件名部分
        int type=suffixJudgment(suffix);
        DateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
        Calendar calendar=Calendar.getInstance();
        int num=1000+(int)(Math.random()*(9000));
        name=df.format(calendar.getTime())+userId+num+"."+suffix;
        data.put("name", name);  //新名字 xxx.xxx(2020.png 1234.mp4 ...)
        data.put("type", type);  ////-1:未知类型 0:图片 1:视频 2:压缩包 3:文档/表格
        data.put("suffix", suffix);  // 后缀 xx(png mp4 ...)
        return data;
    }
    public static int suffixJudgment(String suffix) {  //判断资源类型并返回标识符
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
