package com.ec.crm.advice;

import com.ec.crm.bean.BaseResult;
import com.ec.crm.constant.AuthorizeException;
import com.ec.crm.constant.CommonError;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ValidationException;

/**
 * 全局异常处理Advice
 *
 * @author liujiang
 */
@RestControllerAdvice("com.ec.crm.controller")
@Slf4j
public class ControllerExceptionHandler {

    /**
     * 参数错误
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseResult argumentNotValidException(MethodArgumentNotValidException ex) {
        log.error("Invalid arguments:{}-{}", ex.getClass().getName(), ex.getBindingResult().getFieldError().getDefaultMessage());
        BaseResult fail = BaseResult.fail(CommonError.ERROR_BAD_REQUEST);
        setResult(fail);
        return fail;
    }

    /**
     * 参数错误
     */
    @ExceptionHandler({ValidationException.class, TypeMismatchException.class, HttpMessageNotReadableException.class})
    public BaseResult otherValidException(Exception ex) {
        log.error("Invalid arguments:{}-{}", ex.getClass().getName(), ex.getMessage());
        BaseResult fail = BaseResult.fail(CommonError.ERROR_BAD_REQUEST);
        setResult(fail);
        return fail;
    }

    /**
     * 方法错误
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public BaseResult methodNotSupportedException(Exception ex) {
        log.error("Unsupported methods:{}-{}", ex.getClass().getName(), ex.getMessage());
        BaseResult fail = BaseResult.fail(CommonError.ERROR_BAD_REQUEST);
        setResult(fail);
        return fail;
    }

    /**
     * content-type错误
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public BaseResult contentTypeNotSupportedException(Exception ex) {
        log.error("Unsupported content type:{}-{}", ex.getClass().getName(), ex.getMessage());
        BaseResult fail = BaseResult.fail(CommonError.ERROR_BAD_REQUEST);
        setResult(fail);
        return fail;
    }

    /**
     * 其他错误
     */
    @ExceptionHandler(AuthorizeException.class)
    public BaseResult authorizeException(AuthorizeException ex) {
        log.error("authorize exception ", ex);
        BaseResult fail = BaseResult.fail(ex.getCode(), ex.getErrorMsg());
        setResult(fail);
        return fail;
    }

    /**
     * 其他错误
     */
    @ExceptionHandler(Exception.class)
    public BaseResult uncaughtException(Exception ex) {
        log.error("Unexpected exception", ex);
        BaseResult fail = BaseResult.fail(CommonError.ERROR_SERVER_ERROR);
        setResult(fail);
        return fail;
    }

    private void setResult(BaseResult result) {
        result.setTraceId(MDC.get("X-B3-TraceId"));
    }
}