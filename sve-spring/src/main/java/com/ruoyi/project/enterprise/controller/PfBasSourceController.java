package com.ruoyi.project.enterprise.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.domain.LeftMenuResult;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.LoginUser;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.enterprise.domain.source.BasSourceParam;
import com.ruoyi.project.enterprise.domain.source.SourceResult;
import com.ruoyi.project.enterprise.service.IPfBasSourceService;
import com.ruoyi.project.outwater.domain.entity.NameAndIdEntity;
import com.ruoyi.project.system.domain.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业信息Controller
 *
 * @author ruoyi
 * @date 2020-05-19
 */
@RestController
@RequestMapping("/enterprise/source")
public class PfBasSourceController extends BaseController {
    @Autowired
    private IPfBasSourceService service;

    @Autowired
    private TokenService tokenService;
    /**
     * 查询企业信息列表
     */
    @GetMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(PfBasSource user) {
        startPage();
        List<PfBasSource> enter = service.selectPfBasSourceList(user);
        for (PfBasSource p : enter) {
            p.setTagHeadList(service.selectTagHeadInfo(p.getSourceId()));
        }
        return getDataTable(enter);
    }

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo getSourceList(@RequestBody BasSourceParam param) {
        startPage();
        List<SourceResult> list = service.getSourceList(param);
        for (SourceResult p : list) {
            p.setTagHeadList(service.selectTagHeadInfo(p.getSourceId()));
        }
        return getDataTable(list);
    }

    @GetMapping("/listMap")
    @DataScope(regionAlias = "s")
    public TableDataInfo listMap(PfBasSource user) {
        startPage();
        List<PfBasSource> list = service.selectSourceListMap(user);
        return getDataTable(list);
    }

    @PostMapping("/by_tag")
    @DataScope(regionAlias = "s")
    public TableDataInfo listByTag(@RequestBody SourceByTag model) {
        startPage();
        List<SourceByTag> list = service.getSourceListByTag(model);
        return getDataTable(list);
    }

    @PostMapping("/homeindex")
    @DataScope(regionAlias = "s")
    public TableDataInfo homeIndex(@RequestBody SourceParam entity) {
        if (entity.getArray() == null || entity.getArray().length == 0)
            entity.setArray(new String[]{""});
        if (entity.getStatus() != null && entity.getStatus().length() == 1) {
            entity.setStatusArray(new String[]{entity.getStatus()});
        } else if (entity.getStatus() != null && entity.getStatus().length() > 1) {
            entity.setStatusArray(entity.getStatus().split(","));
        }
        startPage();
        List<PfBasSource> list = service.selectSourceHomeIndex(entity);
        return getDataTable(list);
    }

    @PostMapping("/leftmenu")
    @DataScope(regionAlias = "s")
    public LeftMenuResult leftMenu(@RequestBody SourceParam entity) {
        LeftMneuData data = new LeftMneuData();
        if (entity.getArray() == null || entity.getArray().length == 0)
            entity.setArray(new String[]{""});
        List<LeftMenuEntity> sourceStatusList = service.selectLeftMenuSourceCount(entity);
        int sourceCount = service.selectSourceSum(entity);
        int warnCount = service.selectWarnCount(entity);
        List<LeftMenuEntity> warnLe1 = service.selectWarnLe1(entity);
        List<LeftMenuEntity> warnLe2 = service.selectWarnLe2(entity);
        List<LeftMenuEntity> warnLe3 = service.selectWarnLe3(entity);
        List<LeftMenuEntity> conStatus = service.selectConStatus(entity);
        List<LeftMenuEntity> mList1 = new ArrayList<>();
        List<LeftMenuEntity> mList2 = new ArrayList<>();
        List<LeftMenuEntity> mList3 = new ArrayList<>();
        ConModule module1 = new ConModule();
        module1.setTitle(new LeftMenuEntity("企业总数", sourceCount, ""));
        for (LeftMenuEntity l : sourceStatusList) {
            switch (l.getType()) {
                case "3":
                    mList1.add(new LeftMenuEntity("在产", l.getCount(), getRatio(l.getCount(), sourceCount)));
                    break;
                case "4":
                    mList1.add(new LeftMenuEntity("停产", l.getCount(), getRatio(l.getCount(), sourceCount)));
                    break;
                case "5":
                    mList1.add(new LeftMenuEntity("关闭", l.getCount(), getRatio(l.getCount(), sourceCount)));
                    break;
                case "6":
                    mList1.add(new LeftMenuEntity("其他", l.getCount(), getRatio(l.getCount(), sourceCount)));
                    break;
                default:
                    break;
            }
        }

        int s = 0, cSum = 0;
        for (LeftMenuEntity l : conStatus) {
            if (l.getType().equals("2") || l.getType().equals("3") || l.getType().equals("4") || l.getType().equals("5")) {
                s += l.getCount();
            }
            cSum += l.getCount();
        }

        int MARK = 0;
        for (LeftMenuEntity l : conStatus) {
            LeftMenuEntity e = new LeftMenuEntity("已接入", s, getRatio(s, cSum));
            switch (l.getType()) {
                case "0":
                    mList2.add(new LeftMenuEntity("未开展", l.getCount(), getRatio(l.getCount(), cSum)));
                    break;
                case "1":
                    mList2.add(new LeftMenuEntity("施工中", l.getCount(), getRatio(l.getCount(), cSum)));
                    break;
                case "2":
                case "3":
                case "4":
                    if (MARK == 0) {
                        mList2.add(e);
                        MARK = 1;
                    }
                    break;
                case "6":
                    mList2.add(new LeftMenuEntity("已验收", l.getCount(), getRatio(l.getCount(), cSum)));
                    break;
                default:
                    break;
            }
        }
        mList3.add(new LeftMenuEntity("无异常", sourceCount - warnCount, getRatio(sourceCount - warnCount, sourceCount)));
        mList3.add(new LeftMenuEntity("有异常", warnCount, getRatio(warnCount, sourceCount)));

        Map<Integer, String> accept_status = new HashMap<>();
        accept_status.put(0, "未开展");
        accept_status.put(1, "施工中");
        accept_status.put(2, "已接入");
        accept_status.put(3, "已验收");
        accept_status.forEach((k, v) -> {
//            List<LeftMenuEntity> items = mList2.stream().filter(p -> p.getType().equals(v)).collect(Collectors.toList());
            if (mList2.stream().noneMatch(p -> p.getType().equals(v))) {
                mList2.add(new LeftMenuEntity(v, 0, null, k));
            }
            mList2.stream().filter(p -> p.getType().equals(v)).forEach(f -> f.setOrderBy(k));
        });

        /*
        List<String> titleList = new ArrayList<>(Arrays.asList(new String[]{"已验收", "已联网", "已施工", "未接入"}));
        titleList.forEach(e -> {
            if (item != null) {
                item.get();
            } else {
                mList2.add(new LeftMenuEntity(e, 0, null));
            }
        });*/
        module1.setData(mList1);

        ConModule module2 = new ConModule();
        ConModule module3 = new ConModule();
        ConModule module4 = new ConModule();
        ConModule module5 = new ConModule();
        ConModule module6 = new ConModule();

        module5.setData(mList2);
        module6.setData(mList3);
        int l1 = 0, l2 = 0, l3 = 0;
        int other1 = 0, other2 = 0, other3 = 0;
        for (int i = 0; i < warnLe1.size(); i++) {
            l1 += warnLe1.get(i).getCount();
            if (i > 3) {
                other1 += warnLe1.get(i).getCount();
            }
        }
        for (int i = 0; i < warnLe2.size(); i++) {
            l2 += warnLe2.get(i).getCount();
            if (i > 3) {
                other2 += warnLe2.get(i).getCount();
            }
        }
        for (int i = 0; i < warnLe3.size(); i++) {
            l3 += warnLe3.get(i).getCount();
            if (i > 3) {
                other3 += warnLe3.get(i).getCount();
            }
        }

        module2.setTitle(new LeftMenuEntity("一级异常", l1, ""));

        module3.setTitle(new LeftMenuEntity("二级异常", l2, ""));

        module4.setTitle(new LeftMenuEntity("三级异常", l3, ""));

        List<LeftMenuEntity> listL1 = new ArrayList<>();
        List<LeftMenuEntity> listL2 = new ArrayList<>();
        List<LeftMenuEntity> listL3 = new ArrayList<>();

        for (int i = 0; i < warnLe1.size(); i++) {
            if (i < 4) {
                listL1.add(new LeftMenuEntity(warnLe1.get(i).getDesc(), warnLe1.get(i).getCount(), getRatio(warnLe1.get(i).getCount(), l1)));
            } else {
                listL1.add(new LeftMenuEntity("其他", other1, getRatio(other1, l1)));
                break;
            }
        }
        module2.setData(listL1);

        for (int i = 0; i < warnLe2.size(); i++) {
            if (i < 4) {
                listL2.add(new LeftMenuEntity(warnLe2.get(i).getDesc(), warnLe2.get(i).getCount(), getRatio(warnLe2.get(i).getCount(), l2)));
            } else {
                listL2.add(new LeftMenuEntity("其他", other2, getRatio(other2, l2)));
                break;
            }
        }
        module3.setData(listL2);

        for (int i = 0; i < warnLe3.size(); i++) {
            if (i < 4) {
                listL3.add(new LeftMenuEntity(warnLe3.get(i).getDesc(), warnLe3.get(i).getCount(), getRatio(warnLe3.get(i).getCount(), l3)));
            } else {
                listL3.add(new LeftMenuEntity("其他", other3, getRatio(other3, l3)));
                break;
            }
        }
        module4.setData(listL3);

        data.setModule1(module1);
        data.setModule5(module5);
        data.setModule6(module6);
        data.setModule2(module2);
        data.setModule3(module3);
        data.setModule4(module4);
        return new LeftMenuResult(200, data, "");
    }

    private String getRatio(int count, int sum) {
        double a = Double.valueOf(count);
        double b = Double.valueOf(sum);
        double r = (double) Math.round((a * 100 / b) * 100) / 100;
        ;
        return r + "%";
    }


    @GetMapping("/wade")
    @DataScope(regionAlias = "c")
    public TableDataInfo wade(WadeTaskSummary w) {
        startPage();
        List<WadeTaskSummary> list = service.selectWadeTaskSummary(w);
        return getDataTable(list);
    }

    @GetMapping("/gas")
    @DataScope(regionAlias = "c")
    public TableDataInfo gas(GasTaskSummary g) {
        startPage();
        List<GasTaskSummary> list = service.selectGasTaskSummary(g);
        return getDataTable(list);
    }

    /**
     * 查询企业关联平台列表
     */
    @GetMapping("/detailList")
    /*@DataScope(regionAlias = "s")*/
    public ResultEntity detailList(PfBasSource user) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            user.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        return ResultEntity.success(service.selectDetailListById(user));
    }

    /**
     * 导出企业信息列表
     */
    @PreAuthorize("@ss.hasPermi('system:source:export')")
    @Log(title = "企业信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(PfBasSource pfBasSource) {
        List<PfBasSource> list = service.selectPfBasSourceList(pfBasSource);
        ExcelUtil<PfBasSource> util = new ExcelUtil<PfBasSource>(PfBasSource.class);
        return util.exportExcel(list, "source");
    }

    /**
     * 获取企业信息详细信息
     * 企业端共用接口
     */
    @GetMapping(value = "/{sourceId}")
    public AjaxResult getInfo(@PathVariable("sourceId") String sourceId) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        }
        int isHasVOCs = service.isHasVOCs(sourceId);
        PfBasSource enter = service.selectPfBasSourceById(sourceId);
        enter.setIsHasVOCs(isHasVOCs);
        if (isHasVOCs > 0) {
            SourceVocsParamEntity vocsParamEntity = service.selectSourceVocsParam(sourceId);
            enter.setSourceVocsParamEntity(vocsParamEntity);
        }
        enter.setTags(HashMapUtils.toCamel(service.selectSourceTagById(sourceId)));
        return AjaxResult.success(enter);
    }

    @GetMapping("/module1")
    public AjaxResult module1() {
        String sourceId = "";
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        }
        SourceModuleOne one = service.selectModuleOne(sourceId);
        if (one != null) {
            int isHasVOCs = service.isHasVOCs(sourceId);
            one.setIsHasVOCs(isHasVOCs);

            return AjaxResult.success(one);
        } else
            return AjaxResult.success(new SourceModuleOne());

    }

    @GetMapping("/module2")
    public AjaxResult module2() {
        String sourceId = "";
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        }
        SourceModuleTwo model = service.selectModuleTwo(sourceId);
        return model != null ? AjaxResult.success(model) : AjaxResult.success(new SourceModuleTwo());
    }

    @GetMapping("/vocs")
    public AjaxResult vocs(String sourceId) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        }
        SourceVocsParamEntity model = service.selectSourceVocsParam(sourceId);
        return model != null ? AjaxResult.success(model) : AjaxResult.success(new SourceModuleTwo());
    }

    /**
     * 新增企业信息
     */
    @PreAuthorize("@ss.hasPermi('source:admin:add')")
    @Log(title = "新增企业", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody PfBasSource pfBasSource) {
        pfBasSource.setSourceId(java.util.UUID.randomUUID().toString());
        pfBasSource.setCreatedBy(SecurityUtils.getUsername());
        pfBasSource.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(service.insertPfBasSource(pfBasSource));
    }

    /**
     * 企业关联平台
     */
    @PreAuthorize("@ss.hasPermi('source:admin:task')")
    @PostMapping("/correlation")
    public AjaxResult correlation(@RequestBody PfBasTagDetailListEntity entity) {
        return service.insertTagDetail(entity.getList());
    }

    @PreAuthorize("@ss.hasPermi('source:admin:branch')")
    @PostMapping("/correlationfj")
    public AjaxResult correlationFJ(@RequestBody PfBasTagDetailListEntity entity) {
        return toAjax(service.insertTagDetailFJ(entity.getList()));
    }

    @PostMapping("/tDList")
    public ResultEntity tDList(@RequestBody PfBasTagDetailEntity entity) {
        List<PfBasTagDetailEntity> list = service.selectTagDetail(entity.getPfSourceId());
        return list != null ? ResultEntity.success(list, "请求数据成功") : ResultEntity.error("数据请求失败");
    }

    /**
     * 修改企业信息
     */
    @PreAuthorize("@ss.hasPermi('source:admin:edit')")
    @Log(title = "编辑企业", businessType = BusinessType.UPDATE)
    @PutMapping("/edit")
    public ResultEntity edit(@RequestBody PfBasSource pfBasSource) {

        //当前企业只能修改自己信息
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        String sourceId = loginUser.getUser().getSourceId();
        if (StringUtils.isEmpty(sourceId) || pfBasSource.getSourceId().equals(sourceId)) {
            int res = service.updatePfBasSource(pfBasSource);
            return res == 1 ? ResultEntity.success("企业信息修改成功") : ResultEntity.error("企业信息修改失败");
        } else
            return ResultEntity.error("企业信息修改失败");
    }

    /**
     * 删除企业信息
     */
    @PreAuthorize("@ss.hasPermi('source:admin:del')")
    @Log(title = "删除企业", businessType = BusinessType.DELETE)
    @DeleteMapping("/{sourceIds}")
    public ResultEntity remove(@PathVariable String[] sourceIds) {
        int res = service.deletePfBasSourceByIds(sourceIds);
        if (res == sourceIds.length) {
            service.deleteTagDetailByIds(sourceIds);
            service.deleteEnterUserByIds(sourceIds);
        }
        return res > -1 ? ResultEntity.success("数据删除成功") : ResultEntity.error("数据删除失败");
    }

    @GetMapping("/tagList")
    /*@DataScope(regionAlias = "s")*/
    public ResultEntity tagList() {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        //List<SysMenu> menus = menuService.selectSystemMenuByUserId(user.getUserId());
        List<NameAndIdEntity> list = service.selectTagIdNameList(user.getUserId());
        return ResultEntity.success(list);
    }

      //查询tag名字
    @GetMapping("/tagNameList")  //   /enterprise/source/tagNameList
    public TableDataInfo tagHeadName() {
        return getDataTable(service.selectTagHeadName());
    }
      // 根据sourceid查询tag名字
    @GetMapping("/tagBySource")  //   /enterprise/source/tagBySource
    public TableDataInfo getTagsBySource(String sourceId) {
        return getDataTable(service.getTagsBySource(sourceId));
    }

      // 根据tagId修改tagName
    @GetMapping("/update_tag_name/{tagId}")  //  /enterprise/source/update_tag_name/{tagId}
    public ResultEntity updateTagName(@PathVariable String tagId, String tagName, Integer orderBy) {
      return ResultEntity.toAjax(service.updateTagName(tagId,tagName, orderBy));
    }


}