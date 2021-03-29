package com.ruoyi.project.youyan.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.youyan.domain.YYFacility;
import com.ruoyi.project.youyan.service.YYFacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * 油烟治理设施信息Controller
 *
 * @author ruoyi
 * @date 2020-09-22
 */
@RestController
@RequestMapping(value = "/youyan/facility")
public class YYFacilityController extends BaseController {
    @Autowired
    private YYFacilityService facilityService;

    //查询治理设施
    @Log(title = "查询排放风机", businessType = BusinessType.DELETE)
    @PostMapping("/list")
    public ResultEntity selectBasFacilityList(@RequestBody YYFacility facility) {
        return ResultEntity.success(facilityService.selectBasFacilityList(facility));
    }

    //新增治理设施
    @Log(title = "新增排放风机", businessType = BusinessType.DELETE)
    @PostMapping("/add")
    public ResultEntity addBasFacility(@RequestBody YYFacility facility) {
        ResultEntity result = null;
        try {
            result = facilityService.insertBasFacility(facility);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return result;
    }

    //修改治理设施
    @Log(title = "修改排放风机", businessType = BusinessType.DELETE)
    @PostMapping("/edit")
    public ResultEntity modifyBasFacility(@RequestBody YYFacility facility) {
        ResultEntity result = null;
        try {
            result = facilityService.updateBasFacility(facility);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return result;
    }

    //删除治理设施
    @Log(title = "删除排放风机", businessType = BusinessType.DELETE)
    @DeleteMapping("/delete/{facilityId}")
    public ResultEntity removeBasFacility(@PathVariable Integer facilityId) {
        ResultEntity result = null;
        try {
            result = facilityService.deleteBasFacilityById(facilityId);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return result;
    }

    //根据id查找父级对象
    @Log(title = "查询同级id", businessType = BusinessType.DELETE)
    @GetMapping("/samelevel/{facilityId}")
    public ResultEntity sameLevel(@PathVariable Integer facilityId) {
        return facilityService.selectSameLevelByfacilityId(facilityId);
    }

    //同步关联子级
    @Log(title = "同步关联子级", businessType = BusinessType.DELETE)
    @GetMapping("/related")
    public ResultEntity synchronizeRelated(@RequestParam Integer[] facilityPids, @RequestParam Integer facilityId) {
        return facilityService.updateBasFacilityRelation(facilityPids, facilityId);
    }

}
