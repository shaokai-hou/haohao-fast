package com.haohao.fast.service;

import com.haohao.fast.common.result.ResultData;

/**
 * @author haohao
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return 登录结果
     */
    ResultData login(String username, String password);

    /**
     * 退出登录
     *
     * @return 退出结果
     */
    ResultData logout();
}
