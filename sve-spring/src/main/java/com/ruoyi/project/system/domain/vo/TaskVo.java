package com.ruoyi.project.system.domain.vo;

import lombok.Data;

/**
 * @author solang
 * @date 2020-05-11 16:49
 */

@Data
public class TaskVo {

    /** 处理类型 1 处理，2 知会 */
    private Integer zproctype;

    /** 处理人类型： 1 用户，2 部门 */
    private Integer zprocusertype;

    /** 主办人（可以是UserId 或 UserGroup） */
   private String zprocuserid;


}
