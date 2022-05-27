package com.haohao.fast.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.haohao.fast.common.bean.BaseController;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysDictDataEntity;
import com.haohao.fast.domain.SysDictEntity;
import com.haohao.fast.service.SysDictDataService;
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
@Api(value = "系统字典数据接口", tags = "系统字典数据接口")
public class SysDictDataController extends BaseController<SysDictDataEntity> {

    final SysDictDataService sysDictDataService;

    @ApiOperation("系统字典数据列表")
    @GetMapping("/list")
    public ResultData list(SysDictDataEntity sysDictDataEntity) {
        return ResultData.success().data(sysDictDataService.list(new QueryWrapper<>(sysDictDataEntity)));
    }

    @ApiOperation("系统字典数据分页")
    @GetMapping("/page")
    public ResultData page() {
        return ResultData.success().data(sysDictDataService.page(getPage(), new QueryWrapper<>()));
    }

    @ApiOperation("系统字典数据添加")
    @PostMapping
    public ResultData save(@RequestBody SysDictDataEntity sysDictDataEntity) {
        return ResultData.flag(sysDictDataService.save(sysDictDataEntity));
    }

    @ApiOperation("系统字典数据修改")
    @PutMapping
    public ResultData update(@RequestBody SysDictDataEntity sysDictDataEntity) {
        return ResultData.flag(sysDictDataService.updateById(sysDictDataEntity));
    }
}
