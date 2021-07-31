package com.ec.crm.service.impl;




import com.ec.common.db.auth.mapper.UserMapper;
import com.ec.common.db.fi.mapper.CustomerContactsMapper;
import com.ec.common.db.fi.mapper.CustomerSeaMapper;
import com.ec.common.db.fi.mapper.custom.CustomCustomerSeaMapper;

import com.ec.common.db.fi.mapper.custom.CustomCustomerSeaSaleMapper;
import com.ec.common.db.fi.po.*;


import com.ec.crm.bean.vo.CustomerSeaMapVo;
import com.ec.crm.bean.vo.CustomerSeaVo;
import com.ec.crm.service.CustomerSeasService;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.RequestBody;


@Slf4j
@Service(value = "CustomerSeasService")
public class CustomerSeasServiceImpl implements CustomerSeasService {

    @Resource
    CustomCustomerSeaMapper customerCustomerSeaMapper;

    @Resource
    CustomerSeaMapper customerSeaMapper;

    @Resource
    CustomerContactsMapper customerContactsMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    CustomCustomerSeaSaleMapper customCustomerSeaSaleMapper;

    @Resource
    CustomCustomerSeaMapper customCustomerSeaMapper;

    @Override
    public List<CustomerSea> get(){
        List<CustomerSea>   customerSea = customerCustomerSeaMapper.getCustomerSea();
        return customerSea;
    }

    @Override
    public long insertSelective(CustomerSea record){

        if(customerCustomerSeaMapper.isExist(record)!=null){
            return 0;
        }else {
            record.setStatus(Long.valueOf(0));
            return customerSeaMapper.insert(record);
        }
    }
    @Transactional(rollbackFor = Exception.class,transactionManager = "fiTransactionManager")
    @Override
    public int updateByPrimaryKey(CustomerSeaPlus record){
        int flag=0;
        try {
           int seaf   = customCustomerSeaMapper.updateSeaPlus(record);
           int salef  = customCustomerSeaSaleMapper.updateSaleId(record);
           if (seaf==1 && salef ==1){
               flag=1;
           }
        }catch (Exception e){
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();

        }

        return flag;
    }

    @Override
    public int deleteByPrimaryKey(Long id){
        return customerSeaMapper.deleteByPrimaryKey(id);

    }
    @Override
    public CustomerSeaMapVo slectInfoLike(CustomerSeaView customerSeaView){
        Page<CustomerSea> page = PageHelper.startPage(customerSeaView.getIndex(),customerSeaView.getPageSize())
                .doSelectPage(()-> customerCustomerSeaMapper.selectInfoLike(customerSeaView));

        ArrayList<CustomerSeaVo> customerSeaVo =new ArrayList<>() ;

        for (int i = 0; i < page.size(); i++) {
            CustomerSeaVo vo = new CustomerSeaVo();
            vo.setId(page.getResult().get(i).getId());
            //由sea_id去找sale_id 然后连表找到sale_id的很多信息
            vo.setName(page.getResult().get(i).getName());
            vo.setNumber(page.getResult().get(i).getNumber());
            vo.setCompany(page.getResult().get(i).getCompany());

            if(customCustomerSeaSaleMapper.getSaleIdBySea(vo.getId().intValue())!=null){
                vo.setUserId(customCustomerSeaSaleMapper.getSaleIdBySea(vo.getId().intValue()));
                vo.setSaleName(userMapper.selectName(vo.getUserId()));
                vo.setSaleId(customCustomerSeaSaleMapper.getIntSaleIdBySea(vo.getId().intValue()));
            }

            //  getSaleIDbySea  获得业务员id

            //找业务员名字按照业务员id
            switch ( page.getResult().get(i).getStatus().intValue()){
                case 0:
                    vo.setStatus("否");
                    break;
                case 1:
                    vo.setStatus("是");
                    break;

                    default:
                        vo.setStatus("否");

            }
            switch (page.getResult().get(i).getIntention().intValue()){
                case 0:
                    vo.setIntention("无购买意向");
                    break;
                case 1:
                    vo.setIntention("尚不清楚");
                    break;
                case 2:
                    vo.setIntention("有购买意向");
                    break;
                    default:
                        vo.setIntention("尚不清楚");
            }
            vo.setMoney(page.getResult().get(i).getMoney());
            try{
                page.getResult().get(i).getConnectId();
                vo.setConnectName(customerContactsMapper.selectByPrimaryKey(page.getResult().get(i).getConnectId()).getName());
                vo.setConnectId(page.getResult().get(i).getConnectId());
            }catch (NullPointerException a){
                System.out.println("联系人id没有");
            }

            //拿联系人id 去联系人表里找名字

            vo.setCompanyType(page.getResult().get(i).getCompanyType());
            vo.setScope(page.getResult().get(i).getScope());
            vo.setAddress(page.getResult().get(i).getAddress());
            vo.setCreateTime(page.getResult().get(i).getCreateTime());
            vo.setUpdateTime(page.getResult().get(i).getUpdateTime());


            customerSeaVo.add(i,vo);
            customerSeaVo.size();

        }

        CustomerSeaMapVo resultMap = new CustomerSeaMapVo();

        resultMap.setTotal(page.getTotal());
        resultMap.setData(customerSeaVo);
        return resultMap;
    }

    @Override
    public int addBulkCunstomerSea(List<CustomerSea> customerSeas){

        int flag=0;
        for (int i = 0; i < customerSeas.size(); i++) {
            if(customerCustomerSeaMapper.isExist(customerSeas.get(i))!=null){
                continue;
            }else{
                customerSeaMapper.insert(customerSeas.get(i));
                flag=1;
            }
        }
        return flag;
    }

    @Override
    public List<CustomerSea> selectInfoByStatus(CustomerSea customerSea){

        List<CustomerSea> customerSeas = customerCustomerSeaMapper.selectInfoByStatus(customerSea);
        return customerSeas;
    }


    @Override
    public CustomerSeaMapVo selectNoSaleInfo(CustomerSeaView customerSeaView){

        Page<CustomerSea> page = PageHelper.startPage(customerSeaView.getIndex(),customerSeaView.getPageSize())
                .doSelectPage(()-> customerCustomerSeaMapper.selectNoSaleInfo(customerSeaView));
        ArrayList<CustomerSeaVo> customerSeaVo =new ArrayList<>() ;

        for (int i = 0; i < page.size(); i++) {
            CustomerSeaVo vo = new CustomerSeaVo();
            vo.setId(page.getResult().get(i).getId());
            vo.setName(page.getResult().get(i).getName());
            vo.setNumber(page.getResult().get(i).getNumber());
            vo.setCompany(page.getResult().get(i).getCompany());
            switch ( page.getResult().get(i).getStatus().intValue()){
                case 0:
                    vo.setStatus("否");
                    break;
                case 1:
                    vo.setStatus("是");
                    break;

                default:
                    vo.setStatus("否");

            }
            switch (page.getResult().get(i).getIntention().intValue()){
                case 0:
                    vo.setIntention("无购买意向");
                    break;
                case 1:
                    vo.setIntention("尚不清楚");
                    break;
                case 2:
                    vo.setIntention("有购买意向");
                    break;
                default:
                    vo.setIntention("尚不清楚");
            }
            vo.setMoney(page.getResult().get(i).getMoney());
            try{
                page.getResult().get(i).getConnectId();
                vo.setConnectName(customerContactsMapper.selectByPrimaryKey(page.getResult().get(i).getConnectId()).getName());
                vo.setConnectId(page.getResult().get(i).getConnectId());
            }catch (NullPointerException a){
                System.out.println("联系人id没有");
            }

            //拿联系人id 去联系人表里找名字

            vo.setCompanyType(page.getResult().get(i).getCompanyType());
            vo.setScope(page.getResult().get(i).getScope());
            vo.setAddress(page.getResult().get(i).getAddress());
            vo.setCreateTime(page.getResult().get(i).getCreateTime());
            vo.setUpdateTime(page.getResult().get(i).getUpdateTime());


            customerSeaVo.add(i,vo);
            customerSeaVo.size();

        }

        CustomerSeaMapVo resultMap = new CustomerSeaMapVo();

        resultMap.setTotal(page.getTotal());
        resultMap.setData(customerSeaVo);
        return resultMap;
    }

    @Override
    public int IsProfile(CustomerSeaPlus customerSeaPlus){

        CustomerProfileVo customerProfileVo = customCustomerSeaMapper.selectIsProfile(customerSeaPlus);

        if (customerProfileVo!=null){
            return 1;
        }else {
            return 0;
        }

    }
}
