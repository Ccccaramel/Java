package com.muke.onlineedu.common.tool;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@ConfigurationProperties(prefix = "commonconfig")
public class CommonConfig {
    private String url; // url
    private String realPath; // 资源根目录
    private String photoPathPrefix; // 图片资源目录
    private String videoPathPrefix; // 视频资源目录
    private String filePathPrefix; // 文件资源目录

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    public String getPhotoPathPrefix() {
        return photoPathPrefix;
    }

    public void setPhotoPathPrefix(String photoPathPrefix) {
        this.photoPathPrefix = photoPathPrefix;
    }

    public String getVideoPathPrefix() {
        return videoPathPrefix;
    }

    public void setVideoPathPrefix(String videoPathPrefix) {
        this.videoPathPrefix = videoPathPrefix;
    }

    public String getFilePathPrefix() {
        return filePathPrefix;
    }

    public void setFilePathPrefix(String filePathPrefix) {
        this.filePathPrefix = filePathPrefix;
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
    public String getPhotoPath(){
        return getUrl()+ getPhotoPathPrefix() + File.separator;
    }
    public String getVideoPath(){
        return getUrl()+ getVideoPathPrefix() + File.separator;
    }
    public String getFilePath(){
        return getUrl()+ getFilePathPrefix() + File.separator;
    }

    // 真实资源存放路径
    public String getRealPhotoPath(){
        return getRealPath() + getPhotoPathPrefix() + File.separator;
    }
    public String getRealVideoPath(){
        return getRealPath() + getVideoPathPrefix() + File.separator;
    }
    public String getRealFilePath(){
        return getRealPath() + getFilePathPrefix() + File.separator;
    }

    /**
     * 资源上传的地址
     **/
    public File getPhotoDirFile() {
        String fileDirPath = getRealPhotoPath();
        System.out.println("fileDirPath:"+fileDirPath);
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public File getVideoDirFile(){
        String fileDirPath = getRealVideoPath();
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
    public File getFileDirFile(){
        String fileDirPath = getRealFilePath();
        File fileDir = new File(fileDirPath);
        if(!fileDir.exists()){
            fileDir.mkdirs();
        }
        return fileDir;
    }
}

