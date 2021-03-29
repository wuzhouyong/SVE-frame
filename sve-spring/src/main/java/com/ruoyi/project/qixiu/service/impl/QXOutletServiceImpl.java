package com.ruoyi.project.qixiu.service.impl;

import com.ruoyi.framework.aspectj.lang.annotation.DataSource;
import com.ruoyi.framework.aspectj.lang.enums.DataSourceType;
import com.ruoyi.project.common.domain.flowChart;
import com.ruoyi.project.qixiu.domain.QXDeleteEntity;
import com.ruoyi.project.qixiu.domain.QXOutletEntity;
import com.ruoyi.project.qixiu.domain.QXVideoEntity;
import com.ruoyi.project.qixiu.mapper.QXOutletMapper;
import com.ruoyi.project.qixiu.service.QXOutletService;
import com.ruoyi.project.qixiu.service.QXVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataSource(value = DataSourceType.QIXIU)
@Service
public class QXOutletServiceImpl implements QXOutletService {

    @Autowired
    private QXOutletMapper outletMapper;

    @Autowired
    private QXVideoService videoService;

    @Override
    public List<QXOutletEntity> selectOutlet(QXOutletEntity entity) {
        return outletMapper.selectOutlet(entity);
    }

    @Override
    public int insertOutlet(QXOutletEntity entity) {
        return outletMapper.insertOutlet(entity);
    }

    @Override
    public int updateOutlet(QXOutletEntity entity) {
        return outletMapper.updateOutlet(entity);
    }

    @Override
    public int deleteOutlet(QXDeleteEntity entity) {
        return outletMapper.deleteOutlet(entity);
    }

    /**
     * 基于企业排放口生成流程树
     */
    @Override
    public List<flowChart> bulidOutletTreeBySourceId(String sourceID) {
        QXOutletEntity ent = new QXOutletEntity();
        ent.setSourceId(sourceID);
        List<QXOutletEntity> outList = outletMapper.selectOutlet(ent);

        List<flowChart> tree = new ArrayList<>();
        int i = 1;
        for (QXOutletEntity e : outList) {
            flowChart chart = new flowChart();
            String key = e.getOutletId();
            chart.setId(key);
            chart.setName(e.getOutletName());
            chart.setType("qx_yc_" + key);
            tree.add(chart);

            //添加治理设施,一排放口对应一个设施
            List<flowChart> pro = new ArrayList<>();
            flowChart chart2 = new flowChart();
            key = e.getOutletId() + "-1";
            chart2.setId(key);
            chart2.setName("VOC治理设施");
            chart2.setType("qx_jhq_" + key);
            pro.add(chart2);

            chart.setList(pro);

            //获取排放口摄像头
            QXVideoEntity video = new QXVideoEntity();
            video.setSourceId(sourceID);
            video.setOutletId(e.getOutletId());
            List<QXVideoEntity> videos = videoService.selectVideo(video);
            int j = 1;
            List<flowChart> videoChild = new ArrayList<>();
            for (QXVideoEntity v : videos) {
                //添加治理设施
                flowChart child = new flowChart();
                child.setId(key + j);
                child.setName(v.getVideoDesc());
                child.setType("qx_vo_" + key + j);
                videoChild.add(child);
                j++;
            }
            if (!videoChild.isEmpty())
                chart2.setList(videoChild);

            i++;

        }

        return tree;
    }

    /**
     * 排放口获取所有因子与实时值
     *
     * @param outID
     * @return
     */
    public List<Map> selectCheckElemntTagVal(String outID) {
        return outletMapper.selectCheckElemntTagVal(outID);
    }

    /**
     * 根据排放口值设置状态
     *
     * @param tags
     * @return
     */
    public List<Map> getPointRunStatus(List<Map> tags) {
        String outID = "";
        for (Map tag : tags) {
            if (outID.isEmpty())
                outID = tag.get("PID").toString();

            if (tag.get("ELE_TYPE_ID").toString().indexOf("e") == 0)
                tag.put("PID", tag.get("PID") + "-1");
        }

        List<Map> run = new ArrayList<>();
        Map out = new HashMap();
        out.put("id", outID);
        out.put("run", true);
        run.add(out);

        Map plant = new HashMap();
        plant.put("id", outID + "-1");
        plant.put("run", true);
        run.add(plant);

        //添加车间（虚拟）
        for (int i = 1; i <= 4; i++) {
            Map video = new HashMap();
            video.put("id", outID + "-1" + i);
            video.put("run", true);
            run.add(video);
        }

        return run;
    }
}
