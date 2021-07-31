package com.ec.crm.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseJson<T> {
    private int code;//状态码
    private T content;//内容
}
