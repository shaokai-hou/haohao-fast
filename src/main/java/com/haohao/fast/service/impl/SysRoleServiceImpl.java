package com.haohao.fast.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haohao.fast.domain.SysRoleEntity;
import com.haohao.fast.mapper.SysRoleMapper;
import com.haohao.fast.service.SysRoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haohao
 * @description 针对表【sys_role】的数据库操作Service实现
 * @createDate 2022-05-17 17:28:08
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity>
        implements SysRoleService {

    /**
     * 用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色集合
     */
    @Override
    public List<SysRoleEntity> listRoleByUserId(Long userId) {
        return getBaseMapper().listRoleByUserId(userId);
    }
}
