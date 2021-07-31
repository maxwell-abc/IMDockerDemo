package com.ec.crm.bean.vo;

import com.ec.common.db.fi.po.CustomerContacts;
import lombok.Data;

import java.util.List;

@Data
public class CustomerContactMapVo {
    private List<CustomerContacts> data;
    private long total ;
}
