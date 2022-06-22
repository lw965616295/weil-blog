package com.weil.blog.common;

/**
 * @ClassName BlogException
 * @Author weil
 * @Description //自定义异常
 * @Date 2022/6/22 12:55
 * @Version 1.0.0
 **/
public class BlogException extends RuntimeException{
    public BlogException(String message) {
        super(message);
    }
}
