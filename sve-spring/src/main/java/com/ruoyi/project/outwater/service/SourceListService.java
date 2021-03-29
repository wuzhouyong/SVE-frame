package com.ruoyi.project.outwater.service;

import com.ruoyi.project.outwater.domain.OWNameIdEntity;
import com.ruoyi.project.outwater.domain.OWSourceParam;

import java.util.List;


public interface SourceListService {

    /**
     * 查詢污染源列表
     */
    public List<OWNameIdEntity> selectSourceList(OWSourceParam entity);


}
