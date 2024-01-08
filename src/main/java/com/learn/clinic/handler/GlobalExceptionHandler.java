package com.learn.clinic.handler;

import com.learn.clinic.expection.ServiceException;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author Milk
 * @version 2024/1/8 16:48
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public Result serviceExceptionHandler(ServiceException serviceException){
        return Results.fail(serviceException.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result commonExceptionHandler(Exception exception){
        return Results.fail(exception.getMessage());
    }

}
