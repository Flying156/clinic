package com.learn.clinic.security;

import cn.hutool.core.collection.CollUtil;
import com.learn.clinic.dao.entity.PermissionDO;
import com.learn.clinic.mapper.PermissionMapper;
import com.learn.clinic.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 安全元数据源
 *
 * @author Milk
 * @version 2024/1/4 21:39
 */
@Component
@RequiredArgsConstructor
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    AntPathMatcher antPathMatcher = new AntPathMatcher();

    private final PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        // 查询具体接口权限
        List<PermissionDO> permissionDOList = permissionService.queryPermission(requestUrl);
        // 任意访问
        if(CollUtil.isEmpty(permissionDOList)){
            return null;
        }
        String [] attributes = new String[permissionDOList.size()];
        for(int i = 0; i < attributes.length; i++){
            attributes[i] = permissionDOList.get(i).getPermissionCode();
        }
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
