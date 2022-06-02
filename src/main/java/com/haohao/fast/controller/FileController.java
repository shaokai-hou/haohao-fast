package com.haohao.fast.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.text.StrFormatter;
import cn.hutool.core.util.StrUtil;
import com.haohao.fast.common.properties.MinioProperties;
import com.haohao.fast.common.result.ResultData;
import com.haohao.fast.service.MinioService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;

/**
 * @author haohao
 * @date 2022年05月31日 16:00
 */
@RestController
@RequestMapping("/api/file")
@Api(value = "文件管理接口", tags = "文件管理接口")
@RequiredArgsConstructor
public class FileController {

    final MinioService minioService;
    final MinioProperties minioProperties;

    @PostMapping("/upload")
    public ResultData upload(@RequestBody MultipartFile file) {
        String fileSuffix = FileNameUtil.getSuffix(file.getOriginalFilename());
        if (minioProperties.getAllowedFileTypes().stream().noneMatch(item -> StrUtil.equalsIgnoreCase(item, fileSuffix))) {
            return ResultData.error().message(StrFormatter.format("不支持的文件类型:{}", fileSuffix));
        }
        return ResultData.success().data(minioService.upload(file));
    }

    @GetMapping("/download")
    @SneakyThrows
    public void download(String fileName, HttpServletResponse response) {
        InputStream inputStream = minioService.download(fileName);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        response.setHeader("Content-Disposition", "attachment; fileName=downloadFile." + FileNameUtil.getSuffix(fileName));
        IoUtil.copy(inputStream, response.getOutputStream(), IoUtil.DEFAULT_BUFFER_SIZE);
    }

}
