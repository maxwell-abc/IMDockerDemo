package com.ec.crm.bean.vo;

import lombok.Data;

@Data
public class CustomerSaleVo {
    private int id;
    private String UserId;

    private String type;
    private String typeStatus;

    private String address;
    private String addressStatus;

    private String capital;
    private String capitalStatus;

    private String scope;
    private String scopeStatus;

    private String note;
    private String username;
    private String tel;
    private String email;
}
