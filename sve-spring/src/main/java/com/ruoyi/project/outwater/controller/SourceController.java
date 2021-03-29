package com.ruoyi.project.outwater.controller;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.outwater.domain.OWNameIdEntity;
import com.ruoyi.project.outwater.domain.OWSourceParam;
import com.ruoyi.project.outwater.service.SourceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("OWS")
@RequestMapping("/outwater/source")
public class SourceController extends BaseController{

    @Autowired
    private SourceListService sourceListService;

    /**
     * 获取企业列表
     */

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody OWSourceParam entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        startPage();
        List<OWNameIdEntity> list = sourceListService.selectSourceList(entity);
        return getDataTable(list);
    }
}
