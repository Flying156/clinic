package com.learn.clinic.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 用户角色实体类
 *
 * @author Milk
 * @version 2023/12/27 9:17
 */
@Data
@Builder
@TableName("t_user_role_relation")
@AllArgsConstructor
public class UserRoleRelationDO {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 用户 ID
     */
    private Integer userId;

    /**
     * 角色 ID
     */
    private Integer roleId;

}
