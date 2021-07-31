package com.ec.crm.config;

import com.ec.crm.config.filter.CORSFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author liujiang
 */
@Configuration
@Slf4j
@Profile("dev")
public class CorsConfigurer {
    @Autowired
    private CORSFilter corsFilter;

    /**
     * 跨域处理
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean corsFilterBean() {
        log.info(this.getClass().getName());
        FilterRegistrationBean bean = new FilterRegistrationBean(corsFilter);
        bean.setOrder(0);
        return bean;
    }
}
