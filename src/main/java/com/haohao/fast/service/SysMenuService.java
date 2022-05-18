package com.haohao.fast.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.haohao.fast.domain.SysMenuEntity;

import java.util.List;

/**
 * @author haohao
 * @description 针对表【sys_menu】的数据库操作Service
 * @createDate 2022-05-17 17:28:08
 */
public interface SysMenuService extends IService<SysMenuEntity> {

    /**
     * 查询菜单根据角色ids
     * @param roleIds 角色ids
     * @return 权限集合
     */
    List<SysMenuEntity> listMenuByRoleIds(Long[] roleIds);
}
