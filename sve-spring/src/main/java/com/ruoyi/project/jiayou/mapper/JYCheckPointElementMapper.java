package com.ruoyi.project.jiayou.mapper;

import com.ruoyi.project.jiayou.domain.JYCPElementEntity;
import com.ruoyi.project.jiayou.domain.JYDeleteEntity;
import com.ruoyi.project.jiayou.domain.JYNameIdEntity;

import java.util.List;

public interface JYCheckPointElementMapper {

    public List<JYCPElementEntity> selectCPElement(JYCPElementEntity entity);

    public List<JYNameIdEntity> selectElement(JYNameIdEntity entity);

    public int insertCPElement(JYCPElementEntity entity);

    public int updateCPElement(JYCPElementEntity entity);

    public int deleteCPElement(JYDeleteEntity entity);

    public int repeatCheck(JYCPElementEntity entity);
}
