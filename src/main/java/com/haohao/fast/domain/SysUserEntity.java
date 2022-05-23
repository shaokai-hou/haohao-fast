package com.haohao.fast.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.haohao.fast.common.bean.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author haohao
 * @TableName sys_user
 */
@ApiModel("系统用户实体类")
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SysUserEntity extends BaseEntity {

    @TableId
    @ApiModelProperty(value = "主键", required = true)
    private Long id;
    @ApiModelProperty(value = "用户名", required = true)
    @NotNull(message = "用户名不能为空")
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
    /**
     * 头像
     */
    private String avatar;
    /**
     * 角色ids
     */
    @TableField(exist = false)
    private List<Long> roleIds;
}