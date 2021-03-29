package com.ruoyi.project.youyan.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.youyan.domain.YYOutletEntity;
import com.ruoyi.project.youyan.service.YYOutletService;
import com.ruoyi.project.youyan.service.YYVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2020-09-08
 */
@RestController
@RequestMapping("/youyan/outlet")
public class YYOutletController extends BaseController {
    @Autowired
    private YYOutletService outletService;


    /**
     * 查询【请填写功能名称】列表
     */
    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody YYOutletEntity basOutlet) {
        startPage();
        List<YYOutletEntity> list = outletService.selectBasOutletList(basOutlet);
        return getDataTable(list);
    }

    /**
     * 新增【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('youyan:outlet:add')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public ResultEntity add(@RequestBody YYOutletEntity basOutlet) {
        int isAcc = 0;
        try {
            isAcc = outletService.insertBasOutlet(basOutlet);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    /**
     * 修改【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('youyan:outlet:edit')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public ResultEntity edit(@RequestBody YYOutletEntity basOutlet) {
        int isAcc = 0;
        try {
            isAcc = outletService.updateBasOutlet(basOutlet);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    /**
     * 删除【请填写功能名称】
     */
    @PreAuthorize("@ss.hasPermi('youyan:outlet:del')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @DeleteMapping("/{outletIds}")
    public ResultEntity remove(@PathVariable String[] outletIds) {
        int isAcc = 0;
        try {
            isAcc = outletService.deleteBasOutletByIds(outletIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }
}
