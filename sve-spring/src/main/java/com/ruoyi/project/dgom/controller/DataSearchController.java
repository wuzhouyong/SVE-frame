package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.dgom.domain.DataReturnEntity;
import com.ruoyi.project.dgom.domain.DataSearchPar;
import com.ruoyi.project.dgom.mapper.DataSearchMapper;
import com.ruoyi.project.dgom.service.DataSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 【请填写功能名称】Controller 在线 data_search
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController("online_data_search1")
@RequestMapping("/dgom/data_search")
public class DataSearchController extends BaseController {
    @Autowired
    private DataSearchService dataSearchService;

    @Autowired
    private DataSearchMapper dataSearchMapper;

    //查找企业列表  /dgom/data_search/enterprise
    @GetMapping("/enterprise")
    public ResultEntity getEnterpriseList(String fullName) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            sourceId = dataSearchMapper.checkBizSourceId(sourceId);
            if (sourceId == null) {
                return ResultEntity.error("企业未关联或关联错误");
            }
        }
        return ResultEntity.success(dataSearchService.selectEnterpriseList(sourceId, fullName));
    }

    //查找排放口  /dgom/data_search/checkpoint
    @GetMapping("/checkpoint/{sourceId}")
    public ResultEntity getEmissionList(@PathVariable String sourceId) {
        String source_id = SecurityUtils.getLoginUser().getUser().getSourceId();//企业端
        if (StringUtils.isNotEmpty(source_id)) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(source_id);
            if (StringUtils.isEmpty(bizSourceId)) return ResultEntity.error("企业不能为空");
            return ResultEntity.success(dataSearchService.selectEmissionList(bizSourceId));
        }
        if (StringUtils.isEmpty(sourceId)) return ResultEntity.error("企业不能为空");
        return ResultEntity.success(dataSearchService.selectEmissionList(sourceId)); //中心端
    }

    //查找因子  /dgom/data_search/element
    @GetMapping("/element")
    public ResultEntity getElementList() {
        return ResultEntity.success(dataSearchService.selectElementList());
    }

    //查找排放口下的因子  /dgom/data_search/checkpoint_element
    @GetMapping("/checkpoint_element/{checkpointId}")
    public ResultEntity getElementListByCheckpointId(@PathVariable String checkpointId) {
        return ResultEntity.success(dataSearchService.getElementListByCheckpointId(checkpointId));
    }

    //查找街镇  /dgom/data_search/townname
    @GetMapping("/townname")
    public ResultEntity getTownNameList() {
        return ResultEntity.success(dataSearchService.selectTownNameList());
    }

    //查找任务列表 /dgom/data_search/tasklist 表头
    @PostMapping("/tasklist")
    public TableDataInfo getTasklistByCondition(@RequestBody DataSearchPar entity) {
        startPage();
        List<DataSearchPar> list = dataSearchService.selectTasklistByCondition(entity);
        return getDataTable(list);
    }

    //查找任务详情  /dgom/data_search/taskInfo/{taskheadid}
    @GetMapping("/taskInfo/{taskheadid}")
    public ResultEntity getAbnortaskheadInfo(@PathVariable String taskheadid) {
        return ResultEntity.success(dataSearchService.selectAbnortaskheadInfo(taskheadid));
    }

    // 表头实时数据   /dgom/data_search/header_real
    @PostMapping("/header_real")
    public ResultEntity getHeaderReal(@RequestBody DataSearchPar entity) {
        return ResultEntity.success(dataSearchService.getHeaderReal(entity));
    }

    // 表头分钟数据   /dgom/data_search/header_minute
    @PostMapping("/header_minute")
    public ResultEntity getHeaderMinute(@RequestBody DataSearchPar entity) {
        return ResultEntity.success(dataSearchService.getHeaderMinute(entity));
    }

    // 表头小时数据   /dgom/data_search/header_hour
    @PostMapping("/header_hour")
    public ResultEntity getHeaderHour(@RequestBody DataSearchPar entity) {
        return ResultEntity.success(dataSearchService.getHeaderHour(entity));
    }

    // 表头日数据   /dgom/data_search/header_day
    @PostMapping("/header_day")
    public ResultEntity getHeaderDay(@RequestBody DataSearchPar entity) {
        return ResultEntity.success(dataSearchService.getHeaderDay(entity));
    }

    // 实时数据   /dgom/data_search/real
    @PostMapping("/real")
    public ResultEntity getReal(@RequestBody DataSearchPar entity) {
        return ResultEntity.success(dataSearchService.getReal(entity));
    }

    // 分钟数据   /dgom/data_search/minute
    @PostMapping("/minute")
    public ResultEntity getMinute(@RequestBody DataSearchPar entity) {
        return ResultEntity.success(dataSearchService.getMinute(entity));
    }

    // 小时数据   /dgom/data_search/hour
    @PostMapping("/hour")
    public ResultEntity getHour(@RequestBody DataSearchPar entity) {
        return ResultEntity.success(dataSearchService.getHour(entity));
    }

    // 日数据   /dgom/data_search/day
    @PostMapping("/day")
    public ResultEntity getDay(@RequestBody DataSearchPar entity) {
        return ResultEntity.success(dataSearchService.getDay(entity));
    }

    // 缺失数据(小时和日)   /dgom/data_search/missing_hour
    @PostMapping("/missing_hour")
    public TableDataInfo getMissingHour(@RequestBody DataSearchPar entity) {
        startPage();
        List<DataReturnEntity> list = dataSearchService.getMissingHour(entity);
        return getDataTable(list);
    }

    // 零值数据和超标数据(小时和日)  /dgom/data_search/zerovalue_hour
    @PostMapping("/zerovalue_hour")
    public TableDataInfo getZerovalueHour(@RequestBody DataSearchPar entity) {
        startPage();
        List<DataReturnEntity> list = dataSearchService.getZerovalueHour(entity);
        return getDataTable(list);
    }


}
