package com.baiyan.common.service.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 领域事件基类
 *
 * @author baiyan
 * @date 2021/02/21
 */
@Getter
@Setter
@NoArgsConstructor
public abstract class BaseDomainEvent<T> implements Serializable {

    private static final long serialVersionUID = 1465328245048581896L;

    /**
     * 领域事件id
     */
    private String demandId;

    /**
     * 发生时间
     */
    private LocalDateTime occurredOn;

    /**
     * 领域事件数据
     */
    private T data;

    public BaseDomainEvent(String demandId, T data) {
        this.demandId = demandId;
        this.data = data;
        this.occurredOn = LocalDateTime.now();
    }

}
