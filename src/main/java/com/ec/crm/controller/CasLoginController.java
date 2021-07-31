package com.ec.crm.controller;

import cn.hutool.json.JSONUtil;
import com.ec.crm.bean.BaseResult;
import com.ec.common.db.auth.po.LoginParam;
import com.ec.common.db.auth.po.LoginResult;
import com.ec.crm.bean.LogoutParam;
import com.ec.crm.constant.AuthorizeException;
import com.ec.crm.constant.CommonError;
import com.ec.crm.constant.Constant;
import com.ec.crm.service.CasUserService;
import com.ec.crm.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.jasig.cas.client.validation.Assertion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.groups.Default;
import java.io.IOException;
import java.util.List;

/**
 * CAS Server回调接口
 *
 * @author liujiang
 */
@RestController
@Slf4j
@RequestMapping("/cas")
public class CasLoginController {
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpServletResponse response;
    @Autowired
    private CasUserService userService;
    @Autowired
    private LoginService loginService;
    @Value("${front.url}")
    private String frontUrl;

    @RequestMapping(value = "/caslogin", method = RequestMethod.GET)
    public void casLogin() throws IOException, AuthorizeException {
        HttpSession session = request.getSession();
        Assertion assertion = (Assertion) session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        if (assertion != null) {
            log.info("Assertion.Principal:{}", JSONUtil.toJsonStr(assertion.getPrincipal()));
            //获取登录账号
            String account = assertion.getPrincipal().getName();
            log.info("当前用户：" + account);
            List<com.ec.common.db.cas.po.User> users = userService.getUserByAccount(account);
            if (!CollectionUtils.isEmpty(users)) {
                session.setAttribute(Constant.USER_SESSION_KEY, users.get(0).getUsername());
                // 跳转到前端首页
                response.sendRedirect(frontUrl);
            } else {
                throw new AuthorizeException(CommonError.ERROR_UNAUTHORIZED);
            }
        } else {
            throw new AuthorizeException(CommonError.ERROR_SESSION_INVALID);
        }
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public BaseResult index() throws AuthorizeException {
        HttpSession session = request.getSession();
        Assertion assertion = (Assertion) session.getAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
        if (assertion != null) {
            log.debug("Assertion.Principal:{}", JSONUtil.toJsonStr(assertion.getPrincipal()));
            String userName = (String) session.getAttribute(Constant.USER_SESSION_KEY);
            if (StringUtils.isEmpty(userName)) {
                throw new AuthorizeException(CommonError.ERROR_SESSION_INVALID);
            } else {
                String account = assertion.getPrincipal().getName();
                log.info("当前用户：" + account);
                List<com.ec.common.db.cas.po.User> users = userService.getUserByAccount(account);
                if (!CollectionUtils.isEmpty(users)) {
                    session.setAttribute(Constant.USER_SESSION_KEY, users.get(0).getUsername());
                    LoginParam param = new LoginParam();
                    param.setAccount(account);
                    param.setPassword(users.get(0).getPassword());
                    param.setUserId(users.get(0).getUserId());
//                    param.setCid(users.get(0).getCId());
                    return BaseResult.success(loginService.userLoginWithoutPwd(param));
                } else {
                    throw new AuthorizeException(CommonError.ERROR_UNAUTHORIZED);
                }
            }
        } else {
            throw new AuthorizeException(CommonError.ERROR_SESSION_INVALID);
        }
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BaseResult userLoginout(@RequestBody @Validated({Default.class}) LogoutParam param) throws IOException, AuthorizeException {
        HttpSession session = request.getSession();
        session.invalidate();
//        return "redirect:https://sso.znglzx.com/cas/logout?service=http://subifish.znglzx.com/authorize-service/logout";
        return BaseResult.success("销毁成功！");
    }
}