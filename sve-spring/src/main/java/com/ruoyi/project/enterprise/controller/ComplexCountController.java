package com.ruoyi.project.enterprise.controller;


import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.project.enterprise.service.ComplexCountService;
import com.ruoyi.project.guocheng.domain.GCGatherEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enterprise/statistical")
public class ComplexCountController {

    @Autowired
    private ComplexCountService service;

    @GetMapping("/town_count/{tagHeadId}")
    public ResultEntity getTownComplexCount(@PathVariable String tagHeadId) {
        return ResultEntity.success(service.getTownComplexCount(tagHeadId));
    }

}
