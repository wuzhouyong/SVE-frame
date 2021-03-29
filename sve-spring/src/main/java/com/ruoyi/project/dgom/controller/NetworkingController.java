package com.ruoyi.project.dgom.controller;

import com.miracleren.NiceDoc;
import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.dgom.domain.*;
import com.ruoyi.project.dgom.domain.network_apply.*;
import com.ruoyi.project.dgom.mapper.DataSearchMapper;
import com.ruoyi.project.dgom.mapper.SuperviseTaskMapper;
import com.ruoyi.project.dgom.service.AttachService;
import com.ruoyi.project.dgom.service.NetworkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dgom/networking")
public class NetworkingController extends BaseController {

    @Autowired
    private DataSearchMapper dataSearchMapper;

    @Autowired
    private SuperviseTaskMapper taskMapper;

    @Autowired
    private NetworkingService workingService;

    @Autowired
    private AttachService attachService;

    /**
     * 申请列表
     *
     * @param model
     * @return
     */
    @PostMapping("/list")  // 执行查询 /dgom/networking/list
    public TableDataInfo list(@RequestBody NetworkingSearchModel model) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            try {
                Integer bizSourceId = Integer.valueOf(dataSearchMapper.checkBizSourceId(sourceId));
                model.setSourceId(bizSourceId);
            } catch (Exception ex) {
                return errorForDataTable("未关联正确的在线监控企业");
            }
        }
        if (model.getStatus() != null) {
            model.setStatu(model.getStatus().split(","));
        }
        if (model.getTownCode() != null) {
            model.setTownId(taskMapper.getOMTownIdByCode(model.getTownCode()));
        }
        startPage();
        List<RegisterModel> list = workingService.selectConnectApplyList(model);
        return getDataTable(list);
    }

    @GetMapping("/model/{applyId}")  // 获取详情 /dgom/networking/model/{applyId}
    public ResultEntity model(@PathVariable String applyId) {
        RegisterModel model = workingService.getRegisterModel(applyId);
        return ResultEntity.success(model);
    }

    /**
     * 联网申请
     *
     * @param model 提交上来的数据
     * @return
     */
    @PostMapping("/register")
    public ResultEntity register(@RequestBody RegisterModel model) {
        // 返回id到客户端
        String result = null;
        try {
            result = workingService.insertConnectapply(model);
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
        return ResultEntity.success(result);
    }

    @GetMapping("/export/{applyId}")  // 获取数据根据模板导出word
    public void exportWord(@PathVariable String applyId, HttpServletResponse response) {
        Map<String, Object> params = workingService.selectRegisterModelById(applyId);
        String path = null;
        NiceDoc doc;
        try {
            // word 模板路径 classpath:connect-template.docx
            File sourceFile = ResourceUtils.getFile("classpath:template/connect-template.docx");
            path = sourceFile.getPath();
            doc = new NiceDoc(path);
            doc.setLabel(params);
            doc.setTable((List<Map<String, Object>>) params.get("firstTable"), "firstTable");
            doc.setTable((List<Map<String, Object>>) params.get("twoTable"), "twoTable");
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/vnd.openxmlformats-officedocument.wordprocessingml.document");
            String filename = URLEncoder.encode("联网申请.docx", "UTF-8");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename + "");
            OutputStream outputStream = response.getOutputStream();
            doc.saveOutStream(outputStream);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/delete/{applyId}")
    public ResultEntity delete(@PathVariable String applyId) {
        int res = workingService.deleteRegister(applyId);
        if (res > 0) {
            return ResultEntity.success("联网申请删除成功");
        }
        return ResultEntity.error("联网申请删除失败");
    }

    @PostMapping("/annex/list")  //附件列表
    public ResultEntity annexList(@RequestBody AnnexEntity entity) {
        return ResultEntity.success(workingService.selectAnnexList(entity));
    }

    @PostMapping("/upload") // 上传及新增附件
    public ResultEntity upload(@RequestBody UploadModel model) {
        model.fileData = model.fileData.substring(model.fileData.indexOf(",") + 1);
        String savePath = attachService.upload(model);
        if (StringUtils.isNotEmpty(savePath)) {
            AnnexEntity entity = new AnnexEntity();
            entity.setFileName(model.fileName);
            entity.setMasterType(model.masterType);
            entity.setSavePath(savePath);
            entity.setApplyId(model.applyId);
            int isAcc = workingService.insertAnnex(entity);
            if (isAcc > 0) {
                workingService.updateConnectapplyById(model.applyId);
                return ResultEntity.success("附件上传成功");
            } else return ResultEntity.error("新增失败");
        } else return ResultEntity.error("上传失败");
    }

    // 删除附件
//    @Log(title = "【删除附件】", businessType = BusinessType.DELETE)
//    @DeleteMapping("/delete/{attachfileids}")
//    public ResultEntity annexDelete(@PathVariable Integer[] attachfileids) {
//        int isAcc = workingService.deleteAnnex(attachfileids);
//        return isAcc > 0 ? ResultEntity.success("成功删除" + isAcc + "条") : ResultEntity.error("删除失败");
//    }

    /**
     * 分局审批  /dgom/networking/substation_approval 1 同意变成2，拒绝变成3
     *
     * @return
     */
    @PostMapping("/substation_approval")
    public ResultEntity substationApproval(@RequestBody ApprovalModel model) {
        try {
            workingService.substationApproval(model);
            return ResultEntity.success("分局审批成功");
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 市局审批  /dgom/networking/municipal_approval 2拒绝变成4 同意变成5
     *
     * @return
     */
    @PostMapping("/municipal_approval")
    public ResultEntity municipalApproval(@RequestBody MuApprovalModel model) {
        int result = 0;
        try {
            result = workingService.municipalApproval(model);
            return ResultEntity.success("市局审批成功");
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
    }

    /**
     * 值守  /dgom/networking/municipal_duty 只有4的时候才能值守，变成6
     *
     * @return
     */
    @PostMapping("/municipal_duty")
    public ResultEntity dutyApproval(@RequestBody DutyApprovalModel model) {
        int result = 0;
        try {
            result = workingService.dutyApproval(model);
            return ResultEntity.success("值守审核成功");
        } catch (SQLException e) {
            return ResultEntity.error(e.getMessage());
        }
    }


}
