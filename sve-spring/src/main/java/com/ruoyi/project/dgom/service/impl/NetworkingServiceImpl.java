package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.common.core.lang.UUID;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.*;
import com.ruoyi.project.dgom.domain.network_apply.*;
import com.ruoyi.project.dgom.mapper.NetworkingMapper;
import com.ruoyi.project.dgom.service.NetworkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-09-28 17:07
 */

@Service
@DataSource(value = DataSourceType.HBONLINE)
public class NetworkingServiceImpl implements NetworkingService {
    @Autowired
    private NetworkingMapper workingMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String insertConnectapply(RegisterModel model) throws SQLException {
        model.setApplyId(UUID.randomUUID().toString().replace("-", ""));
        model.setCreatedBy(SecurityUtils.getUsername());
        model.setUpdatedBy(SecurityUtils.getUsername());
        String sourceName = workingMapper.selectSourceNameById(model.getSourceId());
        model.setSourceName(sourceName);
        // 首先插入基础数据
        int Acc = workingMapper.insertConnectapply(model);
        if (Acc < 1) throw new SQLException("新增联网申请");
        for (CollectModel collect : model.getCollects()) {
            collect.setApplyId(model.getApplyId());
            collect.setDetailId(UUID.randomUUID().toString().replace("-", ""));
            // 插入数采信息到数据库
            int count = workingMapper.insertCollectdevdetail(collect);
            if (count < 1) throw new SQLException("新增数采信息失败");
            for (DeviceModel device : collect.getDevices()) {
                device.setCollectDevId(collect.getDetailId());
                device.setDetailId(UUID.randomUUID().toString().replace("-", ""));
                // 插入设备因子到数据库
                int t_count = workingMapper.insertConnectApplydetail(device);
                if (t_count < 1) throw new SQLException("新增设备因子失败");
            }
        }
        return model.getApplyId();
    }

    @Override
    public List<RegisterModel> selectConnectApplyList(NetworkingSearchModel model) {
        return workingMapper.selectConnectApplyList(model);
    }

    @Override //分局审批 1 同意变成2，拒绝变成3
    public int substationApproval(ApprovalModel model) throws SQLException {
        RegisterModel model_t = workingMapper.selectRegisterModelById(model.getApplyId());
        if (model_t.getStatus() != 1 && model_t.getStatus() != 5) throw new SQLException("该状态下不能审批");
        model.setUpdatedBy(SecurityUtils.getUsername());
        int Acc = workingMapper.substationApproval(model);
        if (Acc < 1) throw new SQLException("分局审批异常");
        return Acc;
    }

    @Override //市局审批 2拒绝变成4 同意变成5
    public int municipalApproval(MuApprovalModel model) throws SQLException {
        RegisterModel model_t = workingMapper.selectRegisterModelById(model.getApplyId());
        if (model_t.getStatus() != 2) throw new SQLException("该状态下不能审批");
        model.setUpdatedBy(SecurityUtils.getUsername());
        int Acc = workingMapper.municipalApproval(model);
        if (Acc < 1) throw new SQLException("市局审批异常");
        return Acc;
    }

    @Override //值守 只有4的时候才能值守，变成6
    public int dutyApproval(DutyApprovalModel model) throws SQLException {
        RegisterModel model_t = workingMapper.selectRegisterModelById(model.getApplyId());
        if (model_t.getStatus() != 4) throw new SQLException("该状态下不能值守");
        model.setUpdatedBy(SecurityUtils.getUsername());
        int Acc = workingMapper.dutyApproval(model);
        if (Acc < 1) throw new SQLException("值守审核异常");
        return Acc;
    }

    @Override
    public int deleteRegister(String applyId) {
        String updatedBy = SecurityUtils.getUsername();
        RegisterModel model = workingMapper.selectRegisterModelById(applyId);
        if (model.getStatus() > 0) {
            return -1;
        }
        int res = workingMapper.deleteRegister(applyId, updatedBy);
        if (res > 0) {
            res += workingMapper.deleteCollects(applyId);
            res += workingMapper.deleteDevices(applyId);
        }
        return res;
    }

    @Override   //查询附件列表
    public List<AnnexEntity> selectAnnexList(AnnexEntity entity) {
        return workingMapper.selectAnnexList(entity);
    }

    @Override   //新增附件
    public int insertAnnex(AnnexEntity entity) {
        entity.setCreatedby(SecurityUtils.getUsername());
        entity.setUpdatedby(SecurityUtils.getUsername());
        entity.setAttachfileid(UUID.randomUUID().toString().replace("-", ""));
        return workingMapper.insertAnnex(entity);
    }

    @Override   //删除附件
    public int deleteAnnex(Integer[] attachfileids) {
        String updatedby = SecurityUtils.getUsername();
        return workingMapper.deleteAnnex(attachfileids, updatedby);
    }

    @Override
    public int updateConnectapplyById(String applyId) {
        return workingMapper.updateConnectapplyById(applyId);
    }

    public RegisterModel getRegisterModel(String applyId) {
        RegisterModel model = workingMapper.selectRegisterModelById(applyId);
        List<CollectModel> collectList = workingMapper.selectCollectdevdetailById(model.getApplyId());
        if (StringUtils.isNotEmpty(collectList)) {
            int no = 0;
            for (CollectModel collectModel : collectList) {
                List<DeviceModel> list = workingMapper.selectDeviceModelById(collectModel.getDetailId());
                if (StringUtils.isNotEmpty(list)) collectModel.setDevices(list);
                ++no;
                collectModel.setColno(no);
            }
            model.setCollects(collectList);
        }
        return model;
    }

    @Override //查询联网申请详情
    public Map<String, Object> selectRegisterModelById(String applyId) {
        RegisterModel model = getRegisterModel(applyId);
        Map<String, Object> map = new HashMap<>();
        map.put("applyId", model.getApplyId());
        map.put("sourceId", model.getSourceId());
        map.put("sourcename", model.getSourceName());
        map.put("industryid", model.getIndustryId());
        map.put("envchargeman", model.getEnvChargeMan());
        map.put("socialcreditcode", model.getSocialCreditCode());
        map.put("contacttel", model.getContactTel());
        map.put("networkoperator", model.getNetworkOperator());
        map.put("townid", model.getTownId());
        map.put("address", model.getAddress());
        map.put("longitude", model.getLongitude());
        map.put("latitude", model.getLatitude());
        map.put("applyperson", model.getApplyPerson());
        map.put("applydate", model.getApplyDate());
        map.put("contactcellphone", model.getContactCellPhone());
        map.put("status", model.getStatus());
        map.put("townname", model.getTownName());
        map.put("industryname", model.getIndustryName());
        List<CollectModel> collects = model.getCollects();
        List<Map<String, Object>> list_f = new ArrayList<>();
        List<Map<String, Object>> list_t = new ArrayList<>();
        for (CollectModel collect : collects) {
            Map<String, Object> map_f = new HashMap<>();
            map_f.put("detailid", collect.getDetailId());
            map_f.put("applyid", collect.getApplyId());
            map_f.put("checkpointdesc", collect.getCheckpointDesc());
            map_f.put("collectdevdesc", collect.getCollectDevDesc());
            map_f.put("collectdevspec", collect.getCollectDevSpec());
            map_f.put("mn", collect.getMn());
            map_f.put("colno", collect.getColno());
            List<DeviceModel> devices = collect.getDevices();
            if (StringUtils.isNotEmpty(devices)) {
                for (DeviceModel device : devices) {
                    Map<String, Object> map_t = new HashMap<>();
                    map_t.put("detailid", device.getDetailId());
                    map_t.put("collectdevid", device.getCollectDevId());
                    map_t.put("element", device.getElement());
                    map_t.put("brand", device.getBrand());
                    map_t.put("type", device.getType());
                    map_t.put("alarmmin", device.getAlarmMin());
                    map_t.put("alarmmax", device.getAlarmMax());
                    map_t.put("mn", device.getMn());
                    map_t.put("checkpointdesc", device.getCheckpointDesc());
                    list_t.add(map_t);
                }
//            map_f.put("device", list_t);
                list_f.add(map_f);
            }
        }
        map.put("firstTable", list_f);
        map.put("twoTable", list_t);
        return map;
    }

}
