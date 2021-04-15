package com.baiyan.common.rpc.config.hystrix;

import com.baiyan.common.rpc.config.ExceptionWrapper;
import com.baiyan.common.rpc.config.RpcConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnBean(type = "org.springframework.cloud.netflix.hystrix.HystrixCircuitBreakerConfiguration")
@AutoConfigureBefore(RpcConfiguration.class)
public class HystrixConfiguration {

    @Bean
    public ExceptionWrapper hystrixExceptionWrapper() {
        return new HystrixExceptionWrapper();
    }

}
