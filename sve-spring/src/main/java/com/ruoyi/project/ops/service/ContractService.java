package com.ruoyi.project.ops.service;


import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.ops.domain.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface ContractService {

    public List<OpProjectEntity> selectContractList(OpProjectEntity entity);

    public List<Map> selectElementListByProjectId(Integer opProjectId);

    public int insertContract(OpProjectEntity entity) throws SQLException;

    public ResultEntity updateContract(OpProjectEntity entity);

    public ResultEntity deleteContractByIds(Integer[] contractIds);

    public List<Map> selectDevopsBysourceId();

    public List<Map> selectCollectNameById(Map parm);

    public List<Map> selectElementDescById(Map parm);



}
