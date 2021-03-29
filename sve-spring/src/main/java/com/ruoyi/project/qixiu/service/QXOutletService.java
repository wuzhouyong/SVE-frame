package com.ruoyi.project.qixiu.service;

import com.ruoyi.project.common.domain.flowChart;
import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXOutletEntity;

import java.util.List;
import java.util.Map;

public interface QXOutletService {

    public List<QXOutletEntity> selectOutlet(QXOutletEntity entity);

    public int insertOutlet(QXOutletEntity entity);

    public int updateOutlet(QXOutletEntity entity);

    public int deleteOutlet(QXDeleteEntity entity);

    /**
     * 基于企业排放口生成流程树
     */
    public List<flowChart> bulidOutletTreeBySourceId(String sourceID);

    /**
     * 排放口获取所有因子与实时值
     * @param outID
     * @return
     */
    public List<Map> selectCheckElemntTagVal(String outID);

    /**
     * 根据排放口值设置状态
     * @param tags
     * @return
     */
    public List<Map> getPointRunStatus(List<Map> tags);
}
