package com.ruoyi.project.youyan.service;


import com.ruoyi.project.youyan.domain.YYOutletEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2020-09-08
 */
public interface YYOutletService
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
    public int insertBasOutlet(YYOutletEntity basOutlet) throws SQLException;

    /**
     * 修改【请填写功能名称】
     * 
     * @param basOutlet 【请填写功能名称】
     * @return 结果
     */
    public int updateBasOutlet(YYOutletEntity basOutlet) throws SQLException;

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param outletIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    public int deleteBasOutletByIds(String[] outletIds) throws SQLException;


}
