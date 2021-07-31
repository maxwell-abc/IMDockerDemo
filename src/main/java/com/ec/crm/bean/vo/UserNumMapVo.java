package com.ec.crm.bean.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserNumMapVo {
    List<UserNumVo> userNumVoList;
    int size;
}
