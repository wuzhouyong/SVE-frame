package com.ruoyi.project.enterprise.service;

import com.ruoyi.project.enterprise.domain.PfBasElementRef;

import java.util.List;

public interface PfBasElementRefService {

    public int insertPfBasElementRef(PfBasElementRef entity);

    public int updatePfBasElementRef(PfBasElementRef entity);

    public int deletePfBasElementRef(int tagHeadId, int cpElemId);

    public List<PfBasElementRef> selectPfBasElementRef(PfBasElementRef entity);
}
