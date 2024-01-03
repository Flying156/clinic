package com.learn.clinic.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 注册用户类
 *
 * @author Milk
 * @version 2023/12/27 16:12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String gender;

    /**
     * 账号
     */
    private String account;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 角色
     */
    private String role;
}
