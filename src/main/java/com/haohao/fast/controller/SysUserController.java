package com.haohao.fast.controller;

import cn.hutool.core.util.IdUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haohao.fast.common.bean.BaseController;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysUserEntity;
import com.haohao.fast.domain.param.DeleteUserParam;
import com.haohao.fast.service.MinioService;
import com.haohao.fast.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 系统用户控制器
 *
 * @author haohao
 */
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Api(value = "用户管理接口", tags = "用户管理接口")
@CacheConfig(cacheNames = "user")
public class SysUserController extends BaseController<SysUserEntity> {

    final SysUserService sysUserService;

    @ApiOperation("查询用户列表")
    @GetMapping("/list")
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

    @ApiOperation("通过用户ID获取用户信息")
    @GetMapping("/{userId}")
    @PreAuthorize("@ss.hasAnyRole('dev,test')")
    @Cacheable({"hot"})
    public ResultData get(@PathVariable("userId") Long userId) {
        return sysUserService.getInfo(userId);
    }

    @ApiOperation("添加用户")
    @PostMapping
    @PreAuthorize("@ss.hasAnyRole('dev,test')")
    public ResultData save(@RequestBody @Validated SysUserEntity sysUserEntity) {
        return sysUserService.saveUser(sysUserEntity);
    }

    @ApiOperation("修改用户")
    @PutMapping
    @PreAuthorize("@ss.hasAnyRole('dev,test')")
    public ResultData update(@RequestBody @Validated SysUserEntity sysUserEntity) {
        return sysUserService.updateUser(sysUserEntity);
    }

    @ApiOperation("删除用户")
    @DeleteMapping
    @PreAuthorize("@ss.hasAnyRole('dev,test')")
    public ResultData delete(@RequestBody DeleteUserParam deleteUserParam) {
        return ResultData.flag(sysUserService.removeBatchByIds(Arrays.asList(deleteUserParam.getUserIds())));
    }

    @ApiOperation("设置用户状态")
    @PutMapping("/state/{userId}")
    @PreAuthorize("@ss.hasAnyRole('dev,test')")
    public ResultData updateState(@PathVariable("userId") Long userId) {
        return sysUserService.updateState(userId);
    }

    @ApiOperation("导出用户")
    @GetMapping("/export/excel")
    @PreAuthorize("@ss.hasAnyRole('dev,test')")
    public ResultData exportExcel(SysUserEntity sysUserEntity) {
        return sysUserService.exportUser(sysUserEntity);
    }
}