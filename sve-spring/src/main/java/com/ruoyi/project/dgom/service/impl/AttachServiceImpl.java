package com.ruoyi.project.dgom.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.project.dgom.domain.AnnexEntity;
import com.ruoyi.project.dgom.domain.UploadModel;
import com.ruoyi.project.dgom.service.AttachService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class AttachServiceImpl implements AttachService {

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
    public String upload(UploadModel model) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getAccessToken());
        JSONObject postData = new JSONObject();
        postData.put("path", model.getMasterType());
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
    public byte[] download(AnnexEntity fileAtt) {  //下载的方法需要返回byte[]
        // 根据 attachId 从附件表 zOnlineAttachFile 获取数据
        //AnnexEntity fileAtt = workingMapper.selectOnlineattachfile(attachId); //参数来源从数据库中找到
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + getAccessToken());
        HttpEntity<Map<String, Object>> httpEntity = new HttpEntity<>(headers);
        ResponseEntity<byte[]> response = restTemplate.exchange("http://dgepb.dg.gov.cn/dgom/UtilsApi/DownloadFile?path="+fileAtt.getSavePath()+"&name="+fileAtt.getFileName(),
                HttpMethod.GET, httpEntity, byte[].class);
        // 下载地址 http://dgepb.dg.gov.cn/dgom/UtilsApi/DownloadFile?path=model.savePath&name=model.fileName
        // 返回类型为byte[]
        return response.getBody();
    }


}
