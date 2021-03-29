package com.ruoyi.project.dgom.service;

import com.ruoyi.project.dgom.domain.attach.InsertAttachModel;
import com.ruoyi.project.dgom.domain.attach.UploadFileModel;

import java.util.List;
import java.util.Map;

public interface IAttachService {
    String getAccessToken();
    String upload(UploadFileModel model);
    byte[] download(String path, String name);

    List<Map> getAttachList(String[] refIds, String refType);
    List<Map> getAttachListByIds(String[] attachIds);
    int addAttaches(String[] refIds, String savePath, UploadFileModel model);
    int insertAttach(InsertAttachModel model);
    int removeAttaches(String[] fileIds);
    int removeAttach(String attachFileId);
}
