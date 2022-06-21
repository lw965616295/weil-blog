package com.weil.blog.common;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName Result
 * @Author weil
 * @Description //结果集
 * @Date 2022/6/15 15:28
 * @Version 1.0.0
 **/
@Data
@ToString
public class Result<T> {
    /**
     * 响应码
     */
    private String code;
    /**
     * 响应信息
     */
    private String msg;
    /**
     * 响应结果
     */
    private T data;
}
