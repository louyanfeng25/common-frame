package com.baiyan.common.interaction.handler;

import com.baiyan.common.base.exception.AuthException;
import com.baiyan.common.base.exception.ServiceException;
import com.baiyan.common.base.exception.ValidationException;
import com.baiyan.common.base.result.BaseResult;
import com.baiyan.common.base.result.Result;
import com.baiyan.common.base.utils.StringUtil;
import com.baiyan.common.interaction.config.message.BaiyanMessageSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * 全局默认异常处理
 * 业务应用通过继承此类的方式进行异常拦截处理
 *
 * @author baiyan
 * @date 2021/02/21
 */
@Slf4j
public class GlobalExceptionHandler {

    @Autowired
    private BaiyanMessageSource messageSource;

    @ExceptionHandler(ConstraintViolationException.class)
    public Object handle(ConstraintViolationException ex) {
        log.error("入参校验异常",ex);
        StringBuilder errorCode = new StringBuilder();
        StringBuilder errorMessage = new StringBuilder();
        ex.getConstraintViolations()
                .stream()
                .forEach(error -> {
                    if (StringUtil.isNotBlank(errorCode.toString())) {
                        errorCode.append(",");
                    }
                    errorCode.append(error.getMessageTemplate());
                    if (StringUtil.isNotBlank(errorMessage.toString())) {
                        errorMessage.append(",");
                    }
                    errorMessage.append(error.getMessage());
                });
        return Result.error(errorCode.toString(), errorMessage.toString());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handle(MethodArgumentNotValidException ex) {
        log.error("方法参数校验异常",ex);
        StringBuilder errorCode = new StringBuilder();
        StringBuilder errorMessage = new StringBuilder();
        buildBindingResult(errorCode, errorMessage, ex.getBindingResult());
        return Result.error(errorCode.toString(), errorMessage.toString());
    }

    @ExceptionHandler(ValidationException.class)
    public Object handle(ValidationException ex) {
        log.error("业务参数校验异常",ex);
        String errorMessage = messageSource.getMessage(ex.getErrorCode(), ex.getMessage(), ex.getParams());
        return Result.error(ex.getErrorCode(), errorMessage);
    }

    @ExceptionHandler(ServiceException.class)
    public Object handle(ServiceException ex) {
        return Result.error(ex.getErrorCode(), ex.getMessage());
    }

    @ExceptionHandler(Throwable.class)
    public Object handle(Throwable ex) {
        log.error("全局异常", ex);
        return Result.error(BaseResult.SYSTEM_ERROR);
    }

    @ExceptionHandler(AuthException.class)
    public Object handle(AuthException ex) {
        log.error("鉴权异常", ex);
        return Result.authError();
    }

    /**
     * 获取国际化数据
     * @param messageTemplate 消息模板
     * @return
     */
    private String getFromMessageTemplate(String messageTemplate) {
        if(StringUtil.isBlank(messageTemplate)){
            return null;
        }
        if (messageTemplate.length() < 2) {
            return null;
        }
        return messageTemplate.substring(1, messageTemplate.length() - 1);
    }

    /**
     * 构建并绑定返回结果
     * @param errorCode 错误code
     * @param errorMessage 国际化错误信息
     * @param bindingResult 需要处理的错误信息
     */
    private void buildBindingResult(StringBuilder errorCode, StringBuilder errorMessage, BindingResult bindingResult) {
        List<ObjectError> errors = bindingResult.getAllErrors();
        errors
                .stream()
                .forEach(error -> {
                    if (error.contains(ConstraintViolation.class)) {
                        ConstraintViolation constraintViolation = error.unwrap(ConstraintViolation.class);
                        if (errorCode.length() > 0) {
                            errorCode.append(",");
                        }
                        errorCode.append(getFromMessageTemplate(constraintViolation.getMessageTemplate()));
                    }
                    if (errorMessage.length() > 0) {
                        errorMessage.append(",");
                    }
                    String errorInfo = messageSource.getMessage(getFromMessageTemplate(error.getDefaultMessage()), null, (Object) null);
                    errorMessage.append(errorInfo);
                });
    }



}
