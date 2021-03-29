package com.ruoyi.project.voc.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.voc.domain.VocNameIdEntity;
import com.ruoyi.project.voc.domain.VocSourceParam;
import com.ruoyi.project.voc.service.VocCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/voc/source")
public class VocSourceController extends BaseController{

    @Autowired
    private VocCheckPointService checkPointService;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody VocSourceParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        startPage();
        List<VocNameIdEntity> list = checkPointService.selectSourceList(entity);
        return getDataTable(list);
    }
}
