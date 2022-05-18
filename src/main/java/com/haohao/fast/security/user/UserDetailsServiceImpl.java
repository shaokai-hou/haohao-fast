package com.haohao.fast.security.user;

import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.haohao.fast.domain.SysMenuEntity;
import com.haohao.fast.domain.SysRoleEntity;
import com.haohao.fast.domain.SysUserEntity;
import com.haohao.fast.service.SysMenuService;
import com.haohao.fast.service.SysRoleService;
import com.haohao.fast.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 自定义登录逻辑实现
 *
 * @author haohao
 */
@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    final SysUserService userService;
    final SysRoleService roleService;
    final SysMenuService menuService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        SysUserEntity sysUserEntity = userService.getOne(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getUsername, username));
        if (Objects.isNull(sysUserEntity)) {
            throw new UsernameNotFoundException("User Not Found");
        }
        // 查询角色权限
        List<SysRoleEntity> roleEntityList = roleService.listRoleByUserId(sysUserEntity.getId());
        List<SysMenuEntity> menuEntityList = menuService.listMenuByRoleIds(roleEntityList.stream().map(SysRoleEntity::getId).distinct().toArray(Long[]::new));
        Set<String> roleNames = roleEntityList.stream().map(SysRoleEntity::getName).collect(Collectors.toSet());
        Set<String> permissions = menuEntityList.stream().map(SysMenuEntity::getPermission).collect(Collectors.toSet());
        return UserDetailsImpl.builder()
                .sysUserEntity(sysUserEntity)
                .uuid(IdUtil.fastUUID())
                .roles(roleNames)
                .permissions(permissions)
                .build();
    }
}
