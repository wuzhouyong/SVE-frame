package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.dgom.domain.ReturnParam;
import com.ruoyi.project.dgom.mapper.DataSearchMapper;
import com.ruoyi.project.dgom.service.DGFocalPointService;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @author solang
 * @date 2020-09-17 10:17
 */
@RestController
public class DGFocalPointController extends BaseController {

    @Autowired
    private DGFocalPointService pointService;

    @Autowired
    private DataSearchMapper dataSearchMapper;

    /**
     * 水重点施工单位信息
     */
//    @GetMapping("/water/construction/model/{sourceId}")
//    public ResultEntity waterModel(@PathVariable String sourceId) {
//        String sourceId_t = SecurityUtils.getLoginUser().getUser().getSourceId();
//        if (StringUtils.isNotEmpty(sourceId_t)) sourceId = sourceId_t;
//        if (StringUtils.isEmpty(sourceId)) return ResultEntity.error("企业不能为空");
//        return ResultEntity.success(pointService.getDatWaterConstruction(sourceId));
//    }

    // 水重点近30天传输率图小时数据
    @GetMapping("/water/transfer/chart")
    public ResultEntity getWaterTransferCharts() {
        return pointService.getWaterTransferCharts();
    }

    // 获取水重点 待处理业务总条数
    @GetMapping("/water/transfer/procstate")
    public ResultEntity getWaterProcStateCount() {
        Integer isAcc = pointService.getWaterProcStateCount();
        return (isAcc >= 0 ? ResultEntity.success(isAcc) : ResultEntity.error("请求失败,请重试"));
    }

    // 水重点近30天传输率图小时数据
    @GetMapping("/waterv/transfer/chart")
    public ResultEntity getWaterVTransferCharts() {
        Map<String, ArrayList<Object>> map = new LinkedHashMap<>();
        map.put("cols", new ArrayList<>());
        map.put("data", new ArrayList<>());
        return ResultEntity.success(map);
    }

    // 获取水重点 待处理业务总条数
    @GetMapping("/waterv/transfer/procstate")
    public ResultEntity getWaterVProcStateCount() {
        return ResultEntity.success(0);
    }

    /**
     * 气重点施工单位信息
     */
//    @GetMapping("/gas/construction/model/{sourceId}")
//    public ResultEntity gasModel(@PathVariable String sourceId) {
//        String sourceId_t = SecurityUtils.getLoginUser().getUser().getSourceId();
//        if (StringUtils.isNotEmpty(sourceId_t)) sourceId = sourceId_t;
//        if (StringUtils.isEmpty(sourceId)) return ResultEntity.error("企业不能为空");
//        return ResultEntity.success(pointService.getDatGasConstruction(sourceId));
//    }

    // 气重点近30天传输率图小时数据
    @GetMapping("/gas/transfer/chart")
    public ResultEntity getGasTransferCharts() {
        return pointService.getGasTransferCharts();
    }

    // 获取气重点 待处理业务总条数
    @GetMapping("/gas/transfer/procstate")
    public ResultEntity getGasProcStateCount() {
        Integer isAcc = pointService.getGasProcStateCount();
        return (isAcc >= 0 ? ResultEntity.success(isAcc) : ResultEntity.error("请求失败,请重试"));
    }

    @GetMapping("/gasv/transfer/chart")
    public ResultEntity getGasVTransferCharts() {
        Map<String, ArrayList<Object>> map = new LinkedHashMap<>();
        map.put("cols", new ArrayList<>());
        map.put("data", new ArrayList<>());
        return ResultEntity.success(map);
    }

    // 获取气重点 待处理业务总条数
    @GetMapping("/gasv/transfer/procstate")
    public ResultEntity getGasVProcStateCount() {
        return ResultEntity.success(0);
    }

    @GetMapping("/yitihua/transfer/chart")
    public ResultEntity getYiTiHuaTransferCharts() {
        Map<String, ArrayList<Object>> map = new LinkedHashMap<>();
        map.put("cols", new ArrayList<>());
        map.put("data", new ArrayList<>());
        return ResultEntity.success(map);
    }

    @GetMapping("/yitihua/transfer/procstate")
    public ResultEntity getYiTiHuaProcStateCount() {
        return ResultEntity.success(0);
    }
    /**
     * 排放口查询
     */
    @PostMapping("/dgom/checkpoint/list")
    public TableDataInfo emissionPoint(@RequestBody ReturnParam param) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            param.setSourceId(bizSourceId);
        }
        startPage();
        List<ReturnParam> list = pointService.selectEmissionPointList(param);
        return getDataTable(list);
    }

    @PostMapping("/dgom/checkpoint/dropdown")
    public ResultEntity cpDropdown(@RequestBody ReturnParam param) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            param.setSourceId(bizSourceId);
        }
        List<ReturnParam> list = pointService.selectEmissionPointList(param);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (ReturnParam item : list) {
            Map<String, Object> map = new LinkedMap<>();
            map.put("value", item.getCheckpointid().toString());
            map.put("text", item.getCheckpointdesc());
            mapList.add(map);
        }
        return ResultEntity.success(mapList);
    }

    /**
     * 设备查询
     */
    @PostMapping("/dgom/device/list")
    public TableDataInfo equipmentList(@RequestBody ReturnParam param) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (StringUtils.isNotEmpty(sourceId)) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            param.setSourceId(bizSourceId);
        }
        startPage();
        List<ReturnParam> list = pointService.selectEquipmentList(param);
        return getDataTable(list);
    }

    /**
     * 因子查询
     */
    @PostMapping("/dgom/element/list")
    public TableDataInfo elementList(@RequestBody ReturnParam param) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (StringUtils.isNotEmpty(sourceId)) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            param.setSourceId(bizSourceId);
        }
        startPage();
        List<ReturnParam> list = pointService.selectElementList(param);
        return getDataTable(list);
    }

    @PostMapping("/dgom/cp_element/dropdown")
    public ResultEntity eleDropdown(@RequestBody ReturnParam param) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (StringUtils.isNotEmpty(sourceId)) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            param.setSourceId(bizSourceId);
        }
        List<ReturnParam> list = pointService.selectElementList(param);
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (ReturnParam item : list) {
            Map<String, Object> map = new LinkedMap<>();
            map.put("value", item.getElementid());
            map.put("text", item.getElementdesc());
            mapList.add(map);
        }
        return ResultEntity.success(mapList);
    }
}
