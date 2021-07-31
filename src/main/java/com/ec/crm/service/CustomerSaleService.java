package com.ec.crm.service;

import com.ec.common.db.auth.po.DictionaryStatus;
import com.ec.common.db.fi.po.CustomerMySale;
import com.ec.common.db.fi.po.CustomerSaleView;
import com.ec.crm.bean.view.DictionView;
import com.ec.crm.bean.vo.CustomerSaleMapVo;


import java.util.List;

public interface CustomerSaleService {


    CustomerSaleMapVo selectByType(CustomerSaleView customerSaleView);
    int updateSale(CustomerMySale customerMySale);
    int deleteSale(CustomerMySale customerMySale);
    List<DictionaryStatus> selectDicByname(DictionView dictionView);

}
