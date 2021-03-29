package com.ruoyi.project.dgom.domain;

import com.ruoyi.common.core.lang.UUID;

public class SmsEntity {
    private String smsId;
    private String phoneNumber;
    private String content;
    private Integer smsType;

    public SmsEntity(String phone, String content, int smsType) {
        this.smsId = UUID.getUUID();
        this.phoneNumber = phone;
        this.content = content;
        this.smsType = smsType;
    }

    public String getSmsId() {
        return smsId;
    }

    public void setSmsId(String smsId) {
        this.smsId = smsId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getSmsType() {
        return smsType;
    }

    public void setSmsType(Integer smsType) {
        this.smsType = smsType;
    }
}
