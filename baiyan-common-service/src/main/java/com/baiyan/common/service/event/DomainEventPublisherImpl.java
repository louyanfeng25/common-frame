package com.baiyan.common.service.event;

import com.baiyan.common.base.model.event.BaseDomainEvent;
import com.baiyan.common.base.utils.GsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

/**
 * 领域事件发布实现类
 *
 * @author baiyan
 * @date  2021/02/21
 */
@Component
@Slf4j
@ConditionalOnProperty(value = "baiyan.config.domain.event.enable", havingValue = "true")
public class DomainEventPublisherImpl implements DomainEventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishEvent(BaseDomainEvent event) {
        log.debug("发布事件,event:{}", GsonUtil.gsonToString(event));
        applicationEventPublisher.publishEvent(event);
    }

}
