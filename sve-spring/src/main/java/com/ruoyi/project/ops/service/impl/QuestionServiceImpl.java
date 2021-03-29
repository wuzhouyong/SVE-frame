package com.ruoyi.project.ops.service.impl;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.ops.domain.*;
import com.ruoyi.project.ops.mapper.QuestionMapper;
import com.ruoyi.project.ops.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author solang
 * @date 2020-12-29 10:08
 */
@Service
@DataSource(value = DataSourceType.MASTER)
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionMapper mapper;

    @Override
    public List<QuestionEntity> selectQuestionList(QuestionEntity entity) {
        return mapper.selectQuestionList(entity);
    }

    @Override
    public ResultEntity insertQuestion(QuestionEntity entity) {
        entity.setCreatedBy(SecurityUtils.getUsername());
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.insertQuestion(entity);
        if (isAcc <= 0) {
            return ResultEntity.error("问卷发布失败");
        }
        return ResultEntity.success("问卷发布成功");
    }

    @Override
    public ResultEntity updateQuestion(QuestionEntity entity) {
        QuestionEntity quest = mapper.selectQuestionById(entity);
        if (quest == null) return ResultEntity.error("不存在该数据");
        if (quest.getIsUse() == 1) return ResultEntity.error("使用后不允许编辑");
        entity.setUpdatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.updateQuestion(entity);
        if (isAcc < 0) {
            return ResultEntity.error("编辑评分问题失败");
        }
        return ResultEntity.success("成功编辑" + isAcc + "条");
    }

    @Override
    public ResultEntity deleteQuestionByIds(Integer[] questionIds) {
        List<QuestionEntity> list = mapper.selectQuestionByIds(questionIds);
        List<Integer> isUses = list.stream().map(QuestionEntity::getIsUse).collect(Collectors.toList());
        if (isUses.contains(1)) return ResultEntity.error("使用后不允许删除");
        String updatedBy = SecurityUtils.getUsername();
        int isAcc = mapper.deleteQuestionByIds(questionIds, updatedBy);
        if (isAcc < 0) {
            return ResultEntity.error("删除评分问题失败");
        }
        return ResultEntity.success("成功删除" + isAcc + "条");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertQuestionnaire(QuestionnaireEntity entity) throws SQLException {
        entity.setCreatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.insertQuestionnaire(entity);
        if (isAcc < 0) {
            throw new SQLException("新增评分邀请记录失败");
        }
        for (QuestionnaireDetail detail : entity.getDetails()) {
            detail.setCreatedBy(SecurityUtils.getUsername());
            detail.setQuestionnaireId(entity.getQuestionnaireId());
            int count = mapper.insertQuestionnaireDetail(detail);
            if (count < 0) {
                throw new SQLException("新增评分邀请记录详情失败");
            }
            mapper.updateQuestionById(detail.getQuestionId(),detail.getCreatedBy());
        }
        return isAcc;
    }

    @Override
    public List<QuestionnaireEntity> selectQuestionnaireList() {
        return mapper.selectQuestionaireList();
    }

    @Override
    public List<QuestionnaireEntity> selectQuestionnaireListById(String sourceId) {
        return mapper.selectQuestionaireListById(sourceId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertScoreHead(ScoreHeadEntity entity) throws SQLException {
        entity.setCreatedBy(SecurityUtils.getUsername());
        int isAcc = mapper.insertScoreHead(entity);
        if (isAcc < 0) {
            throw new SQLException("新增企业评分失败");
        }
        for (ScoreDetailEntity detail : entity.getDetails()) {
            detail.setCreatedBy(SecurityUtils.getUsername());
            detail.setScoreId(entity.getScoreId());
            int count = mapper.insertScoreDetail(detail);
            if (count < 0) {
                throw new SQLException("新增新增企业评分详情失败");
            }
        }
        return isAcc;
    }

    @Override
    public List<ScoreHeadEntity> selectDevopsScoreList(int questionnaireId) {
        List<ScoreHeadEntity> list = mapper.selectDevopsScoreList(questionnaireId);
        for (ScoreHeadEntity head : list) {
            List<ScoreDetailEntity> detail = mapper.selectScoreDetailList(head.getOpSourceId());
            head.setDetails(detail != null ? detail : new ArrayList<>());
        }
        return list;
    }

    @Override
    public List<QuestionnaireDetail> selectQuestionDetailList(Integer questionnaireId) {
        return mapper.selectQuestionDetailList(questionnaireId);
    }
}
