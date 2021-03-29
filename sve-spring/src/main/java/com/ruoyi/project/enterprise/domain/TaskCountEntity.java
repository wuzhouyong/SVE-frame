package com.ruoyi.project.enterprise.domain;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

/**
 * @author solang
 * @date 2020-07-22 10:04
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TaskCountEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long code;

    private String name;

    //未接入
    private Long noconnected;

    //已施工
    private Long constructed;

    //已联网
    private Long completed;

    //已验收
    private Long accepted;

    //任务总数
    private Long count;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getNoconnected() {
        return noconnected;
    }

    public void setNoconnected(Long noconnected) {
        this.noconnected = noconnected;
    }

    public Long getConstructed() {
        return constructed;
    }

    public void setConstructed(Long constructed) {
        this.constructed = constructed;
    }

    public Long getCompleted() {
        return completed;
    }

    public void setCompleted(Long completed) {
        this.completed = completed;
    }

    public Long getAccepted() {
        return accepted;
    }

    public void setAccepted(Long accepted) {
        this.accepted = accepted;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TaskCountEntity{" +
                "code=" + code +
                ", name='" + name + '\'' +
                ", noconnected=" + noconnected +
                ", constructed=" + constructed +
                ", completed=" + completed +
                ", accepted=" + accepted +
                ", count=" + count +
                '}';
    }
}
