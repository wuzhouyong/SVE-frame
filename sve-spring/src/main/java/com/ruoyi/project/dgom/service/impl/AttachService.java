package com.ruoyi.project.dgom.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.domain.ResultEntity;
import com.ruoyi.common.utils.HashMapUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.dgom.domain.attach.InsertAttachModel;
import com.ruoyi.project.dgom.domain.attach.UploadFileModel;
import com.ruoyi.project.dgom.mapper.AttachMapper;
import com.ruoyi.project.dgom.service.IAttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@DataSource(value = DataSourceType.HBONLINE)
public class AttachService implements IAttachService {
    @Autowired
    private AttachMapper mapper;

    @Override
    public List<Map> getAttachList(String[] refIds, String refType) {
        return HashMapUtils.toLower(mapper.getAttachList(refIds, refType));
    }

    @Override
    public List<Map> getAttachListByIds(String[] attachIds) {
        return HashMapUtils.toLower(mapper.getAttachListByIds(attachIds));
    }

    @Override
    @Transactional
    public int addAttaches(String[] refIds, String savePath, UploadFileModel model) {

        InsertAttachModel attachModel = new InsertAttachModel();
        attachModel.setRefType(model.getRefType());
        attachModel.setFileName(model.getFileName());
        attachModel.setAttachType(model.getAttachType());
        attachModel.setSavePath(savePath);
        for (String refId : refIds) {
            attachModel.setRefId(refId);
            int res = insertAttach(attachModel);
            if (res < 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
        }
        return refIds.length;
    }

    @Override
    public int insertAttach(InsertAttachModel model) {
        model.setAttachFileId(UUID.randomUUID().toString().replace("-", ""));
        return mapper.insertAttach(model);
    }

    @Override
    @Transactional
    public int removeAttaches(String[] fileIds) {
        for (String fileId : fileIds) {
            int res = removeAttach(fileId);
            if (res < 0) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
                return -1;
            }
        }
        return fileIds.length;
    }

    @Override
    public int removeAttach(String attachFileId) {
        return mapper.removeAttach(attachFileId, SecurityUtils.getUsername());
    }

    @Override
    public String getAccessToken() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "client_credentials");
        params.add("client_id", "0b205516-5eb1-466d-8fb3-e9312c1f3131");
        params.add("client_secret", "PSakhYi/+HujP3YOd4eyVNqMNn9yy5llPI+0y1qghMc=");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        // 请求 http://dgepb.dg.gov.cn/dgom/token 获取token form参数
        ResponseEntity<String> response = restTemplate.postForEntity("http://dgepb.dg.gov.cn/dgom/token", requestEntity, String.class);
        //将json格式的字符串转换为JSON对象
        JSONObject jsonObject = JSONObject.parseObject(response.getBody());
        // 返回 access_token 属性
        return jsonObject.getString("access_token");
    }

    @Override
    public String upload(UploadFileModel model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getAccessToken());
        JSONObject postData = new JSONObject();
        postData.put("path", model.getRefType());
        postData.put("name", model.getFileName());
        postData.put("file", model.getFileData());
        try {
            HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(postData, headers);
            ResponseEntity<String> response = restTemplate.postForEntity("http://dgepb.dg.gov.cn/dgom/UtilsApi/UploadFile", httpEntity, String.class);
            JSONObject jsonObject = JSONObject.parseObject(response.getBody());
            return jsonObject.getString("savePath");
        }
        catch (Exception ex) {
            String msg = ex.getMessage();
        }
        return null;
    }

    @Override
    public byte[] download(String path, String name) {  //下载的方法需要返回byte[]
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getAccessToken());
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> response = restTemplate.exchange(
                "http://dgepb.dg.gov.cn/dgom/UtilsApi/DownloadFile?path="+path+"&name="+name,
                HttpMethod.GET, httpEntity, byte[].class);
        // 下载地址 http://dgepb.dg.gov.cn/dgom/UtilsApi/DownloadFile?path=model.savePath&name=model.fileName
        // 返回类型为byte[]
        return response.getBody();
    }

}
