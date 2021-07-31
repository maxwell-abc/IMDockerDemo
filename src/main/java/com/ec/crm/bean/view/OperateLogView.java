package com.ec.crm.bean.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OperateLogView {
    private Integer id;
    private Integer  systemId;//int system_id;
    private String systemName;
    private String type;
    private String username;
    private String ip;
    private String place;
    private Integer status;
    private Date time;
    private String note;

}
