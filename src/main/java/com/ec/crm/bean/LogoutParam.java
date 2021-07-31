package com.ec.crm.bean;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LogoutParam {
    @NotBlank
    private String account;
}
