package com.haohao.fast.security;

import cn.hutool.core.util.StrUtil;
import com.haohao.fast.common.result.ResultCodeEnum;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.util.JacksonUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 权限不足
 * 当用户尝试访问需要权限的资源、而无权限
 *
 * @author haohao
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        String msg = StrUtil.format("资源路径：{}", request.getRequestURI());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(JacksonUtils.toJsonString(ResultData.setResult(ResultCodeEnum.FORBIDDEN).data(msg)));
    }
}
