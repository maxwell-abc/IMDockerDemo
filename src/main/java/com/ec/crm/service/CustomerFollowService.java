package com.ec.crm.service;

import com.ec.common.db.fi.po.CustomerFollowView;
import com.ec.common.db.fi.po.CustomerSeaSaleVo;
import com.ec.common.db.fi.po.CustomerSeaSales;
import com.ec.crm.bean.vo.CustomerFollowMapVo;

import java.util.List;

public interface CustomerFollowService {
    int addFollow(CustomerSeaSales customerSeaSales);
    int deleteFollow(CustomerSeaSales customerSeaSales);
    int updateFollow(CustomerSeaSales customerSeaSales);
    List<CustomerSeaSaleVo> selectFollowById(CustomerFollowView customerFollowView);
    CustomerFollowMapVo selectFollowByLike(CustomerFollowView customerFollowView);
}
