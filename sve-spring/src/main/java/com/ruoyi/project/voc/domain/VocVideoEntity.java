package com.ruoyi.project.voc.domain;

import com.ruoyi.common.utils.DateStringUtils;
import com.ruoyi.framework.web.domain.BaseEntity;

public class VocVideoEntity extends BaseEntity {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String videoId;

    private String createdBy;

    private String updatedBy;

    private String sourceId;

    private String videoDesc;

    private String videoAddress;

    private String videoType;

    private String settings;

    private String accessToken;

    private String appkey;

    private String appsecret;

    private String expireTime;

    private String fullName;

    private String dictLabel;

    private String bizSourceId;

    public String getBizSourceId() {
        return bizSourceId;
    }

    public void setBizSourceId(String bizSourceId) {
        this.bizSourceId = bizSourceId;
    }

    public String getDictLabel() {
        return dictLabel;
    }

    public void setDictLabel(String dictLabel) {
        this.dictLabel = dictLabel;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getVideoDesc() {
        return videoDesc;
    }

    public void setVideoDesc(String videoDesc) {
        this.videoDesc = videoDesc;
    }

    public String getVideoAddress() {
        return videoAddress;
    }

    public void setVideoAddress(String videoAddress) {
        this.videoAddress = videoAddress;
    }

    public String getVideoType() {
        return videoType;
    }

    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        this.settings = settings;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    public String getAppsecret() {
        return appsecret;
    }

    public void setAppsecret(String appsecret) {
        this.appsecret = appsecret;
    }

    public String getExpireTime() {
        return DateStringUtils.formatTimeStr(expireTime);
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }
}
