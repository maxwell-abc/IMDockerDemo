package com.ec.crm.controller;



import com.ec.common.db.fi.po.CustomerSea;
import com.ec.common.db.fi.po.CustomerSeaPlus;
import com.ec.common.db.fi.po.CustomerSeaView;

import com.ec.crm.bean.ResponseJson;
import com.ec.crm.bean.vo.CustomerSeaMapVo;

import com.ec.crm.constant.Constant;
import com.ec.crm.service.CustomerSeasService;
import com.ec.crm.utils.PoiUtils;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/customersea")
public class CustomerSeasController {

    @Autowired
    CustomerSeasService customerSeasService;


    //测试代码。。
    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseJson addCustomer() throws IOException {

        List<CustomerSea> customerSeas = customerSeasService.get();

        if (customerSeas != null) {
            return new ResponseJson(Constant.SUCCESS_CODE, customerSeas);
        } else {
            return new ResponseJson(Constant.FAIL_CODE, "查询客户失败！");
        }
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseJson addCustomerSea(@RequestBody CustomerSea customerSea) throws IOException {

        long flag = customerSeasService.insertSelective(customerSea);
        if (flag == 1) {
            return new ResponseJson(Constant.SUCCESS_CODE, "添加角色成功！");

        } else if (flag == 0) {
            return new ResponseJson(Constant.EXIST_CODE, "添加的角色重复");
        } else {
            return new ResponseJson(Constant.FAIL_CODE, "添加角色失败！");
        }

    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseJson updateCustomerSea(@RequestBody CustomerSeaPlus customerSeaPlus) throws IOException {
        int flag =customerSeasService.updateByPrimaryKey(customerSeaPlus);
        if (flag == 1) {
            return new ResponseJson(Constant.SUCCESS_CODE, "修改公海成功！");
        } else {
            return new ResponseJson(Constant.FAIL_CODE, "修改公海失败！");
        }

    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public ResponseJson deleteCustomerSea(@RequestBody CustomerSea customerSea) throws IOException {
        int result = customerSeasService.deleteByPrimaryKey(customerSea.getId());
        if (result == 1) {
            return new ResponseJson(Constant.SUCCESS_CODE, "删除成功！");
        } else {
            return new ResponseJson(Constant.FAIL_CODE, "删除失败！");
        }
    }

    @RequestMapping(value = "query-like", method = RequestMethod.POST)
    public ResponseJson slectInfoLike(@RequestBody CustomerSeaView customerSeaView) throws IOException {
        CustomerSeaMapVo customerSeaMapVo = customerSeasService.slectInfoLike(customerSeaView);
        if (customerSeaMapVo != null) {
            return new ResponseJson(Constant.SUCCESS_CODE, customerSeaMapVo);
        } else {
            return new ResponseJson(Constant.FAIL_CODE, "查询角色失败！");
        }
    }
    @RequestMapping(value = "query-none-sale",method = RequestMethod.POST)
    public ResponseJson selectNoSale(@RequestBody CustomerSeaView customerSeaView) throws IOException {
        CustomerSeaMapVo customerSeaMapVo = customerSeasService.selectNoSaleInfo(customerSeaView);
        if (customerSeaMapVo != null) {
            return new ResponseJson(Constant.SUCCESS_CODE, customerSeaMapVo);
        } else {
            return new ResponseJson(Constant.FAIL_CODE, "查询角色失败！");
        }
    }

    @RequestMapping(value = "bulk-import", method = RequestMethod.POST)
    public ResponseJson addBulkCustomerSea(MultipartFile file) throws IOException {
        List<CustomerSea> customerSea = PoiUtils.parseFile2List(file);
        if (customerSeasService.addBulkCunstomerSea(customerSea) != 0) {

            return new ResponseJson(Constant.SUCCESS_CODE, "批量添加成功");
        } else {

            return new ResponseJson(Constant.FAIL_CODE, "文件中的信息全部重复");
        }

    }

    @RequestMapping(value = "query-noncontact", method = RequestMethod.POST)
    public ResponseJson selectSeaByStatus(@RequestBody CustomerSea customerSea) throws IOException {

        List<CustomerSea> customerSeas = customerSeasService.selectInfoByStatus(customerSea);
        if (customerSea != null) {
            return new ResponseJson(Constant.SUCCESS_CODE, customerSeas);
        } else {
            return new ResponseJson(Constant.FAIL_CODE, "查询失败");
        }
    }

    @RequestMapping(value = "geturl", method = RequestMethod.POST)
    public ResponseJson getUrl() throws IOException {

        String url = "www.znglzx.com:11010/filedown/fims-crm-mid/sea.xlsx";
        return new ResponseJson(Constant.SUCCESS_CODE,url);
    }

    @RequestMapping(value = "if-profile",method = RequestMethod.POST)
    public ResponseJson isProfile(@RequestBody CustomerSeaPlus customerSeaPlus)throws IOException{

        int result = customerSeasService.IsProfile(customerSeaPlus);
        if (result ==0){
            return new ResponseJson(Constant.SUCCESS_CODE,"该公海没有档案");
        }else {
            return new ResponseJson(Constant.FAIL_CODE,"该公海已有档案");
        }
    }
//        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
//        StaticUrlMap staticUrlMap = new StaticUrlMap();
//        ArrayList<StaticUrl> staticUrls = new ArrayList<>();
//
//        String resultUrl="http://im.znglzx.com/crm/";
//        Resource[] resources = resolver.getResources("classpath:static/*");
//
//        for (Resource resource:resources){
//        StaticUrl staticUrl = new StaticUrl();
//            String[]  split = resource.getFilename().split("\\.");
//            String fileName = null;
//            if(split!=null && split.length !=0){
//                fileName=split[0];
//            }
//            staticUrl.setFileName(fileName);
//            staticUrl.setFileUrl(resultUrl+resource.getFilename());
//            staticUrls.add(staticUrl);
//        }
//        staticUrlMap.setStaticUrls(staticUrls);
//        staticUrlMap.setTotal(staticUrlMap.getStaticUrls().size());
//
//        if(staticUrlMap!=null){
//            return new ResponseJson(Constant.SUCCESS_CODE,staticUrlMap);
//        }else {
//            return new ResponseJson(Constant.FAIL_CODE,"查询失败");
//        }
//
//}

}
