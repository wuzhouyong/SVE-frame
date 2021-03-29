package com.ruoyi.project.dgom.mapper;


import com.ruoyi.project.dgom.domain.DevApplyDetail;
import com.ruoyi.project.dgom.domain.DevApplyHead;
import com.ruoyi.project.dgom.domain.EntSmsNewEntity;
import com.ruoyi.project.dgom.domain.OpdeviceEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Mapper
public interface DevChangeMapper {

    List<DevApplyHead> selectDevChangeApplyList(DevApplyHead entity);

    Integer insertDevApplyHead(DevApplyHead entity);

    Integer insertDevApplyDetail(DevApplyDetail entity);

    Integer deleteDevApplyDetail(DevApplyDetail entity);

    Integer deleteDevApplyDetailForIds(@Param("applyIds") String[] applyIds);

    List<Integer> selectDevApplyStatus(@Param("applyIds") String[] applyIds);

    Integer deleteDevApplyHead(@Param("applyIds") String[] applyIds, @Param("updatedBy") String updatedBy);

    List<DevApplyDetail> selectDevApplyDetailList(@Param("applyId") String applyId);

    Integer updateDevChangeApply(DevApplyHead entity);

    String selectSourceNameBySourceId(@Param("sourceId") String sourceId);

    DevApplyHead selectDevApplyHeadById(@Param("applyId") String applyId);

    Integer insertEntSmsNew(EntSmsNewEntity entity);


}
