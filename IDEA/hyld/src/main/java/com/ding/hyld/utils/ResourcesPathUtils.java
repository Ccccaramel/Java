package com.ding.hyld.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;

@Component
@ConfigurationProperties(prefix = "resourcespathutils")
public class ResourcesPathUtils {
    private static String url; // 开发/生产环境资源根目录
    private static String realPath; // 开发/生产环境资源根目录
    private static String infactPath; // 根据配置设置最终使用的资源根目录
    private static String photoPathPrefix; // 图片资源目录
    private static String videoPathPrefix; // 视频资源目录
    private static String filePathPrefix; // 文件资源目录
    private static String audioPathPrefix; // 音源资源目录
    private static Environment environment;

    public static String BLOG="blog";
    public static String HYLD="hyld";

    public static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        ResourcesPathUtils.url = url;
    }

    public static String getRealPath() {
        return realPath;
    }

    public static String getInfactPath() {
        if(!StringUtils.hasText(ResourcesPathUtils.infactPath)){
            String[] activeProfiles = environment.getActiveProfiles();
            for (String profile : activeProfiles) {
                if ("dev".equals(profile)) {
                    ResourcesPathUtils.infactPath = getRealPath();
                    break;
                } else if ("test".equals(profile)) {
                    ResourcesPathUtils.infactPath = getRealPath();
                    break;
                } else if ("prod".equals(profile)) {
                    ResourcesPathUtils.infactPath = getUrl();
                    break;
                }
            }
        }
        return ResourcesPathUtils.infactPath;
    }

    public void setRealPath(String realPath) {
        ResourcesPathUtils.realPath = realPath;
    }

    public static String getPhotoPathPrefix() {
        return photoPathPrefix;
    }

    public void setPhotoPathPrefix(String photoPathPrefix) {
        ResourcesPathUtils.photoPathPrefix = photoPathPrefix;
    }

    public static String getVideoPathPrefix() {
        return videoPathPrefix;
    }

    public void setVideoPathPrefix(String videoPathPrefix) {
        ResourcesPathUtils.videoPathPrefix = videoPathPrefix;
    }

    public static String getFilePathPrefix() {
        return filePathPrefix;
    }

    public void setFilePathPrefix(String filePathPrefix) {
        ResourcesPathUtils.filePathPrefix = filePathPrefix;
    }

    public static String getAudioPathPrefix() {
        return audioPathPrefix;
    }

    public void setAudioPathPrefix(String audioPathPrefix) {
        ResourcesPathUtils.audioPathPrefix = audioPathPrefix;
    }

    @Autowired
    private ServerConfig serverConfig;

    public ServerConfig getServerConfig() {
        return serverConfig;
    }

    public void setServerConfig(ServerConfig serverConfig) {
        this.serverConfig = serverConfig;
    }

    /**
     * 访问资源的地址
     * @return
     */
    public static String getPhotoPath(String type){
        return getUrl() + File.separator+ type + getPhotoPathPrefix() + File.separator;
    }
    public static String getVideoPath(String type){
        return getUrl() + File.separator+ type + getVideoPathPrefix() + File.separator;
    }
    public static String getAudioPath(String type){
        return getUrl() + File.separator+ type + getAudioPathPrefix() + File.separator;
    }
    public static String getFilePath(String type){
        return getUrl() + File.separator+ type + getFilePathPrefix() + File.separator;
    }


    /**
     * 真实资源存放路径(资源删除操作)
      */
    public static String getRealPhotoPath(String module){
        return getRealPath() + File.separator + module + getPhotoPathPrefix() + File.separator;
    }
    public static String getRealVideoPath(String module){
        return getRealPath() + File.separator + module + getVideoPathPrefix() + File.separator;
    }
    public static String getRealAudioPath(String module){
        return getRealPath() + File.separator + module + getAudioPathPrefix() + File.separator;
    }
    public static String getRealFilePath(String module){
        return getRealPath() + File.separator + module + getFilePathPrefix() + File.separator;
    }

    /**
     * 资源上传的地址
     **/
    public static File getPhotoDirFile(String module) {
        String fileDirPath = getRealPhotoPath(module);
        System.out.println("fileDirPath:"+fileDirPath);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static File getVideoDirFile(String type){
        String fileDirPath = getRealVideoPath(type);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static File getAudioDirFile(String type){
        String fileDirPath = getRealAudioPath(type);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static File getFileDirFile(String type){
        String fileDirPath = getRealFilePath(type);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }

    public static void main(String[] args) {
        try {
            System.out.println(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}

