package com.ec.crm.service;
import com.ec.common.db.cas.po.SubSystemInfo;
import com.ec.common.db.fi.po.CustomerProfile;
import com.ec.common.db.fi.po.CustomerProfileView;
import com.ec.crm.bean.vo.CustomerProfileMapVo;

import java.util.List;


public interface CustomerProfileService {

     CustomerProfileMapVo selectByLike(CustomerProfileView profileView);
     int updateByKey(CustomerProfile record);
     int deleteByKey(CustomerProfile record);
     int insert(CustomerProfile record);
}
