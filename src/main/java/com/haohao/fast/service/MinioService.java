package com.haohao.fast.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * @author haohao
 * @date 2022年05月31日 15:41
 */
public interface MinioService {

    /**
     * 文件上传
     *
     * @param file 文件对象
     * @return 上传结果
     */
    String upload(MultipartFile file);

    /**
     * 多文件上传
     *
     * @param files 文件对象
     * @return 上传结果（多个用逗号分割）
     */
    String upload(MultipartFile[] files);

    /**
     * 文件上传
     *
     * @param inputStream      inputStream
     * @param originalFilename 原文件名称
     * @return 上传结果
     */
    String upload(InputStream inputStream, String originalFilename);

    /**
     * 下载
     *
     * @param fileName 文件名称
     * @return InputStream 输入流
     */
    InputStream download(String fileName);

    /**
     * 删除文件
     *
     * @param fileNames 文件名称
     */
    void delete(List<String> fileNames);
}
