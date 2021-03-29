package com.ruoyi.project.lwjh.service;

import com.ruoyi.project.lwjh.domain.ElementData;

import java.util.List;

public interface LWJHCheckPointElementService {

    public List<ElementData> selectElementData(String checkpointId, String accessDate);

}
