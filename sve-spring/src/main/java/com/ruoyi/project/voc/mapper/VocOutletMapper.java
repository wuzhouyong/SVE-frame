package com.ruoyi.project.voc.mapper;

import com.ruoyi.project.voc.domain.VocDeleteEntity;
import com.ruoyi.project.voc.domain.VocOutletEntity;

import java.util.List;

public interface VocOutletMapper {

    public List<VocOutletEntity> selectOutlet(VocOutletEntity entity);

    public int insertOutlet(VocOutletEntity entity);

    public int updateOutlet(VocOutletEntity entity);

    public int deleteOutlet(VocDeleteEntity entity);
}
