package com.ruoyi.common.domain;

import java.util.List;
import java.util.Map;


public class DataSearchResult extends DataSearchResultT<Map<String, Object>> {
    
    private static final long serialVersionUID = 1L;

    public DataSearchResult() {
        super();
    }

    public DataSearchResult(List<?> cols, List<Map<String, Object>> data) {
        super(cols, data);
    }
}