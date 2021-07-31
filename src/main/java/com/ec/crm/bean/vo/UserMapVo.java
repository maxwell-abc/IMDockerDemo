package com.ec.crm.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserMapVo {
    private List<UserVo> userVoList;
    private int size;
}
