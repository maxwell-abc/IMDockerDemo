package com.ec.crm.constant;

import org.springframework.http.HttpStatus;

/**
 * @author liujiang
 */
public class Constant {
   // public static final int SUCCESS_CODE = HttpStatus.OK.value();
    public static final int FAIL_LOGIN = HttpStatus.FORBIDDEN.value();

    public final static String USER_SESSION_KEY="USER";

    public static final int FAIL_CODE = 1500;
    public static final int EXIST_CODE = 1501;
    public static final int EMPTY_CODE = 1502;
    public static final int SUCCESS_CODE = 1200;
    public static final int SYSTEMIDNULL_CODE = 1201;


    //使用百度Web服务API获取IP城市信息
    //官方说明文档：http://lbsyun.baidu.com/index.php?title=webapi/ip-api
    public static final String baiduAPIUri="https://api.map.baidu.com/location/ip?";
    public static final String baiduAK="ak=T3YjI1nY0yYsX3XmEiKAKnQwwbGkrAta";
    public static final String baiduCoor="&coor=bd09ll&ip=";

    public static final String browserType="PostmanRuntime";


}