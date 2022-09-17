package com.baiyan.common.service.config.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * minio配置类
 *
 * @author baiyan
 * @time 2021/5/5 13:07
 */
@Data
@ConfigurationProperties(prefix = "spring.minio")
public class MinioConfig {
    /**
     * ip
     */
    private String endpoint;

    /**
     * 端口
     */
    private int port;

    /**
     * 账号
     */
    private String accessKey;

    /**
     * 秘钥
     */
    private String secretKey;

    /**
     * 如果是true，则用的是https而不是http,默认值是true
     */
    private Boolean secure;

    /**
     * 桶名称，默认为 baiyan
     */
    private String bucketName = "baiyan";

    /**
     * 是否开启nginx路由
     */
    private Boolean nginxLoadUrlEnable = true;

    /**
     * 预览的url在nginx中的前缀
     */
    private String nginxLoadUrl = "api/9c16ff1ecec";


    /**
     * 默认最大文件上传为500
     */
    private Integer maxUploadFileSize = 1024*1024*500;
}