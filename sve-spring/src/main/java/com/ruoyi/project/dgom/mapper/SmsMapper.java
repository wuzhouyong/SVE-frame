package com.ruoyi.project.dgom.mapper;

import com.ruoyi.project.dgom.domain.SmsEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SmsMapper {
    int sendSms(SmsEntity entity);
}
