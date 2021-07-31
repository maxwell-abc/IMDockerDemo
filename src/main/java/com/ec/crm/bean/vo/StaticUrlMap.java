package com.ec.crm.bean.vo;

import com.ec.crm.bean.StaticUrl;
import lombok.Data;

import java.util.List;

@Data
public class StaticUrlMap {
   private List<StaticUrl> staticUrls;
    private int total;
}
