package com.haohao.fast.controller;

import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.param.LoginParam;
import com.haohao.fast.service.AuthService;
import com.haohao.fast.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 认证
 *
 * @author haohao
 */
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Api(value = "权限管理接口", tags = "权限管理接口")
public class AuthController {

    final AuthService authService;
    final SysUserService sysUserService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public ResultData login(@RequestBody LoginParam loginParam) {
        return authService.login(loginParam);
    }

    @ApiOperation("获取当前登录用户信息")
    @GetMapping("/info")
    public ResultData getInfo() {
        return authService.getInfo();
    }

    @ApiOperation("获取当前登录用户路由")
    @GetMapping("/routers")
    public ResultData getRouters() {
        return authService.getRouters();
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public ResultData logout() {
        return authService.logout();
    }

}
