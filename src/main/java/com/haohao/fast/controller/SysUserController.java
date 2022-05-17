package com.haohao.fast.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysUserEntity;
import com.haohao.fast.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统用户控制器
 *
 * @author haohao
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Api(value = "用户管理接口", tags = "用户管理接口")
public class SysUserController {

    final SysUserService sysUserService;

    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public ResultData getList(SysUserEntity sysUserEntity) {
        return ResultData.success().data(sysUserService.list(new QueryWrapper<>(sysUserEntity)));
    }
}
