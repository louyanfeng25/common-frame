package com.baiyan.common.service.config.nginx;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * nginx配置
 *
 * @author baiyan
 * @time 2021/6/25 15:42
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "baiyan.nginx")
public class NginxConfig {

    /**
     * ip
     */
    private String endpoint;

    /**
     * 端口
     */
    private int port;

    /**
     * 协议
     * http或者https
     */
    private String protocol;

    /**
     * 请求前置url
     */
    private String prefixNginxUrl;

    @PostConstruct
    public void init(){
        this.setPrefixNginxUrl(getProtocol() + "://" + getEndpoint() + ":" + getPort() + "/");
    }

}
