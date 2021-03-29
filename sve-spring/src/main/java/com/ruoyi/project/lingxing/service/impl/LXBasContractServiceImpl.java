package com.ruoyi.project.lingxing.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.domain.LXBasContract;
import com.ruoyi.project.lingxing.mapper.LXBasContractlMapper;
import com.ruoyi.project.lingxing.service.LXBasContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Service
@DataSource(value = DataSourceType.LINGXING)
public class LXBasContractServiceImpl implements LXBasContractService {
    @Autowired
    private LXBasContractlMapper mapper;

    //查询转运合同管理
    @Override
    public List<LXBasContract> getContractList(LXBasContract contract) {
        return mapper.getContractList(contract);
    }

    //新增转运合同管理
    @Override
    public ResultEntity insertBasContract(LXBasContract contract) {
        Integer acc = mapper.selectContractCount(contract);
        if (acc != 0) return ResultEntity.error("新增失败:合同id重复");
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        contract.setSourceId(sourceId);
        contract.setCreatedBy(SecurityUtils.getUsername());
        contract.setUpdatedBy(SecurityUtils.getUsername());
        int count = mapper.insertBasContract(contract);
        return count > 0 ? ResultEntity.success("成功添加" + count + "条") : ResultEntity.error("新增失败");
    }

    //编辑转运合同管理
    @Override
    public ResultEntity updateBasContract(LXBasContract contract) {
        contract.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.updateBasContract(contract);
        return isAcc > 0 ? ResultEntity.success("成功添加" + isAcc + "条") : ResultEntity.error("编辑失败");
    }

    //删除转运合同管理
    @Override
    public ResultEntity deleteBasContractByIds(String[] contractIds,String updatedBy) {
        int isAcc = mapper.deleteBasContractByIds(contractIds, updatedBy);
        return isAcc > 0 ? ResultEntity.success("成功删除" + isAcc + "条") : ResultEntity.error("删除失败");
    }


}
