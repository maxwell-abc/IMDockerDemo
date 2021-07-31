package com.ec.crm.aop;

import cn.hutool.json.JSONUtil;
import com.ec.crm.bean.BaseResult;
import com.ec.crm.utils.IpUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import javax.servlet.http.HttpServletRequest;

/**
 * Controller切面
 *
 * @author liujiang
 */
@Aspect
@Slf4j
@Component
public class ControllerAspect {
    @Autowired
    private HttpServletRequest request;

    @Around(value = "execution(public * com.ec.crm.controller.*Controller.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        String function = joinPoint.getSignature().getName();
        String body = JSONUtil.toJsonStr(args);
        log.info("ip:{},function:{},request:{}", IpUtil.getIpAddr(request), function, body);
        Object rs = null;
        try {
            rs = joinPoint.proceed(args);
            if (rs instanceof BaseResult) {
                ((BaseResult) rs).setTraceId(MDC.get("X-B3-TraceId"));
            }
        } catch (Throwable e) {
            throw e;
        } finally {
            log.info("[{}] response:{} cost: {}", function, JSONUtil.toJsonStr(rs), stopWatch.getTotalTimeMillis());
        }
        return rs;
    }
}