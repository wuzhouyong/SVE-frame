package com.ruoyi.project.enterprise.domain;

import java.io.Serializable;

public class SourceVocsParamEntity implements Serializable {

    private String sourceId;
    private String invsertTotal;//治理投资 单位（万元）(6)
    private String constructionUnit;//环保施工单位 (128)
    private String productFlow;//生产工艺(128)
    private String gasCollect;//废气收集情况(128)
    private String processFlow;//治理工艺(128)
    private String processFlowMemo;//废气治理工艺说明(256)
    private String keyPump;//水泵功率和流量（注明台数、使用工艺阶段及各自功率）(128)
    private String keyBlower;//风机功率和风量（注明台数、使用工艺阶段及各自功率）(128)
    private String keySorbMaterial;//吸附材料材质及使用量（注明吸附塔数量及各自使用量）(128)
    private String keySorbPeriod;//吸附材料更换周期（注明吸附塔数量及各自周期）(128)
    private String keyPhotoPower;//光催化处理设备功率（注明台数及各自功率）(128)
    private String keyPlasmaPower;//等离子处理设备功率（注明台数及各自功率）(128)
    private String keyRecoveryRate;//脱附回收效率（仅限于回收法）(128)
    private String keyBurnTemp;//一般燃烧温度（包括但不限于RCO/RTO等类似工艺）(128)
    private String keyRecoveryTime;//吸附时间（包括但不限于RCO/RTO等类似工艺）(128)
    private String keyLossPeriod;//催化剂失效周期（采用含催化剂的工艺）(128)
    private String keyBurnerCount;//燃烧器装机量(128)
    private String keyHeatTime;//加热时间（包括但不限于RCO/RTO等类似工艺）(128)
    private String keyInterTemp;//换热器温度（如有）(128)
    private String keyOutgasTemp;//排气温度(128)
    private String keyTowerTime;//生物滤池（或滴滤塔）有效体积及停留时间(128)
    private String keyMudPeriod;//生物污泥处置周期(128)
    private String keyLiquidPeriod;//吸收液添加（更换）周期(128)
    private String keyLiquidAmount;//吸收液添加（更换）用量(128)
    private String keyCyclePeriod;//旋转周期（仅限于旋转式蓄热燃烧工艺）(128)
    private String keyArea;//设备总体占地面积(128)
    private String keyWorkTime;//一般企业开工时间(128)
    private String memo;//其他说明 （企业认为必须补充的说明或解释，若不需补充则填写“无”）(256)
    private String dateCreated;
    private String createdBy;
    private String dateUpdated;
    private String updatedBy;
    private String shortName;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getInvsertTotal() {
        return invsertTotal;
    }

    public void setInvsertTotal(String invsertTotal) {
        this.invsertTotal = invsertTotal;
    }

    public String getConstructionUnit() {
        return constructionUnit;
    }

    public void setConstructionUnit(String constructionUnit) {
        this.constructionUnit = constructionUnit;
    }

    public String getProductFlow() {
        return productFlow;
    }

    public void setProductFlow(String productFlow) {
        this.productFlow = productFlow;
    }

    public String getGasCollect() {
        return gasCollect;
    }

    public void setGasCollect(String gasCollect) {
        this.gasCollect = gasCollect;
    }

    public String getProcessFlow() {
        return processFlow;
    }

    public void setProcessFlow(String processFlow) {
        this.processFlow = processFlow;
    }

    public String getProcessFlowMemo() {
        return processFlowMemo;
    }

    public void setProcessFlowMemo(String processFlowMemo) {
        this.processFlowMemo = processFlowMemo;
    }

    public String getKeyPump() {
        return keyPump;
    }

    public void setKeyPump(String keyPump) {
        this.keyPump = keyPump;
    }

    public String getKeyBlower() {
        return keyBlower;
    }

    public void setKeyBlower(String keyBlower) {
        this.keyBlower = keyBlower;
    }

    public String getKeySorbMaterial() {
        return keySorbMaterial;
    }

    public void setKeySorbMaterial(String keySorbMaterial) {
        this.keySorbMaterial = keySorbMaterial;
    }

    public String getKeySorbPeriod() {
        return keySorbPeriod;
    }

    public void setKeySorbPeriod(String keySorbPeriod) {
        this.keySorbPeriod = keySorbPeriod;
    }

    public String getKeyPhotoPower() {
        return keyPhotoPower;
    }

    public void setKeyPhotoPower(String keyPhotoPower) {
        this.keyPhotoPower = keyPhotoPower;
    }

    public String getKeyPlasmaPower() {
        return keyPlasmaPower;
    }

    public void setKeyPlasmaPower(String keyPlasmaPower) {
        this.keyPlasmaPower = keyPlasmaPower;
    }

    public String getKeyRecoveryRate() {
        return keyRecoveryRate;
    }

    public void setKeyRecoveryRate(String keyRecoveryRate) {
        this.keyRecoveryRate = keyRecoveryRate;
    }

    public String getKeyBurnTemp() {
        return keyBurnTemp;
    }

    public void setKeyBurnTemp(String keyBurnTemp) {
        this.keyBurnTemp = keyBurnTemp;
    }

    public String getKeyRecoveryTime() {
        return keyRecoveryTime;
    }

    public void setKeyRecoveryTime(String keyRecoveryTime) {
        this.keyRecoveryTime = keyRecoveryTime;
    }

    public String getKeyLossPeriod() {
        return keyLossPeriod;
    }

    public void setKeyLossPeriod(String keyLossPeriod) {
        this.keyLossPeriod = keyLossPeriod;
    }

    public String getKeyBurnerCount() {
        return keyBurnerCount;
    }

    public void setKeyBurnerCount(String keyBurnerCount) {
        this.keyBurnerCount = keyBurnerCount;
    }

    public String getKeyHeatTime() {
        return keyHeatTime;
    }

    public void setKeyHeatTime(String keyHeatTime) {
        this.keyHeatTime = keyHeatTime;
    }

    public String getKeyInterTemp() {
        return keyInterTemp;
    }

    public void setKeyInterTemp(String keyInterTemp) {
        this.keyInterTemp = keyInterTemp;
    }

    public String getKeyOutgasTemp() {
        return keyOutgasTemp;
    }

    public void setKeyOutgasTemp(String keyOutgasTemp) {
        this.keyOutgasTemp = keyOutgasTemp;
    }

    public String getKeyTowerTime() {
        return keyTowerTime;
    }

    public void setKeyTowerTime(String keyTowerTime) {
        this.keyTowerTime = keyTowerTime;
    }

    public String getKeyMudPeriod() {
        return keyMudPeriod;
    }

    public void setKeyMudPeriod(String keyMudPeriod) {
        this.keyMudPeriod = keyMudPeriod;
    }

    public String getKeyLiquidPeriod() {
        return keyLiquidPeriod;
    }

    public void setKeyLiquidPeriod(String keyLiquidPeriod) {
        this.keyLiquidPeriod = keyLiquidPeriod;
    }

    public String getKeyLiquidAmount() {
        return keyLiquidAmount;
    }

    public void setKeyLiquidAmount(String keyLiquidAmount) {
        this.keyLiquidAmount = keyLiquidAmount;
    }

    public String getKeyCyclePeriod() {
        return keyCyclePeriod;
    }

    public void setKeyCyclePeriod(String keyCyclePeriod) {
        this.keyCyclePeriod = keyCyclePeriod;
    }

    public String getKeyArea() {
        return keyArea;
    }

    public void setKeyArea(String keyArea) {
        this.keyArea = keyArea;
    }

    public String getKeyWorkTime() {
        return keyWorkTime;
    }

    public void setKeyWorkTime(String keyWorkTime) {
        this.keyWorkTime = keyWorkTime;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }
}
