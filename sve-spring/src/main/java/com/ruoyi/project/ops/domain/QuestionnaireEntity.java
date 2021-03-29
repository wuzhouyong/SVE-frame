package com.ruoyi.project.ops.domain;

import java.util.List;

public class QuestionnaireEntity {

    private Integer questionnaireId;
    private String questionnaireName;
    private String coefficient;
    private String startDate;
    private String endDate;
    private String closedDate;
    private String createdBy;
    private Integer isClosed;

    private Integer isFinish;
    private String fullName;
    private String opSourceId;

    public Integer getIsFinish() {
        return isFinish;
    }

    public void setIsFinish(Integer isFinish) {
        this.isFinish = isFinish;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getOpSourceId() {
        return opSourceId;
    }

    public void setOpSourceId(String opSourceId) {
        this.opSourceId = opSourceId;
    }

    private List<QuestionnaireDetail> details;

    public Integer getIsClosed() {
        return isClosed;
    }

    public void setIsClosed(Integer isClosed) {
        this.isClosed = isClosed;
    }

    public List<QuestionnaireDetail> getDetails() {
        return details;
    }

    public void setDetails(List<QuestionnaireDetail> details) {
        this.details = details;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public String getQuestionnaireName() {
        return questionnaireName;
    }

    public void setQuestionnaireName(String questionnaireName) {
        this.questionnaireName = questionnaireName;
    }

    public String getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(String coefficient) {
        this.coefficient = coefficient;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getClosedDate() {
        return closedDate;
    }

    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
