package com.baiyan.common.service.event.demo;

import com.baiyan.common.base.model.event.BaseDomainEvent;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 领域事件示例
 *
 * @author baiyan
 * @date 2021/02/21
 */
@NoArgsConstructor
@Getter
public class DemoEvent extends BaseDomainEvent<String> {

    private String demoField;

    public DemoEvent(String data,String demoField) {
        super(data);
        this.demoField = demoField;
    }

}