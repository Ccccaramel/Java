package com.ding.hyld.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;

@Component
@ConfigurationProperties(prefix = "resourcespathutils")
public class ResourcesPathUtils {
    private static String url; // 生产环境资源根目录
    private static String realPath; // 测试环境资源根目录
    private static String infactPath; // 根据配置设置最终使用的资源根目录
    private static String photoPathPrefix; // 图片资源目录
    private static String videoPathPrefix; // 视频资源目录
    private static String filePathPrefix; // 文件资源目录
    private static String audioPathPrefix; // 音源资源目录
    private static Environment environment;
    private static String HYLD="hyld";
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
    public static String getPhotoPath(){
        return getUrl() + File.separator+HYLD + getPhotoPathPrefix() + File.separator;
    }
    public static String getVideoPath(){
        return getUrl()+ getVideoPathPrefix() + File.separator;
    }
    public static String getAudioPath(){
        return getUrl()+ getAudioPathPrefix() + File.separator;
    }
    public static String getFilePath(){
        return getUrl()+ getFilePathPrefix() + File.separator;
    }


    /**
     * 真实资源存放路径(资源删除操作)
      */
    public static String getRealPhotoPath(){
        return getRealPath() + getPhotoPathPrefix() + File.separator;
    }
    public static String getRealVideoPath(){
        return getRealPath() + getVideoPathPrefix() + File.separator;
    }
    public static String getRealAudioPath(){
        return getRealPath() + getAudioPathPrefix() + File.separator;
    }
    public static String getRealFilePath(){
        return getRealPath() + getFilePathPrefix() + File.separator;
    }

    /**
     * 资源上传的地址
     **/
    public static File getPhotoDirFile() {
        String fileDirPath = getRealPhotoPath();
        System.out.println("fileDirPath:"+fileDirPath);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static File getVideoDirFile(){
        String fileDirPath = getRealVideoPath();
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static File getAudioDirFile(){
        String fileDirPath = getRealAudioPath();
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public static File getFileDirFile(){
        String fileDirPath = getRealFilePath();
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
}

