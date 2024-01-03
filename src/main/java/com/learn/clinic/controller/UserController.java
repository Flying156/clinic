package com.learn.clinic.controller;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.UserDTO;
import com.learn.clinic.dao.vo.UserVO;
import com.learn.clinic.service.UserService;
import com.learn.clinic.uitls.Result;
import com.learn.clinic.uitls.Results;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


/**
 * 用户
 *
 * @author Milk
 * @version 2023/12/27 16:11
 */
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;




    @GetMapping("/getAllUser")
    public Result<PageDTO<UserVO>> getAllUser(@RequestParam(required = false) String username){
        return Results.success(userService.getAllUser(username));
    }

    @GetMapping("/userDetail")
    public Result<UserVO> getUserVO(@RequestParam String username) {
        return Results.success(userService.getUserVO(username));
    }

    @PutMapping("/enabledUser")
    public Result enabledUser(@RequestBody UserDTO userDTO){
        userService.enabledUser(userDTO);
        return Results.success();
    }

    @PutMapping("/editRole")
    public Result editRole(@RequestBody UserDTO userDTO){
        userService.editRole(userDTO);
        return Results.success();
    }

    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody UserDTO userDTO){
        userService.updateUser(userDTO);
        return Results.success();
    }


    @PutMapping("/register")
    public void saveUser(@RequestBody UserDTO userDTO){
        System.out.println(userDTO);
    }


}
