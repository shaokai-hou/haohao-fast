package com.haohao.fast.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWTUtil;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.properties.JwtProperties;
import com.haohao.fast.security.user.UserDetailsImpl;
import com.haohao.fast.service.AuthService;
import com.haohao.fast.util.JacksonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author haohao
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    final AuthenticationManager authenticationManager;
    final StringRedisTemplate stringRedisTemplate;
    final JwtProperties jwtProperties;

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    @Override
    public ResultData login(String username, String password) {
        try {
            // 认证，该方法会自己调用UserDetailsServiceImpl.loadUserByUsername()
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // 保存认证信息
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            // 获取认证信息
            UserDetailsImpl principal = (UserDetailsImpl) authenticate.getPrincipal();
            // 签发Token
            HashMap<String, Object> payload = new HashMap<>(1);
            payload.put("uuid", principal.getUuid());
            String token = JWTUtil.createToken(payload, jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
            // 放入缓存
            stringRedisTemplate.opsForValue().set(principal.getUuid(), JacksonUtils.toJsonString(principal), jwtProperties.getExpireTime(), TimeUnit.MINUTES);
            // 返回成功
            Map<String, Object> result = new HashMap<>(1);
            result.put("datetime", DateUtil.now());
            result.put("uuid", principal.getUuid());
            result.put("token", token);
            return ResultData.success().data(result);
        } catch (UsernameNotFoundException | DisabledException | BadCredentialsException e) {
            return ResultData.error().message(e.getMessage());
        } catch (AuthenticationException e) {
            log.error("login AuthenticationException is : \n", e);
            return ResultData.error().message(e.getMessage());
        } catch (Exception e) {
            log.error("login Exception is : \n", e);
            return ResultData.error().message(e.getMessage());
        }
    }

    /**
     * 退出登录
     *
     * @return 退出结果
     */
    @Override
    public ResultData logout() {
        // 删除缓存
        UserDetailsImpl principal = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        stringRedisTemplate.delete(principal.getUuid());
        // 清空上下文
        SecurityContextHolder.clearContext();
        return ResultData.success();
    }

}
