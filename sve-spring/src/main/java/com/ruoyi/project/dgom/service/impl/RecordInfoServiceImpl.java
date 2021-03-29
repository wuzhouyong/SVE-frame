package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.OpGDeviceEntity;
import com.ruoyi.project.dgom.domain.OpWDeviceEntity;
import com.ruoyi.project.dgom.domain.OpdeviceEntity;
import com.ruoyi.project.dgom.domain.OpeRecordEntity;
import com.ruoyi.project.dgom.mapper.RecordInfoMapper;
import com.ruoyi.project.dgom.service.RecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Service
@DataSource(value = DataSourceType.HBONLINE)
public class RecordInfoServiceImpl implements RecordInfoService {

    @Autowired
    private RecordInfoMapper mapper;


    @Override
    public List<OpdeviceEntity> selectOpdeviceinfoList(OpdeviceEntity entity) {
        return mapper.selectOpdeviceinfoList(entity);
    }

    @Override
    public List<Map> selectCheckPointList(String checkpointtype, String bizSourceId) {
        List<Map> maps = mapper.selectCheckPointList(checkpointtype, bizSourceId);
        return HashMapUtils.toCamel(maps);
    }

    @Override
    public List<Map> getDeviceListByCpId(String checkpointId) {
        List<Map> maps = mapper.getDeviceListByCpId(checkpointId);
        return HashMapUtils.toLower(maps);
    }

    @Override
    public List<OpGDeviceEntity> selectOpgopdeviceList(String checkpointid) {
        return mapper.selectOpgopdeviceList(checkpointid);
    }

    @Override
    public List<OpWDeviceEntity> selectOpwdeviceList(String checkpointid) {
        return mapper.selectOpwdeviceList(checkpointid);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOpwdevice(OpdeviceEntity entity) throws SQLException {
        if (entity == null) throw new SQLException("备案信息不能为空");
        entity.setCreatedby(SecurityUtils.getUsername());
        entity.setUpdatedby(SecurityUtils.getUsername());
        Integer isAcc = mapper.insertDevice(entity);
        if (isAcc < 1) throw new SQLException("备案信息新增失败");
        List<OpWDeviceEntity> list = entity.getOpw();
        if (list == null || list.isEmpty()) throw new SQLException("水重点设备信息不能为空");
        for (OpWDeviceEntity opw : list) {
            opw.setCheckpointid(entity.getCheckpointid());
            opw.setCreatedby(SecurityUtils.getUsername());
            opw.setUpdatedby(SecurityUtils.getUsername());
            Integer count = mapper.insertOpwDevice(opw);
            if (count < 1) throw new SQLException("水重点设备信息不能为空");
        }
        return isAcc;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertOpgdevice(OpGDeviceEntity entity) throws SQLException {
        entity.setCreatedby(SecurityUtils.getUsername());
        entity.setUpdatedby(SecurityUtils.getUsername());
        Integer isAcc = mapper.insertDevice(entity);
        if (isAcc < 1) throw new SQLException("备案信息新增失败");
        Integer count = mapper.insertOpgdevice(entity);
        if (count < 1) throw new SQLException("气重点设备新增失败");
        return isAcc;
    }

    @Override  //  水重点设备编辑
    @Transactional(rollbackFor = Exception.class)
    public int modifyOpwdevice(OpdeviceEntity entity) throws SQLException {
        if (entity == null) throw new SQLException("备案信息不能为空");
        entity.setUpdatedby(SecurityUtils.getUsername());
        Integer isAcc = mapper.modifyDevice(entity);
        if (isAcc < 1) throw new SQLException("备案信息编辑失败");
        List<OpWDeviceEntity> list = entity.getOpw();
        if (list == null || list.isEmpty()) throw new SQLException("水重点设备信息不能为空");
        for (OpWDeviceEntity opw : list) {
            opw.setCheckpointid(entity.getCheckpointid());
            opw.setUpdatedby(SecurityUtils.getUsername());
            Integer count = mapper.modifyOpwDevice(opw);
            if (count < 1) throw new SQLException("水重点设备信息编辑失败");
        }
        return isAcc;
    }

    @Override  //  气重点设备编辑
    @Transactional(rollbackFor = Exception.class)
    public int modifyOpgdevice(OpGDeviceEntity entity) throws SQLException {
        entity.setUpdatedby(SecurityUtils.getUsername());
        Integer isAcc = mapper.modifyDevice(entity);
        if (isAcc < 1) throw new SQLException("备案信息编辑失败");
        Integer count = mapper.modifyOpgdevice(entity);
        if (count < 1) throw new SQLException("气重点设备编辑失败");
        return isAcc;
    }

    @Override  //删除信息备案
    @Transactional(rollbackFor = Exception.class)
    public int deletedDevice(String checkpointid) throws SQLException {
        OpdeviceEntity entity = mapper.selectOpdeviceinfo(checkpointid);
        entity.setUpdatedby(SecurityUtils.getUsername());
        Integer type = entity.getCheckpointtype();
        if (type == 1) {
            Integer count = mapper.deletedOpwDevice(entity);
            if (count < 1) throw new SQLException("水重点设备删除失败");
        }
        if (type == 2) { //同时也会删除旧数据有多个设备的checkpointid
            Integer count = mapper.deletedOpgdevice(entity);
            if (count < 1) throw new SQLException("气重点设备删除失败");
        }
        if (entity == null) throw new SQLException("备案信息不存在");
        Integer isAcc = mapper.deleteDevice(entity);
        if (isAcc < 1) throw new SQLException("备案信息删除失败");
        return isAcc;
    }

    @Override  //导出 气重点
    public LinkedHashMap<String, Object> selectOpgdeviceModelById(String checkpointid) {
        OpdeviceEntity entity = mapper.selectOpdeviceinfo(checkpointid);
        LinkedHashMap map = new LinkedHashMap(HashMapUtils.beanToMap(entity));//对象转map
        if (entity != null) {
            if (entity.getOpstartdate() != null) {
                map.replace("opstartdate", DateUtils.formatDateStr(entity.getOpstartdate()));
            }
            if (entity.getOpenddate() != null) {
                map.replace("openddate", DateUtils.formatDateStr(entity.getOpenddate()));
            }
            if (entity.getOplicissuedate() != null) {
                map.replace("oplicissuedate", DateUtils.formatDateStr(entity.getOplicissuedate()));
            }
        }
        List<OpGDeviceEntity> list = mapper.selectOpgopdeviceList(checkpointid);
        OpGDeviceEntity opg = null;
        if (list != null) {
            opg = list.get(0);
            LinkedHashMap<String, Object> map_opg = new LinkedHashMap<>(HashMapUtils.beanToMap(opg));//对象转map
            map.putAll(map_opg);//合并map
            if (opg.getVerificationtime() != null) {
                map.replace("verificationtime", DateUtils.formatDateStr(opg.getVerificationtime()));
            }
            if (opg.getAcceptancetime() != null) {
                map.replace("acceptancetime", DateUtils.formatDateStr(opg.getAcceptancetime()));
            }
        }
        return map;
    }

    @Override  //导出 水重点
    public LinkedHashMap<String, Object> selectOpwdeviceModelById(String checkpointid) {
        OpdeviceEntity entity = mapper.selectOpdeviceinfo(checkpointid);
        LinkedHashMap map = new LinkedHashMap(HashMapUtils.beanToMap(entity));//对象转map
        if (entity != null) {
            if (entity.getOpstartdate() != null) {
                map.replace("opstartdate", DateUtils.formatDateStr(entity.getOpstartdate()));
            }
            if (entity.getOpenddate() != null) {
                map.replace("openddate", DateUtils.formatDateStr(entity.getOpenddate()));
            }
            if (entity.getOplicissuedate() != null) {
                map.replace("oplicissuedate", DateUtils.formatDateStr(entity.getOplicissuedate()));
            }
        }
        List<OpWDeviceEntity> list = mapper.selectOpwdeviceList(checkpointid);
        list.stream().forEach(x -> {
            if (x.getAcceptancetime() != null) {
                x.setAcceptancetime(DateUtils.formatDateStr(x.getAcceptancetime()));
            }
            if (x.getVerificationtime() != null) {
                x.setVerificationtime(DateUtils.formatDateStr(x.getVerificationtime()));
            }
            Integer[] acc = mapper.selectElementIdByDevId(x.getDatadevid());
            if (acc != null && acc.length != 0) {
                String prefix = "";
                for (int i = 0; i < acc.length; i++) {
                    switch (acc[i]) {
                        case 1://cod
                            prefix = "cod_";
                            break;
                        case 26://n
                            prefix = "n_";
                            break;
                        case 27://nh
                            prefix = "nh_";
                            break;
                        case 28://p
                            prefix = "p_";
                            break;
                        case 29://flow
                            prefix = "flow_";
                            break;
                        case 35://ph
                            prefix = "ph_";
                            break;
                        default:
                            break;
                    }
                }
                LinkedHashMap map_opg = new LinkedHashMap(HashMapUtils.beanToMap(x, prefix));//对象转map,并加前缀
                map.putAll(map_opg);//合并map
            }
        });
        return map;
    }


    @Override
    public List<OpeRecordEntity> selectOperationRecordList(OpeRecordEntity entity) {
        return mapper.selectOperationRecordList(entity);
    }

    @Override
    public List<Map> selectEquipmentList(String operationType, String bizSourceId) {
        String checkpointtype = null;
        switch (operationType) {
            case "1":
            case "2":
            case "3":
                checkpointtype = "2";
                break;
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
                checkpointtype = "1";
                break;
        }
        List<Map> maps = mapper.selectEquipmentList(checkpointtype, bizSourceId);
        return HashMapUtils.toCamel(maps);
    }

    @Override
    public ResultEntity insertOperationRecord(OpeRecordEntity entity) {
        if (entity == null) return ResultEntity.error("运维管理不能为空");
//        entity.setZoperationid(UUID.randomUUID().toString().replace("-", ""));
        entity.setZcreatedby(SecurityUtils.getUsername());
        entity.setZupdatedby(SecurityUtils.getUsername());
        Integer isAcc = mapper.insertOperationRecord(entity);
        return isAcc > 0 ? ResultEntity.success("成功新增" + isAcc + "条") : ResultEntity.error("新增失败");
    }

    @Override
    public ResultEntity modifyOperationRecord(OpeRecordEntity entity) {
        if (entity == null) return ResultEntity.error("运维管理不能为空");
        entity.setZupdatedby(SecurityUtils.getUsername());
        Integer isAcc = mapper.modifyOperationRecord(entity);
        return isAcc > 0 ? ResultEntity.success("成功编辑" + isAcc + "条") : ResultEntity.error("编辑失败");
    }

    @Override
    public ResultEntity deleteOperationRecord(String[] zoperationids) {
        if (zoperationids == null || zoperationids.length == 0) {
            return ResultEntity.error("参数不能为空");
        }
        String updatedBy = SecurityUtils.getUsername();
        int isAcc = mapper.deleteOperationRecord(zoperationids, updatedBy);
        return isAcc > 0 ? ResultEntity.success("成功删除" + isAcc + "条") : ResultEntity.error("删除失败");
    }


}
