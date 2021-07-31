package com.ec.crm.service.impl;

import com.ec.common.db.auth.po.LoginParam;
import com.ec.common.db.auth.po.LoginResult;
import com.ec.crm.constant.AuthorizeException;
import com.ec.crm.constant.CommonError;
import com.ec.crm.service.LoginService;
import com.ec.crm.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 登录Service
 *
 * @author liujiang
 */
@Component
public class LoginServiceImpl implements LoginService {
    @Resource
    private UserService userService;


    @Override
    public LoginResult userLogin(LoginParam param) throws AuthorizeException {
        String account = param.getAccount();
        String password = param.getPassword();
//        int cid = param.getCid();
        LoginResult list = userService.selectUser(param);
        if (list==null){
            throw new AuthorizeException(CommonError.ERROR_UNAUTHORIZED, "账号或密码错误");
        }
        else{
            return list;
        }


//        if (CollectionUtils.isEmpty(lists)) {
//            throw new AuthorizeException(CommonError.ERROR_UNAUTHORIZED, "账号或密码错误");
//        }
//        User loginUser = lists.get(0);
//        if (loginUser.getStatus() == 1) {
//            throw new AuthorizeException(CommonError.ERROR_FORBIDDEN, "离职用户");
//        } else {
//            LoginResult result = new LoginResult();
//            result.setCid(loginUser.getCId());
//            result.setAccount(loginUser.getAccount());
//            result.setUserMenuList(userService.getUserMenu(param));
//            return result;
//        }
    }

    @Override
    public LoginResult userLoginWithoutPwd(LoginParam param) throws AuthorizeException {
//        String account = param.getAccount();
//        List<User> lists = userService.getUserByAccount(account);
//        if (CollectionUtils.isEmpty(lists)) {
//            throw new AuthorizeException(CommonError.ERROR_UNAUTHORIZED, "账号或密码错误");
//        }
//        User loginUser = lists.get(0);
//        if (loginUser.getStatus() == 1) {
//            throw new AuthorizeException(CommonError.ERROR_FORBIDDEN, "离职用户");
//        } else {
            LoginResult result = new LoginResult();
//            result.setCid(loginUser.getCId());
            result.setAccount(param.getAccount());
            result.setUser_id(param.getUserId());
//            result.setAccountName(loginUser.getUserName());
//            result.setUserMenuList(userService.getUserMenu(param));
//            return result;
//        }
        return result;
    }
}