package com.haohao.fast.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.fast.common.bean.BaseEntity;
import lombok.*;

/**
 * @author haohao
 * @TableName sys_user_role
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUserRoleEntity extends BaseEntity {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 角色ID
     */
    private Long roleId;

}