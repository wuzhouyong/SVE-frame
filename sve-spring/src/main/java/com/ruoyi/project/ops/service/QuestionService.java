package com.ruoyi.project.ops.service;


import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.ops.domain.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Service接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
public interface QuestionService {

    public List<QuestionEntity> selectQuestionList(QuestionEntity entity);

    public ResultEntity insertQuestion(QuestionEntity entity);

    public ResultEntity updateQuestion(QuestionEntity entity);

    public ResultEntity deleteQuestionByIds(Integer[] questionIds);

    public int insertQuestionnaire(QuestionnaireEntity entity) throws SQLException;

    public List<QuestionnaireEntity> selectQuestionnaireListById(String sourceId);

    public List<QuestionnaireEntity> selectQuestionnaireList();

    public int insertScoreHead(ScoreHeadEntity entity) throws SQLException;

    public List<ScoreHeadEntity> selectDevopsScoreList(int questionnaireId);

    public List<QuestionnaireDetail> selectQuestionDetailList(Integer questionnaireId);
}
