package com.ec.crm.bean;

import com.ec.crm.constant.CommonError;
import com.ec.crm.constant.Constant;
import lombok.Data;

/**
 * @author liujiang
 */
@Data
public class BaseResult<T> {
    /**
     * 状态码
     */
    private int code;
    /**
     * 内容
     */
    private T content;

    private String traceId;

    public BaseResult(int code, T data) {
        this.code = code;
        this.content = data;
    }

    public static BaseResult success(Object data) {
        return new BaseResult(Constant.SUCCESS_CODE, data);
    }

    public static BaseResult fail(int code, String msg) {
        return new BaseResult(code, msg);
    }

    public static BaseResult fail(CommonError error) {
        return new BaseResult(error.getCode(), error.getErrorMsg());
    }

    public static BaseResult fail(CommonError error, Object data) {
        return new BaseResult(error.getCode(), data);
    }
}

