package com.ruoyi.project.dgom.controller;

import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.dgom.domain.attach.InsertAttachModel;
import com.ruoyi.project.dgom.domain.attach.UploadFileModel;
import com.ruoyi.project.dgom.service.IAttachService;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dgom/attach")
public class AttachController {
    @Autowired
    private IAttachService service;

    @GetMapping("/list/{refIds}")
    public ResultEntity list(@PathVariable String[] refIds, String refType) {
        if (refIds.length == 0) {
            return ResultEntity.error("参数 refId 不能为空");
        }
        if (StringUtils.isEmpty(refType)) {
            return ResultEntity.error("参数 refType 不能为空");
        }
        List<Map> list = service.getAttachList(refIds, refType);
        return ResultEntity.success(list);
    }

    @PostMapping("/upload/{refIds}")
    public ResultEntity upload(@PathVariable String[] refIds, @RequestBody UploadFileModel model) {
        model.setFileData(model.getFileData().substring(model.getFileData().indexOf(",") + 1));
        String savePath = service.upload(model);
        if (StringUtils.isNotEmpty(savePath)) {
            int res = service.addAttaches(refIds, savePath, model);
            if (res > 0) {
                return ResultEntity.success("附件上传成功");
            }
        }
        return ResultEntity.error("附件上传失败");
    }

    @DeleteMapping("/remove/{fileIds}")
    public ResultEntity remove(@PathVariable String[] fileIds) {
        if (fileIds.length == 0) {
            return ResultEntity.error("请选择要删除的附件");
        }
        int res = service.removeAttaches(fileIds);
        if (res > 0) {
            return ResultEntity.success("附件删除成功");
        }
        return ResultEntity.error("附件删除失败");
    }

    @GetMapping("/download/{fileIds}")
    public void download(@PathVariable String[] fileIds, HttpServletResponse response) throws Exception {
        if (fileIds.length == 0) {
            throw new Exception("请选择要下载的附件");
        }
        List<Map> list = service.getAttachListByIds(fileIds);
        if (list.size() == 1) {
            Map map = list.get(0);
            String path = (String) map.get("save_path");
            String name = (String) map.get("file_name");
            byte[] bytes = service.download(path, name);
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("multipart/form-data");
            response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(name, "UTF-8"));
            OutputStream os = response.getOutputStream();
            os.write(bytes);
            os.flush();
        } else {
            String outputFileName = "文件" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".zip";
            response.reset();
            response.setContentType("content-type:application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((outputFileName).getBytes(), StandardCharsets.ISO_8859_1));
            OutputStream os = response.getOutputStream();
            ZipArchiveOutputStream zip = new ZipArchiveOutputStream(os);
            zip.setUseZip64(Zip64Mode.AsNeeded);
            for (Map map : list) {
                String path = (String) map.get("save_path");
                String name = (String) map.get("file_name");
                byte[] bytes = service.download(path, name);
                //设置文件名
                ArchiveEntry entry = new ZipArchiveEntry(name);
                zip.putArchiveEntry(entry);
                zip.write(bytes);
                zip.closeArchiveEntry();
            }
            zip.close();
        }
    }
}
