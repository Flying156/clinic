package com.learn.clinic.service.Impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.learn.clinic.dao.dto.PageDTO;
import com.learn.clinic.dao.dto.PasswordDTO;
import com.learn.clinic.dao.dto.UserDTO;
import com.learn.clinic.dao.entity.RoleDO;
import com.learn.clinic.dao.entity.UserDO;
import com.learn.clinic.dao.entity.UserRoleRelationDO;
import com.learn.clinic.dao.vo.UserVO;
import com.learn.clinic.expection.ServiceException;
import com.learn.clinic.mapper.RoleMapper;
import com.learn.clinic.mapper.UserMapper;
import com.learn.clinic.mapper.UserRoleMapper;
import com.learn.clinic.service.UserService;
import com.learn.clinic.uitls.PageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务层实现
 *
 * @author Milk
 * @version 2023/12/27 9:46
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RoleMapper roleMapper;
    private final UserRoleMapper userRoleMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserVO getUserVO(String username){
        return userMapper.getUserVO(username);
    }

    @Override
    public void updateUser(UserDTO userDTO) throws ServiceException {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userDTO.getUsername());
        UserDO user = userMapper.selectOne(queryWrapper);
        if(user != null){
            throw new ServiceException("用户名重复");
        }

        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(UserDO::getAccount, userDTO.getAccount())
                .set(StrUtil.isNotBlank(userDTO.getAvatar()), UserDO::getAvatar, userDTO.getAvatar())
                .set(StrUtil.isNotBlank(userDTO.getGender()), UserDO::getGender, userDTO.getGender())
                .set(StrUtil.isNotBlank(userDTO.getUsername()), UserDO::getUsername, userDTO.getUsername());
        userMapper.update(BeanUtil.toBean(userDTO, UserDO.class), updateWrapper);
    }

    @Override
    public PageDTO<UserVO> getAllUser(String username) {
        long offset = PageUtils.offset(), size = PageUtils.size();
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .like(StrUtil.isNotBlank(username), UserDO::getUsername, username);

        long total = userMapper.selectCount(queryWrapper);
        List<UserVO> userVOList = userMapper.queryList(username, offset, size);

        return PageUtils.build(userVOList, total);
    }

    @Override
    public void enabledUser(UserDTO userDTO) {
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .set(UserDO::getEnabled, userDTO.getEnabled())
                .eq(UserDO::getId, userDTO.getUserId());
        UserDO userDO = UserDO.builder()
                .id(userDTO.getUserId())
                .enabled(userDTO.getEnabled())
                .build();

        userMapper.update(userDO, updateWrapper);
    }

    @Override
    public void editRole(UserDTO userDTO) {
        LambdaQueryWrapper<RoleDO> queryWrapper = Wrappers.lambdaQuery(RoleDO.class)
                .eq(RoleDO::getRoleName, userDTO.getRole());
        RoleDO roleDO = roleMapper.selectOne(queryWrapper);
        LambdaQueryWrapper<UserDO> userQueryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userDTO.getUsername());
        UserDO userDO = userMapper.selectOne(userQueryWrapper);

        LambdaUpdateWrapper<UserRoleRelationDO> updateWrapper = Wrappers.lambdaUpdate(UserRoleRelationDO.class)
                .eq(UserRoleRelationDO::getUserId, userDO.getId());
        UserRoleRelationDO userRoleRelationDO = UserRoleRelationDO.builder()
                .roleId(roleDO.getId())
                .build();
        userRoleMapper.update(userRoleRelationDO, updateWrapper);
    }

    @Override
    public void register(UserDTO userDTO) throws ServiceException {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, userDTO.getUsername());
        UserDO user = userMapper.selectOne(queryWrapper);
        if(user != null){
            throw new ServiceException("用户名重复");
        }
        UserDO userDO = UserDO.builder()
                .account(userDTO.getUsername())
                .username(userDTO.getUsername())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .build();
        userMapper.insert(userDO);
    }

    @Override
    public void editPassword(PasswordDTO passwordDTO) throws ServiceException {
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(StrUtil.isNotEmpty(passwordDTO.getUsername()), UserDO::getUsername, passwordDTO.getUsername());
        UserDO user = userMapper.selectOne(queryWrapper);


        String encoderPassword = passwordEncoder.encode(passwordDTO.getOldPassword());
        if(!user.getPassword().equals(encoderPassword)){
            throw new ServiceException("旧密码错误");
        }

        // 更新
        UserDO userDO = UserDO.builder()
                .username(passwordDTO.getUsername())
                .password(passwordEncoder.encode(passwordDTO.getNewPassword()))
                .build();
        LambdaUpdateWrapper<UserDO> updateWrapper = Wrappers.lambdaUpdate(UserDO.class)
                .eq(StrUtil.isNotEmpty(passwordDTO.getUsername()), UserDO::getUsername, passwordDTO.getUsername());
        userMapper.update(userDO, updateWrapper);
    }
}
