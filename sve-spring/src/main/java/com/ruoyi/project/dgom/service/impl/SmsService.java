package com.ruoyi.project.dgom.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.SmsEntity;
import com.ruoyi.project.dgom.mapper.SmsMapper;
import com.ruoyi.project.dgom.service.ISmsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@DataSource(value = DataSourceType.HBONLINE)
public class SmsService implements ISmsService {
    @Autowired
    private SmsMapper mapper;

    @Override
    public int sendSms(String phone, String content) {
        return sendSms(phone, content, 2);
    }

    @Override
    public int sendSms(String phone, String content, int smsType) {
        SmsEntity entity = new SmsEntity(phone, content, smsType);
        return mapper.sendSms(entity);
    }
}
