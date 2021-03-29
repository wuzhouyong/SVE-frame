package com.ruoyi.project.ops.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.ops.domain.OpProjectEntity;
import com.ruoyi.project.ops.mapper.ContractMapper;
import com.ruoyi.project.ops.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController
@RequestMapping("/contract")
public class ContractController extends BaseController {
    @Autowired
    private ContractService service;

    @Autowired
    private ContractMapper mapper;

    //运维单位合同表列表 /contract/list
    @PostMapping("/list")
    public TableDataInfo selectContractList(@RequestBody OpProjectEntity entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) entity.setPfSourceId(sourceId);
        startPage();
        List<OpProjectEntity> list = service.selectContractList(entity);
        return getDataTable(list);
    }

    //运维单位合同表对应的因子详情 /contract/eleList
    @GetMapping("/eleList")
    public ResultEntity selectElementListByProjectId(Integer opProjectId) {
        List<Map> eleList = service.selectElementListByProjectId(opProjectId);
        return ResultEntity.success(eleList);
    }

    @PostMapping("/add")
    public ResultEntity insertContract(@RequestBody OpProjectEntity entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) entity.setPfSourceId(sourceId);
        int isAcc = 0;
        try {
            isAcc = service.insertContract(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success(isAcc);
    }

    @PostMapping("/edit")
    public ResultEntity updateContract(@RequestBody OpProjectEntity entity) {
        return service.updateContract(entity);
    }

    @DeleteMapping("/delete/{questionIds}")
    public ResultEntity deleteContractByIds(@PathVariable Integer[] questionIds) {
        return service.deleteContractByIds(questionIds);
    }

    //查询运维单位名称  /contract/devops_name
    @GetMapping("/devops_name")
    public ResultEntity selectDevopsName() {
        return ResultEntity.success(service.selectDevopsBysourceId());
    }

    //查询数采id和名称  /contract/collect_name
    @PostMapping("/collect_name")
    public ResultEntity selectCollectNameById(@RequestBody Map parm) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId == null) return ResultEntity.error("企业不能为空");
        parm.put("pfSourceId", sourceId);
        if (parm.get("opSourceId") == null) return ResultEntity.error("运维施工单位不能为空");
        if (parm.get("tagHeadId") == null) return ResultEntity.error("平台不能为空");
        String dbUser = mapper.selectDbUserById(Integer.valueOf(parm.get("tagHeadId").toString()));
        if (dbUser == null) return ResultEntity.error("平台前缀不能为空");
        parm.put("dbUser", dbUser);
        return ResultEntity.success(service.selectCollectNameById(parm));
    }

    //查询因子id和名称  /contract/element_desc
    @PostMapping("/element_desc")
    public ResultEntity selectElementDescById(@RequestBody Map parm) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        parm.put("pfSourceId", sourceId);
        if (parm.get("tagHeadId") == null) return ResultEntity.error("平台不能为空");
        String dbUser = mapper.selectDbUserById(Integer.valueOf(parm.get("tagHeadId").toString()));
        if (dbUser == null) return ResultEntity.error("平台前缀不能为空");
        parm.put("dbUser", dbUser);
        return ResultEntity.success(service.selectElementDescById(parm));
    }



}
