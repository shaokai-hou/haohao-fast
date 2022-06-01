package com.haohao.fast.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author haohao
 * @date 2022年05月31日 15:41
 */
public interface MinioService {

    /**
     * /**
     * 文件上传
     *
     * @param file 文件对象
     * @return 上传结果
     */
    String upload(MultipartFile file);

    /**
     * 下载
     *
     * @param fileName 文件名称
     * @return InputStream输入流
     */
    InputStream download(String fileName);
}
