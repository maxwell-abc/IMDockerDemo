package com.ec.crm.utils;



import com.ec.common.db.fi.po.CustomerSea;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PoiUtils {

//    //这是把数据导出到本地保存为Excel文件的方法
//    public static ResponseEntity<byte[]> exportJobLevelExcel(List<User> allUser) throws IOException {
//        HSSFWorkbook workbook = new HSSFWorkbook();//创建一个Excel文件
//
//        //创建Excel文档属性，必不可少。少了的话，getDocumentSummaryInformation()方法就会返回null
//        workbook.createInformationProperties();
//        DocumentSummaryInformation info = workbook.getDocumentSummaryInformation();
//        info.setCompany("KYO Ltd.");//设置公司信息
//        info.setManager("kyo");//设置管理者
//        info.setCategory("职称表");//设置文件名
//
//        //设置文件中的日期格式
//        HSSFCellStyle datecellStyle = workbook.createCellStyle();
//        datecellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));//这个文件的日期格式和平时的不一样
//
//        //创建表单
//        HSSFSheet sheet = workbook.createSheet("百威美食尚职称表");
//        HSSFRow r0 = sheet.createRow(0);//创建第一行
//        HSSFCell c0 = r0.createCell(0);// 创建列
//        HSSFCell c1 = r0.createCell(1);// 创建列
//        HSSFCell c2 = r0.createCell(2);// 创建列
//        HSSFCell c3 = r0.createCell(3);// 创建列
//        HSSFCell c4 = r0.createCell(4);// 创建列
//
//        c0.setCellValue("编号");
//        c1.setCellValue("职称名");
//        c2.setCellValue("职称级别");
//        c3.setCellValue("创建时间");
//        c4.setCellValue("是否可用");
//
//        for (int i = 0; i < allJobLevels.size(); i++) {
//            JObLevel jl=allJobLevels.get(i);
//            HSSFRow row = sheet.createRow(i + 1);
//            HSSFCell cell0 = row.createCell(0);
//            cell0.setCellValue(jl.getId());
//            HSSFCell cell1 = row.createCell(1);
//            cell1.setCellValue(jl.getName());
//            HSSFCell cell2 = row.createCell(2);
//            cell2.setCellValue(jl.getTitlelevel());
//            HSSFCell cell3 = row.createCell(3);
//            cell3.setCellStyle(datecellStyle);//让日期格式数据正确显示
//            cell3.setCellValue(jl.getCreatedate());
//            HSSFCell cell4 = row.createCell(4);
//            cell4.setCellValue(jl.getEnabled()?"是":"否");
//        }
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentDispositionFormData("attachment",
//                new String("职称表.xls".getBytes("UTF-8"),"iso-8859-1"));
//        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//        ByteArrayOutputStream baos=new ByteArrayOutputStream();
//        workbook.write(baos);
//
//        ResponseEntity<byte[]> entity = new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.CREATED);
//
//        return entity;
//    }

    //这是解析上传的Excel文件为对象集合，从而批量添加数据的方法
    public static List<CustomerSea> parseFile2List(MultipartFile file) throws IOException {
        List<CustomerSea> result=new ArrayList<>();
        XSSFWorkbook workbook=new XSSFWorkbook(file.getInputStream());

        XSSFSheet sheet = workbook.getSheetAt(0);
        int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();//获取表单所有的行
        for (int i = 1; i < physicalNumberOfRows; i++) {
            XSSFRow row = sheet.getRow(i);
            CustomerSea  customerSea=new CustomerSea();

//            HSSFCell c0 = row.getCell(0);
//            user1.setAccount((int) c0.getNumericCellValue());

            XSSFCell c1 = row.getCell(0);//excel 表里的下表 从0开始
            if(c1==null){
                customerSea.setName("null");
            }else {
                customerSea.setName(c1.getStringCellValue());
            }
//            XSSFCell c2 = row.getCell(2);
//            user1.setNam(c2.getStringCellValue());

            XSSFCell c2 = row.getCell(1);//客户编号
            if(c2==null){
                customerSea.setNumber("null");
            }else {
                c2.setCellType(CellType.STRING);
                customerSea.setNumber(c2.getStringCellValue());
            }
            XSSFCell c3 = row.getCell(2);//公司名
            if (c3==null){
                customerSea.setCompany("null");
            }else {
                customerSea.setCompany(c3.getStringCellValue());
            }
            XSSFCell c4 = row.getCell(3);//状态
            if(c4 ==null){
                long a3 =0;
                customerSea.setStatus(a3);
            }else {
                Double tmpc4 = new Double(c4.getNumericCellValue());
                long l4 = tmpc4.longValue();
                customerSea.setStatus(l4);
            }

            XSSFCell c5 =row.getCell(4);//购买意向
            if(c5==null){
                long a1=0;
                customerSea.setIntention(a1);
            }else {
                Double tmpc5 = new Double(c5.getNumericCellValue());
                long l5 = tmpc5.longValue();
                customerSea.setIntention(l5);
            }

            XSSFCell c6 = row.getCell(5);//预计金额
            if(c6==null){
                long a=0;
                customerSea.setMoney(a);
            }else {
            Double tmpc6 = new Double(c6.getNumericCellValue());
            long l6 = tmpc6.longValue();
            customerSea.setMoney(l6);
            }


            XSSFCell c7 =row.getCell(6);
            if(c7==null){
                customerSea.setCompany("null");
            }else {
                customerSea.setCompanyType(c7.getStringCellValue());
            }


            XSSFCell c8 =row.getCell(7);
            if(c8==null){
                customerSea.setScope("null");
            }else {
                customerSea.setScope(c8.getStringCellValue());
            }

            XSSFCell c9 =row.getCell(8);
            if(c9==null ){
                customerSea.setAddress("null");
            }else {
                        customerSea.setAddress(c9.getStringCellValue());}
//            Timestamp timestamp=new Timestamp(System.currentTimeMillis());
//            System.out.println(timestamp);
//            user1.setCreateTime(timestamp);

            result.add(customerSea);
        }
        return result;
    }
}

