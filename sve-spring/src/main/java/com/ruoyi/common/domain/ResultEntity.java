package com.ruoyi.common.domain;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.sql.SqlUtil;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableSupport;

import java.io.Serializable;
import java.util.List;

public class ResultEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private int code;

    private String message;

    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static ResultEntity success(String message) {
        return success(null, message);
    }

    public static ResultEntity success(Object data,String message) {
        ResultEntity entity = new ResultEntity();
        entity.code = 200;
        entity.message = message;
        entity.data = data;
        return entity;
    }

    public static ResultEntity success(Object data) {
        ResultEntity entity = new ResultEntity();
        entity.code = 200;
        entity.data = data;
        return entity;
    }

    public static ResultEntity error(String message) {
        ResultEntity entity = new ResultEntity();
        entity.code = 500;
        entity.message = message;
        return entity;
    }

    public static ResultEntity toAjax(int rows) {
        return rows > 0 ? ResultEntity.success("操作成功") : ResultEntity.error("操作失败");
    }

    /**
     * 响应请求分页数据
     */
    public static ResultEntity getDataTable(List<?> list) {
        ResultEntity rspData = new ResultEntity();
        rspData.setCode(200);
        rspData.setData(new ResultEntityData(0,list,(int)new PageInfo(list).getTotal()));
        rspData.setMessage("");
        return rspData;
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }

    protected ResultEntity getDataList(List<?> list, int page, int total){

        ResultEntity resultEntity1 = new ResultEntity();
        if (list != null){
            resultEntity1.setCode(200);
            resultEntity1.setMessage(null);
            ResultEntityData data = new ResultEntityData();
            data.setPageList(list);
            data.setPage(page);
            data.setTotal(total);
            resultEntity1.setData(data);
        }
        return resultEntity1;
    }

    @Override
    public String toString() {
        return "ResultEntity{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
