package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.FtpUtils;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.project.dgom.domain.AnnexEntity;
import com.ruoyi.project.dgom.domain.LabelEntity;
import com.ruoyi.project.dgom.mapper.DataSearchMapper;
import com.ruoyi.project.dgom.service.AttachService;
import com.ruoyi.project.dgom.service.OMUtilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dgom/util")
public class OMUtilController {
    @Autowired
    private OMUtilService service;

    @Autowired
    private AttachService attach;

    @Autowired
    private DataSearchMapper dataSearchMapper;


    @GetMapping("/towns")
    public ResultEntity towns() {
        List<LabelEntity> list = service.getLabelsByParentId(100);
        return ResultEntity.success(list);
    }

    @GetMapping("/industrys")
    public ResultEntity industrys() {
        List<LabelEntity> list = service.getLabelsByParentId(200);
        return ResultEntity.success(list);
    }

    //  通用接口 根据排放口获取设备列表  /dgom/util/datadev/{checkpointid}
    @GetMapping("/datadev/{checkpointid}")
    public ResultEntity datadevList(@PathVariable String checkpointid) {
        List<Map> maps = service.datadevList(checkpointid);
        return ResultEntity.success(HashMapUtils.toLower(maps));
    }

    // 通用接口 根据企业获取排放口列表 /dgom/util/checkpoint
    @GetMapping("/checkpoint")
    public ResultEntity checkpointidList() {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId == null) return ResultEntity.error("企业不能为空");
        String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
        List<Map> maps = service.checkpointidList(bizSourceId);
        return ResultEntity.success(HashMapUtils.toLower(maps));
    }

    //  /dgom/util/annex
    @PostMapping("/annex")  //附件列表 通用接口 参数{masterType,applyId}必填
    public ResultEntity annexList(@RequestBody AnnexEntity entity) {
        if (entity.getMasterType() == null) return ResultEntity.error("附件类型不能为空");
        entity.setMasterTypes(entity.getMasterType().split(","));
        return ResultEntity.success(service.selectAnnexList(entity));
    }

    // 删除附件 通用接口 /dgom/util/delete/{attachfileids}
    @DeleteMapping("/delete/{attachfileids}")
    public ResultEntity annexDelete(@PathVariable String[] attachfileids) {
        if (attachfileids == null || attachfileids.length == 0) return ResultEntity.error("附件ID不能为空");
        String updatedby = SecurityUtils.getUsername();
        int isAcc = service.deleteAnnex(attachfileids, updatedby);
        return isAcc > 0 ? ResultEntity.success("成功删除" + isAcc + "条") : ResultEntity.error("删除失败");
    }

    //附件下载 通用接口  /dgom/util/getfile/{attachfileid} //下载的方法需要返回byte[]
    @GetMapping(value = "/getfile/{attachfileid}")
    public byte[] getFile(@PathVariable String attachfileid) {
        AnnexEntity fileAtt = service.selectAnnexFileById(attachfileid);
        if (fileAtt == null) return null;
        return attach.download(fileAtt);
    }

}
