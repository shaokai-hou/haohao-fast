package com.haohao.fast.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haohao.fast.common.bean.BaseController;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysDictEntity;
import com.haohao.fast.service.SysDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author haohao
 */
@RestController
@RequestMapping("/api/dict/data")
@RequiredArgsConstructor
@Api(value = "系统字典接口", tags = "系统字典接口")
public class SysDictController extends BaseController<SysDictEntity> {

    final SysDictService sysDictService;

    @ApiOperation("系统字典列表")
    @GetMapping("/list")
    public ResultData list(SysDictEntity sysDictEntity) {
        return ResultData.success().data(sysDictService.list(new QueryWrapper<>(sysDictEntity)));
    }

    @ApiOperation("系统字典分页")
    @GetMapping("/page")
    public ResultData page() {
        return ResultData.success().data(sysDictService.page(getPage(), new QueryWrapper<>()));
    }

    @ApiOperation("系统字典添加")
    @PostMapping
    public ResultData save(@RequestBody SysDictEntity sysDictEntity) {
        return ResultData.flag(sysDictService.save(sysDictEntity));
    }

    @ApiOperation("系统字典修改")
    @PutMapping
    public ResultData update(@RequestBody SysDictEntity sysDictEntity) {
        return ResultData.flag(sysDictService.updateById(sysDictEntity));
    }
}
