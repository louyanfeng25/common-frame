package com.baiyan.common.rpc.config;


/**
 * 默认异常处理
 *
 * @author baiyan
 * @time 2021/02/20
 */
public class DefaultExceptionWrapper implements ExceptionWrapper {
    @Override
    public Exception wrap(Exception wrapped) {
        return wrapped;
    }
}
