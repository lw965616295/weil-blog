package com.weil.blog.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @ClassName ExceptionAdvice
 * @Author weil
 * @Description //统一异常处理
 * @Date 2022/6/22 12:53
 * @Version 1.0.0
 **/
@RestControllerAdvice
@Slf4j
public class BlogExceptionHandler {
    /**
     * 自定义blogException处理
     */
    @ExceptionHandler(BlogException.class)
    public Result blogExceptionHandler(BlogException e){
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }
    /**
     * exception处理
     */
    @ExceptionHandler(Exception.class)
    public Result exceptionHandler(Exception e){
        log.error(e.getMessage(), e);
        return Result.fail(e.getMessage());
    }
}
