package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.enterprise.mapper.PfBasSourceCountMapper;
import com.ruoyi.project.enterprise.service.PfBasSourceCountService;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.OutwaterCheckPointEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@DataSource(value = DataSourceType.MASTER)
public class PfBasSourceCountServiceImpl implements PfBasSourceCountService {

    @Autowired
    private PfBasSourceCountMapper pfBasSourceCountMapper;

    /**
     * 获取统计列表
     */

    @Override
    public List<PfBasSourceCount> getCountList(MonitorTaskParam param) {
        return pfBasSourceCountMapper.getCountList(param);
    }

    @Override
    public List<PfBasSourceList> getSourceList(MonitorTaskParam param) {
        List<PfBasSourceList> list = pfBasSourceCountMapper.getSourceList(param);
        if (list == null || list.isEmpty()) return null;
        int i = 1;
        for (PfBasSourceList bas : list) {
            bas.setNumber(String.valueOf(i));
            i++;
        }
        return list;
    }

    @Override
    public int getTotal(String townCode, String fullName) {
        return pfBasSourceCountMapper.getTotal(townCode, fullName);
    }

    @Override
    public List<ExportCountEntity> getExportCountData(MonitorTaskParam param) throws Exception {
        if (param.getTownCodes() != null && param.getTownCodes().length != 0) {
            if (param.getTownCodes().length > 1)
                return pfBasSourceCountMapper.getExportCountData(param);
            else
                return pfBasSourceCountMapper.getExportCountData1(param);
        } else
            throw new Exception("请选择镇街");
    }

    @Override // 查询排水户信息
    @DataSource(value = DataSourceType.OUTWATER)
    public List<OutwaterCheckPointEntity> getExportOutwaterData(CollectCheckPointEntity entity) {

        List<OutwaterCheckPointEntity> list = pfBasSourceCountMapper.getExportOutwaterData(entity);
        for (OutwaterCheckPointEntity temp : list) {
            if (temp.getIsOnline() != null) {
                if (temp.getIsOnline().equals("0")) {
                    temp.setIsOnline("离线");
                }
                if (temp.getIsOnline().equals("1")) {
                    temp.setIsOnline("在线");
                }
            } else {
                temp.setIsOnline("离线");
            }
            if (temp.getCheckPointType() != null) {
                if (temp.getCheckPointType().equals("1")) {
                    temp.setCheckPointType("污水检测井");
                }
                if (temp.getCheckPointType().equals("2")) {
                    temp.setCheckPointType("雨水检测井");
                }
            }
            if (temp.getCheckDate() != null) {
                String date = DateUtils.parseDate(temp.getCheckDate(), "yyyy-MM-dd HH:mm:ss");
                temp.setUpdatedBy(date);
            }

        }
        return list;
    }

    @Override // 查询企业信息
    public List<TaskSourceEntity> selectEnterpriseInformation(EnterpriseEntity entity) {

        return pfBasSourceCountMapper.selectEnterpriseInformation(entity);
    }

    @Override
    public List<TaskSourceEntity> constructionAccepts(EnterpriseEntity entity) {
        return pfBasSourceCountMapper.constructionAccepts(entity);
    }

    @Override
    public List<TaskSourceEntity> taskChanges(EnterpriseEntity entity) {
        return pfBasSourceCountMapper.taskChanges(entity);
    }

    @Override // 更新合同审核
    public int editConstructioninfo(String tagHeadId, String pfSourceId) throws Exception {
        String username = SecurityUtils.getUsername();
        int rownum = pfBasSourceCountMapper.editConstructioninfo(tagHeadId, pfSourceId, username);
        if (rownum > 0) {
            return rownum;
        } else
            throw new Exception("更新失败");
    }

    @Override // 更新验收审核
    public int modifyConstructioninfo(String tagHeadId, String pfSourceId) throws Exception {
        String username = SecurityUtils.getUsername();
        int rownum = pfBasSourceCountMapper.modifyConstructioninfo(tagHeadId, pfSourceId, "1", username);
        if (rownum > 0) {
            DisallowanceEntity entity = new DisallowanceEntity();
            entity.setTagHeadId(tagHeadId);
            entity.setSourceId(pfSourceId);
            entity.setReturnResult(1);
            entity.setReturnReason("验收通过");
            entity.setCreatedBy(SecurityUtils.getUsername());
            pfBasSourceCountMapper.insertDisallowance(entity);
            return rownum;
        } else
            throw new Exception("更新失败");
    }

    @Override // 新增施工驳回信息
    public int insertDisallowance(DisallowanceEntity entity) throws Exception {
        entity.setReturnResult(0);
        entity.setCreatedBy(SecurityUtils.getUsername());
        int count = pfBasSourceCountMapper.insertDisallowance(entity);
        if (count == 1) {
            pfBasSourceCountMapper.updateConstructioninfo(entity);
        } else throw new Exception("新增施工驳回失败");
        return entity.getReturnId();
    }

    @Override // 更新验收审核
    public List<DisallowanceEntity> getDisallowanceList(DisallowanceEntity entit) {
        return pfBasSourceCountMapper.getDisallowanceList(entit);
    }


}
