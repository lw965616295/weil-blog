package com.weil.blog.utils;

import com.weil.blog.common.BlogConstants;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @ClassName BlogUtil
 * @Author weil
 * @Description //工具类
 * @Date 2022/6/25 16:05
 * @Version 1.0.0
 **/
public class BlogUtil {

    /**
     * 判断系统是否为window
     */
    public static boolean isWindow(){
        String name = System.getProperty("os.name");
        return name.toLowerCase().startsWith("window")?true:false;
    }

    /**
     * 获取文件后缀名
     */
    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成文件路径url
     */
    public static String genFileUrl(String url, String tempName) throws Exception{
        URI uri = new URI(url);
        // 重新定义一个uri
        /**
         *
         scheme – Scheme name
         userInfo – User name and authorization information
         host – Host name
         port – Port number
         path – Path
         query – Query
         fragment – Fragment
         */
        URI u2 = new URI(uri.getScheme(), uri.getUserInfo(), uri.getHost(), uri.getPort(), null, null, null);
        return u2.toString() + BlogConstants.PATH_PATTERN.substring(0, BlogConstants.PATH_PATTERN.indexOf("*")) + tempName;
    }

    public static void main(String[] args) throws Exception {
//        System.out.println(isWindow());
//        System.out.println(getSuffix("123.txt"));
        System.out.println(genFileUrl("http://localhost:8081/admin/upload", "hhh.txt"));
    }

}
