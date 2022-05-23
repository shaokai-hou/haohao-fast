package com.haohao.fast.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haohao.fast.common.constant.RedisConstant;
import com.haohao.fast.common.constant.StateConstant;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysRoleEntity;
import com.haohao.fast.domain.SysUserEntity;
import com.haohao.fast.domain.SysUserRoleEntity;
import com.haohao.fast.mapper.SysUserMapper;
import com.haohao.fast.service.SysMenuService;
import com.haohao.fast.service.SysRoleService;
import com.haohao.fast.service.SysUserRoleService;
import com.haohao.fast.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author haohao
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2022-05-17 17:28:08
 */
@Service
@RequiredArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity>
        implements SysUserService {

    final SysRoleService roleService;
    final SysMenuService sysMenuService;
    final SysUserRoleService sysUserRoleService;
    final StringRedisTemplate redisTemplate;

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     * @return ResultData
     */
    @Override
    public ResultData getInfo(Long userId) {
        SysUserEntity userInfo = getById(userId);
        List<SysRoleEntity> roleEntityList = roleService.listRoleByUserId(userId);
        userInfo.setRoleIds(roleEntityList.stream().map(SysRoleEntity::getId).collect(Collectors.toList()));
        return ResultData.success().data(userInfo);
    }

    /**
     * 保存用户
     *
     * @param sysUserEntity 用户实体类
     * @return 保存结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData saveUser(SysUserEntity sysUserEntity) {
        // 用户名（username）唯一验证
        SysUserEntity userInfo = getBaseMapper().selectOne(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getUsername, sysUserEntity.getUsername().trim()));
        if (Objects.nonNull(userInfo)) {
            return ResultData.error().message("用户名已存在");
        }
        sysUserEntity.setPassword(new BCryptPasswordEncoder().encode(sysUserEntity.getPassword()));
        // 添加用户
        if (this.save(sysUserEntity)) {
            // 保存角色
            List<SysUserRoleEntity> userRoleEntityList = new ArrayList<>();
            sysUserEntity.getRoleIds().forEach(item -> userRoleEntityList.add(new SysUserRoleEntity(null, sysUserEntity.getId(), item)));
            return ResultData.flag(sysUserRoleService.saveBatch(userRoleEntityList));
        }
        return ResultData.error();
    }

    /**
     * 更新用户
     *
     * @param sysUserEntity 用户实体
     * @return ResultData
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData updateUser(SysUserEntity sysUserEntity) {
        // 用户名（username）唯一验证
        LambdaQueryWrapper<SysUserEntity> query = new LambdaQueryWrapper<SysUserEntity>()
                .eq(SysUserEntity::getUsername, sysUserEntity.getUsername().trim())
                .ne(SysUserEntity::getId, sysUserEntity.getId());
        SysUserEntity userInfo = getBaseMapper().selectOne(query);

        if (Objects.nonNull(userInfo)) {
            return ResultData.error().message("用户名已存在");
        }
        // 更新
        sysUserEntity.setVersion(getById(sysUserEntity.getId()).getVersion());
        if (updateById(sysUserEntity)) {
            // 更新角色用户关联
            sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRoleEntity>().eq(SysUserRoleEntity::getUserId, sysUserEntity.getId()));
            // 保存角色
            List<SysUserRoleEntity> userRoleEntityList = new ArrayList<>();
            sysUserEntity.getRoleIds().forEach(item -> userRoleEntityList.add(new SysUserRoleEntity(null, sysUserEntity.getId(), item)));
            return ResultData.flag(sysUserRoleService.saveBatch(userRoleEntityList));
        }
        return ResultData.error();
    }

    /**
     * 更新用户状态
     *
     * @param userId 用户Id
     * @return ResultData
     */
    @Override
    public ResultData updateState(Long userId) {
        SysUserEntity sysUser = this.getBaseMapper().selectById(userId);
        sysUser.setState(StateConstant.ENABLE.equals(sysUser.getState()) ? StateConstant.DISABLE : StateConstant.ENABLE);
        redisTemplate.delete(RedisConstant.LOGIN_KEY + userId);
        return ResultData.flag(this.getBaseMapper().updateById(sysUser) > 0);
    }

}
