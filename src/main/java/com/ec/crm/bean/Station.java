package com.ec.crm.bean;

import lombok.Data;

@Data
public class Station {
    private String account;
    private int cId;
    private String password;
    private String institutionName;
    private String roleName;
    private String userName;
    private String userPhone;
    private int dataControlRange;
    private int status;
    private int sex;
    private String entryTime;
}
