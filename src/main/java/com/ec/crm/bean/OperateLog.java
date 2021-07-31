package com.ec.crm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLog {
    private Integer id;
    private String ip;
    private Date time;
    private Integer status;
    private String place;
    private String browserType;
    private String object;
    private String type;
    private String note;
    private Integer operatorId;
    private Integer systemId;
    private Date createTime;
    private Date updateTime;

}
