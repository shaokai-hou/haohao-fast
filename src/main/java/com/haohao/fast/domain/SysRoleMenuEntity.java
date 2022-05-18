package com.haohao.fast.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.fast.common.bean.BaseEntity;
import lombok.*;

/**
 * @author haohao
 * @TableName sys_role_menu
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_role_menu")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysRoleMenuEntity extends BaseEntity {
    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 菜单ID
     */
    private Long menuId;

    /**
     * 角色ID
     */
    private Long roleId;

}