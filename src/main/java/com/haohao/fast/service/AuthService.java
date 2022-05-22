package com.haohao.fast.service;

import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.param.LoginParam;

/**
 * @author haohao
 */
public interface AuthService {

    /**
     * 登录
     *
     * @param loginParam 登录参数
     * @return 登录结果
     */
    ResultData login(LoginParam loginParam);

    /**
     * 获取当前登录用户信息
     *
     * @return ResultData
     */
    ResultData getInfo();

    /**
     * 获取当前登录用户路由信息
     *
     * @return ResultData
     */
    ResultData getRouters();

    /**
     * 退出登录
     *
     * @return 退出结果
     */
    ResultData logout();

}
