package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.domain.UploadFromFile;
import com.ruoyi.project.dgom.domain.SuperviseEntity;
import com.ruoyi.project.dgom.mapper.SuperviseTaskMapper;
import com.ruoyi.project.dgom.service.SuperviseTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.util.Map;


/**
 * 【请填写功能名称】Controller 在线 supervise
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController
@RequestMapping("/dgom/supervise")
public class SuperviseTaskController extends BaseController {

    @Autowired
    private SuperviseTaskService taskService;

    @Autowired
    private SuperviseTaskMapper taskMapper;

    // 查询督办任务列表  /dgom/supervise/task_list 任务状态为4，就是市局值守确认列表
    @PostMapping("/task_list")
    public TableDataInfo superviseTaskList(@RequestBody SuperviseEntity entity) {
        startPage();
        List<SuperviseEntity> list = taskService.selectSuperviseTaskList(entity);
        return getDataTable(list);
    }

    // 新增督办任务  /dgom/supervise/add
    @PostMapping("/add")
    public ResultEntity addSuperviseTask(@RequestBody SuperviseEntity entity) {
        Map map = taskMapper.selectTownCodeByOmtownId(entity.getOmtownId()); //查找中心平台id及名字
        if (map.get("TOWN_CODE") != null) entity.setUntownId(Integer.parseInt(map.get("TOWN_CODE").toString()));
        if (map.get("TOWN_NAME") != null) entity.setTownName(map.get("TOWN_NAME").toString());
        return taskService.insertSuperviseTask(entity);
    }

    // 修改督办任务 /dgom/supervise/edit
    @PostMapping("/edit")
    public ResultEntity editSuperviseTask(@RequestBody SuperviseEntity entity) {
        int isAcc = 0;
        try {
            isAcc = taskService.editSuperviseTask(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    // 删除督办任务  /dgom/supervise/delete/{suptaskIds}
    @DeleteMapping("/delete/{suptaskIds}")
    public ResultEntity deleteSuperviseTask(@PathVariable Integer[] suptaskIds) {
        int isAcc = 0;
        try {
            isAcc = taskService.deleteSuperviseTaskByIds(suptaskIds);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    // 导入督办任务  /dgom/supervise/import_task @RequestParam("file") MultipartFile file
    @PostMapping("/import_task")
    public ResultEntity importSuperviseTask(@RequestBody UploadFromFile file) throws Exception {
        if (file.getFile().equals("data:")) {
            return ResultEntity.error("不允许上传空白文件");
        }
        String fileBase = file.getFile().substring(file.getFile().indexOf("base64,") + 7);
        Base64.Decoder dec = Base64.getDecoder();
        byte[] bytes = dec.decode(fileBase);
        InputStream inputStream = new ByteArrayInputStream(bytes);
        ExcelUtil<SuperviseEntity> util = new ExcelUtil<>(SuperviseEntity.class);
        List<SuperviseEntity> superviseList = util.importExcel(inputStream);
        List<SuperviseEntity> list = taskService.importSuperviseTask(superviseList);
        return ResultEntity.success(list);
    }

    // 批量上传督办任务 /dgom/supervise/batch_add
    @PostMapping("/batch_add")
    public ResultEntity batchAddSuperviseTask(@RequestBody List<SuperviseEntity> list) {
        if (list == null || list.isEmpty()) return ResultEntity.error("任务数不能为空");
        try {
            return taskService.batchAddSuperviseTask(list);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
    }

    // 查询分局处理列表  /dgom/supervise/divide_list
    @PostMapping("/divide_list")
    @DataScope(regionAlias = "s")
    public TableDataInfo divideTaskList(@RequestBody SuperviseEntity entity) {
        startPage();
        List<SuperviseEntity> list = taskService.selectDivideTaskList(entity);
        return getDataTable(list);
    }

    // 分局批量处理 /dgom/supervise/divide_edit
    @PostMapping("/divide_edit")
    public ResultEntity editDivideTask(@RequestBody List<SuperviseEntity> entitys) {
        if (entitys == null || entitys.isEmpty()) ResultEntity.error("请选择需要处理的任务");
        int isAcc = 0;
        try {
            isAcc = taskService.editDivideTask(entitys);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

    // 市局批量处理 /dgom/supervise/municipal_edit 只修改状态 驳回5 同意6
    @PostMapping("/municipal_edit")
    public ResultEntity editMunicipalTask(@RequestBody List<SuperviseEntity> entitys) {
        if (entitys == null || entitys.isEmpty()) ResultEntity.error("请选择需要处理的任务");
        int isAcc = 0;
        try {
            isAcc = taskService.editMunicipalTask(entitys);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功修改" + isAcc + "条");
    }

}
