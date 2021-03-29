package com.ruoyi.project.jinshu.mapper;

import com.ruoyi.project.jinshu.domain.JSCPElementEntity;
import com.ruoyi.project.jinshu.domain.JSDeleteEntity;
import com.ruoyi.project.jinshu.domain.JSNameIdEntity;

import java.util.List;

public interface JSCheckPointElementMapper {

    public List<JSCPElementEntity> selectCPElement(JSCPElementEntity entity);

    public List<JSNameIdEntity> selectElement(JSNameIdEntity entity);

    public int insertCPElement(JSCPElementEntity entity);

    public int updateCPElement(JSCPElementEntity entity);

    public int deleteCPElement(JSDeleteEntity entity);

    public int repeatCheck(JSCPElementEntity entity);
}
