package com.ruoyi.project.dgom.mapper;

import com.ruoyi.common.domain.DataSearchColEntity;
import com.ruoyi.common.domain.DataSearchEntity;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.ReturnParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-09-17 10:19
 */
public interface DGFocalPointMapper {

    //水重点施工单位信息
    public Map getDatConstruction(@Param("sourceId") String sourceId, @Param("tagHeadId") Integer tagHeadId);

    // 获取水气重点 待处理业务总条数
    @DataSource(value = DataSourceType.HBONLINE)
    public int getProcStateCount(@Param("bizSourceId") String bizSourceId, @Param("zcheckpointtype") Integer zcheckpointtype);

    // 查找bizSourceId
    public String checkSourceId(@Param("sourceId") String sourceId, @Param("tagHeadId") Integer tagHeadId);

    // 水气重点近30天传输率图小时数据
    @DataSource(value = DataSourceType.HBONLINE)
    public List<DataSearchEntity> getTransferCharts(@Param("bizSourceId") String bizSourceId, @Param("zcheckpointtype") Integer zcheckpointtype);

    // 统计传输率统计头
    public List<DataSearchColEntity> getTransfer();

    // 排放口查询 水气一体
    @DataSource(value = DataSourceType.HBONLINE)
    public List<ReturnParam> selectEmissionPointList(ReturnParam param);

    // 设备查询
    @DataSource(value = DataSourceType.HBONLINE)
    public List<ReturnParam> selectEquipmentList(ReturnParam param);

    // 因子查询
    @DataSource(value = DataSourceType.HBONLINE)
    public List<ReturnParam> selectElementList(ReturnParam param);

}
