package com.baiyan.common.base.result;

import lombok.Data;

/**
 * 基础返回实体
 *
 * @author baiyan
 * @time 2020/11/13 13:17
 */
@Data
public class BaseResult {
    /**
     * httpCode
     */
    private Integer code;

    /**
     * 业务code
     */
    private String errorCode;

    /**
     * 业务信息
     */
    private String message;

    /**
     * 链路id
     */
    private String traceId;

    public BaseResult() {
    }

    public BaseResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseResult(Integer code, String errorCode, String message) {
        this.code = code;
        this.errorCode = errorCode;
        this.message = message;
    }

    protected static final Integer CODE_SUCCESS = 200;

    protected static final Integer CODE_SYSTEM_ERROR = 500;

    protected static final Integer CODE_CLIENT_ERROR = 400;

    protected static final String MESSAGE_SUCCESS = "请求成功";

}
