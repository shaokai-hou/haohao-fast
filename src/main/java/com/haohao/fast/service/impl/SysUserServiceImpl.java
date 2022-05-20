package com.haohao.fast.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysUserEntity;
import com.haohao.fast.mapper.SysUserMapper;
import com.haohao.fast.service.SysUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author haohao
 * @description 针对表【sys_user】的数据库操作Service实现
 * @createDate 2022-05-17 17:28:08
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity>
        implements SysUserService {

    /**
     * 保存用户
     *
     * @param sysUserEntity 用户实体类
     * @return 保存结果
     */
    @Override
    public ResultData saveUser(SysUserEntity sysUserEntity) {
        // 用户名（username）唯一验证
        SysUserEntity userInfo = getBaseMapper().selectOne(new LambdaQueryWrapper<SysUserEntity>().eq(SysUserEntity::getUsername, sysUserEntity.getUsername().trim()));
        if (Objects.nonNull(userInfo)) {
            return ResultData.error().message("用户名已存在");
        }
        sysUserEntity.setPassword(new BCryptPasswordEncoder().encode(sysUserEntity.getPassword()));
        return getBaseMapper().insert(sysUserEntity) > 0 ? ResultData.success() : ResultData.error();
    }

}
