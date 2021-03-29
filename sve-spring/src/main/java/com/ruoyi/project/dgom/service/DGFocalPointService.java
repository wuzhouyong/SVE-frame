package com.ruoyi.project.dgom.service;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.dgom.domain.ReturnParam;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-09-17 10:18
 */

public interface DGFocalPointService {

    //水重点施工单位信息
    public Map getDatWaterConstruction(String sourceId);

    // 水重点近30天传输率图小时数据
    public ResultEntity getWaterTransferCharts();

    // 获取水重点 待处理业务总条数
    public Integer getWaterProcStateCount();

    //气重点施工单位信息
    public Map getDatGasConstruction(String sourceId);

    // 气重点近30天传输率图小时数据
    public ResultEntity getGasTransferCharts();

    // 获取气重点 待处理业务总条数
    public Integer getGasProcStateCount();

    // 排放口查询 水气一体
    public List<ReturnParam> selectEmissionPointList(ReturnParam param);

    // 设备查询
    public List<ReturnParam> selectEquipmentList(ReturnParam param);

    // 因子查询
    public List<ReturnParam> selectElementList(ReturnParam param);

}
