package com.ruoyi.project.jiayou.service;

import com.ruoyi.project.jiayou.domain.JYDeleteEntity;
import com.ruoyi.project.jiayou.domain.JYOutletEntity;

import java.util.List;

public interface JYOutletService {

    public List<JYOutletEntity> selectOutlet(JYOutletEntity entity);

    public int insertOutlet(JYOutletEntity entity);

    public int updateOutlet(JYOutletEntity entity);

    public int deleteOutlet(JYDeleteEntity entity);

    public List<JYOutletEntity> selectOutletList(String sourceId);

}
