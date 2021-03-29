package com.ruoyi.project.guocheng.domain;

import java.io.Serializable;

public class GCCheckPointElementEntity implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private String checkpointName;
    private String elemName;


    public String getCheckpointName() {
        return checkpointName;
    }

    public void setCheckpointName(String checkpointName) {
        this.checkpointName = checkpointName;
    }

    public String getElemName() {
        return elemName;
    }

    public void setElemName(String elemName) {
        this.elemName = elemName;
    }
}
