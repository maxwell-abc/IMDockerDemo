package com.ec.crm.bean.vo;


import lombok.Data;

import java.util.List;

@Data
public class CustomerSaleMapVo {
    private List<CustomerSaleVo> data;
    private long total;
}
