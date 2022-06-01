package com.haohao.fast.common.properties;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author haohao
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {
    /**
     * 令牌自定义标识
     */
    private String header;
    /**
     * 令牌密钥
     */
    private String secret;
    /**
     * 令牌有效期（默认30分钟）
     */
    private Long expireTime;
    /**
     * 令牌前缀
     */
    private String prefix;
}
