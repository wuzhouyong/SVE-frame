package com.ruoyi.project.enterprise.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.enterprise.service.PfBasSourceCountService;
import com.ruoyi.project.enterprise.domain.DisallowanceEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/enterprise/monitor_task")
public class PfBasSourceCountController extends BaseController {

    @Autowired
    private PfBasSourceCountService pfBasSourceCountService;

    @GetMapping("/summary")
    @DataScope(regionAlias = "a")
    public TableDataInfo summary(MonitorTaskParam param) {
        if (param.getListType().equals("-1"))
            param.setListTypes(new String[]{"1", "0"});
        else
            param.setListTypes(new String[]{param.getListType()});
        List<PfBasSourceCount> countList = pfBasSourceCountService.getCountList(param);
        return getDataTable(countList);
    }

    @PostMapping("/task")
    @DataScope(regionAlias = "a")
    public TableDataInfo task(@RequestBody MonitorTaskParam param) {
        if (param.getListType().equals("-1"))
            param.setListTypes(new String[]{"1", "0"});
        else
            param.setListTypes(new String[]{param.getListType()});
        startPage();
        List<PfBasSourceList> sourceList = pfBasSourceCountService.getSourceList(param);
        return getDataTable(sourceList);
    }

    @GetMapping("/count")   //  /enterprise/monitor_task/count
    public AjaxResult count(MonitorTaskParam param) {
        //查找镇街企业是否被监控
        List<ExportCountEntity> mapList = null;
        if (param.getListType().equals("-1"))
            param.setListTypes(new String[]{"1", "0"});
        else
            param.setListTypes(new String[]{param.getListType()});
        try {
            mapList = pfBasSourceCountService.getExportCountData(param);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success(mapList);
    }

    //查询企业信息
    @PostMapping("/source")   //  /enterprise/monitor_task/source
    @DataScope(regionAlias = "s")
    public TableDataInfo selectEnterpriseInformation(@RequestBody EnterpriseEntity entity) {
        if (entity.getListType().equals("-1"))
            entity.setListTypes(new String[]{"1", "0"});
        else
            entity.setListTypes(new String[]{entity.getListType()});

        startPage();             //查询企业信息
        List<TaskSourceEntity> list = pfBasSourceCountService.selectEnterpriseInformation(entity);
        return getDataTable(list);
    }


    @PostMapping("/construction_accepts")
    @DataScope(regionAlias = "s")
    public TableDataInfo constructionAccepts(@RequestBody EnterpriseEntity entity) {

        startPage();
        List<TaskSourceEntity> list = pfBasSourceCountService.constructionAccepts(entity);
        return getDataTable(list);
    }

    @PostMapping("/task_changes")
    @DataScope(regionAlias = "s")
    public TableDataInfo taskChanges(@RequestBody EnterpriseEntity entity) {

        startPage();
        List<TaskSourceEntity> list = pfBasSourceCountService.taskChanges(entity);
        return getDataTable(list);
    }

    //更新合同审核
    @PreAuthorize("@ss.hasPermi('information:con:unit')")
    @GetMapping("/audit")   //  /enterprise/monitor_task/audit
    public AjaxResult editConstructioninfo(String tagHeadId, String pfSourceId) {
        int rownum = 0;
        try {
            rownum = pfBasSourceCountService.editConstructioninfo(tagHeadId, pfSourceId);
        } catch (Exception e) {

            return AjaxResult.error(e.getMessage());
        }
        return toAjax(rownum);
    }

    //更新验收审核
    @PreAuthorize("@ss.hasPermi('accept:con:unit')")
    @GetMapping("/accept")   //  /enterprise/monitor_task/accept
    public AjaxResult modifyConstructioninfo(String tagHeadId, String pfSourceId) {
        int rownum = 0;
        try {
            rownum = pfBasSourceCountService.modifyConstructioninfo(tagHeadId, pfSourceId);
        } catch (Exception e) {
            return AjaxResult.error(e.getMessage());
        }
        return toAjax(rownum);
    }

    /**
     * 新增施工驳回信息  /enterprise/monitor_task/reject
     */
    @Log(title = "新增施工驳回信息", businessType = BusinessType.INSERT)
    @PostMapping("/reject")
    public ResultEntity addDisallowance(@RequestBody DisallowanceEntity entity) {
        int rownum = 0;
        try {
            rownum = pfBasSourceCountService.insertDisallowance(entity);
        } catch (Exception e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success(rownum);
    }

    /**
     * 施工驳回信息列表  /enterprise/monitor_task/reject_list
     */
    @PostMapping("/reject_list")
    public ResultEntity getDisallowanceList(@RequestBody DisallowanceEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return ResultEntity.success(pfBasSourceCountService.getDisallowanceList(entity));
    }

}