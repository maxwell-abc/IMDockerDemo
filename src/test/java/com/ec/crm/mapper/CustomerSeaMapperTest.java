package com.ec.crm.mapper;

import com.ec.common.db.fi.mapper.CustomerSeaMapper;
import com.ec.common.db.fi.mapper.custom.CustomCustomerSeaMapper;
import com.ec.common.db.fi.po.CustomerSea;
import com.ec.crm.CrmApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * <br>
 *
 * @author liujiang
 * @date 2021/4/6 08:51
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = CrmApplication.class)
@Slf4j
public class CustomerSeaMapperTest {
    @Autowired
    private CustomCustomerSeaMapper customCustomerSeaMapper;
    @Autowired
    private CustomerSeaMapper customerSeaMapper;

    @Test
    public void queryAll() {
        List<CustomerSea> list = customerSeaMapper.selectByExample(null);
        log.info("queryAll>>>>>" + list.size());
    }

    @Test
    public void queryAll2() {
        List<CustomerSea> list = customCustomerSeaMapper.getCustomerSea();
        log.info("queryAll2>>>>>" + list.size());
    }
}
