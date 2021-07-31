package com.ec.crm.bean.view;

import lombok.Data;

import java.util.List;

@Data
public class OperateLogMapView {
    private List<OperateLogView> data;
    private long total;
}
