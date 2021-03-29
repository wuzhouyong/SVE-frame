package com.ruoyi.project.common;

import com.ruoyi.common.utils.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.security.service.TokenService;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.common.domain.PfBasIndustry;
import com.ruoyi.project.common.domain.PfFileAttachment;
import com.ruoyi.project.common.domain.UploadFromFile;
import com.ruoyi.project.system.domain.SysUser;
import com.ruoyi.project.system.service.IComGetService;
import com.ruoyi.project.system.service.ISysDictDataService;
import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * 通用数据get
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/com")
public class ComgetController {

    @Autowired
    private ISysDictDataService dictDataService;

    @Autowired
    private IComGetService comGetService;

    @Autowired
    private TokenService tokenService;


    /**
     * 根据字典类型查询字典数据信息
     */
    @GetMapping(value = "/{dictType}")
    public AjaxResult dictType(@PathVariable String dictType) {
        return AjaxResult.success(dictDataService.selectDictDataByTypeLim(dictType));
    }

    @GetMapping(value = "/region/{code}")
    public AjaxResult region(@PathVariable String code) {
        return AjaxResult.success(HashMapUtils.toCamel(comGetService.selectRegionByCode(code)));
    }

    @GetMapping(value = "/dgregion")
    public AjaxResult dgregion() {
        SysUser user = tokenService.getLoginUser(ServletUtils.getRequest()).getUser();
        String[] regs = user.getRegions().split(",");

        boolean isAll = false;
        for (String reg : regs) {
            if (reg.equals("'4419'"))
                isAll = true;
        }
        if (user.isAdmin() || isAll)
            return AjaxResult.success(HashMapUtils.toCamel(comGetService.selectRegionByCode("4419")));
        else
            return AjaxResult.success(HashMapUtils.toCamel(comGetService.selectRegionsByIds(user.getRegions())));
    }


    @GetMapping("/industryTree")
    public AjaxResult getIndustryTree() {
        List<PfBasIndustry> ind = comGetService.selectAllIndustry();
        return AjaxResult.success(comGetService.buildDeptTreeSelect(ind));
    }

    //@PreAuthorize("@ss.hasPermi('upload:file:list')")
    @Transactional
    @Log(title = "附件上传", businessType = BusinessType.UPDATE)
    @PostMapping("/file/upload")
    public AjaxResult uploadFiles(@RequestBody UploadFromFile file) {
        if (file.getFile().equals("data:")) {
            return AjaxResult.error("不允许上传空白文件");
        }
        String fileBase = file.getFile().substring(file.getFile().indexOf("base64,") + 7);
        FtpUtils ftp = new FtpUtils();
        if (StringUtils.isNotEmpty(fileBase) && ftp.checkSuffix(file.getFileName())) {
            String patch = "/" + file.getRefType().toLowerCase() + "/" + file.getAttachType().toLowerCase() + "/" + DateStringUtils.dateToString("YYYY-MM", (new Date()));
            String filePatch = ftp.uploadFile(fileBase, patch, DateStringUtils.dateToString("YYYYMMdd-HHmmssSSS", (new Date())) + "_" + file.getFileName());
            if (StringUtils.isNotEmpty(filePatch)) {
                String[] recordIds = null;
                int res = 0;
                if (file.getRefId().contains(","))
                    recordIds = file.getRefId().split(",");
                else
                    recordIds = new String[]{file.getRefId()};
                for (String id : recordIds) {
                    PfFileAttachment fileAtt = new PfFileAttachment();
                    fileAtt.setAttachmentId(UUID.randomUUID().toString());
                    fileAtt.setBizTableName(file.getRefType());
                    fileAtt.setBizRecordId(id);
                    fileAtt.setAttachmentType(file.getAttachType());
                    fileAtt.setShowName(file.getFileName());
                    fileAtt.setSaveName(filePatch);
                    fileAtt.setCreatedBy(tokenService.getLoginUser(ServletUtils.getRequest()).getUsername());
                    res = comGetService.insertPfFileAttachment(fileAtt);
                }

                return res > 0 ? AjaxResult.success("文件上传成功") : AjaxResult.error("文件上传记录失败");
            } else
                return AjaxResult.error("文件上传失败");
        }
        return AjaxResult.error("无效文件");

    }

    @PostMapping("/file/list")
    public AjaxResult uploadFilesList(@RequestBody UploadFromFile file) {
        PfFileAttachment fileAtt = new PfFileAttachment();
        fileAtt.setBizTableName(file.getRefType());
        fileAtt.setBizRecordId(file.getRefId());
        return AjaxResult.success(comGetService.selectHashFileAttachmentList(fileAtt));
    }

    @GetMapping("/delfile/{fileId}")
    public AjaxResult delAttFile(@PathVariable String fileId) {
        PfFileAttachment fileAtt = new PfFileAttachment();
        fileAtt.setAttachmentId(fileId);
        fileAtt.setUpdatedBy(tokenService.getLoginUser(ServletUtils.getRequest()).getUsername());
        return AjaxResult.success(comGetService.updatePfFileAttachment(fileAtt));
    }

    @GetMapping(value = "/getfile/{fileId}")
    public void getFile(@PathVariable String fileId, HttpServletResponse response) throws Exception {
        PfFileAttachment fileAtt = comGetService.selectPfFileAttachmentById(fileId);
        if (fileAtt != null) {
            FtpUtils ftp = new FtpUtils();
            ftp.downloadFile(fileAtt.getShowName(), fileAtt.getSaveName(), response);
//            InputStream fileStream = ftp.downloadFile(fileAtt.getSaveName());
//
//            response.setCharacterEncoding("utf-8");
//            response.setContentType("multipart/form-data");
//            response.setHeader("Content-Disposition",
//                    "attachment;fileName=" + URLEncoder.encode(fileAtt.getShowName(), "UTF-8"));
//
//            OutputStream out = null;
//            byte buf[] = new byte[1024];
//            int len = 0;
//            out = response.getOutputStream();
//            while ((len = fileStream.read(buf)) > 0) {
//                out.write(buf, 0, len);
//            }
//            fileStream.close();
//            out.close();
        }
    }

    @GetMapping(value = "/getfiles/{fileId}")
    public void getFiles(@PathVariable String fileId, HttpServletResponse response) throws Exception {
        String[] fileIds = fileId.split(",");
        List<PfFileAttachment> attaches = comGetService.selectPfFileAttachmentByIds(fileIds);
        if (attaches.size() > 0) {
            FtpUtils ftp = new FtpUtils();
            String outputFileName = "文件" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".zip";
            // 设置response参数
            response.reset();
            response.setContentType("content-type:application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((outputFileName).getBytes(), "iso-8859-1"));

            ServletOutputStream out = response.getOutputStream();
            ZipArchiveOutputStream zous = new ZipArchiveOutputStream(out);
            zous.setUseZip64(Zip64Mode.AsNeeded);
            for (PfFileAttachment attach : attaches) {
                byte[] bytes = ftp.downloadFile(attach.getSaveName());

                //设置文件名
                ArchiveEntry entry = new ZipArchiveEntry(attach.getShowName());
                zous.putArchiveEntry(entry);
                zous.write(bytes);
                zous.closeArchiveEntry();
            }
            zous.close();
        }
    }

    @GetMapping(value = "/ref_files") //批量下载
    public void getFilesByRef(String refType, String refId, HttpServletResponse response) throws Exception {
        String[] refIds = refId.split(",");
        List<PfFileAttachment> attaches = comGetService.selectPfFileAttachmentsByRef(refType, refIds);
        if (attaches.size() > 0) {
            FtpUtils ftp = new FtpUtils();
            String outputFileName = "文件" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + ".zip";
            // 设置response参数
            response.reset();
            response.setContentType("content-type:application/octet-stream;charset=UTF-8");
            response.setHeader("Content-Disposition", "attachment;filename=" + new String((outputFileName).getBytes(), StandardCharsets.ISO_8859_1));

            ServletOutputStream out = response.getOutputStream();
            ZipArchiveOutputStream zip = new ZipArchiveOutputStream(out);
            zip.setUseZip64(Zip64Mode.AsNeeded);
            zip.setEncoding("utf-8");
            for (PfFileAttachment attach : attaches) {
                byte[] bytes = ftp.downloadFile(attach.getSaveName());
                //设置文件名
                ArchiveEntry entry = new ZipArchiveEntry(attach.getShowName());
                zip.putArchiveEntry(entry);
                zip.write(bytes);
                zip.closeArchiveEntry();
            }
            zip.close();
        }
    }

}
