package com.weil.blog.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @ClassName BlogConstants
 * @Author weil
 * @Description //定义常量
 * @Date 2022/6/25 11:33
 * @Version 1.0.0
 **/
@Component
public class BlogConstants {
    public static String FILE_UPLOAD_DIR_WINDOW = "c:/upload/";
    public static String FILE_UPLOAD_DIR_LINUX = "/opt/upload/";
    /**
     * 资源映射路径
     */
    public static String PATH_PATTERN = "/resource/**";

    @Value("${upload.path.window}")
    public void setFileUploadDirWindow(String path){
        FILE_UPLOAD_DIR_WINDOW = path;
    }
    @Value("${upload.path.linux}")
    public void setFileUploadDirLinux(String path){
        FILE_UPLOAD_DIR_LINUX = path;
    }
    @Value("${upload.path.pattern}")
    public void setPathPattern(String pathPattern) {
        PATH_PATTERN = pathPattern;
    }
}
