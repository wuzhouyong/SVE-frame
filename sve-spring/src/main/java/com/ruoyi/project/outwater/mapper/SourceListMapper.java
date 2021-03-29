package com.ruoyi.project.outwater.mapper;

import com.ruoyi.project.outwater.domain.OWNameIdEntity;
import com.ruoyi.project.outwater.domain.OWSourceParam;

import java.util.List;

public interface SourceListMapper {
    /**
     * 查詢污染源列表
     */
    public List<OWNameIdEntity> selectSourceList(OWSourceParam entity);

}
