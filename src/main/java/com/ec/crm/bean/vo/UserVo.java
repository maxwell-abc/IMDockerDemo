package com.ec.crm.bean.vo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserVo {
    private int userId;
    private Timestamp updateTime;
    private String username;
    private String email;
    private String tel;
    private String account;
}
