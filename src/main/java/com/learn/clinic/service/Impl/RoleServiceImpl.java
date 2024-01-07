package com.learn.clinic.service.Impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.RoleDTO;
import com.learn.clinic.dao.entity.RoleDO;
import com.learn.clinic.dao.entity.RolePermissionDO;
import com.learn.clinic.mapper.RoleMapper;
import com.learn.clinic.mapper.RolePermissionMapper;
import com.learn.clinic.service.RoleService;
import com.learn.clinic.uitls.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色服务层实现层
 *
 * @author Milk
 * @version 2024/1/7 13:45
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleMapper roleMapper;
    private final RolePermissionMapper rolePermissionMapper;

    @Override
    public PageDTO<RoleDO> getRoleList(String roleName) {

        LambdaQueryWrapper<RoleDO> queryWrapper = Wrappers.lambdaQuery(RoleDO.class)
                .like(StrUtil.isNotEmpty(roleName), RoleDO::getRoleName, roleName);

        Page<RoleDO> roleDOPage = new Page<>(PageUtils.offset(), PageUtils.size());
        IPage<RoleDO> roleDOIPage = roleMapper.selectPage(roleDOPage, queryWrapper);
        return PageUtils.build(roleDOIPage.getRecords(), roleDOIPage.getTotal());
    }

    @Override
    public void editRole(RoleDTO roleDTO) {
        LambdaUpdateWrapper<RoleDO> updateWrapper = Wrappers.lambdaUpdate(RoleDO.class)
                .eq(RoleDO::getId, roleDTO.getId())
                .set(StrUtil.isNotEmpty(roleDTO.getRoleName()), RoleDO::getRoleName, roleDTO.getRoleName())
                .set(StrUtil.isNotEmpty(roleDTO.getDescription()), RoleDO::getRoleDescription, roleDTO.getDescription());
        roleMapper.update(null, updateWrapper);

        // 更新权限, 先删除，在加入
        LambdaQueryWrapper<RolePermissionDO> queryWrapper = Wrappers.lambdaQuery(RolePermissionDO.class)
                .eq(RolePermissionDO::getRoleId, roleDTO.getId());
        rolePermissionMapper.delete(queryWrapper);

        roleDTO.getKeyList().forEach((id)->{
                RolePermissionDO rolePermissionDO =  RolePermissionDO.builder()
                    .roleId(roleDTO.getId())
                    .permissionId(id)
                    .build();
                rolePermissionMapper.insert(rolePermissionDO);
        });
    }

    @Override
    public void addRole(RoleDTO roleDTO) {
        RoleDO roleDO =RoleDO.builder()
                .roleName(roleDTO.getRoleName())
                .roleDescription(roleDTO.getDescription())
                .build();

        Integer roleId =  roleMapper.insert(roleDO);

        roleDTO.getKeyList().forEach((id)->{
            RolePermissionDO rolePermissionDO =  RolePermissionDO.builder()
                    .roleId(roleId)
                    .permissionId(id)
                    .build();
            rolePermissionMapper.insert(rolePermissionDO);
        });

    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteRole(Integer id) {
        roleMapper.deleteById(id);
        LambdaQueryWrapper<RolePermissionDO> queryWrapper = Wrappers.lambdaQuery(RolePermissionDO.class)
                .eq(RolePermissionDO::getRoleId, id);
        rolePermissionMapper.delete(queryWrapper);
    }
}
