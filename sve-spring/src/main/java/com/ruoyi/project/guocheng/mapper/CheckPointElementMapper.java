package com.ruoyi.project.guocheng.mapper;

import com.ruoyi.project.guocheng.domain.CPElementEntity;
import com.ruoyi.project.guocheng.domain.DeleteEntity;
import com.ruoyi.project.guocheng.domain.GCNameIdEntity;

import java.util.List;

public interface CheckPointElementMapper {

    public List<CPElementEntity> selectCPElement(CPElementEntity entity);

    public List<GCNameIdEntity> selectElement(GCNameIdEntity entity);

    public int insertCPElement(CPElementEntity entity);

    public int updateCPElement(CPElementEntity entity);

    public int deleteCPElement(DeleteEntity entity);

    public int repeatCheck(CPElementEntity entity);
}
