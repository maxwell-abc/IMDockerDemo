package com.ec.crm.service.impl;

import com.ec.common.db.auth.mapper.DictionStatusMapper;
import com.ec.common.db.cas.po.SubSystemInfo;


import com.ec.common.db.fi.mapper.CustomerProfileMapper;
import com.ec.common.db.fi.mapper.custom.CustomCustomerProfileMapper;
import com.ec.common.db.fi.mapper.custom.CustomCustomerSeaMapper;
import com.ec.common.db.fi.po.CustomerProfile;
import com.ec.common.db.fi.po.CustomerProfileView;
import com.ec.common.db.fi.po.CustomerProfileVo;
import com.ec.crm.bean.vo.CustomerProfileMapVo;
import com.ec.crm.service.CustomerProfileService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@Service(value = "CustomerProfileService")
public class CustomerProfileImpl implements CustomerProfileService {


    @Resource
    CustomerProfileMapper customerProfileMapper;

    @Resource
    CustomCustomerProfileMapper customCustomerProfileMapper;


    @Resource
    CustomCustomerSeaMapper customCustomerSeaMapper;

    @Resource
    DictionStatusMapper dictionStatusMapper;

    @Override
    public CustomerProfileMapVo selectByLike(CustomerProfileView profileView){

        Page<CustomerProfileVo> page = PageHelper.startPage(profileView.getIndex(),profileView.getPageSize())
                .doSelectPage(()-> customCustomerProfileMapper.selectInfoLike(profileView));


        for (int i = 0; i < page.size(); i++) {
            String totalType="";
            String totalAddress="";
            String totalCapital="";
            String totalScope="";
        //获取企业类型
        if (page.getResult().get(i).getCompanyType()!=null &&page.getResult().get(i).getCompanyType().length()>0){
            page.getResult().get(i).setTypeStatus(page.getResult().get(i).getCompanyType());
            String[] type= page.getResult().get(i).getCompanyType().split(",");
            int[] intType = new int[type.length];
            for (int j = 0; j < type.length; j++) {
                try {
                    intType[j] = Integer.parseInt(type[j]);
                    if (totalType!=""){
                        totalType=totalType+","+dictionStatusMapper.selectType(intType[j]).getNote();
                    }else {
                        totalType=dictionStatusMapper.selectType(intType[j]).getNote();
                    }
                } catch (NumberFormatException e){
                    e.printStackTrace();
                }
            }
        }

            //获取地址信息
            if (page.getResult().get(i).getAddress() != null && page.getResult().get(i).getAddress().length()>0){
                page.getResult().get(i).setAddressStatus(page.getResult().get(i).getAddress());
                String[] address = page.getResult().get(i).getAddress().split(",");
                int[] intaddress = new int[address.length];
                for (int j = 0;  j< address.length; j++) {
                    try{
                        intaddress[j] = Integer.parseInt(address[j]);
                        if (totalAddress!=""){
                            totalAddress = totalAddress+","+dictionStatusMapper.selectAddress(intaddress[j]).getNote();
                        }else {
                            totalAddress=dictionStatusMapper.selectAddress(intaddress[j]).getNote();
                        }
                    } catch (NumberFormatException e){
                        e.printStackTrace();
                    }

                }
            }
            //获取资本信息
            if (page.getResult().get(i).getCapital()!=null && page.getResult().get(i).getCapital().length()>0){
                page.getResult().get(i).setCapitalStatus(page.getResult().get(i).getCapital());
                String[] capital = page.getResult().get(i).getCapital().split(",");
                int[] intCapital = new int[capital.length];
                for (int j = 0; j < capital.length; j++) {
                    try {
                        intCapital[j] = Integer.parseInt(capital[j]);
                        if (totalCapital!=""){
                            totalCapital = totalCapital+","+dictionStatusMapper.selectCapital(intCapital[j]).getNote();
                        }else {
                            totalCapital = dictionStatusMapper.selectCapital(intCapital[j]).getNote();
                        }
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                }
            }
            //获取营业范围
            if (page.getResult().get(i).getScope()!=null && page.getResult().get(i).getScope().length()>0){
                page.getResult().get(i).setScopeStatus(page.getResult().get(i).getScope());
                String[] scope= page.getResult().get(i).getScope().split(",");
                int[] intScope = new int[scope.length];
                for (int j = 0; j < scope.length; j++) {
                    try {
                        intScope[j] = Integer.parseInt(scope[j]);
                        if (totalScope!=""){
                            totalScope = totalScope+","+dictionStatusMapper.selectScope(intScope[j]).getNote();
                        }else {
                            totalScope = dictionStatusMapper.selectScope(intScope[j]).getNote();
                        }
                    }catch (NumberFormatException e){
                        e.printStackTrace();
                    }
                }
            }
        page.getResult().get(i).setCompanyType(totalType);
        page.getResult().get(i).setAddress(totalAddress);
        page.getResult().get(i).setCapital(totalCapital);
        page.getResult().get(i).setScope(totalScope);
        }


        CustomerProfileMapVo result =new CustomerProfileMapVo();
        result.setData(page.getResult());
        result.setTotal(page.getTotal());

        return result ;
    }
    @Override
    public int updateByKey(CustomerProfile record){

        return customerProfileMapper.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByKey(CustomerProfile record){

        int flag =0;
        CustomerProfile tmp = customerProfileMapper.selectByPrimaryKey(record.getId());
        long seaId = tmp.getSeaId();
        flag = customerProfileMapper.deleteByPrimaryKey(record.getId());
//        if (flag==1){
//            customCustomerSeaMapper.updateStatus(seaId);
//        }
        return flag;
    }

    @Override
    public int insert(CustomerProfile record){

        customCustomerSeaMapper.updateStatus(record.getSeaId());
        return customerProfileMapper.insert(record);
    }


    //客户档案添加
}
