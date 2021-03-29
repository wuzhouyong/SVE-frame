package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.dgom.domain.DataReturnEntity;
import com.ruoyi.project.dgom.domain.DataSearchPar;
import com.ruoyi.project.dgom.service.WorkPlaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 【请填写功能名称】Controller 在线 data_search
 *
 * @author ruoyi
 * @date 2020-08-25
 */
@RestController
@RequestMapping("/dgom/work_place")
public class WorkPlaceController extends BaseController {
    @Autowired
    private WorkPlaceService placeService;

    //传输率低于90%污染源  /dgom/work_place/source_name
    @GetMapping("/source_name")
    public ResultEntity getSourceNameList() {
        return ResultEntity.success(placeService.getSourceNameList());
    }


}
