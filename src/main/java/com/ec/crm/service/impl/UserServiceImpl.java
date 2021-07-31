package com.ec.crm.service.impl;



import com.ec.common.db.auth.po.LoginParam;
import com.ec.common.db.auth.po.LoginResult;

import com.ec.common.db.auth.mapper.EcUserMapper;
import com.ec.crm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;


@Slf4j
@Service(value = "UserService")
public class UserServiceImpl implements UserService {

    @Resource
    EcUserMapper ecUserMapper;


//应该把这个放在authcon里去



    @Override
    public LoginResult selectUser(LoginParam loginParam) {
        return ecUserMapper.selectUser(loginParam);
    }
}
