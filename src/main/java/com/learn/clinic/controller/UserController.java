package com.learn.clinic.controller;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.PasswordDTO;
import com.learn.clinic.dao.dto.UserDTO;
import com.learn.clinic.dao.vo.UserVO;
import com.learn.clinic.expection.ServiceException;
import com.learn.clinic.service.UserService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * 用户
 *
 * @author Milk
 * @version 2023/12/27 16:11
 */
@Tag(name = "用户管理")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "分页查询用户")
    @GetMapping("/getAllUser")
    public Result<PageDTO<UserVO>> getAllUser(@RequestParam(required = false) String username){
        return Results.success(userService.getAllUser(username));
    }

    @Operation(summary = "获取用户信息")
    @GetMapping("/userDetail")
    public Result<UserVO> getUserVO(@RequestParam String username) {
        return Results.success(userService.getUserVO(username));
    }

    @Operation(summary = "启用或封禁用户")
    @PutMapping("/enabledUser")
    public Result enabledUser(@RequestBody UserDTO userDTO){
        userService.enabledUser(userDTO);
        return Results.success();
    }

    @Operation(summary = "修改用户角色")
    @PutMapping("/editRole")
    public Result editRole(@RequestBody UserDTO userDTO){
        userService.editRole(userDTO);
        return Results.success();
    }

    @Operation(summary = "更新用户信息")
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody UserDTO userDTO) throws ServiceException {
        userService.updateUser(userDTO);
        return Results.success();
    }

    @Operation(summary = "注册用户")
    @PutMapping("/register")
    public Result saveUser(@RequestBody UserDTO userDTO) throws ServiceException {
        userService.register(userDTO);
        return Results.success();
    }
    @Operation(summary = "修改密码")
    @PostMapping("/editPassword")
    public Result editPassword(@RequestBody PasswordDTO passwordDTO) throws ServiceException {
        userService.editPassword(passwordDTO);
        return Results.success();
    }



}
