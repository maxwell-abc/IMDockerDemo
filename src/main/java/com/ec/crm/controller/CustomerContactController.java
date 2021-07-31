package com.ec.crm.controller;

import com.ec.common.db.fi.po.CustomerContactView;
import com.ec.common.db.fi.po.CustomerContacts;
import com.ec.crm.bean.ResponseJson;
import com.ec.crm.bean.vo.CustomerContactMapVo;
import com.ec.crm.constant.Constant;
import com.ec.crm.service.CustomerContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping(value = "/customercontact")
public class CustomerContactController {

    @Autowired
    CustomerContactService customerContactService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseJson addCustomerContact(@RequestBody CustomerContacts customerContacts)throws IOException{

        long flag = customerContactService.insertSelective(customerContacts);
        if(flag==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"添加联系人成功!");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"添加联系人失败！");
        }
    }

    @RequestMapping(value = "/query-like",method = RequestMethod.POST)
    public ResponseJson slectInfoLike(@RequestBody CustomerContactView customerContactView)throws IOException{
        CustomerContactMapVo contactMapVo = customerContactService.selectInfoLike(customerContactView);
        if(contactMapVo!=null){
            return new ResponseJson(Constant.SUCCESS_CODE,contactMapVo);
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"查询联系人失败！");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseJson updateCustomerContact(@RequestBody CustomerContacts customerContacts)throws IOException{
        if(customerContactService.updateByPrimaryKey(customerContacts)==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"修改联系人成功！");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"修改联系人失败！");
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseJson deleteCustomerSea(@RequestBody CustomerContacts customerContacts) throws IOException{
        int result =customerContactService.deleteByPrimaryKey(customerContacts.getId());
        if(result==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"删除成功！");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"删除失败！");
        }
    }
    @RequestMapping(value = "/query-key",method = RequestMethod.POST)
    public ResponseJson selectByKey(@RequestBody CustomerContacts customerContacts)throws IOException{

        CustomerContacts result =customerContactService.selectByKey(customerContacts.getId());

        if (result!=null){
            return new ResponseJson(Constant.SUCCESS_CODE,result);
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"查询联系人失败！");
        }
    }

}
