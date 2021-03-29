package com.ruoyi.project.outwater.service;

import java.util.List;
import java.util.Map;

public interface OWMonitorService {

    //设备数量
    public Map getEquipmentQuantity();

    //预警数
    public Map getWarningQuantity();

    //近30天预警数
    public Map getWarningQuantityMonth();

    //街镇报警排名
    public List<Map> getWarningRank();

    //预警产生趋势
    public List<Map> getWarningTrend();

    //污染最严重企业top10
    public List<Map> getWarningPollution();

    //污染程度分析
    public Map getPollutionAnalysis();

}
