package com.yy.common;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//统一响应结果
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Result<T> {
    private Integer code;//业务状态码  200-成功  500-失败
    private String message;//提示信息
    private T data;//响应数据

    //快速返回操作成功响应结果(带响应数据)
    public static <E> Result<E> success(E data) {
        return new Result<>(200, "操作成功", data);
    }

    //快速返回操作成功响应结果
    public static Result success() {
        return new Result(200, "操作成功", null);
    }

    public static Result error(String message) {
        return new Result(500, message, null);
    }
    public static <E> Result<E> error(String message,E data) {
        return new Result(500, message, data);
    }
    public static Result error(Integer code,String message) {
        return new Result(code, message, null);
    }
}
