package com.haohao.fast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haohao.fast.domain.SysRoleEntity;

import java.util.List;

/**
 * @author haohao
 * @description 针对表【sys_role】的数据库操作Mapper
 * @createDate 2022-05-17 17:28:08
 * @Entity com.haohao.fast.domain.SysRole
 */
public interface SysRoleMapper extends BaseMapper<SysRoleEntity> {

    /**
     * 用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色集合
     */
    List<SysRoleEntity> listRoleByUserId(Long userId);
}
