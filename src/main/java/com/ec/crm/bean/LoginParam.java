package com.ec.crm.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * 登录api请求参数
 *
 * @author liujiang
 */
@Data
public class LoginParam {
    @NotBlank
    private String account;
    private String password;
    private int userId;
//    private int cid;
}
