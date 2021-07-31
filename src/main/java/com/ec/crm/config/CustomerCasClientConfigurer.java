package com.ec.crm.config;

import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.boot.configuration.CasClientConfigurer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置不被AuthenticationFilter过滤的资源
 *
 * @author liujiang
 */
@Slf4j
@Configuration
public class CustomerCasClientConfigurer implements CasClientConfigurer {

    @Override
    public void configureAuthenticationFilter(FilterRegistrationBean authenticationFilter) {
        log.info(this.getClass().getName());
        authenticationFilter.getInitParameters().put("ignorePattern", "/index|logout|/*");
    }
}