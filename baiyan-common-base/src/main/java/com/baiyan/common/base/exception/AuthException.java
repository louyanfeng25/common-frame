package com.baiyan.common.base.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 鉴权错误异常
 *
 * @author baiyan
 * @time 2022/04/02
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class AuthException extends RuntimeException {

    private String errorMessage;

    /**
     * 用给定的异常信息构造新实例。
     * @param errorMessage 异常信息。
     */
    public AuthException(String errorMessage) {
        super((String)null);
        this.errorMessage = errorMessage;
    }

}
