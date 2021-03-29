package com.ruoyi.project.lingxing.service;


import com.ruoyi.project.lingxing.domain.LXBasTankcar;
import com.ruoyi.project.lingxing.domain.LXBasTrucktank;
import com.ruoyi.project.lingxing.domain.LXDatCargps;
import com.ruoyi.project.lingxing.domain.LXTransjobhead;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface LXBasTankcarService {
    //查找处置企业
    public List<Map> selectEnterpriseList();

    //新增运输车辆
    public int insertBasTankcar(LXBasTankcar basTankcar) throws SQLException;

    //查询运输车辆
    public List<LXBasTankcar> selectBasTankcarList(LXBasTankcar basTankcar);

    //编辑运输车辆
    public int updateBasTankcar(LXBasTankcar basTankcar) throws SQLException;

    //删除运输车辆
    public int deleteBasTankcarByIds(Integer[] carIds) throws SQLException;
    /**
     * 查询【请填写功能名称】
     *
     * @param carId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public LXBasTankcar selectBasTankcarById(Integer carId);

    //查询车辆水箱
    public List<LXBasTrucktank> selectTrucktankList(LXBasTrucktank trucktank);

    // 新增【车辆水箱】列表
    public int insertTrucktank(LXBasTrucktank trucktank) throws SQLException;

    public int updateTrucktank(LXBasTrucktank trucktank) throws SQLException;

    //删除车辆水箱
    public int deleteBasTrucktankByIds(Integer[] tankIds) throws SQLException;

    //查找车牌
    public List<LXBasTankcar> selectTankcarList(String sourceId);

    //查找工作单id
    public List<String> selectTransHeadList(String receiveDate,Integer carId);

    // 查询车辆轨迹列表
    public Map<String,Object> selectTruckTrackList(String jobHeadid);

}
