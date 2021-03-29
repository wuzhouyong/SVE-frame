package com.ruoyi.common.utils;

import java.util.ArrayList;
import java.util.List;

public class LabelQueryUtils {
    public static String getQuery(List<List<Integer>> labelIds, String alias) {
        if (labelIds != null) {
            List<String> querys = new ArrayList<>();
            for (List<Integer> list : labelIds) {
                if (list.size() > 0) {
                    querys.add(StringUtils.format(
                        "exists (select source_id from basecenter.pf_bas_label_detail where is_deleted = 0 and label_head_id in ({}) and source_id = {}.source_id)",
                        StringUtils.join(list, ","), alias));
                }
            }
            if (querys.size() > 0) {
                return StringUtils.format(
                    " and ({})",
                    StringUtils.join(querys, " and ")
                );
            }
        }
        return null;
    }
}
