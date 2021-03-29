package com.ruoyi.project.voc.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.voc.domain.VocBasFacility;
import com.ruoyi.project.voc.domain.VocBasTechnology;
import com.ruoyi.project.voc.domain.VocCPElementEntity;
import com.ruoyi.project.voc.service.VocBasFacilityService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2020-08-12
 */
@RestController
@RequestMapping("/voc/facility")
public class VocBasFacilityController extends BaseController {
    @Autowired
    private VocBasFacilityService vocBasFacilityService;

    //查询生产线管理   /voc/facility/production/list
    @GetMapping("/production/list")
    public TableDataInfo productionList(VocBasFacility basFacility) {
        startPage();
        List<VocBasFacility> list = vocBasFacilityService.selectProductionList(basFacility);
        return getDataTable(list);
    }

    // 新增生产线管理  /voc/facility/production/add
    @Log(title = "【新增生产线管理】", businessType = BusinessType.INSERT)
    @PostMapping("/production/add")
    public ResultEntity productionAdd(@RequestBody VocBasFacility basFacility) {
        int isAcc = 0;
        List<VocCPElementEntity> entity = basFacility.getEntity();
        try {
            isAcc = vocBasFacilityService.insertProduction(basFacility,entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    // 编辑生产线管理  /voc/facility/production/modify
    @Log(title = "【编辑生产线管理】", businessType = BusinessType.INSERT)
    @PostMapping("/production/modify")
    public ResultEntity productionModify(@RequestBody VocBasFacility basFacility) {
        int isAcc = 0;
        List<VocCPElementEntity> entity = basFacility.getEntity();
        try {
            isAcc = vocBasFacilityService.updateProduction(basFacility,entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    // 删除生产线管理  /voc/facility/production/delete
    @Log(title = "【删除生产线管理】", businessType = BusinessType.INSERT)
    @DeleteMapping("/production/delete/{facilityIds}")
    public ResultEntity productionDeleted(@PathVariable Integer[] facilityIds) {
        int isAcc = 0;
        try {
            isAcc = vocBasFacilityService.deleteProduction(facilityIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    //查询治理设施   /voc/facility/handle/list
    @GetMapping("/handle/list")
    public TableDataInfo handleFacilityList(VocBasFacility basFacility) {
        startPage();
        List<VocBasFacility> list = vocBasFacilityService.selectHandleList(basFacility);
        return getDataTable(list);
    }

    // 新增治理设施  /voc/facility/handle/add
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/handle/add")
    public ResultEntity handleAdd(@RequestBody VocBasFacility basFacility) {
        int isAcc = 0;
        List<VocBasTechnology> techno = basFacility.getTechno();
        try {
            isAcc = vocBasFacilityService.insertHandleFacility(basFacility,techno);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    // 修改治理设施管理  /voc/facility/handle/modify
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/handle/modify")
    public ResultEntity handleModify(@RequestBody VocBasFacility basFacility) {
        int isAcc = 0;
        List<VocBasTechnology> techno = basFacility.getTechno();
        try {
            isAcc = vocBasFacilityService.updateHandle(basFacility,techno);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    //删除治理设施管理  /voc/facility/handle/delete
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @DeleteMapping("/handle/delete/{facilityIds}")
    public ResultEntity handleMove(@PathVariable Integer[] facilityIds) {
        int isAcc = 0;
        try {
            isAcc = vocBasFacilityService.deleteHandle(facilityIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    //查询收集风机管理   /voc/facility/collect_blower/list
    @GetMapping("/collect_blower/list")
    public TableDataInfo collectList(VocBasFacility basFacility) {
        startPage();
        List<VocBasFacility> list = vocBasFacilityService.selectCollectList(basFacility);
        return getDataTable(list);
    }

    // 新增收集风机管理  /voc/facility/collect_blower/add
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/collect_blower/add")
    public ResultEntity collectAdd(@RequestBody VocBasFacility basFacility) {
        int isAcc = 0;
        List<VocCPElementEntity> entity = basFacility.getEntity();
        try {
            isAcc = vocBasFacilityService.insertCollect(basFacility,entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    // 修改收集风机管理  /voc/facility/collect_blower/modify
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/collect_blower/modify")
    public ResultEntity collectBlowerModify(@RequestBody VocBasFacility basFacility) {
        int isAcc = 0;
        List<VocCPElementEntity> entity = basFacility.getEntity();
        try {
            isAcc = vocBasFacilityService.updateCollectBlowerModify(basFacility,entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    // 删除收集风机管理  /voc/facility/collect_blower/delete
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @DeleteMapping("/collect_blower/delete/{facilityIds}")
    public ResultEntity collectBlowerMove(@PathVariable Integer[] facilityIds) {
        int isAcc = 0;
        try {
            isAcc = vocBasFacilityService.deleteCollectBlower(facilityIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    //查询排放口管理   /voc/facility/emission_point/list
    @GetMapping("/emission_point/list")
    public TableDataInfo emissionPointList(VocBasFacility basFacility) {
        startPage();
        List<VocBasFacility> list = vocBasFacilityService.selectEmissionPointList(basFacility);
        return getDataTable(list);
    }

    // 新增排放口管理  /voc/facility/emission_point/add
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/emission_point/add")
    public ResultEntity emissionPointAdd(@RequestBody VocBasFacility basFacility) {
        int isAcc = 0;
        List<VocCPElementEntity> entity = basFacility.getEntity();
        try {
            isAcc = vocBasFacilityService.insertEmissionPoint(basFacility,entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    // 修改排放口管理  /voc/facility/emission_point/modify
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/emission_point/modify")
    public ResultEntity emissionPointModify(@RequestBody VocBasFacility basFacility) {
        int isAcc = 0;
        List<VocCPElementEntity> entity = basFacility.getEntity();
        try {
            isAcc = vocBasFacilityService.updateEmissionPointrModify(basFacility,entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    // 删除排放口管理  /voc/facility/emission_point/delete
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @DeleteMapping("/emission_point/delete/{facilityIds}")
    public ResultEntity emissionPointMove(@PathVariable Integer[] facilityIds) {
        int isAcc = 0;
        try {
            isAcc = vocBasFacilityService.deleteEmissionPointr(facilityIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    //查询排放风机管理   /voc/facility/emission_blower/list
    @GetMapping("/emission_blower/list")
    public TableDataInfo blowerList(VocBasFacility basFacility) {
        startPage();
        List<VocBasFacility> list = vocBasFacilityService.selectBlowerList(basFacility);
        return getDataTable(list);
    }

    // 新增排放风机管理  /voc/facility/emission_blower/add
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/emission_blower/add")
    public ResultEntity blowerAdd(@RequestBody VocBasFacility basFacility) {
        int isAcc = 0;
        List<VocCPElementEntity> entity = basFacility.getEntity();
        try {
            isAcc = vocBasFacilityService.insertBlower(basFacility,entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    // 修改排放风机管理  /voc/facility/emission_blower/modify
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/emission_blower/modify")
    public ResultEntity blowerModify(@RequestBody VocBasFacility basFacility) {
        int isAcc = 0;
        List<VocCPElementEntity> entity = basFacility.getEntity();
        try {
            isAcc = vocBasFacilityService.updateEmissionBlowerModify(basFacility,entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    // 删除排放风机管理  /voc/facility/emission_blower/delete
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @DeleteMapping("/emission_blower/delete/{facilityIds}")
    public ResultEntity blowerDelete(@PathVariable Integer[] facilityIds) {
        int isAcc = 0;
        try {
            isAcc = vocBasFacilityService.deleteEmissionBlower(facilityIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    // 查询治理设施工况详情列表  /voc/facility/working_condition/details
    @Log(title = "【治理设施工况详情】", businessType = BusinessType.INSERT)
    @GetMapping("/working_condition/details")
    public ResultEntity workingConditionDetails(String sourceId) {
        if(!ObjectUtils.anyNotNull(sourceId)){
            return ResultEntity.error("请传递sourceId");
        }
        return ResultEntity.success(vocBasFacilityService.workingConditionDetails(sourceId));
    }

    // 查询治理设施工况详情  /voc/facility/working_condition/getinfo
    @GetMapping("/working_condition/getinfo")
    public ResultEntity workingConditionGetinfo(String sourceId,String time) {
        if (!ObjectUtils.anyNotNull(sourceId)) {
            return ResultEntity.error("请传递sourceId");
        }
        return ResultEntity.success(vocBasFacilityService.workingConditionGetinfo(sourceId,time));
    }

}
