package com.learn.clinic.expection;

/**
 * 服务异常
 *
 * @author Milk
 * @version 2024/1/5 16:41
 */
public class ServiceException extends Exception{

    public ServiceException(String message) {
        super(message);
    }
}
