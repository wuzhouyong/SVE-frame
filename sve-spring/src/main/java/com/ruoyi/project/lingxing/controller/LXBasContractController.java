package com.ruoyi.project.lingxing.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.lingxing.domain.LXBasContract;
import com.ruoyi.project.lingxing.mapper.LXBasContractlMapper;
import com.ruoyi.project.lingxing.service.LXBasContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController
@RequestMapping("/lingxing/contract")
public class LXBasContractController extends BaseController {
    @Autowired
    private LXBasContractService service;

    @Autowired
    private LXBasContractlMapper mapper;

    // 查询【转运合同管理】列表
    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo transferContractList(@RequestBody LXBasContract contract) {
        startPage();
        List<LXBasContract> list = service.getContractList(contract);
        return getDataTable(list);
    }

    // 新增【转运合同管理】  /lingxing/contract/add
    @PostMapping("/add")
    public ResultEntity transferContractAdd(@RequestBody LXBasContract contract) {
        return service.insertBasContract(contract);
    }

    // 修改【转运合同管理】 /lingxing/contract/edit
    @PostMapping("/edit")
    public ResultEntity transferContractEdit(@RequestBody LXBasContract contract) {
        return service.updateBasContract(contract);
    }

    // 删除【转运合同管理】  /lingxing/contract/delete/
    @DeleteMapping("/delete/{contractIds}")
    public ResultEntity transferContractDelete(@PathVariable String[] contractIds) {
        String updatedBy = SecurityUtils.getUsername();
        int count = mapper.deleteContractAttFile(contractIds, updatedBy);
        if (count <= 0) ResultEntity.error("删除转运合同附件失败");
        return service.deleteBasContractByIds(contractIds,updatedBy);
    }


}

