package com.ruoyi.project.ops.service;


import com.ruoyi.project.ops.domain.OpSourceEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface DevopsService {
    public List<OpSourceEntity> selectBasDevopsList(OpSourceEntity entity);

    public int insertBasDevops(OpSourceEntity entity) throws SQLException;

    public int updateBasDevops(OpSourceEntity entity) throws SQLException;

    public int deleteBasDevopsByIds(Integer[] opSourceIds) throws SQLException;

}
