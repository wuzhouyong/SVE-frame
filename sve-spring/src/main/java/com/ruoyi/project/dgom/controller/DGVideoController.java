package com.ruoyi.project.dgom.controller;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.domain.ResultStr;
import com.ruoyi.common.utils.DateStringUtils;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.dgom.domain.DGVideoEntity;
import com.ruoyi.project.dgom.mapper.DataSearchMapper;
import com.ruoyi.project.dgom.service.DGVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/dgom/video")
public class DGVideoController extends BaseController {

    @Autowired
    private DGVideoService videoService;

    @Autowired
    private DataSearchMapper dataSearchMapper;

    @Resource
    private ThreadPoolTaskExecutor myThreadPoolTaskExecutor;

    @PostMapping("/list")
    public TableDataInfo list(@RequestBody DGVideoEntity entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            if (StringUtils.isEmpty(bizSourceId)) {
                TableDataInfo rspData = new TableDataInfo();
                rspData.setMsg("企业不存在");
                rspData.setCode(404);
                return rspData;
            }
            entity.setSourceId(bizSourceId);
        } else {
            if (StringUtils.isNotEmpty(entity.getSourceId())) {
                String bizSourceId = entity.getSourceId();
                sourceId = dataSearchMapper.checkSourceId(bizSourceId);
                if (StringUtils.isEmpty(sourceId)) {
                    TableDataInfo rspData = new TableDataInfo();
                    rspData.setMsg("企业不存在");
                    rspData.setCode(404);
                    return rspData;
                }
                entity.setSourceId(sourceId);
            }
        }
        startPage();
        List<DGVideoEntity> list = videoService.selectVideo(entity);
        return getDataTable(list);
    }

    @PreAuthorize("@ss.hasPermi('lingxing:video:add')")
    @Log(title = "视频新增", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult add(@RequestBody DGVideoEntity entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            entity.setBizSourceId(bizSourceId);
            entity.setSourceId(sourceId);
        } else {
            if (StringUtils.isNotEmpty(entity.getSourceId())) {
                String bizSourceId = entity.getSourceId();
                sourceId = dataSearchMapper.checkSourceId(bizSourceId);
                entity.setBizSourceId(bizSourceId);
                entity.setSourceId(sourceId);
            }
        }
        entity.setVideoId(java.util.UUID.randomUUID().toString());
        entity.setCreatedBy(SecurityUtils.getUsername());
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(videoService.insertVideo(entity));
    }

    @PreAuthorize("@ss.hasPermi('lingxing:video:edit')")
    @Log(title = "视频编辑", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody DGVideoEntity entity) {
        String sourceId = SecurityUtils.getLoginUser().getUser().getSourceId();
        if (sourceId != null) {
            String bizSourceId = dataSearchMapper.checkBizSourceId(sourceId);
            entity.setBizSourceId(bizSourceId);
            entity.setSourceId(sourceId);
        } else {
            if (StringUtils.isNotEmpty(entity.getSourceId())) {
                String bizSourceId = entity.getSourceId();
                sourceId = dataSearchMapper.checkSourceId(bizSourceId);
                entity.setBizSourceId(bizSourceId);
                entity.setSourceId(sourceId);
            }
        }
        entity.setUpdatedBy(SecurityUtils.getUsername());
        return toAjax(videoService.updateVideo(entity));
    }

    @PreAuthorize("@ss.hasPermi('lingxing:video:del')")
    @Log(title = "视频删除", businessType = BusinessType.UPDATE)
    @DeleteMapping("/{videoIds}")
    public AjaxResult delete(@PathVariable String[] videoIds) {
        return toAjax(videoService.deleteVideo(videoIds));
    }

    @GetMapping("/get_ws_address")
    public ResultStr getVideoWS(String id, Date time) {
        DGVideoEntity model = videoService.videoModel(id);
        if (model != null) {
            Long start = 0L;
            Long end = 0L;
            if (time != null) {
                start = time.getTime() / 1000 + 28800;
                end = start + 3600;
            }
            switch (model.getVideoType()) {
                case "twAgent": {
                    String settings = "type=99&channel=0";
                    if (model.getSettings() != null) {
                        settings = "type=" + String.join("&channel=", model.getSettings().split(":"));
                    }
                    String ws = "ws://192.168.10.209:888/byip?ip=" + model.getVideoAddress() + "&user="
                            + (StringUtils.isEmpty(model.getAppkey()) ? "admin" : model.getAppkey()) + "&pass="
                            + (StringUtils.isEmpty(model.getAppsecret()) ? "abc123..." : model.getAppsecret()) + "&"
                            + settings + "&vodstart=" + start + "&vodend=" + end;
                    return new ResultStr(200, ws, null);
                }
                case "omAgent": {
                    String settings = "&channel=0";
                    if (model.getSettings() != null) {
                        settings = "&channel=" + model.getSettings();
                    }
                    String ws = "ws://192.168.10.209:888/tmvideo?ip=" + model.getVideoAddress()
                            + settings + "&vodstart=" + start + "&vodend=" + end;
                    return new ResultStr(200, ws, null);
                }
                default:
                    return new ResultStr(-1, null, "不支持的视频类型");
            }
        }
        return new ResultStr(-1, null, "视频已删除或不存在");
    }

    @GetMapping("/get_token")
    public DeferredResult<ResultStr> getToken(String id) {
        // 设置超时时间
        DGVideoEntity model = videoService.videoModel(id);
        model.setUpdatedBy(SecurityUtils.getUsername());
        DeferredResult<ResultStr> result = new DeferredResult<ResultStr>(60 * 1000L);
        // 处理超时事件 采用委托机制
        result.onTimeout(new Runnable() {

            @Override
            public void run() {
                result.setResult(new ResultStr(-1, "超时", "Token获取失败"));
            }
        });
        result.onCompletion(new Runnable() {

            @Override
            public void run() {
                // 完成后
            }
        });
        myThreadPoolTaskExecutor.execute(new Runnable() {

            @Override
            public void run() {
                String token = null;
                String msg = null;
                int res = 0;
                // 处理业务逻辑
                if (model != null) {
                    try {
                        URL url = new URL("https://open.ys7.com/api/lapp/token/get?appKey=" + model.getAppkey()
                                + "&appSecret=" + model.getAppsecret());
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setConnectTimeout(15000);
                        connection.setReadTimeout(60000);
                        connection.connect();
                        if (connection.getResponseCode() == 200) {
                            InputStream is = connection.getInputStream();
                            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                            StringBuffer sbf = new StringBuffer();
                            String temp = null;
                            while ((temp = br.readLine()) != null) {
                                sbf.append(temp);
                            }
                            String result = sbf.toString();
                            JSONObject json = JSONObject.parseObject(result);
                            msg = json.getString("msg");
                            if (json.getString("code").equals("200")) {
                                JSONObject data = (JSONObject) json.get("data");
                                token = data.getString("accessToken");
                                String expireTime = data.getString("expireTime");
                                // Date sDate = DateStringUtils.stringToDate("yyyy-MM-dd HH:mm:ss", "1970-01-01 08:00:00");
                                // Long startTime = sDate.getTime();
                                model.setExpireTime(DateStringUtils.LongToDate("yyyy-MM-dd HH:mm:ss", expireTime));
                                model.setAccessToken(token);
                                res = videoService.updateToken(model);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                // 返回结果
                if (res == 1)
                    result.setResult(new ResultStr(200, token, null));
                else
                    result.setResult(new ResultStr(-1, msg, "Token获取失败"));
            }
        });
        return result;
    }
}
