package com.haohao.fast.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.jwt.JWTUtil;
import com.haohao.fast.common.constant.RedisConstant;
import com.haohao.fast.common.properties.JwtProperties;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysMenuEntity;
import com.haohao.fast.domain.SysRoleEntity;
import com.haohao.fast.domain.param.LoginParam;
import com.haohao.fast.domain.router.Router;
import com.haohao.fast.security.user.UserDetailsImpl;
import com.haohao.fast.service.AuthService;
import com.haohao.fast.service.SysMenuService;
import com.haohao.fast.service.SysRoleService;
import com.haohao.fast.util.JacksonUtils;
import com.haohao.fast.util.SecurityUtils;
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
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
    final SysMenuService sysMenuService;
    final SysRoleService sysRoleService;

    /**
     * 登录
     *
     * @param loginParam 登录参数
     * @return 登录结果
     */
    @Override
    public ResultData login(LoginParam loginParam) {
        String username = loginParam.getUsername();
        String password = loginParam.getPassword();
        try {
            // 认证，该方法会自己调用UserDetailsServiceImpl.loadUserByUsername()
            Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            // 保存认证信息
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            // 获取认证信息
            UserDetailsImpl principal = (UserDetailsImpl) authenticate.getPrincipal();
            // 签发Token
            HashMap<String, Object> payload = new HashMap<>(1);
            payload.put("userId", principal.getUserId());
            String token = JWTUtil.createToken(payload, jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8));
            // 放入缓存
            stringRedisTemplate.opsForValue().set(RedisConstant.LOGIN_KEY + principal.getUserId(), JacksonUtils.toJsonString(principal), jwtProperties.getExpireTime(), TimeUnit.MINUTES);
            // 返回成功
            Map<String, Object> result = new HashMap<>(1);
            result.put("datetime", DateUtil.now());
            result.put("token", token);
            return ResultData.success().data(result);
        } catch (UsernameNotFoundException e) {
            return ResultData.error().message("用户不存在！");
        } catch (BadCredentialsException e) {
            return ResultData.error().message("密码错误！");
        } catch (DisabledException e) {
            return ResultData.error().message("账号被封禁！");
        } catch (AuthenticationException e) {
            log.error("login AuthenticationException is : \n", e);
            return ResultData.error().message(e.getMessage());
        } catch (Exception e) {
            log.error("login Exception is : \n", e);
            return ResultData.error().message(e.getMessage());
        }
    }

    /**
     * 获取当前登录用户信息
     *
     * @return ResultData
     */
    @Override
    public ResultData getInfo() {
        UserDetailsImpl userDetails = SecurityUtils.getUserDetails();
        return ResultData.success().data(userDetails);
    }

    /**
     * 获取当前登录用户路由信息
     *
     * @return ResultData
     */
    @Override
    public ResultData getRouters() {
        Long userId = SecurityUtils.getUserId();
        List<SysRoleEntity> roles = sysRoleService.listRoleByUserId(userId);
        Long[] roleIds = roles.stream().map(SysRoleEntity::getId).toArray(Long[]::new);
        List<SysMenuEntity> menus = sysMenuService.listMenuByRoleIds(roleIds);
        String[] menuType = {"M", "C"};
        menus = menus.stream().filter(item -> ArrayUtil.contains(menuType, item.getMenuType())).collect(Collectors.toList());
        // 构建树结构
        List<Tree<Long>> trees = TreeUtil.build(menus, 0L, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setName(treeNode.getMenuName());
            tree.setWeight(treeNode.getSort());
            tree.put("path", treeNode.getPath());
            tree.put("component", treeNode.getComponent());
            tree.put("icon", treeNode.getIcon());
        });
        List<Router> routers = trees.stream().map(Router::new).collect(Collectors.toList());
        return ResultData.success().data(routers);
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
        stringRedisTemplate.delete(principal.getUserId());
        // 清空上下文
        SecurityContextHolder.clearContext();
        return ResultData.success();
    }

}
