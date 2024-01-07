package com.learn.clinic.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色权限类
 *
 * @author Milk
 * @version 2024/1/7 19:59
 */
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@TableName("t_role_permission_relation")
public class RolePermissionDO {

    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 角色 ID
     */
    private Integer roleId;

    /**
     * 权限 ID
     */
    private Integer permissionId;

}
