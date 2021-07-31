//package com.ec.crm.aop;
//
//
//import cn.hutool.json.JSONUtil;
//import com.ec.crm.bean.OperateLog;
//import com.ec.crm.constant.Constant;
//import com.ec.crm.service.OperateLogService;
//import com.ec.crm.utils.IpUtil;
//import lombok.extern.slf4j.Slf4j;
//import nl.bitwalker.useragentutils.UserAgent;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.util.StopWatch;
//
//import javax.servlet.http.HttpServletRequest;
//
//@Aspect
//@Slf4j
//@Component
//public class OperateLogAspect {
//    @Autowired
//    private HttpServletRequest request;
//
//
//    private OperateLog operateLog=new OperateLog();
//
//    @Autowired
////    private OperateLogService operateLogService;
//
//    @Around(value = "execution(public * com.ec.crm.controller.*Controller.*(..))")
//    public Object around(ProceedingJoinPoint joinPoint) throws Throwable{
//        String ip="";
//        String city="";
//        StopWatch stopWatch = new StopWatch();
//        stopWatch.start();
//
//        Object[] args = joinPoint.getArgs();
//        String agent=request.getHeader("user-agent");
//        UserAgent userAgent=UserAgent.parseUserAgentString(agent);
//        String browserName=userAgent.getBrowser().getName();
////        System.out.println("user-agent:"+agent);
//        String[] temp=agent.split("/");
//        if(temp[0].equals(Constant.browserType)) operateLog.setBrowserType(agent);
//        else operateLog.setBrowserType(browserName);
//
//        String function = joinPoint.getSignature().getName();
//        String URL=request.getRequestURL().toString();
////        System.out.println(URL);
//        String[] strArr=URL.split("/");
//        switch (strArr[4]){
//            case "subsystem":
//                operateLog.setObject("业务系统管理");
//                break;
//            case "institution":
//                operateLog.setObject("组织机构管理");
//                break;
//            case "opetate-log":
//                operateLog.setObject("日志管理");
//                break;
//            case "user-cas-auth":
//                operateLog.setObject("用户管理");
//                break;
//            case "role":
//                operateLog.setObject("角色管理");
//                break;
//            case "menu":
//                operateLog.setObject("资源管理");
//                break;
//            default:
//                operateLog.setObject("其他");
//        }
//
//        switch(strArr[5]){
//            case "update":
//                operateLog.setType("修改");
//                break;
//            case "add":
//                operateLog.setType("添加");
//                break;
//            case "delete":
//                operateLog.setType("删除");
//                break;
//            case "query":
//                operateLog.setType("查询");
//                break;
//            case "login":
//                operateLog.setType("登录");
//                break;
//            case "logou":
//                operateLog.setType("登出");
//                break;
//            default:
//                operateLog.setType("其他");
//        }
//
//
//        String body = JSONUtil.toJsonStr(args);
//        ip=IpUtil.getIpAddr(request);
//        operateLog.setIp(ip);
//        operateLog.setStatus(1);
//        city=IpUtil.getIPCity(ip);
//        operateLog.setPlace(city);
//
//
//        operateLog.setNote("无");
//
//        String systemId="";
//        systemId=request.getHeader("systemId");
//        String operatorId="";
//        operatorId=request.getHeader("operatorId");
//        if (systemId==null || operatorId==null){
//            operateLog.setSystemId(-1);
//            operateLog.setOperatorId(-1);
//        }
//        else{
//            operateLog.setSystemId(Integer.valueOf(systemId));
//            operateLog.setOperatorId(Integer.valueOf(operatorId));
//        }
//
////        operateLogService.insertOperateLog(operateLog);
//
//
//        Object rs = null;
//        rs = joinPoint.proceed(args);
//        return rs;
//    }
//
//}
