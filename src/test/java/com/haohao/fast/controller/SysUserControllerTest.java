package com.haohao.fast.controller;

import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.domain.SysUserEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysUserControllerTest {

    @Autowired
    SysUserController sysUserController;

    @Test
    void exportExcel() {
        ResultData resultData = sysUserController.exportExcel(new SysUserEntity());
        System.out.println(resultData);
    }
}