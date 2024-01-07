package com.learn.clinic.service;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.RoleDTO;
import com.learn.clinic.dao.entity.RoleDO;

/**
 * 角色服务层
 * 
 * @author Milk
 * @version 2024/1/7 13:45
 */
public interface RoleService {

    /**
     * 分页查询角色
     */
    PageDTO<RoleDO> getRoleList(String roleName);

    /**
     * 修改角色
     */
    void editRole(RoleDTO roleDTO);

    /**
     * 创建用户
     */
    void addRole(RoleDTO roleDTO);

    /**
     * 删除角色
     */
    void deleteRole(Integer id);
}
