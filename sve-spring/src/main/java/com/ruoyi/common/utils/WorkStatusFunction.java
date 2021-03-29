package com.ruoyi.common.utils;

import java.util.Date;
import java.util.Map;

@FunctionalInterface
public interface WorkStatusFunction {
    void apply(Date date, Map<String, Object> map);
}
