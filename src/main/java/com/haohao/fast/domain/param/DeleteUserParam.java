package com.haohao.fast.domain.param;

import lombok.Data;

/**
 * @author haohao
 */
@Data
public class DeleteUserParam {

    /**
     * 用户ids
     */
    private Long[] userIds;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 删除标志 0：逻辑删除，默认  1：物理删除
     */
    private String reallyFlag;
}
