package com.ruoyi.project.inspect.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.domain.UploadFromFile;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.inspect.domain.DatInspectStat;
import com.ruoyi.project.inspect.domain.DatInspectParm;
import com.ruoyi.project.inspect.service.IDatInspectStatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;


@RestController
@RequestMapping("/inspect")
public class DatInspectStatController extends BaseController {

    @Autowired
    private IDatInspectStatService service;

    // 导入巡检任务 /inspect/import_task
    @PostMapping("/import_task")
    public ResultEntity importInspectStatTask(@RequestBody UploadFromFile file) throws Exception {
        if (file.getFile().equals("data:")) {
            return ResultEntity.error("不允许上传空白文件");
        }
        String fileBase = file.getFile().substring(file.getFile().indexOf("base64,") + 7);
        Base64.Decoder dec = Base64.getDecoder();
        byte[] bytes = dec.decode(fileBase);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        ExcelUtil<DatInspectStat> util = new ExcelUtil<>(DatInspectStat.class);
        List<DatInspectStat> inspectList = util.importExcel(inputStream);
        List<DatInspectStat> list = service.importInspectStatTask(inspectList);
        return ResultEntity.success(list);
    }

    @PostMapping("/batch_add")
    public ResultEntity batchAddInspectStatTask(@RequestBody List<DatInspectStat> list) {
        if (list == null || list.isEmpty()) return ResultEntity.error("任务数不能为空");
        try {
            return service.batchAddInspectStatTask(list);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResultEntity addInspectStatTask(@RequestBody DatInspectStat parm) {
        try {
            return service.addInspectStatTask(parm);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
    }

    // 查询巡检任务列表
    @PostMapping("/task_list")
    @DataScope(regionAlias = "s")
    public TableDataInfo inspectStatTask(@RequestBody DatInspectParm param) {
        startPage();
        List<DatInspectStat> list = service.selectInspectStatTaskList(param);
        return getDataTable(list);
    }

    // 修改历史记录
    @PostMapping("/edit")
    public ResultEntity editInspectStatRecordTask(@RequestBody DatInspectStat entity) {
        int isAcc = 0;
        try {
            isAcc = service.editInspectStatRecordTask(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    // 修改巡检任务
    @PostMapping("/task_edit")
    public ResultEntity editInspectStatTask(@RequestBody DatInspectStat entity) {
        int isAcc = 0;
        try {
            isAcc = service.editInspectStatTask(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    // 删除巡检任务
    @DeleteMapping("/delete/{statId}")
    public ResultEntity deleteInspectStatTask(@PathVariable Integer statId) {
        int isAcc = 0;
        try {
            isAcc = service.deleteInspectStatTaskById(statId);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    // 查询巡检任务历史列表
    @GetMapping("/record_list/{statId}")
    public ResultEntity inspectStatrRecordTaskList(@PathVariable Integer statId) {
        List<Map> list = service.selectStatrRecordTaskListById(statId);
        return ResultEntity.success(list);
    }

    @PostMapping("/export_task")
    @DataScope(regionAlias = "s")
    public AjaxResult exportInspectStatTask(@RequestBody DatInspectParm parm) {
        List<DatInspectStat> data = service.selectInspectStatTaskList(parm);
        int i = 1;
        for (DatInspectStat entity : data) {
            entity.setStatId(i++);
        }
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("序号", "statId", 10);
        doc.addColumn("镇属", "townName", 15);
        doc.addColumn("企业名称", "sourceName", 35);
        doc.addColumn("巡查日期", "inspectDate", 18);
        doc.addColumn("存在问题", "abnormalCause", 60);
        doc.addColumn("预警级别\n白/黄/红", "warnLevel", 10);
        doc.addColumn("重点/非重点排污单位", "keyPoint", 15);
        doc.addColumn("整改情况", "rectification", 20);
        doc.addColumn("预计整改完成时间", "expectFinishDate", 18);
        return doc.exportExcel("巡检统计表");
    }


}

