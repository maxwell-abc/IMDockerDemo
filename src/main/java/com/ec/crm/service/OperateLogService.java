package com.ec.crm.service;

import com.ec.crm.bean.OperateLog;
import com.ec.crm.bean.view.OperateLogMapView;
import com.ec.crm.bean.vo.OperateLogVo;

import java.util.List;
import java.util.Map;

public interface OperateLogService {
    OperateLogMapView select(OperateLogVo operateLogVo);


    //分页模糊查询日志
    List<OperateLog> getOperateLog(Map map);


    int insertOperateLog(OperateLog operateLog);
}
