package com.ec.crm.constant;

import lombok.Getter;

/**
 * 公共错误码定义
 *
 * @author liujiang
 */
public enum CommonError {
    ERROR_BAD_REQUEST(400, "请求错误"),
    ERROR_UNAUTHORIZED(401, "用户认证失败"),
    ERROR_FORBIDDEN(403, "拒绝访问"),
    ERROR_NOT_FOUND(404, "资源未找到"),
    ERROR_SERVER_ERROR(500, "服务器内部错误"),

    ERROR_NET_TIMEOUT(600, "内部系统访问超时"),
    ERROR_EXTERNAL_NET_TIMEOUT(601, "外部系统访问超时"),
    ERROR_REQUEST_PARAM(602, "请求参数缺失"),
    ERROR_SESSION_INVALID(603, "登录过期，请重新登录"),


    /**
     * 1000-1099，用户相关错误
      */
    ERROR_OPERATE_FAIL(1000, "操作失败"),
    ERROR_USER_DUP(1001, "该用户名已存在，不允许重复添加"),
    ERROR_ROLE_DUP(1002, "该角色已存在，不允许重复添加"),
    ERROR_INSTITUTION_DUP(1003, "该机构已存在，不允许重复添加");
    /**
     * 错误码
     */
    @Getter
    private int code;
    /**
     * 错误信息
     */
    @Getter
    private String errorMsg;

    CommonError(int code, String error) {
        this.code = code;
        this.errorMsg = error;
    }
}
