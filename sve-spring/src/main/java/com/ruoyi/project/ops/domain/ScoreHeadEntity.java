package com.ruoyi.project.ops.domain;

import java.math.BigDecimal;
import java.util.List;

public class ScoreHeadEntity {

    private Integer scoreId;
    private String pfSourceId;
    private Integer opSourceId;
    private String opSourceName;
    private Integer questionnaireId;
    private Integer avgScore;
    private Integer totalScore;
    private Integer sourceCnt;
    private BigDecimal finalScore;
    private String createdBy;
    private List<ScoreDetailEntity> details;

    private BigDecimal score;

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getOpSourceName() {
        return opSourceName;
    }

    public void setOpSourceName(String opSourceName) {
        this.opSourceName = opSourceName;
    }

    public Integer getSourceCnt() {
        return sourceCnt;
    }

    public void setSourceCnt(Integer sourceCnt) {
        this.sourceCnt = sourceCnt;
    }

    public BigDecimal getFinalScore() {
        return finalScore;
    }

    public void setFinalScore(BigDecimal finalScore) {
        this.finalScore = finalScore;
    }

    public Integer getScoreId() {
        return scoreId;
    }

    public void setScoreId(Integer scoreId) {
        this.scoreId = scoreId;
    }

    public String getPfSourceId() {
        return pfSourceId;
    }

    public void setPfSourceId(String pfSourceId) {
        this.pfSourceId = pfSourceId;
    }

    public Integer getOpSourceId() {
        return opSourceId;
    }

    public void setOpSourceId(Integer opSourceId) {
        this.opSourceId = opSourceId;
    }

    public Integer getQuestionnaireId() {
        return questionnaireId;
    }

    public void setQuestionnaireId(Integer questionnaireId) {
        this.questionnaireId = questionnaireId;
    }

    public Integer getAvgScore() {
        return avgScore;
    }

    public void setAvgScore(Integer avgScore) {
        this.avgScore = avgScore;
    }

    public Integer getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Integer totalScore) {
        this.totalScore = totalScore;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<ScoreDetailEntity> getDetails() {
        return details;
    }

    public void setDetails(List<ScoreDetailEntity> details) {
        this.details = details;
    }
}
