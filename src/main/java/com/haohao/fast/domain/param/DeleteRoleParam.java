package com.haohao.fast.domain.param;

import lombok.Data;

/**
 * @author haohao
 */
@Data
public class DeleteRoleParam {

    /**
     * 角色Id
     */
    private Long roleId;
    /**
     * 角色ids
     */
    private Long[] roleIds;
    /**
     * 删除标志 0：逻辑删除，默认  1：物理删除
     */
    private String reallyFlag = "0";
}
