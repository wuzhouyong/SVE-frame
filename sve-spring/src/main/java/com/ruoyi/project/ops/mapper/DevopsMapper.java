package com.ruoyi.project.ops.mapper;


import com.ruoyi.project.ops.domain.*;
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
public interface DevopsMapper {

    public List<OpSourceEntity> selectBasDevopsList(OpSourceEntity entity);

    public int insertBasDevops(OpSourceEntity entity);

    public int updateBasDevops(OpSourceEntity entity);

    public int deleteBasDevopsByIds(@Param("opSourceIds") Integer[] opSourceIds, @Param("updatedBy") String updatedBy);

}
