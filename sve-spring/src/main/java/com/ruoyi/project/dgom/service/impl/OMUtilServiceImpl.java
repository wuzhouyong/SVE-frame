package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.AnnexEntity;
import com.ruoyi.project.dgom.domain.LabelEntity;
import com.ruoyi.project.dgom.mapper.OMUtilMapper;
import com.ruoyi.project.dgom.service.OMUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-12-22 15:32
 */
@Service
@DataSource(value = DataSourceType.HBONLINE)
public class OMUtilServiceImpl implements OMUtilService {
    @Autowired
   private OMUtilMapper mapper;


    @Override
    public List<LabelEntity> getLabelsByParentId(Integer parentId) {
        return mapper.getLabelsByParentId(parentId);
    }

    @Override
    public List<Map> datadevList(String checkpointid) {
        return mapper.datadevList(checkpointid);
    }

    @Override
    public List<Map> checkpointidList(String sourceid) {
        return mapper.checkpointidList(sourceid);
    }

    @Override
    public List<AnnexEntity> selectAnnexList(AnnexEntity entity) {
        return mapper.selectAnnexList(entity);
    }

    @Override
    public Integer deleteAnnex(String[] attachfileids, String updatedby) {
        return mapper.deleteAnnex(attachfileids,updatedby);
    }

    @Override
    public AnnexEntity selectAnnexFileById(String attachfileid) {
        return mapper.selectAnnexFileById(attachfileid);
    }


}
