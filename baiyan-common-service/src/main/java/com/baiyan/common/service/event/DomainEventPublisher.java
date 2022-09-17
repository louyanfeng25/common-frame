package com.baiyan.common.service.event;


import com.baiyan.common.base.model.event.BaseDomainEvent;

/**
 * 领域事件发布接口
 *
 * @author baiyan
 * @date  2021/02/21
 */
public interface DomainEventPublisher {

    /**
     * 发布事件
     *
     * @param event 领域事件
     */
    void publishEvent(BaseDomainEvent event);

}
