package com.ruoyi.project.youyan.service;


import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.common.domain.flowChart;
import com.ruoyi.project.youyan.domain.YYFacility;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 油烟治理设施信息Service接口
 *
 * @author ruoyi
 * @date 2020-09-22
 */
public interface YYFacilityService {

    //查询治理设施
    public List<YYFacility> selectBasFacilityList(YYFacility facility);

    //新增治理设施
    public ResultEntity insertBasFacility(YYFacility facility) throws SQLException;

    //修改治理设施
    public ResultEntity updateBasFacility(YYFacility facility) throws SQLException;

    //删除治理设施
    public ResultEntity deleteBasFacilityById(Integer facilityId) throws SQLException;

    //根据id查找父级对象
    public ResultEntity selectSameLevelByfacilityId(Integer facilityId);

    //同步关联子级
    public ResultEntity updateBasFacilityRelation(Integer[] facilityPids, Integer facilityId);


    /**
     * 根据企业ID生成设施树结构
     *
     * @param sourceID
     * @return
     */
    public List<flowChart> selectBasFacilityBySourceId(String sourceID);

    /**
     * 根据设施获取设施所有因子实时数据
     *
     * @param facID
     * @return
     */
    public List<Map> selectFacilityRealElementData(int facID);

    /**
     * 根据设施获取所有设备运行情况
     *
     * @param facID
     * @return
     */
    public List<Map> selectFacilityRunStatus(int facID);
}
