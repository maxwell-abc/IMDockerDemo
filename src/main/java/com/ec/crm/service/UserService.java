package com.ec.crm.service;


import com.ec.common.db.auth.po.LoginParam;
import com.ec.common.db.auth.po.LoginResult;


public interface UserService {

    LoginResult selectUser(LoginParam loginParam);



}
