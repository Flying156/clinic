package com.learn.clinic.dao.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.learn.clinic.dao.BaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;


/**
 * 医生实体类
 *
 * @author Milk
 * @version 2023/12/26 21:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName("t_user")
@AllArgsConstructor
public class UserDO extends BaseDO {

    /**
     * ID
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private String gender;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号
     */
    private String account;

    /**
     * 是否可用
     */
    private Boolean enabled;

    /**
     * 是否过期
     */
    private Boolean accountNotExpired;

    /**
     * 密码是否过期
     */
    private Boolean credentialsNotExpired;

    /**
     * 是否封禁
     */
    private Boolean accountNotLocked;


}
