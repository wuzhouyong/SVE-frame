package com.ruoyi.project.system.controller;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author solang
 * @date 2020-05-07 20:42
 */
@Data
class Dept {
    private String label;
    private Boolean isDepa;
    private String key;
    private List<Dept> children;
}

public class Test {
    public static void main(String[] args) {

        Dept personA = new Dept();
        personA.setLabel("用户A");
        personA.setKey("personA");
        personA.setIsDepa(false);
        List<Dept> deptAList = new ArrayList();
        deptAList.add(personA);
        Dept deptA = new Dept();
        deptA.setIsDepa(true);
        deptA.setKey("DepaA");
        deptA.setLabel("部门A");
        deptA.setChildren(deptAList);

        Dept personB = new Dept();
        personB.setLabel("用户B");
        personB.setKey("personB");
        personB.setIsDepa(false);
        List<Dept> deptBList = new ArrayList();
        deptBList.add(personB);
        Dept deptB = new Dept();
        deptB.setIsDepa(true);
        deptB.setKey("DepaA");
        deptB.setLabel("部门A");
        deptB.setChildren(deptBList);

        List<Dept> depts = new ArrayList<>();
        depts.add(deptA);
        depts.add(deptB);
        Dept deptBigA = new Dept();
        deptBigA.setIsDepa(true);
        deptBigA.setKey("bigA");
        deptBigA.setLabel("最外层部门A");
        deptBigA.setChildren(depts);
        System.out.println(JSON.toJSONString(deptBigA));

    }
}