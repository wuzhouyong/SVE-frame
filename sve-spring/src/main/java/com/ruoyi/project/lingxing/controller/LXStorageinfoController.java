package com.ruoyi.project.lingxing.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.lingxing.domain.LXMeterinfo;
import com.ruoyi.project.lingxing.domain.LXStorageinfo;
import com.ruoyi.project.lingxing.service.LXStorageinfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2020-08-12
 */
@RestController
@RequestMapping("/lingxing")
public class LXStorageinfoController extends BaseController {
    @Autowired
    private LXStorageinfoService storageinfoService;

    //查询废水收集池   /lingxing/storage/list
    @GetMapping("/storage/list")
    public TableDataInfo storageinfoList(LXStorageinfo storageinfo) {
        startPage();
        List<LXStorageinfo> list = storageinfoService.selectStorageinfoList(storageinfo);
        return getDataTable(list);
    }

    //新增废水收集池   /lingxing/storage/add
    @PostMapping("/storage/add")
    public ResultEntity storageinfoAdd(@RequestBody LXStorageinfo storageinfo) {
        int isAcc = 0;
        try {
            isAcc = storageinfoService.insertStorageinfo(storageinfo);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    //编辑废水池   /lingxing/storage/edit
    @PostMapping("/storage/edit")
    public ResultEntity storageinfoEdit(@RequestBody LXStorageinfo storageinfo) {
        int isAcc = 0;
        try {
            isAcc = storageinfoService.updateStorageinfo(storageinfo);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功编辑" + isAcc + "条");
    }

    //删除废水收集池   /lingxing/storage/delete
    @DeleteMapping("/storage/delete/{storageIds}")
    public ResultEntity storageinfoDelete(@PathVariable String[] storageIds) {
        int isAcc = 0;
        try {
            isAcc = storageinfoService.deleteStorageinfo(storageIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    //查询废水表   /lingxing/meterinfo/data
    @GetMapping("/meterinfo/data")
    public ResultEntity meterinfoList(LXMeterinfo meterinfo) {
        return ResultEntity.success(storageinfoService.selectMeterinfoById(meterinfo));
    }

    //查询废水表数据   /lingxing/meterinfo/list
    @GetMapping("/meterinfo/list")
    public TableDataInfo meterinfoListBy(LXMeterinfo meterinfo) {
        startPage();
        List<LXMeterinfo> list = storageinfoService.selectMeterinfoList(meterinfo);
        return getDataTable(list);
    }

    //新增废水表   /lingxing/meterinfo/add
    @PostMapping("/meterinfo/add")
    public ResultEntity meterinfoAdd(@RequestBody LXMeterinfo meterinfo) {
        int isAcc = 0;
        try {
            isAcc = storageinfoService.insertMeterinfo(meterinfo);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    //编辑废水表   /lingxing/meterinfo/edit
    @PostMapping("/meterinfo/edit")
    public ResultEntity meterinfoEdit(@RequestBody LXMeterinfo meterinfo) {
        int isAcc = 0;
        try {
            isAcc = storageinfoService.updateMeterinfo(meterinfo);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功编辑" + isAcc + "条");
    }

    //删除废水表  /lingxing/meterinfo/delete/{meterids}
    @DeleteMapping("/meterinfo/delete/{meterids}")
    public ResultEntity meterinfoDelete(@PathVariable String[] meterids) {
        int isAcc = 0;
        try {
            isAcc = storageinfoService.deleteMeterinfo(meterids);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    //查询数采  /lingxing/getinfo
    @GetMapping("/getinfo")
    public ResultEntity selectGetinfo(@RequestParam("collectId") String collectId, @RequestParam("eleTypeId") String eleTypeId) {
        return ResultEntity.success(storageinfoService.selectGetinfo(collectId, eleTypeId), null);
    }

    //水表统计  /lingxing/meterinfo/day
    @GetMapping("meterinfo/day")
    public ResultEntity selectMeterinfoDay(String sourceId,String beginTime,String endTime) {
        return ResultEntity.success(storageinfoService.selectMeterinfoDay(sourceId, beginTime,endTime));
    }

    //水池统计分钟数  /lingxing/storage/minute
    @GetMapping("storage/minute")
    public ResultEntity selectStoragefoMinute(String sourceId,String beginTime,String endTime) {
        return ResultEntity.success(storageinfoService.selectStoragefoMinute(sourceId, beginTime,endTime));
    }

    //水池总量分钟数  /lingxing/storage_total/minute
    @GetMapping("storage_total/minute")
    public ResultEntity selectStoragefoToalMinute(String sourceId,String beginTime) {
        return ResultEntity.success(storageinfoService.selectStoragefoToalMinute(sourceId, beginTime));
    }

    //水池统计小时数  /lingxing/storage/hour
    @GetMapping("storage/hour")
    public ResultEntity selectStoragefoHour(String sourceId,String beginTime,String endTime) {
        return ResultEntity.success(storageinfoService.selectStoragefoHour(sourceId, beginTime,endTime));
    }

    //水池统计小时总数  /lingxing/storage_total/hour
    @GetMapping("storage_total/hour")
    public ResultEntity selectStoragefoToalHour(String sourceId,String beginTime) {
        return ResultEntity.success(storageinfoService.selectStoragefoToalHour(sourceId, beginTime));
    }
}
