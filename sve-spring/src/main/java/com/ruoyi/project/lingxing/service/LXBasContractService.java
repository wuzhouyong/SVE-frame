package com.ruoyi.project.lingxing.service;


import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.lingxing.domain.LXBasContract;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface LXBasContractService {

    //查询转运合同管理
    public List<LXBasContract> getContractList(LXBasContract contract);

    //新增转运合同管理
    public ResultEntity insertBasContract(LXBasContract contract);

    //编辑转运合同管理
    public ResultEntity updateBasContract(LXBasContract contract);

    //删除转运合同管理
    public ResultEntity deleteBasContractByIds(String[] contractIds,String updatedBy);

}
