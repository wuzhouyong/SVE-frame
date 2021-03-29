package com.ruoyi.project.dgom.service;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.dgom.domain.ent_accept.*;

import java.util.List;

public interface IEntAcceptService {
    List<EntAcceptResult> getEntAcceptList(EntAcceptParam param);
    List<EntAcceptDetailResult> getEntAcceptDeviceList(String applyId);
    int addEntAcceptHead(EntAcceptEntity entity);
    int addEntAcceptDetail(EntAcceptDetailEntity entity);
    int editEntAcceptHead(EntAcceptEntity entity);
    int editEntAcceptDetail(EntAcceptDetailEntity entity);
    ResultEntity submitEntAccept(String applyId);
    ResultEntity deleteEntAccept(String applyId);
    ResultEntity deleteEntAcceptDetail(String detailId);
    ResultEntity approval(EntApprovalEntity entity);
}
