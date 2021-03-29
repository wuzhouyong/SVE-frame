package com.ruoyi.project.lwjh.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.lwjh.domain.LWJHCheckPointEntity;
import com.ruoyi.project.lwjh.service.LWJHCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/lwjh/point")
public class LWJHCheckPointController extends BaseController {

    @Autowired
    private LWJHCheckPointService checkPointService;

    @PostMapping("/list")
    public ResultEntity list(@RequestBody LWJHCheckPointEntity entity)
    {
        return checkPointService.selectCheckPoint(entity.getSourceId());
    }

    @Log(title = "排放口新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public ResultEntity add(@RequestBody LWJHCheckPointEntity entity) {
        return checkPointService.insertCheckPoint(entity);
    }

    @Log(title = "排放口修改", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public ResultEntity edit(@RequestBody LWJHCheckPointEntity entity) {
        return checkPointService.updateCheckPoint(entity);
    }

    @Log(title = "排放口删除", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    public ResultEntity remove(@RequestBody LWJHCheckPointEntity entity) {
        return checkPointService.removeCheckPoint(entity);
    }
}
