package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.project.enterprise.domain.label.LabelDetail;
import com.ruoyi.project.enterprise.domain.label.LabelHead;
import com.ruoyi.project.enterprise.domain.label.LabelSourceEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

public interface LabelMapper {
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
