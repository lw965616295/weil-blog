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
    public static final String SUCCESS = "200";
    public static final String FAIL = "-1";
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

    public Result() {
    }

    public Result(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public static <T> Result success(String msg){
        return new Result(SUCCESS, msg, null);
    }
    public static <T> Result success(T data){
        return new Result(SUCCESS, "", data);
    }
    public static <T> Result success(String msg, T data){
        return new Result(SUCCESS, msg, data);
    }
    public static <T> Result fail(String msg){
        return new Result(FAIL, msg, null);
    }
    public boolean isSuccess(){
        return SUCCESS.equals(this.code)?true:false;
    }
    public boolean isFailed(){
        return FAIL.equals(this.code)?true:false;
    }
}
