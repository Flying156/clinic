package com.learn.clinic.controller;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.RoleDTO;
import com.learn.clinic.dao.entity.RoleDO;
import com.learn.clinic.dao.vo.PermissionRequestVO;
import com.learn.clinic.service.PermissionService;
import com.learn.clinic.service.RoleService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 角色展示框
 *
 * @author Milk
 * @version 2024/1/7 13:36
 */
@Tag(name = "角色管理")
@RestController
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final PermissionService permissionService;

    @Operation(summary = "分页查询角色")
    @GetMapping("/getRoleList")
    private Result<PageDTO<RoleDO>> getRoleList(@RequestParam(required = false) String roleName){
        return Results.success(roleService.getRoleList(roleName));
    }

    @Operation(summary = "获取角色权限")
    @GetMapping("/getPermission")
    private Result<List<PermissionRequestVO>> getPermissionList(@RequestParam(required = false) Integer roleId){
        return Results.success(permissionService.queryPermissionList(roleId));
    }

    @Operation(summary = "修改角色")
    @PostMapping("/editRole")
    private Result editRole(@RequestBody RoleDTO roleDTO){
        roleService.editRole(roleDTO);

        return Results.success();
    }

    @Operation(summary = "添加角色")
    @PutMapping("/addRole")
    private Result addRole(@RequestBody RoleDTO roleDTO){
        roleService.addRole(roleDTO);

        return Results.success(roleDTO);
    }

    @Operation(summary = "删除角色")
    @DeleteMapping("/deleteRole")
    private Result deleteRole(Integer id){
        roleService.deleteRole(id);
        return Results.success();
    }

}
