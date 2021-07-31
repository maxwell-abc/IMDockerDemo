package com.ec.crm;

import com.ec.crm.bean.CustomerSeas;
//import com.ec.crm.mapper.CustomerSeasMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CrmApplicationTests {

    @Autowired
    //private CustomerSeasMapper customerSeasMapper;
    @Test
    void contextLoads() {
      //  CustomerSeas customerSeas = customerSeasMapper.getCustomerById(1);
       // System.out.println(customerSeas);
    }

}
