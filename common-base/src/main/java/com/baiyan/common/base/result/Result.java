package com.baiyan.common.base.result;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回实体
 *
 * @author baiyan
 * @time 2020/11/13 13:18
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class Result<T> extends BaseResult {

    private T data;

    public Result() {
    }

    public Result(Integer code, String message, T data) {
        super(code, message);
        this.data = data;
    }

    public Result(Integer code, String errorCode, String message, T data) {
        super(code, errorCode, message);
        this.data = data;
    }

    public boolean success() {
        return CODE_SUCCESS.equals(getCode());
    }

    public boolean systemFatal() {
        return CODE_SYSTEM_ERROR.equals(getCode());
    }

    public static Result<Object> ok() {
        return new Result<>(CODE_SUCCESS, "", null);
    }

    public static Result<Object> ok(String message) {
        return new Result<>(CODE_SUCCESS, message, null);
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(CODE_SUCCESS, MESSAGE_SUCCESS, data);
    }

    public static <T> Result<T> success(T data, String message) {
        return new Result<>(CODE_SUCCESS, message, data);
    }

    public static Result<Object> error(Integer code, String message) {
        return new Result<>(code, message, null);
    }

    public static Result<Object> error(Integer code, String errorCode, String message) {
        return new Result<>(code, errorCode, message, null);
    }

    public static Result<Object> error(Integer code, String message, Object data) {
        return new Result<>(code, message, data);
    }

    public static Result<Object> error(Integer code, String errorCode, String message, Object data) {
        return new Result<>(code, errorCode, message, data);
    }

    public static Result<Object> clientError() {
        return new Result<>(CODE_CLIENT_ERROR, "", null);
    }

    public static Result<Object> clientError(String message) {
        return new Result<>(CODE_CLIENT_ERROR, message, null);
    }

    public static Result<Object> clientError(Throwable throwable) {
        return new Result<>(CODE_CLIENT_ERROR, throwable.getMessage(), null);
    }

    public static Result<Object> clientError(String errorCode, String message) {
        return new Result<>(CODE_CLIENT_ERROR, errorCode, message, null);
    }

    public static Result<Object> systemError() {
        return new Result<>(CODE_SYSTEM_ERROR, "", null);
    }

    public static Result<Object> systemError(String message) {
        return new Result<>(CODE_SYSTEM_ERROR, message, null);
    }

    public static Result<Object> systemError(Throwable throwable) {
        return new Result<>(CODE_SYSTEM_ERROR, throwable.getMessage(), null);
    }

}

