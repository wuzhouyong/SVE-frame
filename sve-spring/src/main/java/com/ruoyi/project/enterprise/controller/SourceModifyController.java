package com.ruoyi.project.enterprise.controller;

import com.miracleren.NiceDoc;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataScope;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.enterprise.domain.*;
import com.ruoyi.project.enterprise.service.IPfBasSourceService;
import com.ruoyi.project.enterprise.service.SourceModifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enterprise/modify")
public class SourceModifyController extends BaseController {

    @Autowired
    private SourceModifyService sourceModifyService;

    @Autowired
    private IPfBasSourceService pfBasSourceService;

    //@PreAuthorize("@ss.hasPermi('qixiu:checkpoint:add')")
    @Log(title = "企业基础资料修改申请新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody SourceModifyEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        //downloadWord();
        return sourceModifyService.insertSourceModify(entity);
    }

    @GetMapping("/getid")
    public AjaxResult getid() {
        String sourceId = "";
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        }
        SourceModifyEntity entity = sourceModifyService.getSourceEntity(sourceId);
        if (entity != null)
            return AjaxResult.success("数据请求成功", entity.getModifyId());
        else
            return AjaxResult.success("", null);
    }

    @PostMapping("/list")
    @DataScope(regionAlias = "s")
    public TableDataInfo list(@RequestBody SourceModifyEntity entity) {
        startPage();
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        List<SourceModifyEntity> list = sourceModifyService.selectSourceModify(entity);
        return getDataTable(list);
    }

    @PostMapping("/agree")
    public AjaxResult agree(@RequestBody SourceModifyEntity entity) {
        return toAjax(sourceModifyService.agree(entity));
    }

    @PostMapping("/source")
    public AjaxResult source(@RequestBody SourceOtherColumn entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(sourceModifyService.updateBasSource(entity));
    }

    @PostMapping("/vocs")
    public AjaxResult vocs(@RequestBody SourceVocsParamEntity entity) {
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            entity.setSourceId(SecurityUtils.getLoginUser().getUser().getSourceId());
        }
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(sourceModifyService.updateVocs(entity));
    }

    @GetMapping("/word")
    public void downloadWord(String sourceId, HttpServletResponse response) {
        SourceModuleOne one = pfBasSourceService.selectModuleOne(sourceId);
        SourceModuleTwo two = pfBasSourceService.selectModuleTwo(sourceId);
        SourceVocsParamEntity vocs = pfBasSourceService.selectSourceVocsParam(sourceId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("fullName", one.getFullName());
        map.put("legalPersonName", one.getLegalPersonName());
        map.put("envContactName", two.getEnvContactName());
        map.put("address", one.getAddress());
        map.put("envContactPhne", two.getEnvContactPhone());

        if (vocs != null) {
            map.put("invsertTotal", vocs.getInvsertTotal());
            map.put("constructionUnit", vocs.getConstructionUnit());
            map.put("productFlow", vocs.getProductFlow());
            map.put("gasCollect", vocs.getGasCollect());
            map.put("processFlow", vocs.getProcessFlow());
            map.put("processFlowMemo", vocs.getProcessFlowMemo());
            map.put("keyPump", vocs.getKeyPump());
            map.put("keyBlower", vocs.getKeyBlower());
            map.put("keySorbMaterial", vocs.getKeySorbMaterial());
            map.put("keySorbPeriod", vocs.getKeySorbPeriod());
            map.put("keyPhotoPower", vocs.getKeyPhotoPower());
            map.put("keyPlasmaPower", vocs.getKeyPlasmaPower());
            map.put("keyRecoveryRate", vocs.getKeyRecoveryRate());
            map.put("keyBurnTemp", vocs.getKeyBurnTemp());
            map.put("keyRecoveryTime", vocs.getKeyRecoveryTime());
            map.put("keyLossPeriod", vocs.getKeyLossPeriod());
            map.put("keyBurnerCount", vocs.getKeyBurnerCount());
            map.put("keyHeatTime", vocs.getKeyHeatTime());
            map.put("keyInterTemp", vocs.getKeyInterTemp());
            map.put("keyOutgasTemp", vocs.getKeyOutgasTemp());
            map.put("keyTowerTime", vocs.getKeyTowerTime());
            map.put("keyMudPeriod", vocs.getKeyMudPeriod());
            map.put("keyLiquidPeriod", vocs.getKeyLiquidPeriod());
            map.put("keyLiquidAmount", vocs.getKeyLiquidAmount());
            map.put("keyCyclePeriod", vocs.getKeyCyclePeriod());
            map.put("keyArea", vocs.getKeyArea());
            map.put("keyWorkTime", vocs.getKeyWorkTime());
            map.put("memo", vocs.getMemo());
        }

        String path = null;
        NiceDoc doc;
        try {
            // word 模板路径 classpath:template.docx
            File sourceFile = ResourceUtils.getFile("classpath:template/template.docx");
            path = sourceFile.getPath();
            doc = new NiceDoc(path);
            doc.setLabel(map);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            String filename = URLEncoder.encode("VOCs企业废气治理设施参数记录表.docx", "UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename + "");
//            response.setHeader("content-disposition", "attachment;filename=" + URLEncoder.encode("VOCs企业废气治理设施参数记录表.docx", "UTF-8"));
            OutputStream outputStream = response.getOutputStream();
            doc.saveOutStream(outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/history")
    public TableDataInfo history() {
        String sourceId = "";
        if (!StringUtils.isEmpty(SecurityUtils.getLoginUser().getUser().getSourceId())) {
            sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        }
        return getDataTable(sourceModifyService.selectModifyHistory(sourceId));
    }
}
