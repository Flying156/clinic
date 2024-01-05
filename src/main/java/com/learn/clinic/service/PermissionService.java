package com.learn.clinic.service;

import com.learn.clinic.dao.entity.PermissionDO;

import java.util.List;

/**
 * 权限服务处
 *
 * @author Milk
 * @version 2024/1/4 21:47
 */
public interface PermissionService {

    /**
     * 查询请求路径的权限
     *
     * @param path 请求路径
     * @return     权限列表
     */
    List<PermissionDO>  queryPermission(String path);
}
