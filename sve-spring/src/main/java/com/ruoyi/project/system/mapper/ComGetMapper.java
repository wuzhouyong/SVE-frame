package com.ruoyi.project.system.mapper;

import com.ruoyi.project.common.domain.PfBasIndustry;
import com.ruoyi.project.common.domain.PfFileAttachment;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 参数配置 数据层
 *
 * @author ruoyi
 */
public interface ComGetMapper
{
    /**
     * 查询区域子区域
     *
     * @param code 查询区域子区域
     * @return 查询区域子区域
     */
    public List<Map> selectRegionByCode(String code);
    public List<Map> selectRegionsByIds(String ids);
    /**
     * 查课所有行业
     * @return
     */
    public List<PfBasIndustry> selectAllIndustry();



    //附件
    public List<PfFileAttachment> selectPfFileAttachmentList(PfFileAttachment pfFileAttachment);
    public int insertPfFileAttachment(PfFileAttachment pfFileAttachment);
    public PfFileAttachment selectPfFileAttachmentById(String attachmentId);
    public List<PfFileAttachment> selectPfFileAttachmentByIds(String[] attachmentIds);
    public List<PfFileAttachment> selectPfFileAttachmentsByRef(@Param("refType") String refType, @Param("refIds") String[] refIds);
    public int updatePfFileAttachment(PfFileAttachment pfFileAttachment);
}
