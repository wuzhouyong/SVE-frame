package com.ruoyi.project.dgom.service;


import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.dgom.domain.OpGDeviceEntity;
import com.ruoyi.project.dgom.domain.OpWDeviceEntity;
import com.ruoyi.project.dgom.domain.OpdeviceEntity;
import com.ruoyi.project.dgom.domain.OpeRecordEntity;
import org.springframework.web.bind.annotation.RequestBody;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface RecordInfoService {

    public List<OpdeviceEntity> selectOpdeviceinfoList(OpdeviceEntity entity);

    public List<Map> selectCheckPointList(String checkpointtype, String bizSourceId);

    List<Map> getDeviceListByCpId(String checkpointId);

    public List<OpGDeviceEntity> selectOpgopdeviceList(String checkpointid); //气重点设备

    public List<OpWDeviceEntity> selectOpwdeviceList(String checkpointid); //水重点设备

    public int insertOpwdevice(OpdeviceEntity entity) throws SQLException;

    public int insertOpgdevice(OpGDeviceEntity entity) throws SQLException;

    public int modifyOpwdevice(OpdeviceEntity entity) throws SQLException;

    public int modifyOpgdevice(OpGDeviceEntity entity) throws SQLException;

    public int deletedDevice(String checkpointid) throws SQLException;

    public Map<String, Object> selectOpgdeviceModelById(String checkpointid);

    public Map<String, Object> selectOpwdeviceModelById(String checkpointid);

    public List<OpeRecordEntity> selectOperationRecordList(OpeRecordEntity entity);

    public List<Map> selectEquipmentList(String operationType, String bizSourceId);

    public ResultEntity insertOperationRecord(OpeRecordEntity entity);

    public ResultEntity modifyOperationRecord(OpeRecordEntity entity);

    public ResultEntity deleteOperationRecord(String[] zoperationids);

}
