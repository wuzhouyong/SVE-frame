package com.ruoyi.project.dgom.service;

import com.ruoyi.project.dgom.domain.AnnexEntity;
import com.ruoyi.project.dgom.domain.network_apply.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * @author solang
 * @date 2020-09-28 17:04
 */
public interface NetworkingService {

    //新增联网申请
    String insertConnectapply(RegisterModel model) throws SQLException;

    //查询联网申请列表
    List<RegisterModel> selectConnectApplyList(NetworkingSearchModel model);

    //查询联网申请详情
    RegisterModel getRegisterModel(String applyId);

    //查询联网申请详情
    Map<String, Object> selectRegisterModelById(String applyId);

    //分局审批
    int substationApproval(ApprovalModel model) throws SQLException;

    //市局审批
    int municipalApproval(MuApprovalModel model) throws SQLException;

    //值守
    int dutyApproval(DutyApprovalModel model) throws SQLException;

    //删除附件
    int deleteRegister(String applyId);

    //查询附件列表
    List<AnnexEntity> selectAnnexList(AnnexEntity entity);

    //新增附件
    int insertAnnex(AnnexEntity entity);

    //删除附件
    int deleteAnnex(Integer[] attachfileids);

    int updateConnectapplyById(String applyId);
}
