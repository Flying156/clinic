package com.learn.clinic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.clinic.dao.entity.PermissionDO;

import java.util.List;

/**
 * 权限持久层
 *
 * @author Milk
 * @version 2023/12/27 9:52
 */
public interface PermissionMapper extends BaseMapper<PermissionDO> {

    /**
     * 查询权限
     * @param id 用户 ID
     * @return 权限列表
     */
    List<PermissionDO> selectPermission(Integer id);

    /**
     * 查询访问接口所需权限
     *
     * @param path 接口路径
     * @return     权限列表
     */
    List<PermissionDO> queryPermission(String path);
}
