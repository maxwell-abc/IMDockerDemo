package com.ec.crm.service.impl;

import com.ec.common.db.auth.mapper.UserMapper;
import com.ec.common.db.fi.mapper.CustomerSeaSalesMapper;
import com.ec.common.db.fi.mapper.custom.CustomerFollowMapper;
import com.ec.common.db.fi.po.CustomerFollowView;
import com.ec.common.db.fi.po.CustomerSeaSaleVo;
import com.ec.common.db.fi.po.CustomerSeaSales;
import com.ec.crm.bean.vo.CustomerFollowMapVo;
import com.ec.crm.service.CustomerFollowService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service(value = "CustomerFollowService")
public class CustomerFollowServiceImpl implements CustomerFollowService {

    @Resource
    CustomerSeaSalesMapper customerSeaSalesMapper;

    @Resource
    CustomerFollowMapper customerFollowMapper;

    @Resource
    UserMapper userMapper;

    @Override
    public int addFollow(CustomerSeaSales customerSeaSales){

        if(customerSeaSales.getIntentionCode()!=null){
            int code =customerSeaSales.getIntentionCode().intValue();
            if (code==0){
                customerSeaSales.setIntentionName("无购买意向");
            }else if(code==1){
                customerSeaSales.setIntentionName("尚不清楚");
            }else if(code==2){
                customerSeaSales.setIntentionName("有购买意向");
            }
        }else {
            customerSeaSales.setIntentionCode(Long.valueOf(0));
            customerSeaSales.setIntentionName("无购买意向");
        }

        int flag = customerSeaSalesMapper.insert(customerSeaSales);
        return flag;
    }

    @Override
    public int deleteFollow(CustomerSeaSales customerSeaSales){
        int flag = customerSeaSalesMapper.deleteByPrimaryKey(customerSeaSales.getId());
        return flag;
    }

    @Override
    public int updateFollow(CustomerSeaSales customerSeaSales){

        if(customerSeaSales.getIntentionCode()!=null){
            int code =customerSeaSales.getIntentionCode().intValue();
            if (code==0){
                customerSeaSales.setIntentionName("无购买意向");
            }else if(code==1){
                customerSeaSales.setIntentionName("尚不清楚");
            }else if(code==2){
                customerSeaSales.setIntentionName("有购买意向");
            }
        }else {
            customerSeaSales.setIntentionCode(Long.valueOf(0));
            customerSeaSales.setIntentionName("无购买意向");
        }
        int flag = customerSeaSalesMapper.updateByPrimaryKey(customerSeaSales);
        return flag;
    }

    @Override
    public List<CustomerSeaSaleVo> selectFollowById(CustomerFollowView customerFollowView){
        List<CustomerSeaSaleVo> result =customerFollowMapper.selectFolow(customerFollowView);

        for (int i = 0; i < result.size(); i++) {

            if (result.get(i).getSeaId()!=null){
             result.get(i).setSeaName(customerFollowMapper.selectSeaName(result.get(i).getSeaId().intValue()));
            }
            if (result.get(i).getSalesId()!=null){
                result.get(i).setSaleName(customerFollowMapper.selectSaleName(result.get(i).getSalesId().intValue()));
                result.get(i).setSaleName(userMapper.selectName(result.get(i).getSaleName()));
            }
        }

        return result;

    }

    @Override
    public CustomerFollowMapVo selectFollowByLike(CustomerFollowView customerFollowView){
        Page<CustomerSeaSaleVo> page = PageHelper.startPage(customerFollowView.getIndex(),customerFollowView.getPageSize())
                .doSelectPage(()-> customerFollowMapper.selectFolow(customerFollowView));

        for (int i = 0; i < page.size(); i++) {

            if (page.get(i).getSeaId()!=null){
                page.get(i).setSeaName(customerFollowMapper.selectSeaName(page.get(i).getSeaId().intValue()));
            }
            if (page.get(i).getSalesId()!=null){
                page.get(i).setSaleName(customerFollowMapper.selectSaleName(page.get(i).getSalesId().intValue()));
                page.get(i).setSaleName(userMapper.selectName(page.get(i).getSaleName()));
            }
        }
        CustomerFollowMapVo result = new CustomerFollowMapVo();
        result.setData(page.getResult());
        result.setTotal(page.getTotal());

        return result;

    }


}
