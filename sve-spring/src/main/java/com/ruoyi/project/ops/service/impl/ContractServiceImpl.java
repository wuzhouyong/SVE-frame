package com.ruoyi.project.ops.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.ops.domain.ContractElement;
import com.ruoyi.project.ops.domain.OpProjectEntity;
import com.ruoyi.project.ops.mapper.ContractMapper;
import com.ruoyi.project.ops.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-12-29 10:08
 */
@Service
@DataSource(value = DataSourceType.MASTER)
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractMapper mapper;

    @Override
    public List<OpProjectEntity> selectContractList(OpProjectEntity entity) {
        //        for (OpProjectEntity con : list) {
//            String dbUser = mapper.selectDbUserById(con.getTagHeadId());
//            Map parm = new HashMap();
//            parm.put("dbUser", dbUser);
//            parm.put("collectId", con.getCollectId());
//            parm.put("sourceId", con.getPfSourceId());
//            String collectName = mapper.selectCollectName(parm);
//            con.setCollectName(collectName);
//            List<Integer> elemId = mapper.selectElementId(con.getOpProjectId());  //会报错
//            con.setElements(elemId);
//        }
        return mapper.selectContractList(entity);
    }

    @Override
    public List<Map> selectElementListByProjectId(Integer opProjectId) {
        String dbUser = mapper.selectdbUserByProjectId(opProjectId);
        List<Map> elemId = mapper.selectElementList(opProjectId, dbUser);
        return HashMapUtils.toLower(elemId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertContract(OpProjectEntity entity) throws SQLException {
        entity.setCreatedBy(SecurityUtils.getUsername());
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.insertContract(entity);
        if (isAcc < 0) {
            throw new SQLException("新增运维单位合同表失败");
        }
//        for (Integer eleId : entity.getElements()) {
//            ContractElement elem = new ContractElement();
//            elem.setElementId(eleId);
//            elem.setCreatedBy(SecurityUtils.getUsername());
//            elem.setOpProjectId(entity.getOpProjectId());
//            int count = mapper.insertContractElement(elem);
//            if (count < 0) {
//                throw new SQLException("新增运维单位合同表失败");
//            }
//        }
        return entity.getOpProjectId();
    }

    @Override
    public ResultEntity updateContract(OpProjectEntity entity) {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.updateContract(entity);
        if (isAcc < 0) {
            return ResultEntity.error("编辑运维单位合同表失败");
        }
        return ResultEntity.success("成功编辑" + isAcc + "条");
    }

    @Override
    public ResultEntity deleteContractByIds(Integer[] contractIds) {
        String updatedBy = SecurityUtils.getUsername();
        int isAcc = mapper.deleteContractByIds(contractIds, updatedBy);
        if (isAcc < 0) {
            return ResultEntity.error("删除运维单位合同表失败");
        }
        mapper.deleteContractEleByIds(contractIds, updatedBy);
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    @Override
    public List<Map> selectDevopsBysourceId() {
        return HashMapUtils.toCamel(mapper.selectDevopsBysourceId());
    }

    @Override
    public List<Map> selectCollectNameById(Map parm) {
        return HashMapUtils.toCamel(mapper.selectCollectNameById(parm));
    }


    @Override
    public List<Map> selectElementDescById(Map parm) {
        return HashMapUtils.toCamel(mapper.selectElementDescById(parm));
    }

}
