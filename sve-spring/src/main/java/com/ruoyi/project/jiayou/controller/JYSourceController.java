package com.ruoyi.project.jiayou.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.jiayou.domain.JYNameIdEntity;
import com.ruoyi.project.jiayou.domain.JYSourceParam;
import com.ruoyi.project.jiayou.service.JYCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/jiayou/source")
public class JYSourceController extends BaseController{

    @Autowired
    private JYCheckPointService checkPointService;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody JYSourceParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        startPage();
        List<JYNameIdEntity> list = checkPointService.selectSourceList(entity);
        return getDataTable(list);
    }
}
