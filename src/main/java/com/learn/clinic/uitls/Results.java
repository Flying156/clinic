package com.learn.clinic.uitls;

import com.learn.clinic.common.ResultEnum;

/**
 * 返回值封装
 *
 * @author Milk
 * @version 2023/12/27 10:47
 */
public class Results {
    public static Result success() {
        return new Result(true);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>(true, data);
    }

    public static Result fail() {
        return new Result(false);
    }

    public static Result fail(ResultEnum resultEnum) {
        return new Result(false, resultEnum);
    }
    public static Result fail(String message){
        return new Result(false, message);
    }
}
