package com.ruoyi.project.dgom.service;

public interface ISmsService {
    int sendSms(String phone, String content);
    int sendSms(String phone, String content, int smsType);
}
