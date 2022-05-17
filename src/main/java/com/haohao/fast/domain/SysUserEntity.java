package com.haohao.fast.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.fast.common.bean.BaseEntity;
import lombok.*;

/**
 * @author haohao
 * @TableName sys_user
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUserEntity extends BaseEntity {

    /**
     * 主键
     */
    @TableId
    private Long id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 状态:(0:正常 1:禁用)
     */
    private Integer state;

    /**
     * 手机号
     */
    private String phone;

}