package com.ruoyi.project.enterprise.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.enterprise.domain.label.LabelDetail;
import com.ruoyi.project.enterprise.domain.label.LabelHead;
import com.ruoyi.project.enterprise.domain.label.LabelSourceEntity;
import com.ruoyi.project.enterprise.mapper.LabelMapper;
import com.ruoyi.project.enterprise.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@DataSource(value = DataSourceType.MASTER)
public class LabelServiceImpl implements LabelService {
    @Autowired
    private LabelMapper mapper;

    @Override
    public List<LabelHead> getLabelListByUser(String username) {
        return mapper.getLabelListByUser(username);
    }

    @Override
    public List<LabelSourceEntity> getSourceList(Map<String, String> param) {
        return mapper.getSourceList(param);
    }

    @Override
    public List<LabelSourceEntity> getSourceListByLabel(Map<String, String> param) {
        return mapper.getSourceListByLabel(param);
    }

    @Override
    public boolean existsTopLevel(int labelHeadId) {
        return mapper.existsTopLevel(labelHeadId);
    }

    @Override
    public boolean hasChildren(int parentId) {
        return mapper.hasChildren(parentId);
    }

    @Override
    public int addLabelHead(LabelHead model) {
        return mapper.addLabelHead(model);
    }

    @Override
    public int editLabelHead(LabelHead model) {
        return mapper.editLabelHead(model);
    }

    @Override
    public int removeLabelHead(int labelHeadId) {
        return mapper.removeLabelHead(labelHeadId);
    }

    @Override
    public int addLabelDetail(LabelDetail model) {
        return mapper.addLabelDetail(model);
    }

    @Override
    public int removeLabelDetail(LabelDetail model) {
        return mapper.removeLabelDetail(model);
    }
}
