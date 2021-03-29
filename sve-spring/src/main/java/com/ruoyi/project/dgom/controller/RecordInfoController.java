package com.ruoyi.project.dgom.controller;

import com.miracleren.NiceDoc;
import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.dgom.domain.OpGDeviceEntity;
import com.ruoyi.project.dgom.domain.OpWDeviceEntity;
import com.ruoyi.project.dgom.domain.OpdeviceEntity;
import com.ruoyi.project.dgom.domain.OpeRecordEntity;
import com.ruoyi.project.dgom.mapper.DataSearchMapper;
import com.ruoyi.project.dgom.service.RecordInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;


/**
 * 【信息备案列表】Controller 在线 设施
 * *
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController
@RequestMapping("/dgom/facility")
public class RecordInfoController extends BaseController {

    @Autowired
    private RecordInfoService service;

    @Autowired
    private DataSearchMapper dataSearchMapper;

    // 查询信息备案列表  /dgom/facility/list
    @PostMapping("/list")
    public TableDataInfo selectOpdeviceinfoList(@RequestBody OpdeviceEntity entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            entity.setBizSourceId(bizSourceId);
        }
        startPage();
        List<OpdeviceEntity> list = service.selectOpdeviceinfoList(entity);
        return getDataTable(list);
    }

    // 查找监测点列表  /dgom/facility/checkpoint_list/{checkpointtype}  checkpointtype 1为水，2为气
    @GetMapping("/checkpoint_list/{checkpointtype}")
    public ResultEntity selectCheckPointList(@PathVariable String checkpointtype) {
        if (StringUtils.isEmpty(checkpointtype)) return ResultEntity.error("检测点类型不能为空");
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
        if (bizSourceId == null) return ResultEntity.error("企业id不能为空");
        List<Map> list = service.selectCheckPointList(checkpointtype, bizSourceId);
        return ResultEntity.success(list);
    }
    @GetMapping("/device_list/{checkpointId}")
    public  ResultEntity getDeviceListByCpId(@PathVariable String checkpointId) {
        return ResultEntity.success(service.getDeviceListByCpId(checkpointId));
    }

    // 气重点设备列表  /dgom/facility/opgdevice_list/{checkpointid}
    @GetMapping("/opgdevice_list/{checkpointid}")
    public ResultEntity selectOpgopdeviceList(@PathVariable String checkpointid) {
        List<OpGDeviceEntity> list = service.selectOpgopdeviceList(checkpointid);
        return ResultEntity.success(list);
    }

    // 水重点设备列表  /dgom/facility/opwdevice_list/{checkpointid}
    @GetMapping("/opwdevice_list/{checkpointid}")
    public ResultEntity selectOpwdeviceList(@PathVariable String checkpointid) {
        List<OpWDeviceEntity> list = service.selectOpwdeviceList(checkpointid);
        return ResultEntity.success(list);
    }

    // 水重点设备新增  /dgom/facility/opwdevice_add
    @PostMapping("/opwdevice_add")
    public ResultEntity insertOpwdevice(@RequestBody OpdeviceEntity entity) {
        int isAcc = 0;
        try {
            isAcc = service.insertOpwdevice(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功新增" + isAcc + "条");
    }

    // 气重点设备新增  /dgom/facility/opgdevice_add
    @PostMapping("/opgdevice_add")
    public ResultEntity insertOpgdevice(@RequestBody OpGDeviceEntity entity) {
        int isAcc = 0;
        try {
            isAcc = service.insertOpgdevice(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功新增" + isAcc + "条");
    }

    // 水重点设备编辑  /dgom/facility/opwdevice_edit
    @PostMapping("/opwdevice_edit")
    public ResultEntity modifyOpwdevice(@RequestBody OpdeviceEntity entity) {
        int isAcc = 0;
        try {
            isAcc = service.modifyOpwdevice(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功编辑" + isAcc + "条");
    }

    // 气重点设备编辑  /dgom/facility/opgdevice_edit
    @PostMapping("/opgdevice_edit")
    public ResultEntity modifyOpgdevice(@RequestBody OpGDeviceEntity entity) {
        int isAcc = 0;
        try {
            isAcc = service.modifyOpgdevice(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功编辑" + isAcc + "条");
    }

    // 删除信息备案  /dgom/facility/delete/{checkpointid}
    @DeleteMapping("/delete/{checkpointid}")
    public ResultEntity deletedOpgdevice(@PathVariable String checkpointid) {
        int isAcc = 0;
        try {
            isAcc = service.deletedDevice(checkpointid);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    //导出 气重点  /dgom/facility/export_opg/{checkpointid}
    @GetMapping("/export_opg/{checkpointid}")  // 获取数据根据模板导出word
    public void exportOpgdevice(@PathVariable String checkpointid, HttpServletResponse response) {
        Map<String, Object> map = service.selectOpgdeviceModelById(checkpointid);
        String path = null;
        NiceDoc doc;
        try {
            File sourceFile = ResourceUtils.getFile("classpath:template/empty_gas.docx");
            path = sourceFile.getPath();
            doc = new NiceDoc(path);
            doc.setLabel(map);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            String filename = URLEncoder.encode(map.get("sourcename") + ".docx", "UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename + "");
            OutputStream outputStream = response.getOutputStream();
            doc.saveOutStream(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //导出 水重点  /dgom/facility/export_opw/{checkpointid}
    @GetMapping("/export_opw/{checkpointid}")
    public void exportOpwdevice(@PathVariable String checkpointid, HttpServletResponse response) {
        Map<String, Object> map = service.selectOpwdeviceModelById(checkpointid);
        String path = null;
        NiceDoc doc;
        try {
            File sourceFile = ResourceUtils.getFile("classpath:template/empty_water.docx");
            path = sourceFile.getPath();
            doc = new NiceDoc(path);
            doc.setLabel(map);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            String filename = URLEncoder.encode(map.get("sourcename") + ".docx", "UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename + "");
            OutputStream outputStream = response.getOutputStream();
            doc.saveOutStream(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 查询运维管理列表  /dgom/facility/record_list
    @PostMapping("/record_list")
    public TableDataInfo selectOperationRecordList(@RequestBody OpeRecordEntity entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
        entity.setBizSourceId(bizSourceId);
        startPage();
        List<OpeRecordEntity> list = service.selectOperationRecordList(entity);
        return getDataTable(list);
    }

    // 运维管理查找设备列表  /dgom/facility/devdef_list/{operationType}
    @GetMapping("/devdef_list/{operationType}")
    public ResultEntity selectEquipmentList(@PathVariable String operationType) {
        if (StringUtils.isEmpty(operationType)) return ResultEntity.error("台账类型不能为空");
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
//        bizSourceId = "270";
        if (bizSourceId == null) return ResultEntity.error("企业id不能为空");
        List<Map> list = service.selectEquipmentList(operationType, bizSourceId);
        return ResultEntity.success(list);
    }

    // 运维管理新增  /dgom/facility/record_add
    @PostMapping("/record_add")
    public ResultEntity insertOperationRecord(@RequestBody OpeRecordEntity entity) {
        return service.insertOperationRecord(entity);
    }

    // 运维管理编辑  /dgom/facility/record_edit
    @PostMapping("/record_edit")
    public ResultEntity modifyOperationRecord(@RequestBody OpeRecordEntity entity) {
        return service.modifyOperationRecord(entity);
    }

    // 运维管理删除  /dgom/facility/record_delete/{zoperationids}
    @DeleteMapping("/record_delete/{zoperationids}")
    public ResultEntity deleteOperationRecord(@PathVariable String[] zoperationids) {
        return service.deleteOperationRecord(zoperationids);
    }


}
