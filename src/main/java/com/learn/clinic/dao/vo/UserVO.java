package com.learn.clinic.dao.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 用户展示信息
 *
 * @author Milk
 * @version 2024/1/2 9:45
 */
@Data
@Builder
@AllArgsConstructor
public class UserVO {

    /**
     * ID
     */
    private Integer id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 角色
     */
    private String role;

    /**
     * 是否可用
     */
    private Boolean enabled;


    /**
     * 性别
     */
    private String gender;

    /**
     * 账号
     */
    private String account;



    /**
     * 头像
     */
    private String avatar;





}
