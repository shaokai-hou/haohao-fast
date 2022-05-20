package com.haohao.fast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysUserEntity;

/**
 * @author haohao
 * @description 针对表【sys_user】的数据库操作Service
 * @createDate 2022-05-17 17:28:08
 */
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 保存用户
     *
     * @param sysUserEntity 用户实体类
     * @return 保存结果
     */
    ResultData saveUser(SysUserEntity sysUserEntity);
}
