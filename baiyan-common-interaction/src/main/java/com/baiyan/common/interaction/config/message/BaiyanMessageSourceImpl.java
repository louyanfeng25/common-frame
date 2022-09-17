package com.baiyan.common.interaction.config.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * 国际化数据处理配置
 *
 * @author baiyan
 * @date 2021/02/01
 */
@Component
public class BaiyanMessageSourceImpl implements BaiyanMessageSource {

    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String code, Object... params) {
        return messageSource.getMessage(code, params, LocaleContextHolder.getLocale());
    }

    @Override
    public String getMessage(String code, String defaultMessage, Object... params) {
        return messageSource.getMessage(code, params, defaultMessage, LocaleContextHolder.getLocale());
    }
}
