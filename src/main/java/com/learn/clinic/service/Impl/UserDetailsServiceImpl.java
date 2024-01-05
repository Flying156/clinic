package com.learn.clinic.service.Impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.learn.clinic.dao.entity.PermissionDO;
import com.learn.clinic.dao.entity.UserDO;
import com.learn.clinic.mapper.PermissionMapper;
import com.learn.clinic.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义用户认证
 *
 * @author Milk
 * @version 2023/12/27 9:39
 */
@Component
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    private final PermissionMapper permissionMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StrUtil.isBlank(username)){
            throw new UsernameNotFoundException("用户不能为空");
        }
        // 查询用户
        LambdaQueryWrapper<UserDO> queryWrapper = Wrappers.lambdaQuery(UserDO.class)
                .eq(UserDO::getUsername, username);
        UserDO userDO = userMapper.selectOne(queryWrapper);
        if(userDO == null){
            throw new UsernameNotFoundException("用户名不存在");
        }

        // 查询用户权限
        List<PermissionDO> permissionList = permissionMapper.selectPermission(userDO.getId());

        List<GrantedAuthority> grantedAuthorityList = new ArrayList<>();

        permissionList.forEach(permissionDO -> {
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permissionDO.getPermissionCode());
            grantedAuthorityList.add(grantedAuthority);
        });

        System.out.println(grantedAuthorityList);


        return new User(userDO.getAccount(), userDO.getPassword(), userDO.getEnabled(), userDO.getAccountNotExpired(), userDO.getCredentialsNotExpired(), userDO.getAccountNotLocked(), grantedAuthorityList);
    }
}
