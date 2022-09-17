package com.baiyan.common.base.model.event;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

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
     * 领域事件数据
     */
    private T data;

    public BaseDomainEvent(T data) {
        this.data = data;
    }

}
