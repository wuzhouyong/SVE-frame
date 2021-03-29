package com.ruoyi.project.dgom.mapper;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.attach.InsertAttachModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface AttachMapper {
    List<Map> getAttachList(@Param("refIds") String[] refId, @Param("refType") String refType);
    List<Map> getAttachListByIds(@Param("attachIds") String[] attachIds);
    int insertAttach(InsertAttachModel model);
    int removeAttach(@Param("attachFileId") String attachFileId, @Param("updatedBy") String updatedBy);
}
