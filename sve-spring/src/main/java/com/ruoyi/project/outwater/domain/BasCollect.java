package com.ruoyi.project.outwater.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.aspectj.lang.annotation.Excel.Type;

import java.io.Serializable;
import java.util.Date;

public class BasCollect implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    @Excel(name = "数采ID")
    private String Collect_Id;

    @Excel(name = "污染源编号")
    private String Source_Id;

    @Excel(name = "排放口名称")
    private String Collect_Name;

    @Excel(name = "MN码")
    private String MN;

    @Excel(name = "IP地址")
    private String IpAddress;

    @Excel(name = "是否在线", readConverterExp = "0=否,1=是")
    private int Is_Online;

    @Excel(name = "检测时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date Check_Date;

    @Excel(name = "设备品牌")
    private String Brand_Name;

    @Excel(name = "是否有中环认证")
    private String Has_Env_Cert;

    public void setCollect_Id(String collect_Id) {
        this.Collect_Id = collect_Id;
    }

    public void setSource_Id(String source_Id) {
        this.Source_Id = source_Id;
    }

    public void setCollect_Name(String collect_Name) {
        this.Collect_Name = collect_Name;
    }

    public void setMN(String MN) {
        this.MN = MN;
    }

    public void setIpAddress(String ipAddress) {
        this.IpAddress = ipAddress;
    }

    public void setIs_Online(int is_Online) {
        this.Is_Online = is_Online;
    }

    public void setCheck_Date(Date check_Date) {
        this.Check_Date = check_Date;
    }

    public void setBrand_Name(String brand_Name) {
        this.Brand_Name = brand_Name;
    }

    public void setHas_Env_Cert(String has_Env_Cert) {
        this.Has_Env_Cert = has_Env_Cert;
    }


    public String getCollect_Id() {
        return Collect_Id;
    }

    public String getSource_Id() {
        return Source_Id;
    }

    public String getCollect_Name() {
        return Collect_Name;
    }

    public String getMN() {
        return MN;
    }

    public String getIpAddress() {
        return IpAddress;
    }

    public int getIs_Online() {
        return Is_Online;
    }

    public Date getCheck_Date() {
        return Check_Date;
    }

    public String getBrand_Name() {
        return Brand_Name;
    }

    public String getHas_Env_Cert() {
        return Has_Env_Cert;
    }

}
