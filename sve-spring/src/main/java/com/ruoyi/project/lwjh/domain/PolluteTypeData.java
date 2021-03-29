package com.ruoyi.project.lwjh.domain;

import com.ruoyi.project.common.poi.TreeNode;

public class PolluteTypeData extends TreeNode<PolluteTypeData> {

    private String water;

    private String air;

    private String soil;

    private String voice;

    private String other;

    public String getWater() {
        return water;
    }

    public void setWater(String water) {
        this.water = water;
    }

    public String getAir() {
        return air;
    }

    public void setAir(String air) {
        this.air = air;
    }

    public String getSoil() {
        return soil;
    }

    public void setSoil(String soil) {
        this.soil = soil;
    }

    public String getVoice() {
        return voice;
    }

    public void setVoice(String voice) {
        this.voice = voice;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
