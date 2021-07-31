package com.ec.crm.controller;

import com.ec.common.db.fi.po.CustomerSeaSale;
import com.ec.crm.bean.ResponseJson;
import com.ec.crm.bean.view.CustomerSeaDis;
import com.ec.crm.constant.Constant;
import com.ec.crm.service.CustomerSeaSaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "sea-sale")
public class CustomerSeaSaleController {
    @Autowired
    CustomerSeaSaleService customerSeaSaleService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ResponseJson addSaleSea(@RequestBody CustomerSeaDis customerSeaDis)throws IOException {
        int flag =customerSeaSaleService.addSaleSea(customerSeaDis);
        if (flag ==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"添加成功！");
        }else if (flag==2){
            return new ResponseJson(Constant.EMPTY_CODE,"入参有空值");
        }else
        {
            return new ResponseJson(Constant.FAIL_CODE,"添加失败！(所有公海已被分配过业务员)");
        }
    }

    @RequestMapping(value = "get-sale-count",method = RequestMethod.POST)
    public ResponseJson getSaleCount(@RequestBody CustomerSeaSale customerSeaSale)throws IOException{

        int flag = customerSeaSaleService.getCountSale(customerSeaSale);

        return new ResponseJson(Constant.SUCCESS_CODE,flag);




    }
}
