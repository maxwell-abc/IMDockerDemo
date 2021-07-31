package com.ec.crm.service;

import com.ec.common.db.auth.po.LoginParam;
import com.ec.common.db.auth.po.LoginResult;
import com.ec.crm.constant.AuthorizeException;

public interface LoginService {
    LoginResult userLogin(LoginParam param) throws AuthorizeException;

    LoginResult userLoginWithoutPwd(LoginParam param) throws AuthorizeException;
}
