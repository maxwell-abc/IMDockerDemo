package com.ec.crm.bean.vo;

import lombok.Data;

import java.util.Date;

@Data
public class OperateLogVo {

    private String systemId;
    private String username;
    private Date time;

    private Date startTime;
    private Date endTime;
    private String type;
    private Integer status;

    private int index;
    private int pageSize;


}
