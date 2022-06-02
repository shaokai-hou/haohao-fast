package com.haohao.fast.security;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.haohao.fast.common.result.ResultCodeEnum;
import com.haohao.fast.common.exception.BaseException;
import com.haohao.fast.security.user.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * ss SpringSecurity
 * @author haohao
 */
@Component(value = "ss")
public class PermissionServiceImpl {

    /**
     * 所有权限标识
     */
    private static final String ALL_PERMISSION = "*:*:*";
    /**
     * 所有角色权限标识
     */
    private static final String ALL_ROLE = "admin";
    /**
     * 角色分割标识
     */
    private static final String ROLE_SEPARATOR = ",";
    /**
     * 权限分割标识
     */
    private static final String PERMISSION_SEPARATOR = ",";

    /**
     * 包含指定权限
     *
     * @param permission 权限字符串
     * @return 验证结果
     */
    public boolean hasPermission(String permission) {
        if (StrUtil.isBlank(permission)) {
            return Boolean.FALSE;
        }
        UserDetailsImpl userDetailsInfo = getCustomUserDetailsInfo();
        if (CollUtil.isEmpty(userDetailsInfo.getPermissions())) {
            return false;
        }
        return hasPermissions(userDetailsInfo.getPermissions(), permission);
    }

    /**
     * 任何一个包含指定权限
     *
     * @param permissions 权限字符串,多个用逗号分割
     * @return 验证结果
     */
    public boolean hasAnyPermission(String permissions) {
        if (StrUtil.isBlank(permissions)) {
            return false;
        }
        UserDetailsImpl userDetailsInfo = getCustomUserDetailsInfo();
        if (CollUtil.isEmpty(userDetailsInfo.getPermissions())) {
            return false;
        }
        for (String permission : permissions.split(PERMISSION_SEPARATOR)) {
            if (hasPermissions(userDetailsInfo.getPermissions(), permission)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 包含指定角色
     *
     * @param role 角色字符串
     * @return 验证结果
     */
    public boolean hasRole(String role) {
        if (StrUtil.isBlank(role)) {
            return false;
        }
        UserDetailsImpl userDetailsInfo = getCustomUserDetailsInfo();
        if (CollUtil.isEmpty(userDetailsInfo.getRoles())) {
            return false;
        }
        return hasRole(userDetailsInfo.getRoles(), role);
    }

    /**
     * 任何一个包含指定角色
     *
     * @param roles 角色字符串,多个用逗号分割
     * @return 验证结果
     */
    public boolean hasAnyRole(String roles) {
        if (StrUtil.isBlank(roles)) {
            return false;
        }
        UserDetailsImpl userDetailsInfo = getCustomUserDetailsInfo();
        if (CollUtil.isEmpty(userDetailsInfo.getPermissions())) {
            return false;
        }
        for (String role : roles.split(ROLE_SEPARATOR)) {
            if (hasRole(userDetailsInfo.getRoles(), role)) {
                System.out.println(hasRole(userDetailsInfo.getRoles(), role));
                System.out.println(userDetailsInfo.getRoles());
                System.out.println(role);
                return true;
            }
        }
        return false;
    }


    /**
     * 判断是否包含权限
     *
     * @param permissions 权限列表
     * @param permission  权限字符串
     * @return 用户是否包含某权限
     */
    private boolean hasPermissions(Set<String> permissions, String permission) {
        return permissions.contains(ALL_PERMISSION) || permissions.contains(StrUtil.trim(permission));
    }

    /**
     * 判断是否包含角色
     *
     * @param roles 角色列表
     * @param role  角色字符串
     * @return 用户是否包含某角色
     */
    private boolean hasRole(Set<String> roles, String role) {
        return roles.contains(ALL_ROLE) || roles.contains(StrUtil.trim(role));
    }

    /**
     * 获取当前登录用户
     *
     * @return CustomUserDetails
     */
    private UserDetailsImpl getCustomUserDetailsInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails) {
            return (UserDetailsImpl) principal;
        }
        throw new BaseException(ResultCodeEnum.UNAUTHORIZED.getCode(), "获取用户信息异常");
    }

}
