package com.haohao.fast.util;

import com.haohao.fast.common.constant.HttpConstant;
import com.haohao.fast.exception.BaseException;
import com.haohao.fast.security.user.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author haohao
 */
public class SecurityUtils {

    /**
     * 用户当前用户ID
     **/
    public static Long getUserId() {
        try {
            return getUserDetails().getSysUserEntity().getId();
        } catch (Exception e) {
            throw new BaseException(HttpConstant.UNAUTHORIZED, "获取用户ID异常");
        }
    }

    /**
     * 获取当前用户
     **/
    public static UserDetailsImpl getUserDetails() {
        try {
            return (UserDetailsImpl) getAuthentication().getPrincipal();
        } catch (Exception e) {
            throw new BaseException(HttpConstant.UNAUTHORIZED, "获取用户信息异常");
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
