package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.List;

public class BreakUpUtils {

    //拆分多个类型ID的∑
    public static List<String> breakUp(int sum) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (sum > 0) {
            int num = sum & (new Double(Math.pow(2, i))).intValue();
            if (num != 0) {
                sum -= num;
                list.add(num + "");
            }
            i++;
        }
        return list;
    }

    public static List<Integer> breakUpInt(int sum) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (sum > 0) {
            int num = sum & (new Double(Math.pow(2, i))).intValue();
            if (num != 0) {
                sum -= num;
                list.add(num);
            }
            i++;
        }
        return list;
    }

    public static String getPolluteType(String sum) {
        int m = 0;
        if (!StringUtils.isEmpty(sum)) {
            m = Integer.parseInt(sum);
        }
        String polluteType = "";
        List<Integer> polluteTypes = BreakUpUtils.breakUpInt(m);
        for (int i : polluteTypes) {
            switch (i) {
                case 1:
                    polluteType += "水环境重点排污单位,";
                    break;
                case 2:
                    polluteType += "大气环境重点排污单位,";
                    break;
                case 4:
                    polluteType += "土壤环境污染重点监管单位,";
                    break;
                case 8:
                    polluteType += "声环境重点排污单位,";
                    break;
                case 16:
                    polluteType += "其他重点排污单位";
                    break;
                default:
                    break;
            }
        }
        return polluteType;
    }
}
