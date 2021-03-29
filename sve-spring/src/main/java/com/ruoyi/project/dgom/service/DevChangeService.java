package com.ruoyi.project.dgom.service;


import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.dgom.domain.DevApplyDetail;
import com.ruoyi.project.dgom.domain.DevApplyHead;
import com.ruoyi.project.dgom.domain.OpdeviceEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface DevChangeService {

    public List<DevApplyHead> selectDevChangeApplyList(DevApplyHead entity);

    public List<DevApplyDetail> selectDevApplyDetailList(String applyId);

    public int insertDevChangeApply(DevApplyHead entity) throws SQLException;

    public ResultEntity deleteDevChangeApply(String[] applyIds);

    public ResultEntity acceptDevChangeApply(DevApplyHead entity);

    public ResultEntity approvalDevChangeApply(DevApplyHead entity);
    public ResultEntity dutyDevChangeApply(DevApplyHead entity);

}
