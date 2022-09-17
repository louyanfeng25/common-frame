package com.baiyan.common.rpc.rpc;

import com.baiyan.common.api.api.DemoApi;
import com.baiyan.common.rpc.config.DemoConstants;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

/**
 * @author baiyan
 * @time 2020/12/09
 */
@FeignClient(
        name = DemoConstants.Demo.SERVICE_NAME,
        contextId = DemoConstants.Demo.NAME,
        fallback = DemoApiClient.AccessFallback.class,
        configuration = DemoApiClient.AccessFallback.class
)
public interface DemoApiClient extends DemoApi {

    @Component
    class AccessFallback implements DemoApiClient {

        @Override
        public void delete(Long id){}

    }

}
