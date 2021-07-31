package com.ec.crm.bean;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Data
public class User {
    private Integer userId;
    private String username;
    private String password;
    private String email;
    private String tel;
    private String account;
    private String institutionName;

    private int institutionId;
    private int positionId;

    private String positionName;
    private Date entry_time;
    private int status;
    private String note;
    private Timestamp createTime;
    private Timestamp updateTime;
}
