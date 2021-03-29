package com.ruoyi.project.system.controller;

import java.util.List;

import com.ruoyi.framework.redis.RedisCache;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.domain.TreeSelect;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.domain.SysDept;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.ISysDeptService;
import com.ruoyi.project.system.service.ISysUserService;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;


@ApiOperation("Redis")
@RestController
@RequestMapping("/system/tool/redis")
public class SysRedisController extends BaseController {
    @Autowired
    private RedisCache redis;
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysUserService userService;


    /**
     * 获取redis缓存数据
     */
    @PreAuthorize("@ss.hasPermi('tool:redis:list')")
    @ApiOperation("获取redis列表数据")
    @ApiImplicitParam(name = "key", value = "获取redis列表数据", dataType = "String")
    @PostMapping("/getCacheList")
    public TableDataInfo getCacheList(String key) {
        List<ValueOperations<String, T>> res = redis.getCacheObjectList(key);
        return getDataTable(res);
    }

    @PreAuthorize("@ss.hasPermi('tool:redis:updatedeptUseratree')")
    @ApiOperation("获取redis部门用户结构树")
    @GetMapping("/getCacheDeptUserTree")
    public AjaxResult updateCacheDeptUserTree() {
        //List<TreeSelect> res = redis.getCacheList(dept_user_tree);

        List<SysDept> depts = deptService.selectDeptList(new SysDept());
        List<SysUser> users = userService.selectUserList(new SysUser());
        List<TreeSelect> tree = deptService.buildDeptUserTreeSelect(depts, users);
        redis.setCacheObject(redis.dept_user_tree, tree);
        return AjaxResult.success();
    }


    @PreAuthorize("@ss.hasPermi('tool:redis:updatedeptUseratree')")
    @GetMapping("/getCacheDept")
    public AjaxResult updateCacheDept() {
        redis.delCacheObjectList(redis.sys_dept);
        List<SysDept> dps = deptService.setAllDeptChild();
        redis.addCacheObjectList(redis.sys_dept, deptService.selectDeptList(new SysDept()));
        return AjaxResult.success();
    }

}