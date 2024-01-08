package com.learn.clinic.security;

import cn.hutool.core.collection.CollUtil;
import com.learn.clinic.dao.entity.PermissionDO;
import com.learn.clinic.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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

    private final PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        int questionMarkIndex = requestUrl.indexOf("?");
        if (questionMarkIndex != -1) {
            requestUrl = requestUrl.substring(0, questionMarkIndex);
        }
        // 查询具体接口权限
        List<PermissionDO> permissionDOList = permissionService.queryPermission(requestUrl);
        // 任意访问
        if(CollUtil.isEmpty(permissionDOList)){
            return null;
        }
        List<ConfigAttribute> attributes = new ArrayList<>();
        for(PermissionDO permission : permissionDOList){
            String pattern = permission.getPermissionCode();
            attributes.add(new SecurityConfig(pattern));
        }
        return attributes;
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
