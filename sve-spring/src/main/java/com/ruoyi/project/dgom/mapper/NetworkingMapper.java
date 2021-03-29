package com.ruoyi.project.dgom.mapper;

import com.ruoyi.project.dgom.domain.*;
import com.ruoyi.project.dgom.domain.network_apply.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author solang
 * @date 2020-09-28 17:04
 */
public interface NetworkingMapper {

    //新增联网申请
    int insertConnectapply(RegisterModel model);

    //插入数采信息到数据库
    int insertCollectdevdetail(CollectModel collect);

    // 插入设备因子到数据库
    int insertConnectApplydetail(DeviceModel device);

    //分局审批
    int substationApproval(ApprovalModel model);

    //更新状态1
    int updateConnectapplyById(String applyId);

    //市局审批
    int municipalApproval(MuApprovalModel model);

    //值守
    int dutyApproval(DutyApprovalModel model);

    int deleteRegister(String applyId,String updatedBy);

    int deleteCollects(String applyId);

    int deleteDevices(String applyId);

    //查询附件列表
    List<AnnexEntity> selectAnnexList(AnnexEntity entity);

    //查询附件
    AnnexEntity selectOnlineattachfile(@Param("attachfileid") String attachfileid);

    //新增附件
    int insertAnnex(AnnexEntity entity);

    //删除附件
    int deleteAnnex(@Param("attachfileids") Integer[] attachfileids, @Param("updatedby") String updatedby);

    //查询联网申请列表
    List<RegisterModel> selectConnectApplyList(NetworkingSearchModel model);

    //查询联网申请详情
    RegisterModel selectRegisterModelById(String applyId);

    //查询数采信息列表
    List<CollectModel> selectCollectdevdetailById(String applyId);

    //查询设备因子列表
    List<DeviceModel> selectDeviceModelById(String detailId);

    // 查询企业名字
    String selectSourceNameById(Integer sourceId);
}
