package com.muke.onlineedu.common.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

public class ResourcePathUtils {
    public final static String REAL_PATH = "D:/onlineEdu";
    public final static String PHOTO_PATH_PREFIX = "/upload/photo";
    public final static String VEDIO_PATH_PREFIX = "/upload/vedio";
    public final static String FILE_PATH_PREFIX = "/upload/file";

    @Autowired
    private static ServerConfig serverConfig;

    public static File getPhotoDirFile() {
//        String fileDirPath = ResourceUtils.getURL("classpath:").getPath() +"static" + PHOTO_PATH_PREFIX;
        String fileDirPath = REAL_PATH + PHOTO_PATH_PREFIX;
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static File getVedioDirFile(){
//        String fileDirPath = ResourceUtils.getURL("classpath:").getPath() +"static" + VEDIO_PATH_PREFIX;
        String fileDirPath = REAL_PATH + VEDIO_PATH_PREFIX;
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static File getFileDirFile(){
//        String fileDirPath = ResourceUtils.getURL("classpath:").getPath() +"static" + FILE_PATH_PREFIX;
        String fileDirPath = REAL_PATH + FILE_PATH_PREFIX;
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static String getPhotoPath(String url){
        return url+ ResourcePathUtils.PHOTO_PATH_PREFIX+ File.separator;
    }
    public static String getVedioPath(String url){
        return url+ ResourcePathUtils.VEDIO_PATH_PREFIX+ File.separator;
    }
    public static String getFilePath(String url){
        return url+ ResourcePathUtils.FILE_PATH_PREFIX+ File.separator;
    }
    public static String getRealPhotoPath(){
        return REAL_PATH+ ResourcePathUtils.PHOTO_PATH_PREFIX+ File.separator;
    }
    public static String getRealVedioPath(){
        return REAL_PATH+ ResourcePathUtils.VEDIO_PATH_PREFIX+ File.separator;
    }
    public static String getRealFilePath(){
        return REAL_PATH+ ResourcePathUtils.FILE_PATH_PREFIX+ File.separator;
    }
}

