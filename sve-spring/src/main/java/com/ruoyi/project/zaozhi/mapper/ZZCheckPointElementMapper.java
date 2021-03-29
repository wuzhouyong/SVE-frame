package com.ruoyi.project.zaozhi.mapper;

import java.util.List;

import com.ruoyi.project.zaozhi.domain.ZZCPElementEntity;
import com.ruoyi.project.zaozhi.domain.ZZDeleteEntity;
import com.ruoyi.project.zaozhi.domain.ZZNameIdEntity;

public interface ZZCheckPointElementMapper {
    public List<ZZCPElementEntity> selectCPElement(ZZCPElementEntity entity);

    public List<ZZNameIdEntity> selectElement(ZZNameIdEntity entity);

    public int insertCPElement(ZZCPElementEntity entity);

    public int updateCPElement(ZZCPElementEntity entity);

    public int deleteCPElement(ZZDeleteEntity entity);

    public int repeatCheck(ZZCPElementEntity entity);
}
