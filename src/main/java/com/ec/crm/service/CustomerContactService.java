package com.ec.crm.service;

import com.ec.common.db.fi.po.CustomerContactView;
import com.ec.common.db.fi.po.CustomerContacts;
import com.ec.crm.bean.vo.CustomerContactMapVo;

public interface CustomerContactService {

    long insertSelective(CustomerContacts record);
    CustomerContactMapVo selectInfoLike(CustomerContactView customerContactView);
    int updateByPrimaryKey(CustomerContacts record);
    int deleteByPrimaryKey(Long id);

    CustomerContacts selectByKey(Long id);
}
