package com.ec.crm.service;

import com.ec.common.db.fi.po.CustomerSeaSale;
import com.ec.crm.bean.view.CustomerSeaDis;

public interface CustomerSeaSaleService {

    int addSaleSea(CustomerSeaDis customerSeaDis);

    int getCountSale(CustomerSeaSale customerSeaSale);

}
