package com.ec.crm.bean.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserNumVo {
    private int userId;
    private Timestamp updateTime;
    private String username;
    private String account;
}
