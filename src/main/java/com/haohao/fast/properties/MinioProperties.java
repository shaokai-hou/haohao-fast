package com.haohao.fast.properties;

import io.minio.MinioClient;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author haohao
 */
@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    /**
     * 服务地址
     */
    private String url;
    /**
     * 用户名
     */
    private String user;
    /**
     * 密码
     */
    private String password;
    /**
     * 存储桶名称
     */
    private String bucketName;


}
