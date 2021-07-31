package com.ec.crm.service;


import com.ec.common.db.fi.po.CustomerSea;
import com.ec.common.db.fi.po.CustomerSeaPlus;
import com.ec.common.db.fi.po.CustomerSeaView;
import com.ec.crm.bean.vo.CustomerSeaMapVo;


import java.util.List;

public interface CustomerSeasService {


    List<CustomerSea> get();
    long insertSelective(CustomerSea record);
    int updateByPrimaryKey(CustomerSeaPlus customerSeaPlus);
    int deleteByPrimaryKey(Long id);
    CustomerSeaMapVo slectInfoLike(CustomerSeaView customerSeaView);

    int addBulkCunstomerSea(List<CustomerSea> customerSeas);
    List<CustomerSea> selectInfoByStatus(CustomerSea customerSea);

    CustomerSeaMapVo selectNoSaleInfo(CustomerSeaView customerSeaView);

    int IsProfile(CustomerSeaPlus customerSeaPlus);
}
