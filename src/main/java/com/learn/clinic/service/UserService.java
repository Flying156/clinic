package com.learn.clinic.service;

import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.PasswordDTO;
import com.learn.clinic.dao.dto.UserDTO;
import com.learn.clinic.dao.vo.UserVO;
import com.learn.clinic.expection.ServiceException;

/**
 * 用户服务层
 *
 * @author Milk
 * @version 2023/12/27 9:46
 */
public interface UserService {
    /**
     * 获取用户信息
     *
     * @param username 用户名
     * @return  用户信息
     */
    UserVO getUserVO( String username);

    /**
     * 更新用户信息
     *
     * @param userDTO  用户类
     */
    void updateUser(UserDTO userDTO) throws ServiceException;

    /**
     * 获取所有的用户
     *
     * @param username 用户名
     * @return 分页数据
     */
    PageDTO<UserVO> getAllUser(String username);

    /**
     * 是否启用用户
     *
     * @param userDTO 数据
     */
    void enabledUser(UserDTO userDTO);

    /**
     * 修改用户角色
     *
     * @param userDTO 信息
     */
    void editRole(UserDTO userDTO);

    /**
     * 注册用户
     */
    void register(UserDTO userDTO) throws ServiceException;

    /**
     * 修改密码
     */
    void editPassword(PasswordDTO passwordDTO) throws ServiceException;
}
