package com.haohao.fast.domain.param;

import lombok.Data;

/**
 * 登录参数
 *
 * @author haohao
 */
@Data
public class LoginParam {

    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
}
