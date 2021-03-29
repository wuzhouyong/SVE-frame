package com.ruoyi.project.dgom.mapper;


import com.ruoyi.project.dgom.domain.OpGDeviceEntity;
import com.ruoyi.project.dgom.domain.OpWDeviceEntity;
import com.ruoyi.project.dgom.domain.OpdeviceEntity;
import com.ruoyi.project.dgom.domain.OpeRecordEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Mapper
public interface RecordInfoMapper {

    public List<OpdeviceEntity> selectOpdeviceinfoList(OpdeviceEntity entity);

    public List<Map> selectCheckPointList(@Param("checkpointtype") String checkpointtype,
                                          @Param("bizSourceId") String bizSourceId);

    List<Map> getDeviceListByCpId(String checkpointId);

    public List<OpGDeviceEntity> selectOpgopdeviceList(@Param("checkpointid") String checkpointid);

    public List<OpWDeviceEntity> selectOpwdeviceList(@Param("checkpointid") String checkpointid);

    public Integer[] selectElementIdByDevId(@Param("datadevid") Integer datadevid);

    public Integer insertDevice(OpdeviceEntity entity);

    public Integer insertOpwDevice(OpWDeviceEntity opw);

    public Integer insertOpgdevice(OpGDeviceEntity opg);

    public Integer modifyDevice(OpdeviceEntity entity);

    public Integer modifyOpwDevice(OpWDeviceEntity opw);

    public Integer modifyOpgdevice(OpGDeviceEntity opw);

    public OpdeviceEntity selectOpdeviceinfo(@Param("checkpointid") String checkpointid);

    public Integer deleteDevice(OpdeviceEntity entity);

    public Integer deletedOpwDevice(OpdeviceEntity entity);

    public Integer deletedOpgdevice(OpdeviceEntity entity);

    public List<OpeRecordEntity> selectOperationRecordList(OpeRecordEntity entity);

    public List<Map> selectEquipmentList(@Param("checkpointtype") String checkpointtype,
                                         @Param("bizSourceId") String bizSourceId);

    public Integer insertOperationRecord(OpeRecordEntity entity);

    public Integer modifyOperationRecord(OpeRecordEntity entity);

    public Integer deleteOperationRecord(@Param("zoperationids") String[] zoperationids,
                                         @Param("updatedBy") String updatedBy);


}
