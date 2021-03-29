package com.ruoyi.project.enterprise.service;

import com.ruoyi.project.enterprise.domain.label.LabelDetail;
import com.ruoyi.project.enterprise.domain.label.LabelHead;
import com.ruoyi.project.enterprise.domain.label.LabelSourceEntity;

import java.util.List;
import java.util.Map;

public interface LabelService {
    List<LabelHead> getLabelListByUser(String username);
    List<LabelSourceEntity> getSourceList(Map<String, String> param);
    List<LabelSourceEntity> getSourceListByLabel(Map<String, String> param);
    boolean existsTopLevel(int labelHeadId);
    boolean hasChildren(int parentId);
    int addLabelHead(LabelHead model);
    int editLabelHead(LabelHead model);
    int removeLabelHead(int labelHeadId);
    int addLabelDetail(LabelDetail model);
    int removeLabelDetail(LabelDetail model);
}
