package com.ruoyi.project.enterprise.service;

import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.enterprise.domain.source.BasSourceParam;
import com.ruoyi.project.enterprise.domain.source.SourceResult;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;

import java.util.List;
import java.util.Map;

/**
 * 企业信息Service接口
 *
 * @author ruoyi
 * @date 2020-05-19
 */
public interface IPfBasSourceService
{
    public List<SourceResult> getSourceList(BasSourceParam param);
    /**
     * 查询企业信息
     *
     * @param sourceId 企业信息ID
     * @return 企业信息
     */
    public PfBasSource selectPfBasSourceById(String sourceId);

    public List<String> selectDetailListById(PfBasSource pfBasSource);

    public int isHasVOCs(String sourceId);

    public SourceModuleOne selectModuleOne(String sourceId);

    public SourceModuleTwo selectModuleTwo(String sourceId);

    public SourceVocsParamEntity selectSourceVocsParam(String sourceId);

    public List<LeftMenuEntity> selectLeftMenuSourceCount(SourceParam entity);

    public int selectSourceSum(SourceParam entity);

    public int selectWarnCount(SourceParam entity);

    public List<LeftMenuEntity> selectWarnLe1(SourceParam entity);

    public List<LeftMenuEntity> selectWarnLe2(SourceParam entity);

    public List<LeftMenuEntity> selectWarnLe3(SourceParam entity);

    public List<LeftMenuEntity> selectConStatus(SourceParam entity);

    List<SourceByTag> getSourceListByTag(SourceByTag model);

    /**
     * 查询企业信息列表
     *
     * @param pfBasSource 企业信息
     * @return 企业信息集合
     */
    public List<PfBasSource> selectPfBasSourceList(PfBasSource pfBasSource);

    public List<PfBasSource> selectSourceListMap(PfBasSource pfBasSource);

    public int getTotal(PfBasSource pfBasSource);

    public List<PfBasSource> selectSourceHomeIndex(SourceParam entity);

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
     * 批量删除企业信息
     *
     * @param sourceIds 需要删除的企业信息ID
     * @return 结果
     */
    public int deletePfBasSourceByIds(String[] sourceIds);

    //查询tag名字
    public List<TagHeadEntity> selectTagHeadName();

      // 根据sourceid查询tag名字
    public List<TagHeadEntity> getTagsBySource(String sourceId);

    // 根据tagId修改tagName
    public int updateTagName(String tagId,String tagName, Integer orderBy);

    /**
     * 删除企业信息信息
     *
     * @param sourceId 企业信息ID
     * @return 结果
     */
    public int deletePfBasSourceById(String sourceId);

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


    public List<TagHeadEntity> selectTagHeadInfo(String pfSourceId);
    /**
     * 删除企业关联的平台
     */
    public int deleteTagDetailByIds(String[] sourceIds);

	public int deleteEnterUserByIds(String[] sourceIds);

    public List<PfBasTagDetailEntity> selectTagDetail(String pfSourceId);

    public AjaxResult insertTagDetail(List<PfBasTagDetailEntity> list);

    public int insertTagDetailFJ(List<PfBasTagDetailEntity> list);

    public List<WadeTaskSummary> selectWadeTaskSummary(WadeTaskSummary w);

    public List<GasTaskSummary> selectGasTaskSummary(GasTaskSummary s);
}