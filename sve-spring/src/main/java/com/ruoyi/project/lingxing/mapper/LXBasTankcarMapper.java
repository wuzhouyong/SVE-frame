package com.ruoyi.project.lingxing.mapper;


import com.ruoyi.project.lingxing.domain.LXBasTankcar;
import com.ruoyi.project.lingxing.domain.LXBasTrucktank;
import com.ruoyi.project.lingxing.domain.LXDatCargps;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface LXBasTankcarMapper {

    //查询处置企业
    public List<Map> selectEnterpriseList();

    //查询运输车辆
    public List<LXBasTankcar> selectBasTankcarList(LXBasTankcar basTankcar);

    //新增运输车辆
    public int insertBasTankcar(LXBasTankcar basTankcar);

    //编辑运输车辆
    public int updateBasTankcar(LXBasTankcar basTankcar);

    //删除运输车辆
    public int deleteBasTankcarByIds(@Param("carIds") Integer[] carIds,@Param("updatedBy") String updatedBy);

    /**
     * 删除【请填写功能名称】
     *
     * @param carId 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBasTankcarById(Integer carId);

    /**
     * 查询【请填写功能名称】
     *
     * @param carId 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public LXBasTankcar selectBasTankcarById(Integer carId);

    //查询车辆水箱
    public List<LXBasTrucktank> selectTrucktankList(LXBasTrucktank trucktank);

    //新增车辆水箱
    public int insertTrucktank(LXBasTrucktank trucktank);

    //编辑车辆水箱
    public int updateTrucktank(LXBasTrucktank trucktank);

    //删除车辆水箱
    public int deleteBasTrucktankByIds(@Param("tankIds") Integer[] tankIds, @Param("updatedBy") String updatedBy);

    //查找车牌
    public List<LXBasTankcar> selectTankcarList(String sourceId);

    //查询企业轨迹列表
    public List<LXDatCargps> selectTruckTrackList(String jobHeadid);

    //查询车辆路径轨迹
    public List<LXDatCargps> selectDatCargpsList(String jobHeadid);

}
