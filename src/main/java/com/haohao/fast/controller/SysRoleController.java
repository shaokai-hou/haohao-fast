package com.haohao.fast.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.haohao.fast.common.bean.BaseController;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysRoleEntity;
import com.haohao.fast.domain.param.DeleteRoleParam;
import com.haohao.fast.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

/**
 * @author haohao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/role")
@Api(value = "角色管理接口", tags = "角色管理接口")
public class SysRoleController extends BaseController<SysRoleEntity> {

    final SysRoleService sysRoleService;

    @ApiOperation("查询角色列表")
    @GetMapping("/list")
    @PreAuthorize("@ss.hasAnyRole('dev,test')")
    public ResultData list(SysRoleEntity sysRoleEntity) {
        return ResultData.success().data(sysRoleService.list(new QueryWrapper<>(sysRoleEntity)));
    }

    @ApiOperation("查询角色列表分页")
    @GetMapping("/page")
    @PreAuthorize("@ss.hasAnyRole('dev,test')")
    public ResultData page(SysRoleEntity sysRoleEntity) {
        Page<SysRoleEntity> page = sysRoleService.page(getPage(), new QueryWrapper<>(sysRoleEntity));
        return ResultData.success().data(page);
    }

    @ApiOperation("添加角色")
    @PostMapping
    @PreAuthorize("@ss.hasPermission('system:role:save')")
    public ResultData save(@RequestBody @Validated SysRoleEntity sysRoleEntity) {
        return ResultData.flag(sysRoleService.save(sysRoleEntity));
    }

    @ApiOperation("修改角色")
    @PutMapping
    @PreAuthorize("@ss.hasRole('dev,test')")
    public ResultData update(@RequestBody @Validated SysRoleEntity sysRoleEntity) {
        return ResultData.flag(sysRoleService.updateById(sysRoleEntity));
    }

    @ApiOperation("删除角色")
    @DeleteMapping
    @PreAuthorize("@ss.hasRole('dev,test')")
    public ResultData delete(DeleteRoleParam deleteRoleParam) {
        return ResultData.flag(sysRoleService.removeBatchByIds(Arrays.asList(deleteRoleParam.getRoleIds())));
    }

}
