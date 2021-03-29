package com.ruoyi.project.jiayou.mapper;

import com.ruoyi.project.jiayou.domain.JYDeleteEntity;
import com.ruoyi.project.jiayou.domain.JYOutletEntity;
import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXOutletEntity;

import java.util.List;

public interface JYOutletMapper {

    public List<JYOutletEntity> selectOutlet(JYOutletEntity entity);

    public int insertOutlet(JYOutletEntity entity);

    public int updateOutlet(JYOutletEntity entity);

    public int deleteOutlet(JYDeleteEntity entity);

    public List<JYOutletEntity> selectOutletList(String sourceId);
}
