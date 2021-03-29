package com.ruoyi.project.dgom.service;

import com.ruoyi.project.dgom.domain.AnnexEntity;
import com.ruoyi.project.dgom.domain.UploadModel;

public interface AttachService {
    public String getAccessToken();

    public String upload(UploadModel model);

    public byte[] download(AnnexEntity fileAtt);
}
