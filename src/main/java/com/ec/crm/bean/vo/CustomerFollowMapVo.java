package com.ec.crm.bean.vo;

import com.ec.common.db.fi.po.CustomerSeaSaleVo;
import com.ec.common.db.fi.po.CustomerSeaSales;
import lombok.Data;

import java.util.List;

@Data
public class CustomerFollowMapVo {

    private List<CustomerSeaSaleVo> data;
    private long total;

}
