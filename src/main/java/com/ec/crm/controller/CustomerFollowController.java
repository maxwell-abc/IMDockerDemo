package com.ec.crm.controller;

import com.ec.common.db.fi.po.CustomerFollowView;
import com.ec.common.db.fi.po.CustomerSeaSaleVo;
import com.ec.common.db.fi.po.CustomerSeaSales;
import com.ec.crm.bean.ResponseJson;
import com.ec.crm.bean.vo.CustomerFollowMapVo;
import com.ec.crm.constant.Constant;
import com.ec.crm.service.CustomerFollowService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.PublicKey;
import java.util.List;

@RestController
@RequestMapping(value = "customerfollow")
public class CustomerFollowController {

    @Autowired
    CustomerFollowService customerFollowService;

    @RequestMapping(value = "add",method = RequestMethod.POST)
    public ResponseJson addFollow(@RequestBody CustomerSeaSales customerSeaSales) throws IOException{
        int flag = customerFollowService.addFollow(customerSeaSales);
        if (flag==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"添加成功");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"添加失败");
        }

    }

    @RequestMapping(value = "delete",method = RequestMethod.POST)
    public ResponseJson deleteFollow(@RequestBody CustomerSeaSales customerSeaSales)throws IOException{
        int flag =customerFollowService.deleteFollow(customerSeaSales);
        if (flag==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"删除成功");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"删除失败");
        }

    }

    @RequestMapping(value = "update",method = RequestMethod.POST)
    public ResponseJson updateFollow(@RequestBody CustomerSeaSales customerSeaSales)throws IOException{
        int flag = customerFollowService.updateFollow(customerSeaSales);
        if (flag==1){
            return new ResponseJson(Constant.SUCCESS_CODE,"修改成功");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"修改失败");
        }

    }

    @RequestMapping(value = "query-follow",method = RequestMethod.POST)
    public ResponseJson selectFollow(@RequestBody CustomerFollowView customerFollowView)throws IOException{
        List<CustomerSeaSaleVo> result=customerFollowService.selectFollowById(customerFollowView);
        if (result!=null){
            return new ResponseJson(Constant.SUCCESS_CODE,result);
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"查询失败");
        }
    }

    @RequestMapping(value = "query-like",method = RequestMethod.POST)
    public ResponseJson selectFollowByLike(@RequestBody CustomerFollowView customerFollowView)throws IOException{
        CustomerFollowMapVo result = customerFollowService.selectFollowByLike(customerFollowView);
        if (result!=null){
            return new ResponseJson(Constant.SUCCESS_CODE,result);
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"查询失败");
        }

    }
}
