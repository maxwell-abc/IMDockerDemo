package com.ec.crm.service.impl;

import com.ec.common.db.fi.mapper.CustomerContactsMapper;

import com.ec.common.db.fi.mapper.custom.CustomCustomerContactMapper;
import com.ec.common.db.fi.mapper.custom.CustomCustomerSeaMapper;
import com.ec.common.db.fi.po.CustomerContactView;
import com.ec.common.db.fi.po.CustomerContacts;
import com.ec.common.db.fi.po.CustomerSea;
import com.ec.crm.bean.vo.CustomerContactMapVo;
import com.ec.crm.service.CustomerContactService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Slf4j
@Service(value = "CustomerContactService")
public class CustomerContactServiceImpl implements CustomerContactService {

    @Resource
    CustomerContactsMapper customerContactsMapper;

    @Resource
    CustomCustomerContactMapper customCustomerContactMapper;

    @Resource
    CustomCustomerSeaMapper customCustomerSeaMapper;
    @Override
    public long insertSelective(CustomerContacts record){


        if(record.getSeaId()!=null){
            CustomerSea companyName = customCustomerSeaMapper.selectCompany(record);
            record.setCompany(companyName.getCompany());
        }
        return customerContactsMapper.insert(record);

    }


    @Override
    public CustomerContactMapVo selectInfoLike(CustomerContactView customerContactView){

    Page<CustomerContacts> page = PageHelper.startPage(customerContactView.getIndex(),customerContactView.getPageSize())
            .doSelectPage(()-> customCustomerContactMapper.selectInfoLike(customerContactView));

        CustomerContactMapVo contactMapVo =new CustomerContactMapVo();
        contactMapVo.setTotal(page.getTotal());
        contactMapVo.setData(page.getResult());

        return contactMapVo;
    }

    @Override
    public int updateByPrimaryKey(CustomerContacts record){

        customCustomerSeaMapper.updateContact(record);
        if(record.getSeaId()!=null){
            CustomerSea companyName = customCustomerSeaMapper.selectCompany(record);
            record.setCompany(companyName.getCompany());
        }
        return customerContactsMapper.updateByPrimaryKey(record);

    }

    @Override
    public int deleteByPrimaryKey(Long id){
        return customerContactsMapper.deleteByPrimaryKey(id);

    }
    @Override
    public CustomerContacts selectByKey(Long id){
        return customerContactsMapper.selectByPrimaryKey(id);
    }
}
