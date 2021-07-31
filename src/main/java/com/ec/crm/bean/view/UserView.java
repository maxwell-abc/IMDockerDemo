package com.ec.crm.bean.view;

import lombok.Data;

@Data
public class UserView {
    private String username;
    private String email;
    private String tel;
    private int index;
    private int pageSize;

    //分页查询，把关联了的用户或者子业务系统记录放到前面去
    private int userId;
    private int subSystemId;
}
