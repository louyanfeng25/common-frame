package com.baiyan.common.api.api;

import com.baiyan.common.api.config.VersionConfig;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * rpc定义示例
 *
 * @author baiyan
 * @time 2021/02/20
 */
@RequestMapping(VersionConfig.COMMON_RPC_VERSION_URL+"demo")
public interface DemoApi {

    /**
     * 删除RPC接口实例
     *
     * @param id
     * @return
     */
    @PostMapping
    void delete(@RequestParam("id") Long id);

}
