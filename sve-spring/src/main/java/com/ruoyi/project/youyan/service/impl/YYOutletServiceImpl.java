package com.ruoyi.project.youyan.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.youyan.domain.YYOutletEntity;
import com.ruoyi.project.youyan.mapper.YYOutletMapper;
import com.ruoyi.project.youyan.service.YYOutletService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Service业务层处理
 *
 * @author ruoyi
 * @date 2020-09-08
 */
@Service
@DataSource(value = DataSourceType.YOUYAN)
public class YYOutletServiceImpl implements YYOutletService {
    @Autowired
    private YYOutletMapper outletMapper;


    /**
     * 查询【请填写功能名称】列表
     *
     * @param basOutlet 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<YYOutletEntity> selectBasOutletList(YYOutletEntity basOutlet) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (!StringUtils.isEmpty(sourceId)) {
            basOutlet.setSourceId(sourceId);
        }
        return outletMapper.selectBasOutletList(basOutlet);
    }

    /**
     * 新增【请填写功能名称】
     *
     * @param basOutlet 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertBasOutlet(YYOutletEntity basOutlet) throws SQLException {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (!StringUtils.isEmpty(sourceId)) {
            basOutlet.setSourceId(sourceId);
        }
        basOutlet.setOutletId(java.util.UUID.randomUUID().toString());
        basOutlet.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = outletMapper.insertBasOutlet(basOutlet);
        if (isAcc < 0) {
            throw new SQLException("新增运输车辆失败");
        }
        return isAcc;
    }

    /**
     * 修改【请填写功能名称】
     *
     * @param basOutlet 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateBasOutlet(YYOutletEntity basOutlet) throws SQLException {
        basOutlet.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = outletMapper.updateBasOutlet(basOutlet);
        if (isAcc < 0) {
            throw new SQLException("新增运输车辆失败");
        }
        return isAcc;
    }

    /**
     * 批量删除【请填写功能名称】
     *
     * @param outletIds 需要删除的【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteBasOutletByIds(String[] outletIds) throws SQLException {
        String updatedBy = SecurityUtils.getUsername();
        int count = outletMapper.deleteBasOutletByIds(outletIds, updatedBy);
        if (count < 0) {
            throw new SQLException("删除运输车辆失败");
        }
        return count;
    }

}
