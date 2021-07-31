package com.ec.crm.bean.vo;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerSeaVo {
    private Long id;

    private String name;

    private String number;

    private String company;

    private String status;

    private String intention;

    private Long money;

    private String connectName;

    private long connectId;

    private String companyType;

    private String scope;

    private String address;

    private Date createTime;

    private Date updateTime;

    private String userId;

    private int saleId;
    private String saleName;


}
