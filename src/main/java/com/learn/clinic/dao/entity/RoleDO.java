package com.learn.clinic.dao.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * 角色实体类
 *
 * @author Milk
 * @version 2023/12/27 9:10
 */

@Data
@Builder
@TableName("t_role")
@AllArgsConstructor
public class RoleDO {


    /**
     * id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;
    /**
     * 角色名称
     */
    private String roleName;

    /**
     * 角色描述
     */
    private String roleDescription;

}
