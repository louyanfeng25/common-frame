package com.baiyan.common.service.config.minio;

import io.minio.MinioClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * minio自动配置类
 *
 * @author baiyan
 * @time 2021/5/5 14:53
 */
@Configuration
@Import({MinioUtil.class})
@ConditionalOnProperty(value = "spring.minio.enable",havingValue = "true")
@EnableConfigurationProperties(MinioConfig.class)
public class MinioAutoConfiguration {

    @Bean
    public MinioClient getMinioClient(MinioConfig minioConfig) {
        return MinioClient.builder()
                .endpoint(minioConfig.getEndpoint(),minioConfig.getPort(),minioConfig.getSecure())
                .credentials(minioConfig.getAccessKey(),minioConfig.getSecretKey())
                .build();
    }

}
