package com.haohao.fast.common.result;

import lombok.Data;

/**
 * Minio通用返回
 *
 * @author haohao
 * @date 2022年05月31日 14:34
 */
@Data
public class MinioResult {

    /**
     * 文件下载地址
     */
    private String fileUrl;
}
