package com.ec.crm.constant;

import lombok.Getter;
import lombok.Setter;

/**
 * @author liujiang
 */
@Getter
@Setter
public class AuthorizeException extends Exception {
    private int code;
    private String errorMsg;

    public AuthorizeException(CommonError error) {
        super(error.getCode() + "|" + error.getErrorMsg());
        this.code = error.getCode();
        this.errorMsg = error.getErrorMsg();
    }

    public AuthorizeException(CommonError error, String errorMsg) {
        super(error.getCode() + "|" + error.getErrorMsg());
        this.code = error.getCode();
        this.errorMsg = errorMsg;
    }
}
