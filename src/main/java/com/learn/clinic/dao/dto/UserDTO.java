package com.learn.clinic.dao.dto;

import lombok.Data;

/**
 * 注册用户类
 *
 * @author Milk
 * @version 2023/12/27 16:12
 */
@Data
public class UserDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

}
