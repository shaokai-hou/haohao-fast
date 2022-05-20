package com.haohao.fast.service.impl;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.haohao.fast.domain.SysMenuEntity;
import com.haohao.fast.service.SysMenuService;
import com.haohao.fast.mapper.SysMenuMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author haohao
 * @description 针对表【sys_menu】的数据库操作Service实现
 * @createDate 2022-05-17 17:28:08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenuEntity>
        implements SysMenuService {

    /**
     * 查询菜单根据角色ids
     *
     * @param roleIds 角色ids
     * @return 权限集合
     */
    @Override
    public List<SysMenuEntity> listMenuByRoleIds(Long[] roleIds) {
        return getBaseMapper().listMenuByRoleIds(roleIds);
    }

    /**
     * 查询菜单树结构
     *
     * @return List<Tree < Integer>>
     */
    @Override
    public List<Tree<Integer>> listMenuTree() {
        List<SysMenuEntity> menuList = getBaseMapper().selectList(new QueryWrapper<>());
        return TreeUtil.build(menuList, 0, (treeNode, tree) -> {
            tree.setId(treeNode.getId().intValue());
            tree.setParentId(treeNode.getParentId().intValue());
            tree.setName(treeNode.getMenuName());
        });
    }
}
