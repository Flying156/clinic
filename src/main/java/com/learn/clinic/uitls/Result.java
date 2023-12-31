package com.learn.clinic.uitls;

import com.learn.clinic.common.ResultEnum;
import lombok.*;


/**
 * 返回数据统一封装
 *
 * @author Milk
 * @version 2023/12/26 20:16
 */
@Data
public class Result<T> {

    /**
     * 返回数据
     */
    private T data;

    /**
     * 返回给前端的 code
     */
    private Integer code;


    /**
     * 返回给前端的消息
     */
    private String message;

    /**r
     * 标识符
     */
    private Boolean flag;
    public Result() {
    }

    public Result(boolean flag) {
        this.flag = flag;
        this.code = flag ? ResultEnum.SUCCESS.getCode() : ResultEnum.COMMON_FAIL.getCode();
        this.message = flag ? ResultEnum.SUCCESS.getMessage() : ResultEnum.COMMON_FAIL.getMessage();
    }

    public Result(boolean flag, ResultEnum resultEnum) {
        this.flag = flag;
        this.code = flag ? ResultEnum.SUCCESS.getCode() : (resultEnum == null ? ResultEnum.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.message = flag ? ResultEnum.SUCCESS.getMessage() : (resultEnum == null ? ResultEnum.COMMON_FAIL.getMessage() : resultEnum.getMessage());
    }

    public Result(boolean flag, T data) {
        this.flag = flag;
        this.code = flag ? ResultEnum.SUCCESS.getCode() : ResultEnum.COMMON_FAIL.getCode();
        this.message = flag ? ResultEnum.SUCCESS.getMessage() : ResultEnum.COMMON_FAIL.getMessage();
        this.data = data;
    }

    public Result(boolean flag, ResultEnum resultEnum, T data) {
        this.flag = flag;
        this.code = flag ? ResultEnum.SUCCESS.getCode() : (resultEnum == null ? ResultEnum.COMMON_FAIL.getCode() : resultEnum.getCode());
        this.message = flag ? ResultEnum.SUCCESS.getMessage() : (resultEnum == null ? ResultEnum.COMMON_FAIL.getMessage() : resultEnum.getMessage());
        this.data = data;
    }
}
