package com.ec.crm.service.impl;

import com.ec.common.db.auth.mapper.DictionStatusMapper;
import com.ec.common.db.auth.mapper.UserMapper;
import com.ec.common.db.auth.po.DictionaryStatus;
import com.ec.common.db.auth.po.User;
import com.ec.common.db.fi.mapper.custom.CustomCustomerSaleMapper;
import com.ec.common.db.fi.po.CustomerMySale;
import com.ec.common.db.fi.po.CustomerSaleView;
import com.ec.crm.bean.view.DictionView;
import com.ec.crm.bean.vo.CustomerSaleMapVo;
import com.ec.crm.bean.vo.CustomerSaleVo;
import com.ec.crm.service.CustomerSaleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service(value = "CustomerSaleService")
public class CustomerSaleServiceImpl implements CustomerSaleService {

    @Resource
    CustomCustomerSaleMapper customCustomerSaleMapper;

    @Resource
    UserMapper userMapper;

    @Resource
    DictionStatusMapper dictionStatusMapper;

    @Override
    public CustomerSaleMapVo selectByType(CustomerSaleView customerSaleView){
        Page<CustomerMySale> page = PageHelper.startPage(customerSaleView.getIndex(),customerSaleView.getPageSize())
                .doSelectPage(()-> customCustomerSaleMapper.selectByType(customerSaleView));

        ArrayList<CustomerSaleVo> volist =new ArrayList<>();


        for (int i = 0; i < page.size(); i++) {
            CustomerSaleVo vo =new CustomerSaleVo();

            String totalType="";
            String totalAddress="";
            String totalCapital="";
            String totalScope="";
            //获取用户信息
            User user = userMapper.selectUser(page.getResult().get(i).getUserId());

            vo.setId(page.getResult().get(i).getId());
            vo.setUserId(user.getAccount());
            vo.setNote(user.getNote());
            vo.setUsername(user.getUsername());
            vo.setTel(user.getTel());
            vo.setEmail(user.getEmail());


          //获取企业类型
            if (page.getResult().get(i).getType()!=null &&page.getResult().get(i).getType().length()>0){
                String[] type= page.getResult().get(i).getType().split(",");
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

            vo.setTypeStatus(page.getResult().get(i).getType());
            vo.setAddressStatus(page.getResult().get(i).getAddress());
            vo.setScopeStatus(page.getResult().get(i).getScope());
            vo.setCapitalStatus(page.getResult().get(i).getCapital());
            vo.setType(totalType);
            vo.setAddress(totalAddress);
            vo.setCapital(totalCapital);
            vo.setScope(totalScope);
            volist.add(i,vo);
            volist.size();
        }

        CustomerSaleMapVo result =new CustomerSaleMapVo();

          result.setData(volist);
          result.setTotal(page.getTotal());

        return result;
    }

    @Override
    public int updateSale(CustomerMySale customerMySale){

        return customCustomerSaleMapper.updateSale(customerMySale);
    }

    @Override
    public int deleteSale(CustomerMySale customerMySale){
        return customCustomerSaleMapper.deleteById(customerMySale);
    }

    @Override
    public List<DictionaryStatus> selectDicByname(DictionView dictionView){

        List<DictionaryStatus> dictionaryStatusList=dictionStatusMapper.selectByName(dictionView.getName());
        return dictionaryStatusList;
    }


}
