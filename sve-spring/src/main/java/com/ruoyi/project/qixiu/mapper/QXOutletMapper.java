package com.ruoyi.project.qixiu.mapper;

import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXOutletEntity;

import java.util.List;
import java.util.Map;

public interface QXOutletMapper {

    public List<QXOutletEntity> selectOutlet(QXOutletEntity entity);

    public int insertOutlet(QXOutletEntity entity);

    public int updateOutlet(QXOutletEntity entity);

    public int deleteOutlet(QXDeleteEntity entity);

    /**
     * 排放口获取所有因子与实时值
     *
     * @param outID
     * @return
     */
    public List<Map> selectCheckElemntTagVal(String outID);
}
