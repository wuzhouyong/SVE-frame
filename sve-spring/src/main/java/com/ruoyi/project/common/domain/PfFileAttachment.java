package com.ruoyi.project.common.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * pf_file_attachment
 *
 * @author ruoyi
 * @date 2020-05-23
 */
public class PfFileAttachment {
    private String attachmentId;

    private String bizSystemId;

    private String bizTableName;

    private String bizRecordId;

    private String attachmentType;

    private String showName;

    private String saveName;

    private Long isDeleted;

    private String createdBy;

    private String updatedBy;

    private Date dateCreated;

    private Date dateUpdated;

    private String attachmentTypeName;

    public void setAttachmentId(String attachmentId) {
        this.attachmentId = attachmentId;
    }

    public String getAttachmentId() {
        return attachmentId;
    }

    public void setBizSystemId(String bizSystemId) {
        this.bizSystemId = bizSystemId;
    }

    public String getBizSystemId() {
        return bizSystemId;
    }

    public void setBizTableName(String bizTableName) {
        this.bizTableName = bizTableName;
    }

    public String getBizTableName() {
        return bizTableName;
    }

    public void setBizRecordId(String bizRecordId) {
        this.bizRecordId = bizRecordId;
    }

    public String getBizRecordId() {
        return bizRecordId;
    }

    public void setAttachmentType(String attachmentType) {
        this.attachmentType = attachmentType;
    }

    public String getAttachmentType() {
        return attachmentType;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getShowName() {
        return showName;
    }

    public void setSaveName(String saveName) {
        this.saveName = saveName;
    }

    public String getSaveName() {
        return saveName;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("attachmentId", getAttachmentId())
                .append("bizSystemId", getBizSystemId())
                .append("bizTableName", getBizTableName())
                .append("bizRecordId", getBizRecordId())
                .append("attachmentType", getAttachmentType())
                .append("showName", getShowName())
                .append("saveName", getSaveName())
                .append("isDeleted", getIsDeleted())
                .append("createdBy", getCreatedBy())
                .append("updatedBy", getUpdatedBy())
                .append("dateCreated", getDateCreated())
                .append("dateUpdated", getDateUpdated())
                .toString();
    }

    public String getAttachmentTypeName() {
        return attachmentTypeName;
    }

    public void setAttachmentTypeName(String attachmentTypeName) {
        this.attachmentTypeName = attachmentTypeName;
    }
}