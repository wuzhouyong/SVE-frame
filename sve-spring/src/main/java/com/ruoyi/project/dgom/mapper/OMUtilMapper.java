package com.ruoyi.project.dgom.mapper;

import com.ruoyi.project.dgom.domain.AnnexEntity;
import com.ruoyi.project.dgom.domain.LabelEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface OMUtilMapper {
    /**
     * 根据父节点id获取标签列表
     *
     * @param parentId
     * @return
     */
    public List<LabelEntity> getLabelsByParentId(@Param("parentId") Integer parentId);

    //查询附件列表通用接口
    List<AnnexEntity> selectAnnexList(AnnexEntity entity);

    //根据排放口获取设备列表
    List<Map> datadevList(@Param("checkpointid") String checkpointid);

    List<Map> checkpointidList(@Param("sourceid") String sourceid);

    //删除附件
    int deleteAnnex(@Param("attachfileids") String[] attachfileids, @Param("updatedby") String updatedby);

    //附件下载
    AnnexEntity selectAnnexFileById(@Param("attachfileid") String attachfileid);

}
