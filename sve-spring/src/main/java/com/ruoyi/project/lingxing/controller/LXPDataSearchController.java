package com.ruoyi.project.lingxing.controller;

import com.ruoyi.common.domain.DataSearchEntity;
import com.ruoyi.common.domain.DataSearchParam;
import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.DateStringUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.lingxing.service.LXPDataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

@RestController
@RequestMapping("/lingxingp/data_search")
public class LXPDataSearchController extends BaseController {

    @Autowired
    private LXPDataSearchService dataSearchService;

    @PostMapping("/lastdata")
    public ResultEntity lastData(String sourceId) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        }
        List<DataSearchEntity> getLastData = dataSearchService.getLastData(sourceId);
        return ResultEntity.success(getLastData);
    }

    @PostMapping("/real")
    public ResultEntity real(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return dataSearchService.getReal(entity);
    }

    @PostMapping("/minute")
    public ResultEntity minute(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return dataSearchService.getMinute(entity);
    }

    @PostMapping("/hour")
    public ResultEntity hour(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return dataSearchService.getHour(entity);
    }

    @PostMapping("/day")
    public ResultEntity day(@RequestBody DataSearchParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return dataSearchService.getDay(entity);
    }



}
