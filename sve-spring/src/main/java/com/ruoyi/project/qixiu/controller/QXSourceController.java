package com.ruoyi.project.qixiu.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.qixiu.domain.QXNameIdEntity;
import com.ruoyi.project.qixiu.domain.QXSourceParam;
import com.ruoyi.project.qixiu.service.QXCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/qixiu/source")
public class QXSourceController extends BaseController{

    @Autowired
    private QXCheckPointService checkPointService;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody QXSourceParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        startPage();
        List<QXNameIdEntity> list = checkPointService.selectSourceList(entity);
        return getDataTable(list);
    }
}
