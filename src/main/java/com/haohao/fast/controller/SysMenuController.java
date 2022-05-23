package com.haohao.fast.controller;

import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.service.SysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单接口
 *
 * @author haohao
 */
@RestController
@RequestMapping("/api/menu")
@RequiredArgsConstructor
@Api(value = "菜单管理接口", tags = "菜单管理接口")
public class SysMenuController {

    final SysMenuService sysMenuService;

    @GetMapping("/tree")
    @ApiOperation("查询所有菜单（树结构）")
    @PreAuthorize("@ss.hasAnyRole('test,dev')")
    public ResultData menuTreeAll() {
        return ResultData.success().data(sysMenuService.listMenuTree());
    }

}
