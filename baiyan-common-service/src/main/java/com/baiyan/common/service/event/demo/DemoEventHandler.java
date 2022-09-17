package com.baiyan.common.service.event.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * 领域事件处理类
 *
 * @author baiyan
 * @date 2021/02/21
 */
@Component
@Slf4j
public class DemoEventHandler {

    /**
     * 此注解帮助处理器与发布事件的服务处于同一事务中
     * <href> https://blog.csdn.net/qq_41378597/article/details/105748703 </href>
     *
     * @param event 领域事件
     */
    @TransactionalEventListener(fallbackExecution = true)
    public void handleEvent(DemoEvent event) {
        // 领域事件逻辑处理
    }

}
