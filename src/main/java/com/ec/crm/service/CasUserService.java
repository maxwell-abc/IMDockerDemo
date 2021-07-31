package com.ec.crm.service;


import com.ec.crm.bean.User;

import java.util.List;

public interface CasUserService {

    //删除用户
    int deleteUser(String account);

    //模糊查询用户
    List<com.ec.common.db.cas.po.User> selectByLike(User user);



    List<com.ec.common.db.cas.po.User> selectByLike2(User user);

    //添加资源
    int addUser(com.ec.common.db.cas.po.User user);

    //更新用户
    int updateUser(com.ec.common.db.cas.po.User user);

    //查询用户By account
    List<com.ec.common.db.cas.po.User> getUserByAccount(String account);

}
