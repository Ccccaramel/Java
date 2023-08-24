package com.ding.hyld.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ResourceUploadAndDownloadUtils {
    private String module;
    private File photoDirFile;
    private File videoDirFile;
    private File fileDirFile;
    private File audioDirFile;

    public ResourceUploadAndDownloadUtils() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ResourceUploadAndDownloadUtils(String module,File photoDirFile, File videoDirFile,File audioDirFile,File fileDirFile) {
        super();
        this.module = module;
        this.photoDirFile= photoDirFile;
        this.videoDirFile= videoDirFile;
        this.audioDirFile= audioDirFile;
        this.fileDirFile= fileDirFile;
    }

    /**
     * 批量文件资源上传
     * @param fileMap
     * @param userId
     * @return
     */
    public Map<String,String> resourceUpload(Map<String, MultipartFile> fileMap, int userId){  //资源上传至本地并返回相关数据
        Map<String,String> result= new HashMap<>();
        for(String fileName:fileMap.keySet()){
            Map<String, String> res = resourceUpload(fileMap.get(fileName), userId);
            if(res == null){
                continue;
            }
            result.put(res.get("newName"),fileName); // 避免重名
        }
        return result;
    }

    /**
     * 单个文件资源上传
     * @param multipartFile
     * @param userId
     * @return
     */
    public Map<String,String> resourceUpload(MultipartFile multipartFile, int userId){  //资源上传至本地并返回相关数据
        String fileName = multipartFile.getOriginalFilename();
        Map<String,String> result= new HashMap<>();
        if(!StringUtils.hasText(fileName)){
            return null;
        }
        Map<String,Object> data= newNameUtils(fileName, userId);
        String newName = (String) data.get("name");
        result.put("fileName",fileName);
        result.put("newName",newName);
        Integer type= (Integer) data.get("type");//-1:未知类型 0:图片 1:视频 2:压缩包 3:文档/表格 4:音源
        String finalPath = getUpPath(type,newName); // 上传至服务器的位置
        String accessPath = getAccessPath(type,newName); // 公网访问路径
        result.put("accessPath",accessPath);
        try {
            multipartFile.transferTo(new File(finalPath)); // 流输出,将文件资源上传至指定位置
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getUpPath(Integer type,String newName){
        String finalPath = null;
        if(type==-1){
            finalPath=this.fileDirFile+File.separator+newName;
        }
        else if(type==0){
            finalPath=this.photoDirFile+File.separator+newName;
        }
        else if(type==1){
            finalPath=this.videoDirFile +File.separator+newName;
        }
        else if(type==2 || type==3){
            finalPath=this.fileDirFile+File.separator+newName;
        }
        else if(type==4){
            finalPath=this.audioDirFile+File.separator+newName;
        }
        return finalPath;
    }

    private String getAccessPath(Integer type,String newName){ //-1:未知类型 0:图片 1:视频 2:压缩包 3:文档/表格 4:音源
        String accessPath = null;
        if(type==-1){
            accessPath=ResourcesPathUtils.getFilePath(module) + newName;
        }
        else if(type==0){
            accessPath=ResourcesPathUtils.getPhotoPath(module)+newName;
        }
        else if(type==1){
            accessPath=ResourcesPathUtils.getVideoPath(module)+newName;
        }
        else if(type==2 || type==3){ // 有时间将其分开,【3】可以在线查看
            accessPath=ResourcesPathUtils.getFilePath(module)+newName;
        }
        else if(type==4){
            accessPath=ResourcesPathUtils.getAudioPath(module)+newName;
        }
        return accessPath;
    }


    private Map<String,Object> newNameUtils(String fileName, int userId) {
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
//        data.put("suffix", suffix);  // 后缀 xx(png mp4 ...)
        return data;
    }
    public static int suffixJudgment(String suffix) {  //判断资源类型并返回标识符
        int type=-1;  //-1:未知类型 0:图片 1:视频 2:压缩包 3:文档/表格 4.音源
        if(suffix.equalsIgnoreCase("png")||suffix.equalsIgnoreCase("jpeg")||suffix.equalsIgnoreCase("jpg")||suffix.equalsIgnoreCase("tif")) {
            type=0;
        }else if(suffix.equalsIgnoreCase("avi")||suffix.equalsIgnoreCase("mp4")||suffix.equalsIgnoreCase("wmv")||suffix.equalsIgnoreCase("mov")){
            type=1;
        }else if(suffix.equalsIgnoreCase("zip")||suffix.equalsIgnoreCase("rar")||suffix.equalsIgnoreCase("arj")||suffix.equalsIgnoreCase("z")){
            type=2;
        }else if(suffix.equalsIgnoreCase("txt")||suffix.equalsIgnoreCase("doc")||suffix.equalsIgnoreCase("docx")||suffix.equalsIgnoreCase("rtf")||suffix.equalsIgnoreCase("wps")||suffix.equalsIgnoreCase("xls")||suffix.equalsIgnoreCase("xlsx")||suffix.equalsIgnoreCase("pdf")){
            type=3;
        }else if(suffix.equalsIgnoreCase("wav")||suffix.equalsIgnoreCase("flac")||suffix.equalsIgnoreCase("ape")||suffix.equalsIgnoreCase("mp3")||suffix.equalsIgnoreCase("aac")||suffix.equalsIgnoreCase("xls")||suffix.equalsIgnoreCase("alac")||suffix.equalsIgnoreCase("ogg")){
            type=4;
        }
        return type;
    }
}
