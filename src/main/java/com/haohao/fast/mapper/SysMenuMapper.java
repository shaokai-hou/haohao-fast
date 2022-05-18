package com.haohao.fast.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.haohao.fast.domain.SysMenuEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author haohao
 * @description 针对表【sys_menu】的数据库操作Mapper
 * @createDate 2022-05-17 17:28:08
 * @Entity com.haohao.fast.domain.SysMenu
 */
public interface SysMenuMapper extends BaseMapper<SysMenuEntity> {

    /**
     * 查询菜单根据角色ids
     *
     * @param roleIds 角色ids
     * @return 权限集合
     */
    List<SysMenuEntity> listMenuByRoleIds(@Param("roleIds") Long[] roleIds);
}
