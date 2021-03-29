package com.ruoyi.project.enterprise.mapper;

import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.enterprise.domain.source.BasSourceParam;
import com.ruoyi.project.enterprise.domain.source.SourceResult;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 企业信息Mapper接口
 *
 * @author ruoyi
 * @date 2020-05-19
 */
public interface PfBasSourceMapper
{
    public List<SourceResult> getSourceList(BasSourceParam param);
    /**
     * 查询企业信息
     *
     * @param sourceId 企业信息ID
     * @return 企业信息
     */
    public PfBasSource selectPfBasSourceById(String sourceId);
    public List<SourceByTag> getSourceListByTag(SourceByTag model);

    public int isHasVOCs(@Param("sourceId") String sourceId);

    public SourceVocsParamEntity selectSourceVocsParam(@Param("sourceId") String sourceId);

    public SourceModuleOne selectModuleOne(@Param("sourceId") String sourceId);

    public SourceModuleTwo selectModuleTwo(@Param("sourceId") String sourceId);

    /**
     * 查询企业信息列表
     *
     * @param pfBasSource 企业信息
     * @return 企业信息集合
     */
    public List<PfBasSource> selectPfBasSourceListByTag(PfBasSource pfBasSource);

    // 根据tagId修改tagName
    public int updateTagName(PfBasSource pfBasSource);

    public List<PfBasSource> selectSourceListMap(PfBasSource pfBasSource);

    public List<PfBasSource> selectSourceHomeIndex(SourceParam entity);

    //查询tag名字
    public List<TagHeadEntity> selectTagHeadName(String userId);

    // 根据sourceid查询tag名字
    public List<TagHeadEntity> getTagsBySource(String sourceId);

    public int getTotal(PfBasSource pfBasSource);

    public List<PfBasTagDetail> selectDetailListById(PfBasSource pfBasSource);

    public List<LeftMenuEntity> selectLeftMenuSourceCount(SourceParam entity);

    public int selectSourceSum(SourceParam entity);

    public int selectWarnCount(SourceParam entity);

    public List<LeftMenuEntity> selectWarnLe1(SourceParam entity);

    public List<LeftMenuEntity> selectWarnLe2(SourceParam entity);

    public List<LeftMenuEntity> selectWarnLe3(SourceParam entity);

    public List<LeftMenuEntity> selectConStatus(SourceParam entity);
    /**
     * 新增企业信息
     *
     * @param pfBasSource 企业信息
     * @return 结果
     */
    public int insertPfBasSource(PfBasSource pfBasSource);

    /**
     * 修改企业信息
     *
     * @param pfBasSource 企业信息
     * @return 结果
     */
    public int updatePfBasSource(PfBasSource pfBasSource);

    /**
     * 删除企业信息
     *
     * @param sourceId 企业信息ID
     * @return 结果
     */
    public int deletePfBasSourceById(String sourceId);

    /**
     * 批量删除企业信息
     *
     * @param sourceIds 需要删除的数据ID
     * @return 结果
     */
    public int deletePfBasSourceByIds(String[] sourceIds);

    /**
     * 查找企业标签
     * @param serouceId
     * @return
     */
    public List<Map> selectSourceTagById(String serouceId);

    /**
     * 获取PF_BAS_TAG_HEAD表的数据列表
     */
    public List<NameAndIdEntity> selectTagIdNameList(Long userId);

    public List<NameAndIdEntity> selectTagIdNameListAll();

    public List<WadeTaskSummary> selectWadeTaskSummary(WadeTaskSummary w);

    public List<GasTaskSummary> selectGasTaskSummary(GasTaskSummary g);

    /**
     * 删除企业关联的平台
     */
    public int deleteTagDetailByIds(String[] sourceIds);

    public int deleteEnterUserByIds(String[] sourceIds);

    public List<PfBasTagDetailEntity> selectTagDetail(@Param("pfSourceId")String pfSourceId);

    public List<PfBasTagDetailEntity> selectTagDetailFJ(@Param("pfSourceId")String pfSourceId);

    public List<TagHeadEntity> selectTagHeadInfo(@Param("pfSourceId")String pfSourceId);

    public int insertTagDetail(PfBasTagDetailEntity entity);

    public int selectTaskAdjust(@Param("pfSourceId")String pfSourceId,@Param("tagHeadId")String tagHeadId);

    public int checkHasDetail(PfBasTagDetailEntity entity);

    public int removeDetail(PfBasTagDetailEntity entity);

    public int updateDetail(PfBasTagDetailEntity entity);

    public int removeConstructioninfo(ConstructioninfoEntity entity);

    public int removeAdjust(ConstructioninfoEntity entity);

    public int existsConstruction(PfBasTagDetailEntity entity);

    public int insertConstructioninfo(ConstructioninfoEntity entity);
}
