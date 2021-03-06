package com.haohao.fast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysRoleEntity;
import com.haohao.fast.domain.param.DeleteRoleParam;

import java.util.List;

/**
 * @author haohao
 * @description 针对表【sys_role】的数据库操作Service
 * @createDate 2022-05-17 17:28:08
 */
public interface SysRoleService extends IService<SysRoleEntity> {

    /**
     * 用户ID查询角色
     *
     * @param userId 用户ID
     * @return 角色集合
     */
    List<SysRoleEntity> listRoleByUserId(Long userId);

    /**
     * 删除
     * @param deleteRoleParam 删除参数
     * @return 删除结果
     */
    ResultData delete(DeleteRoleParam deleteRoleParam);
}
