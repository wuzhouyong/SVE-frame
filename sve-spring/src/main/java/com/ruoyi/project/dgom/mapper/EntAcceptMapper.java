package com.ruoyi.project.dgom.mapper;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.dgom.domain.ent_accept.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EntAcceptMapper {
    List<EntAcceptResult> getEntAcceptList(EntAcceptParam param);
    EntAcceptResult getEntAcceptModel(@Param("applyId") String applyId);
    List<EntAcceptDetailResult> getEntAcceptDetailList(@Param("applyId") String applyId);
    int addEntAcceptHead(EntAcceptEntity entity);
    int addEntAcceptDetail(EntAcceptDetailEntity entity);
    int editEntAcceptHead(EntAcceptEntity entity);
    int editEntAcceptDetail(EntAcceptDetailEntity entity);
    int submitEntAccept(@Param("applyId") String applyId, @Param("updatedBy") String updatedBy);
    int deleteEntAccept(@Param("applyId") String applyId, @Param("updatedBy") String updatedBy);
    int deleteEntAcceptDetail(String detailId);
    int approval(EntApprovalEntity entity);
}
