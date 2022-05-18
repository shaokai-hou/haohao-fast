package com.haohao.fast.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.fast.common.bean.BaseEntity;
import lombok.*;

/**
 * @author haohao
 * @TableName sys_role
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName(value = "sys_role")
public class SysRoleEntity extends BaseEntity {


    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

}