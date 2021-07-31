package com.ec.crm.controller;

import com.ec.common.db.auth.po.DictionaryStatus;
import com.ec.common.db.fi.po.CustomerMySale;
import com.ec.common.db.fi.po.CustomerSaleView;
import com.ec.common.db.fi.po.CustomerSales;
import com.ec.crm.bean.ResponseJson;
import com.ec.crm.bean.view.DictionView;
import com.ec.crm.bean.vo.CustomerSaleMapVo;
import com.ec.crm.constant.Constant;
import com.ec.crm.service.CustomerSaleService;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(value = "/customersale")
public class CustomerSaleController {

    @Autowired
    CustomerSaleService customerSaleService;

    @RequestMapping(value = "/query-like",method = RequestMethod.POST)
    public ResponseJson selectByType(@RequestBody CustomerSaleView customerSaleView)throws IOException{

        CustomerSaleMapVo customerSalesList = customerSaleService.selectByType(customerSaleView);
        if (customerSalesList!=null){

            return new ResponseJson(Constant.SUCCESS_CODE,customerSalesList);
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"查询失败");

        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseJson updateSale(@RequestBody CustomerMySale customerMySale)throws IOException{
        int flag =customerSaleService.updateSale(customerMySale);
        if (flag==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"修改成功");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"修改失败");
        }

    }
    @RequestMapping(value="/delete",method = RequestMethod.POST)
    public ResponseJson deleteSale(@RequestBody CustomerMySale customerMySale)throws IOException{
        int flag = customerSaleService.deleteSale(customerMySale);
        if(flag==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"删除成功");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"删除失败");
        }

    }

    //获取业务员服务信息
    @RequestMapping(value = "/query-diction",method = RequestMethod.POST)
    public ResponseJson selectDiction(@RequestBody DictionView dictionView) throws  IOException{
        List<DictionaryStatus> list = customerSaleService.selectDicByname(dictionView);
        if(list!=null){
            return new ResponseJson(Constant.SUCCESS_CODE,list);
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"获取字典失败");
        }
    }
}
