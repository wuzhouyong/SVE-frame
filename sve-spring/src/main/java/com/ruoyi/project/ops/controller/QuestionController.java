package com.ruoyi.project.ops.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.common.poi.DocumentUtil;
import com.ruoyi.project.ops.domain.QuestionEntity;
import com.ruoyi.project.ops.domain.QuestionnaireEntity;
import com.ruoyi.project.ops.domain.ScoreHeadEntity;
import com.ruoyi.project.ops.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/**
 * 【请填写功能名称】Controller
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController
@RequestMapping
public class QuestionController extends BaseController {
    @Autowired
    private QuestionService service;

    //评分问题列表  /question/list
    @PostMapping("/question/list")
    public TableDataInfo selectQuestionList(@RequestBody QuestionEntity entity) {
        startPage();
        List<QuestionEntity> list = service.selectQuestionList(entity);
        return getDataTable(list);
    }

    @PostMapping("/question/add")
    public ResultEntity insertQuestion(@RequestBody QuestionEntity entity) {
        return service.insertQuestion(entity);
    }

    @PostMapping("/question/edit")
    public ResultEntity updateQuestion(@RequestBody QuestionEntity entity) {
        return service.updateQuestion(entity);
    }

    @DeleteMapping("/question/delete/{questionIds}")
    public ResultEntity deleteQuestionByIds(@PathVariable Integer[] questionIds) {
        return service.deleteQuestionByIds(questionIds);
    }

    //新增评分邀请记录  /questionnaire/add
    @PostMapping("/questionnaire/add")
    public ResultEntity insertQuestionnaire(@RequestBody QuestionnaireEntity entity) {
        if (entity.getDetails() == null || entity.getDetails().isEmpty()) {
            return ResultEntity.error("评分不能为空");
        }
        int isAcc = 0;
        try {
            isAcc = service.insertQuestionnaire(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success("成功添加" + isAcc + "条");
    }

    //获取问卷主题列表  /questionnaire/theme_list  企业端
    @GetMapping("/questionnaire/theme_list")
    public ResultEntity selectQuestionnaireListById() {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId == null) return ResultEntity.error("企业不能为空");
        return ResultEntity.success(service.selectQuestionnaireListById(sourceId));
    }

    //评分邀请记录列表   /questionnaire/list
    @GetMapping("/questionnaire/list")
    public ResultEntity selectQuestionnaireList() {
        return ResultEntity.success(service.selectQuestionnaireList());
    }

    //新增企业评分   /score/add 中心端
    @PostMapping("/score/add")
    public ResultEntity insertScoreHead(@RequestBody ScoreHeadEntity entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) entity.setPfSourceId(sourceId);
        int isAcc = 0;
        try {
            isAcc = service.insertScoreHead(entity);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success(isAcc);
    }

    //获取评分列表   /score/list 中心端
    @GetMapping("/score/list/{questionnaireId}")
    public TableDataInfo selectDevopsScoreList(@PathVariable int questionnaireId) {
        startPage();
        List<ScoreHeadEntity> list = service.selectDevopsScoreList(questionnaireId);
        return getDataTable(list);
    }

    //评分列表导出
    @GetMapping("/score/export_list/{questionnaireId}")
    public AjaxResult exportDevopsScoreList(@PathVariable int questionnaireId) {
        List<ScoreHeadEntity> data = service.selectDevopsScoreList(questionnaireId);
        DocumentUtil doc = new DocumentUtil(data);
        doc.addColumn("运维单位", "opSourceName", 40);
        doc.addColumn("评分企业数", "sourceCnt", 15);
        doc.addColumn("平均分", "avgScore", 15);
        doc.addColumn("最终得分(平均分*系数)", "finalScore", 20);
        return doc.exportExcel("用户评价评分");
    }

    //获取问题列表  /questionnaire/list?questionnaireId=1
    @GetMapping("/questionnaire/list/{questionnaireId}")
    public ResultEntity selectQuestionDetailList(@PathVariable Integer questionnaireId) {
        return ResultEntity.success(service.selectQuestionDetailList(questionnaireId));
    }
}
