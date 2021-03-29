package com.ruoyi.project.lingxing.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.lingxing.domain.LXNameIdEntity;
import com.ruoyi.project.lingxing.domain.LXSourceParam;
import com.ruoyi.project.lingxing.domain.LXTransbillinfo;
import com.ruoyi.project.lingxing.service.LXCheckPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/lingxing/source")
public class LXSourceController extends BaseController {

    @Autowired
    private LXCheckPointService checkPointService;

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody LXSourceParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        startPage();
        List<LXNameIdEntity> list = checkPointService.selectSourceList(entity);
        return getDataTable(list);
    }

    @PostMapping("/billinfo_list") //危废列表
    public TableDataInfo transbillinfoList(@RequestBody LXTransbillinfo info) {
        startPage();
        List<LXTransbillinfo> list = checkPointService.selectTransbillinfoList(info);
        return getDataTable(list);
    }


}
