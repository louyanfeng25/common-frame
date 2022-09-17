package com.baiyan.common.rpc.config.feign;

import com.baiyan.common.base.exception.ServiceException;
import com.baiyan.common.base.exception.SystemException;
import com.baiyan.common.base.result.Result;
import com.baiyan.common.rpc.config.ExceptionWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.Objects;

/**
 * 请求错误编码解析
 * 服务端能处理的异常返回都是500，如果是其他的，那么就是无法处理的。
 * @author baiyan
 * @date 2021/03/05
 */
@Slf4j
@AllArgsConstructor
public class FeignErrorDecoder implements ErrorDecoder {

    private final ObjectMapper objectMapper;

    private final ExceptionWrapper exceptionWrapper;

    @Override
    public Exception decode(String methodKey, Response response) {

        if (HttpStatus.INTERNAL_SERVER_ERROR.value() != response.status()) {
            String message = MessageFormat.format("响应异常, code:{0}, reason:{1}", response.status(), response.reason());
            throw new SystemException(message);
        }

        Result result = decodeResponseAsResult(methodKey, response);

        // 如果Result为空，那么返回系统异常
        if (Objects.isNull(result)) {
            log.debug("响应异常, response=====> status:{}, reason:{}, 可能由于未经过GlobalExceptionHandler处理", response.status(), response.reason());
            return wrap(new SystemException(response.reason()));
        }
        if (result.systemFail()) {
            return wrap(new SystemException(result.getMessage()));
        }
        // 否则就返回服务业务异常
        return wrap(new ServiceException(result.getErrorCode(), result.getMessage()));
    }

    private Result decodeResponseAsResult(String methodKey, Response response) {
        Result result = null;
        try {
            // 如果时500的响应吗，尝试解析响应内容为Result
            result = objectMapper.readValue(response.body().asInputStream(), Result.class);
        } catch (IOException e) {
            log.error("解析feign错误响应失败methodKey:{},response:{}", methodKey, response);
            log.error("异常信息", e);
        }
        return result;
    }

    private Exception wrap(Exception exception) {
        return exceptionWrapper.wrap(exception);
    }

    private final ErrorDecoder defaultErrorDecoder = new Default();

}
