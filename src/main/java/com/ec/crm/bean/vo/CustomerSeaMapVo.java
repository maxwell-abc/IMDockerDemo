package com.ec.crm.bean.vo;


import lombok.Data;

import java.util.List;

@Data
public class CustomerSeaMapVo {
    private List<CustomerSeaVo> data;
    private long total;
}
