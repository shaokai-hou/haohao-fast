package com.haohao.fast.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haohao.fast.common.bean.BaseController;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysUserEntity;
import com.haohao.fast.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统用户控制器
 *
 * @author haohao
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api(value = "用户管理接口", tags = "用户管理接口")
public class SysUserController extends BaseController<SysUserEntity> {

    final SysUserService sysUserService;

    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasRole('dev')")
    public ResultData list(SysUserEntity sysUserEntity) {
        return ResultData.success().data(sysUserService.list(new QueryWrapper<>(sysUserEntity)));
    }

    @ApiOperation("查询用户列表分页")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasPermission('system:user:page')")
    public ResultData page(SysUserEntity sysUserEntity) {
        Page<SysUserEntity> page = sysUserService.page(getPage(), new QueryWrapper<>(sysUserEntity));
        return ResultData.success().data(page);
    }

    @ApiOperation("添加用户")
    @PostMapping
    @PreAuthorize("@ss.hasRole('dev')")
    public ResultData save(@RequestBody @Validated SysUserEntity sysUserEntity) {
        sysUserEntity.setPassword(new BCryptPasswordEncoder().encode(sysUserEntity.getPassword()));
        return ResultData.flag(sysUserService.save(sysUserEntity));
    }

    @ApiOperation("修改用户")
    @PutMapping
    @PreAuthorize("@ss.hasRole('dev')")
    public ResultData update(@RequestBody @Validated SysUserEntity sysUserEntity) {
        return ResultData.flag(sysUserService.updateById(sysUserEntity));
    }

}
