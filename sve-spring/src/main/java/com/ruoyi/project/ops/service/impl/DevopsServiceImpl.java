package com.ruoyi.project.ops.service.impl;

import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.ops.domain.OpSourceEntity;
import com.ruoyi.project.ops.mapper.DevopsMapper;
import com.ruoyi.project.ops.service.DevopsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

/**
 * @author solang
 * @date 2020-12-29 10:08
 */
@Service
@DataSource(value = DataSourceType.MASTER)
public class DevopsServiceImpl implements DevopsService {

    @Autowired
    private DevopsMapper mapper;

    @Override
    public List<OpSourceEntity> selectBasDevopsList(OpSourceEntity entity) {
        return mapper.selectBasDevopsList(entity);
    }

    @Override
    public int insertBasDevops(OpSourceEntity entity) throws SQLException {
        entity.setCreatedBy(SecurityUtils.getUsername());
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.insertBasDevops(entity);
        if (isAcc < 0) {
            throw new SQLException("新增运维单位基础信息失败");
        }
        return isAcc;
    }

    @Override
    public int updateBasDevops(OpSourceEntity entity) throws SQLException {
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.updateBasDevops(entity);
        if (isAcc < 0) {
            throw new SQLException("编辑运维单位基础信息失败");
        }
        return isAcc;
    }

    @Override
    public int deleteBasDevopsByIds(Integer[] opSourceIds) throws SQLException {
        String updatedBy = SecurityUtils.getUsername();
        int count = mapper.deleteBasDevopsByIds(opSourceIds, updatedBy);
        if (count < 0) {
            throw new SQLException("删除运维单位基础信息失败");
        }
        return count;
    }

}
