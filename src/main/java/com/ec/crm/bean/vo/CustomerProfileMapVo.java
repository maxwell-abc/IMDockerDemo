package com.ec.crm.bean.vo;


import com.ec.common.db.fi.po.CustomerProfileVo;
import lombok.Data;

import java.util.List;

@Data
public class CustomerProfileMapVo {

    private List<CustomerProfileVo> data;
    private long total;
}
