package com.haohao.fast.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.util.IdUtil;
import com.haohao.fast.common.properties.MinioProperties;
import com.haohao.fast.service.MinioService;
import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.messages.DeleteObject;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

/**
 * @author haohao
 * @date 2022年05月31日 15:41
 */
@Service
@RequiredArgsConstructor
public class MinioServiceImpl implements MinioService {

    final MinioClient minioClient;
    final MinioProperties minioProperties;

    /**
     * /**
     * 文件上传
     *
     * @param file 文件对象
     * @return 上传结果
     */
    @Override
    @SneakyThrows
    public String upload(MultipartFile file) {
        // 生成文件路径
        StringJoiner fileName = new StringJoiner(File.separator);
        fileName.add(String.valueOf(DateUtil.thisYear()));
        fileName.add(String.valueOf(DateUtil.thisMonthEnum().getValueBaseOne()));
        fileName.add(String.valueOf(DateUtil.thisDayOfMonth()));
        fileName.add(IdUtil.fastSimpleUUID() + "." + FileNameUtil.getSuffix(file.getOriginalFilename()));
        // 上传
        minioClient.putObject(PutObjectArgs.builder()
                .object(fileName.toString())
                .bucket(minioProperties.getBucketName())
                .contentType(file.getContentType())
                .stream(file.getInputStream(), file.getSize(), -1)
                .build());
        return fileName.toString();
    }

    /**
     * 文件上传
     *
     * @param files 文件对象
     * @return 上传结果（多个用逗号分割）
     */
    @Override
    public String upload(MultipartFile[] files) {
        StringJoiner result = new StringJoiner(",");
        for (MultipartFile file : files) {
            result.add(this.upload(file));
        }
        return result.toString();
    }

    /**
     * 下载
     *
     * @param fileName 文件名称
     * @return InputStream 输入流
     */
    @Override
    @SneakyThrows
    public InputStream download(String fileName) {
        return minioClient.getObject(GetObjectArgs.builder().bucket(minioProperties.getBucketName()).object(fileName).build());
    }

    /**
     * 删除文件
     *
     * @param fileNames 文件名称
     */
    @Override
    public void delete(List<String> fileNames) {
        minioClient.removeObjects(RemoveObjectsArgs
                .builder()
                .bucket(minioProperties.getBucketName())
                .objects(fileNames.stream().map(DeleteObject::new).collect(Collectors.toList()))
                .build());
    }
}
