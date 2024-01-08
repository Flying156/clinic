package com.learn.clinic.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;


/**
 * 返回数字枚举
 *
 * @author Milk
 * @version 2023/12/26 20:22
 */
@Getter
@RequiredArgsConstructor
public enum ResultEnum {



    /* 成功 */
    SUCCESS(200, "成功"),

    /* 默认失败 */
    COMMON_FAIL(999, "失败"),

    /* 参数错误：1000～1999 */
    PARAM_NOT_VALID(1001, "参数无效"),
    PARAM_IS_BLANK(1002, "参数为空"),
    PARAM_TYPE_ERROR(1003, "参数类型错误"),
    PARAM_NOT_COMPLETE(1004, "参数缺失"),

    /* 用户错误 */
    USER_NOT_LOGIN(2001, "用户未登录"),
    USER_ACCOUNT_EXPIRED(2002, "账号已过期"),
    USER_CREDENTIALS_ERROR(2003, "密码错误"),
    USER_CREDENTIALS_EXPIRED(2004, "密码过期"),
    USER_ACCOUNT_DISABLE(2005, "账号不可用"),
    USER_ACCOUNT_LOCKED(2006, "账号被锁定"),
    USER_ACCOUNT_NOT_EXIST(2007, "账号不存在"),
    USER_ACCOUNT_ALREADY_EXIST(2008, "账号已存在"),
    USER_ACCOUNT_USE_BY_OTHERS(2009, "账号下线"),

    /* 业务错误 */
    NO_PERMISSION(3001, "没有权限"),
    OLD_PASSWORD_ERROR(3002, "旧密码错误"),
    USER_NAME_DUP(3003, "用户名重复"),

    /*验证错误*/
    VERIFICATION_CODE_ERROR(4001, "验证码错误");
    private Integer code;
    private String message;

    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }


    public void setCode(Integer code) {
        this.code = code;
    }


    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * 根据 code 获取 message
     */
    public static String getMessageByCode(Integer code) {
        for (ResultEnum ele : values()) {
            if (ele.getCode().equals(code)) {
                return ele.getMessage();
            }
        }
        return null;
    }



}
