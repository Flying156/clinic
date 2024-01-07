package com.learn.clinic.service.Impl;

import com.learn.clinic.dao.entity.PermissionDO;
import com.learn.clinic.dao.vo.PermissionRequestVO;
import com.learn.clinic.mapper.PermissionMapper;
import com.learn.clinic.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 权限服务实现层
 *
 * @author Milk
 * @version 2024/1/4 21:47
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionMapper permissionMapper;

    @Override
    public List<PermissionDO> queryPermission(String path) {
        return permissionMapper.queryPermission(path);
    }


    @Override
    public List<PermissionRequestVO> queryPermissionList(Integer roleId) {
        return permissionMapper.queryPermissionList(roleId);
    }
}
