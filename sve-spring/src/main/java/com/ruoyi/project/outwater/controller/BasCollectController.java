package com.ruoyi.project.outwater.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableSupport;
import com.ruoyi.project.outwater.domain.entity.BasCollectEntity;
import com.ruoyi.project.outwater.domain.entity.CollectCheckPointEntity;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.outwater.service.BasCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@DataSource(value = DataSourceType.OUTWATER)
@RestController
@RequestMapping("/outwater/basCollect")
public class BasCollectController extends BaseController {

    @Autowired
    private BasCollectService collectService;

    /**
     * 新增数采仪
     */
    @PreAuthorize("@ss.hasPermi('outwater:collect:add')")
    @Log(title = "数采新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public ResultEntity add(@RequestBody CollectCheckPointEntity entity)
    {
        entity.setCollectId(java.util.UUID.randomUUID().toString());
        entity.setCheckPointId(java.util.UUID.randomUUID().toString());
        entity.setCreatedBy(SecurityUtils.getUsername());
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int res = collectService.insertData(entity);
        if (res == 4)
        {
            ResultEntity.success("数采新增成功");
        } else {
            ResultEntity.error("数采新增失败");
        }
        return ResultEntity.success(null);
    }

    /**
     * 编辑数采仪
     */
    @PreAuthorize("@ss.hasPermi('outwater:collect:edit')")
    @Log(title = "数采编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public ResultEntity edit(@RequestBody BasCollectEntity entity)
    {
        entity.setUpdatedBy(SecurityUtils.getUsername());

        int res = collectService.updateBasCollect(entity);
        if (res > 0)
        {
            ResultEntity.success("数采修改成功");
        } else {
            ResultEntity.error("数采修改失败");
        }
        return ResultEntity.success(null);
    }

    /**
     * 删除数采仪
     */
    @PreAuthorize("@ss.hasPermi('outwater:collect:remove')")
    @Log(title = "数采删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{collectIds}")
    public ResultEntity delete(@PathVariable String[] collectIds)
    {
        //entity.setUpdatedBy(SecurityUtils.getUsername());
        String collectId = "";
        for (int i = 0; i < collectIds.length; i++){
            if (i == collectIds.length - 1){
                collectId += "'" + collectIds[i] + "'";
            } else {
                collectId += "'" + collectIds[i] + "',";
            }
        }
        int res = collectService.deleteBasCollectById(collectId, SecurityUtils.getUsername());
        if (res > 0)
        {
            ResultEntity.success("数采删除成功");
        } else {
            ResultEntity.error("数采删除失败");
        }
        return ResultEntity.success(null);
    }

    @PreAuthorize("@ss.hasPermi('outwater:collect:list')")
    /*@DataScope(regionAlias = "s")*/
    @PostMapping("/list")
    public ResultEntity list(@RequestBody BasCollectEntity entity)
    {
        startPage();
        PageDomain pageDomain = TableSupport.buildPageRequest();
        int pageNum = pageDomain.getPageNum();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())){
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<BasCollectEntity> list = collectService.selectBasCollectList(entity);
        int count = collectService.getTotal(entity);
        return getDataList(list, pageNum, count);
    }

    @PostMapping("/idNameList")
    public ResultEntity idNameList(@RequestBody BasCollectEntity entity)
    {
        List<NameAndIdEntity> list = collectService.selectBasCollectIdNameList(entity.getCollectName(), entity.getCollectId());
        return ResultEntity.success(list);
    }




}
