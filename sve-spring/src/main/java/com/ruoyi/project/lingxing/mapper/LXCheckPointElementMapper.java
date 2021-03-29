package com.ruoyi.project.lingxing.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.lingxing.domain.LXCPElementEntity;
import com.ruoyi.project.lingxing.domain.LXDeleteEntity;
import com.ruoyi.project.lingxing.domain.LXNameIdEntity;
import com.ruoyi.project.voc.domain.VocCPElementEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface LXCheckPointElementMapper {

    @DataSource(value = DataSourceType.LINGXING)
    public List<LXCPElementEntity> selectCPElement(LXCPElementEntity entity);

    @DataSource(value = DataSourceType.LINGXING)
    public List<LXNameIdEntity> selectElement(LXNameIdEntity entity);

    @DataSource(value = DataSourceType.LINGXING)
    public int insertCPElement(LXCPElementEntity entity);

    @DataSource(value = DataSourceType.LINGXING)
    public int updateCPElement(LXCPElementEntity entity);

    @DataSource(value = DataSourceType.LINGXING)
    public int deleteCPElement(LXDeleteEntity entity);

    @DataSource(value = DataSourceType.LINGXING)
    public int repeatCheck(LXCPElementEntity entity);

    @DataSource(value = DataSourceType.LINGXING)
    public List<LXCPElementEntity> selectCPElementById(@Param("facilityId")String facilityId);

    //单独新增因子
    @DataSource(value = DataSourceType.LINGXING)
    public int insertCPElementByEle(LXCPElementEntity entity);

    //查询是否已经存在删除的数据
    public int selectCPElementByCollectId(LXCPElementEntity entity);

    //更新已经存在删除的数据
    public int updateCPElementByCollectId(LXCPElementEntity entity);

    //查询旧因子
    @DataSource(value = DataSourceType.LINGXING)
    public List<LXCPElementEntity> selectCPElementByIds(@Param("storageIds")String[] storageIds);

    //查询旧因子
    @DataSource(value = DataSourceType.LINGXING)
    public String selectCPElementByStorageId(@Param("storageId")String storageId);

    //删除因子
    @DataSource(value = DataSourceType.LINGXING)
    public int deleteCPElementById(LXCPElementEntity entity);

    //删除旧的数采
    @DataSource(value = DataSourceType.LINGXING)
    public int delectCPElementByStorageId(LXCPElementEntity entity);
}
