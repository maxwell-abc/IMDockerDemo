package com.ec.crm.service.impl;


import com.ec.common.db.fi.mapper.custom.CustomCustomerSeaSaleMapper;
import com.ec.common.db.fi.po.CustomerSeaSale;
import com.ec.crm.bean.view.CustomerSeaDis;
import com.ec.crm.service.CustomerSeaSaleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "CustomerSeaSaleService")
public class CustomerSeaSaleServiceImpl implements CustomerSeaSaleService {

    @Resource
    CustomCustomerSeaSaleMapper customCustomerSeaSaleMapper;

    @Override
    public int addSaleSea(CustomerSeaDis customerSeaDis){

        if(customerSeaDis.getSaleId()==null || customerSeaDis.getSaleId()== "" ||
                customerSeaDis.getSeaId()==null || customerSeaDis.getSeaId()==""){
            return 2;
        }
        int flag =0;
        String[] arraySeaid = customerSeaDis.getSeaId().split(",");
        String[] arraySaleid = customerSeaDis.getSaleId().split(",");
        int[] intSeaid = new int[arraySeaid.length];
        int[] intSaleid = new int[arraySaleid.length];

        for (int i = 0; i < arraySeaid.length; i++) {
            intSeaid[i] = Integer.parseInt(arraySeaid[i]);
        }
        for (int i = 0; i < arraySaleid.length; i++) {
            intSaleid[i] =Integer.parseInt(arraySaleid[i]);
        }
        int scrollNum = 0;
        for (int i = 0; i < intSeaid.length; i++) {
            if(customCustomerSeaSaleMapper.isExist(intSeaid[i])!=null){
                continue;
            }else {
                CustomerSeaSale customerSeaSale=new CustomerSeaSale();
                customerSeaSale.setSaleId(intSaleid[scrollNum]);
                customerSeaSale.setSeaId(intSeaid[i]);
                customCustomerSeaSaleMapper.add(customerSeaSale);
                //修改状态
                //customCustomerSeaSaleMapper.updateStatus(intSeaid[i]);
                flag=1;
                scrollNum++;
                if(scrollNum>=intSaleid.length){
                    scrollNum=0;
                }
            }

        }
        return flag;
    }

    @Override
    public int getCountSale(CustomerSeaSale customerSeaSale){

        return customCustomerSeaSaleMapper.countSale(customerSeaSale);
    }

}
