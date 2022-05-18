package com.haohao.fast.security;

import com.haohao.fast.common.result.ResultCodeEnum;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.util.JacksonUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 无权限访问
 * 当用户尝试访问需要权限的资源、而不提供Token或者Token过期、错误
 *
 * @author haohao
 */
@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JacksonUtils.toJsonString(ResultData.setResult(ResultCodeEnum.UNAUTHORIZED)));
    }
}
