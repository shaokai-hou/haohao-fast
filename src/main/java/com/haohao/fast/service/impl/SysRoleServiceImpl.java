package com.haohao.fast.service.impl;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysRoleEntity;
import com.haohao.fast.domain.param.DeleteRoleParam;
import com.haohao.fast.mapper.SysRoleMapper;
import com.haohao.fast.mapper.SysRoleMenuMapper;
import com.haohao.fast.mapper.SysUserMapper;
import com.haohao.fast.mapper.SysUserRoleMapper;
import com.haohao.fast.service.SysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * @author haohao
 * @description 针对表【sys_role】的数据库操作Service实现
 * @createDate 2022-05-17 17:28:08
 */
@Service
@RequiredArgsConstructor
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRoleEntity>
        implements SysRoleService {

    final SysUserMapper sysUserMapper;
    final SysUserRoleMapper sysUserRoleMapper;
    final SysRoleMenuMapper sysRoleMenuMapper;

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

    /**
     * 删除
     *
     * @param deleteRoleParam 删除参数
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultData delete(DeleteRoleParam deleteRoleParam) {

        // TODO
        // 已关联的用户、已关联的权限一并删除
        Long roleId = deleteRoleParam.getRoleId();

        if (Objects.nonNull(roleId)) {
            int flag = StrUtil.equals(deleteRoleParam.getReallyFlag(), "0") ?
                    sysUserMapper.deleteById(roleId) :
                    sysUserMapper.reallyDeleteById(roleId);
            return flag > 0 ? ResultData.success() : ResultData.error();
        }

        Long[] roleIds = deleteRoleParam.getRoleIds();
        if (ArrayUtil.isNotEmpty(roleIds)) {
            int flag = StrUtil.equals(deleteRoleParam.getReallyFlag(), "0") ?
                    sysUserMapper.deleteBatchIds(Arrays.asList(roleIds)) :
                    sysUserMapper.reallyDeleteByIds(roleIds);
            return flag > 0 ? ResultData.success() : ResultData.error();
        }

        return ResultData.error();
    }
}
