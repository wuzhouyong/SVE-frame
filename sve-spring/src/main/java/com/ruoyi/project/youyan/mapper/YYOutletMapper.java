package com.ruoyi.project.youyan.mapper;


import com.ruoyi.project.youyan.domain.YYOutletEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-09-08
 */
public interface YYOutletMapper
{

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param basOutlet 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<YYOutletEntity> selectBasOutletList(YYOutletEntity basOutlet);

    /**
     * 新增【请填写功能名称】
     * 
     * @param basOutlet 【请填写功能名称】
     * @return 结果
     */
    public int insertBasOutlet(YYOutletEntity basOutlet);

    /**
     * 修改【请填写功能名称】
     * 
     * @param basOutlet 【请填写功能名称】
     * @return 结果
     */
    public int updateBasOutlet(YYOutletEntity basOutlet);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param outletIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteBasOutletByIds(@Param("outletIds") String[] outletIds,@Param("updatedBy") String updatedBy);
}
