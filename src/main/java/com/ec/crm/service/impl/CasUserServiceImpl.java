package com.ec.crm.service.impl;

import com.ec.crm.bean.User;
import com.ec.crm.service.CasUserService;
import com.ec.common.db.cas.mapper.SerMapper;
import com.ec.common.db.cas.po.UserExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service(value = "CasUserService")
public class CasUserServiceImpl implements CasUserService {

    @Autowired
    private SerMapper userMapper;


    @Override
    public int deleteUser(String account) {
        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo(account);
        int i = userMapper.deleteByExample(example);
        return i;
    }

    //通过auth表中account，institutionName，positionName三个条件查询
    @Override
    public List<com.ec.common.db.cas.po.User> selectByLike(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andAccountEqualTo(user.getAccount())
                .andUsernameLike(user.getUsername())
                .andTelLike(user.getTel())
                .andEmailLike(user.getEmail());

        List<com.ec.common.db.cas.po.User> users = userMapper.selectByExample(example);


        return users;
    }

    //根据cas库中的条件查询
    @Override
    public List<com.ec.common.db.cas.po.User> selectByLike2(User user) {
        UserExample example = new UserExample();
        example.createCriteria()
                .andUsernameLike(user.getUsername())
                .andTelLike(user.getTel())
                .andEmailLike(user.getEmail());
        List<com.ec.common.db.cas.po.User> users = userMapper.selectByExample(example);
        return users;
    }

    @Override
    public int addUser(com.ec.common.db.cas.po.User user) {
        int i = userMapper.insertSelective(user);
        return i;
    }

    @Override
    public int updateUser(com.ec.common.db.cas.po.User user) {

        UserExample example = new UserExample();
        example.createCriteria().andAccountEqualTo(user.getAccount());

        int i = userMapper.updateByExampleSelective(user, example);
        return i;
    }

    //查询用户By account
    @Override
    public List<com.ec.common.db.cas.po.User> getUserByAccount(String account){
        List<com.ec.common.db.cas.po.User> users =null;
        users =userMapper.selectByAccount(account);
        return users;
    }

}
