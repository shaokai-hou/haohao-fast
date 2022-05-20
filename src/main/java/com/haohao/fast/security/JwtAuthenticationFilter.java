package com.haohao.fast.security;

import cn.hutool.core.util.StrUtil;
import cn.hutool.jwt.JWTUtil;
import com.haohao.fast.properties.JwtProperties;
import com.haohao.fast.security.user.UserDetailsImpl;
import com.haohao.fast.util.JacksonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 自定义JwtToken验证
 *
 * @author haohao
 */
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    final JwtProperties jwtProperties;
    final StringRedisTemplate stringRedisTemplate;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 拿到Authorization请求头内的信息
        String token = request.getHeader(jwtProperties.getHeader());
        // 判断不为空、指定字符串开头
        if (StrUtil.isNotBlank(token) && StrUtil.startWith(token, jwtProperties.getPrefix())) {
            // 获取真实token、验证token
            String realToken = token.substring(jwtProperties.getPrefix().length()).trim();
            if (JWTUtil.verify(realToken, jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8))) {
                // token获取userId
                String uuid = (String) JWTUtil.parseToken(realToken).getPayload("uuid");
                if (StrUtil.isNotBlank(uuid) && Boolean.TRUE.equals(stringRedisTemplate.hasKey(uuid))) {
                    // 查询缓存用户信息
                    String cacheStr = stringRedisTemplate.opsForValue().get(uuid);
                    UserDetailsImpl userDetails = JacksonUtils.parseObject(cacheStr, UserDetailsImpl.class);
                    // 组装authentication对象，构造参数是Principal Credentials 与 Authorities
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, userDetails.getPassword(), userDetails.getAuthorities());
                    // 将authentication信息放入到上下文对象中
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }
}
