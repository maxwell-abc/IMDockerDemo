package com.ec.crm.controller;




import com.ec.common.db.fi.po.CustomerProfile;
import com.ec.common.db.fi.po.CustomerProfileView;
import com.ec.crm.bean.ResponseJson;

import com.ec.crm.bean.vo.CustomerProfileMapVo;
import com.ec.crm.constant.Constant;
import com.ec.crm.service.CustomerProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/customerprofile")
public class CustomerProfileController {

    @Autowired
    CustomerProfileService customerProfileService;

    @RequestMapping(value = "/query-like",method = RequestMethod.POST)
    public ResponseJson addCustomer(@RequestBody CustomerProfileView customerProfileView) throws IOException{

        CustomerProfileMapVo mapVo = customerProfileService.selectByLike(customerProfileView);

        if(mapVo!=null){
            return new ResponseJson(Constant.SUCCESS_CODE,mapVo);
        }else{
            return new ResponseJson(Constant.FAIL_CODE,"查询客户失败！");
        }
    }

    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public ResponseJson deleteByKey(@RequestBody CustomerProfile customerProfile) throws IOException{
        int flag =customerProfileService.deleteByKey(customerProfile);
        if (flag==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"删除成功！");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"删除失败");
        }
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST)
    public ResponseJson updateByKey(@RequestBody CustomerProfile customerProfile)throws IOException{
        if(customerProfileService.updateByKey(customerProfile)==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"修改成功!");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"修改失败");
        }
    }
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public ResponseJson addProfile(@RequestBody CustomerProfile customerProfile)throws IOException{
        if (customerProfileService.insert(customerProfile)==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"添加成功!");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"添加失败");
        }
    }

}
