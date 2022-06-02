package com.haohao.fast.config;

import com.haohao.fast.common.properties.MinioProperties;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author haohao
 * @date 2022年05月31日 15:17
 */
@Slf4j
@Configuration
@RequiredArgsConstructor
public class MinioConfig {

    final MinioProperties minioProperties;

    /**
     * 注入minio 客户端
     *
     * @return MinioClient
     */
    @Bean
    public MinioClient minioClient() {
        log.info("Init minioClient success");
        return MinioClient.
                builder().
                endpoint(minioProperties.getUrl()).
                credentials(minioProperties.getUser(), minioProperties.getPassword()).
                build();
    }
}
