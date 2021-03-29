package com.ruoyi.project.facility.domain;

public class ElementParam {
    private String dbName;
    private String sourceId;
    private String collectId;
    private Integer cpElemId;
    private String facilityId;
    private String[] facilityIds;

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getCollectId() {
        return collectId;
    }

    public void setCollectId(String collectId) {
        this.collectId = collectId;
    }

    public Integer getCpElemId() {
        return cpElemId;
    }

    public void setCpElemId(Integer cpElemId) {
        this.cpElemId = cpElemId;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
    }

    public String[] getFacilityIds() {
        return facilityIds;
    }

    public void setFacilityIds(String[] facilityIds) {
        this.facilityIds = facilityIds;
    }
}
