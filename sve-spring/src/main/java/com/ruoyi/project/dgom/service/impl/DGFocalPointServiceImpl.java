package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.domain.DataSearchColEntity;
import com.ruoyi.common.domain.DataSearchEntity;
import com.ruoyi.common.domain.DataSearchResult;
import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.DataSearchUtils;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.ReturnParam;
import com.ruoyi.project.dgom.mapper.DGFocalPointMapper;
import com.ruoyi.project.dgom.service.DGFocalPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-09-17 10:18
 */
@Service
@DataSource(value = DataSourceType.MASTER)
public class DGFocalPointServiceImpl implements DGFocalPointService {

    @Autowired
    private DGFocalPointMapper pointMapper;

    @Override  //水重点施工单位信息
    public Map getDatWaterConstruction(String sourceId) {
        Integer tagHeadId = 1017;
        Map t_map = pointMapper.getDatConstruction(sourceId, tagHeadId);
        if (StringUtils.isEmpty(t_map)) return new HashMap();
        Map tempMap = HashMapUtils.toCamel(t_map);
        Map<String, Object> map = HashMapUtils.toTypeOf(tempMap);
        return map;
    }

    @Override // 水重点近30天传输率图小时数据
    public ResultEntity getWaterTransferCharts() {
        if (StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId()))
            return ResultEntity.error("企业不能为空");
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        Integer tagHeadId = 1017;
        String bizSourceId = pointMapper.checkSourceId(sourceId, tagHeadId);
        if (StringUtils.isEmpty(bizSourceId)) return ResultEntity.error("监控平台没有该企业");
//        String bizSourceId ="109";
        Integer zcheckpointtype = 1;
        List<DataSearchEntity> day = pointMapper.getTransferCharts(bizSourceId, zcheckpointtype);
        List<Map<String, Object>> tList = DataSearchUtils.searchData(day, "yyyy-MM-dd");
        List<DataSearchColEntity> dataList = pointMapper.getTransfer();
        return ResultEntity.success(new DataSearchResult(dataList, tList));
    }

    @Override // 获取水重点 待处理业务总条数
    public Integer getWaterProcStateCount() {
        if (StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) return 0;
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        Integer tagHeadId = 1017;
        String bizSourceId = pointMapper.checkSourceId(sourceId, tagHeadId);
        if (StringUtils.isEmpty(bizSourceId)) return 0;
        Integer zcheckpointtype = 1;
        return pointMapper.getProcStateCount(bizSourceId, zcheckpointtype);
    }

    @Override  //气重点施工单位信息
    public Map getDatGasConstruction(String sourceId) {
        Integer tagHeadId = 1021;
        Map t_map = pointMapper.getDatConstruction(sourceId, tagHeadId);
        if (StringUtils.isEmpty(t_map)) return new HashMap();
        Map tempMap = HashMapUtils.toCamel(t_map);
        Map<String, Object> map = HashMapUtils.toTypeOf(tempMap);
        return map;
    }

    @Override // 气重点近30天传输率图小时数据
    public ResultEntity getGasTransferCharts() {
        if (StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId()))
            return ResultEntity.error("企业不能为空");
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        Integer tagHeadId = 1021;
        String bizSourceId = pointMapper.checkSourceId(sourceId, tagHeadId);
        if (StringUtils.isEmpty(bizSourceId)) return ResultEntity.error("监控平台没有该企业");
        Integer zcheckpointtype = 2;

        List<DataSearchEntity> day = pointMapper.getTransferCharts(bizSourceId, zcheckpointtype);
        List<Map<String, Object>> tList = DataSearchUtils.searchData(day, "yyyy-MM-dd");
        List<DataSearchColEntity> dataList = pointMapper.getTransfer();
        return ResultEntity.success(new DataSearchResult(dataList, tList));
    }

    @Override // 获取气重点 待处理业务总条数
    public Integer getGasProcStateCount() {
        if (StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) return 0;
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        Integer tagHeadId = 1021;
        String bizSourceId = pointMapper.checkSourceId(sourceId, tagHeadId);
        if (StringUtils.isEmpty(bizSourceId)) return 0;
        Integer zcheckpointtype = 2;
        return pointMapper.getProcStateCount(bizSourceId, zcheckpointtype);
    }

    @Override // 排放口查询 水气一体
    public List<ReturnParam> selectEmissionPointList(ReturnParam param) {
        return pointMapper.selectEmissionPointList(param);
    }

    @Override  // 设备查询
    public List<ReturnParam> selectEquipmentList(ReturnParam param) {
        return pointMapper.selectEquipmentList(param);
    }

    @Override  // 因子查询
    public List<ReturnParam> selectElementList(ReturnParam param) {
        return pointMapper.selectElementList(param);
    }

}
