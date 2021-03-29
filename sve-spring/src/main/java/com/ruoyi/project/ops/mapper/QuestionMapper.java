package com.ruoyi.project.ops.mapper;


import com.ruoyi.project.ops.domain.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 【请填写功能名称】Mapper接口
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@Mapper
public interface QuestionMapper {

    public List<QuestionEntity> selectQuestionList(QuestionEntity entity);

    public int insertQuestion(QuestionEntity entity);

    public int updateQuestion(QuestionEntity entity);

    public int deleteQuestionByIds(@Param("questionIds") Integer[] questionIds, @Param("updatedBy") String updatedBy);

    public QuestionEntity selectQuestionById(QuestionEntity entity);

    public List<QuestionEntity> selectQuestionByIds(@Param("questionIds") Integer[] questionIds);

    public int insertQuestionnaire(QuestionnaireEntity entity);

    public int insertQuestionnaireDetail(QuestionnaireDetail entity);

    public int updateQuestionById(@Param("questionId") Integer questionId,@Param("createdBy") String createdBy);

    public List<QuestionnaireEntity> selectQuestionaireList();

    public List<QuestionnaireEntity> selectQuestionaireListById(@Param("sourceId") String sourceId);

    public List<QuestionnaireDetail> selectQuestionaireDetailList(@Param("questionnaireId") Integer questionnaireId);

    public List<ScoreHeadEntity> selectDevopsScoreList(@Param("questionnaireId") int questionnaireId);

    public List<ScoreDetailEntity> selectScoreDetailList(@Param("opSourceId") Integer opSourceId);

    public int insertScoreHead(ScoreHeadEntity entity);

    public int insertScoreDetail(ScoreDetailEntity entity);

    public List<QuestionnaireDetail> selectQuestionDetailList(@Param("questionnaireId") Integer questionnaireId);

}
