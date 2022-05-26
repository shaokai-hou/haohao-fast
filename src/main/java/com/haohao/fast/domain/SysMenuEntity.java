package com.haohao.fast.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.fast.common.bean.BaseEntity;
import lombok.*;

import java.util.List;

/**
 * @author haohao
 * @TableName sys_menu
 */

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_menu")
public class SysMenuEntity extends BaseEntity {

    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 父菜单ID
     */
    private Long parentId;
    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 菜单类型（M：目录｜C：菜单｜P：权限）
     */
    private String menuType;
    /**
     * 路由地址
     */
    private String path;
    /**
     * 组件名字
     */
    private String component;
    /**
     * 权限标识
     */
    private String permission;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 图标
     */
    private String icon;
    /**
     * 子菜单
     */
    @TableField(exist = false)
    List<SysMenuEntity> childrenMenu;
}