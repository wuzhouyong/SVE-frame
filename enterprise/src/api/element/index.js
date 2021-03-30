import request from "@/utils/request";

//因子列表
export function list(prefix, query, page) {
    return request({
        url: `/${prefix}/cp_element/list?${page}`,
        method: "post",
        data: query
    });
}
//因子列表下拉
export function factorList(prefix,query) {
    return request({
        url: `/${prefix}/cp_element/dropdown`,
        method: "post",
        data: query
    });
}

// 造纸行业和金属行业 是否关联别的数据源以及获取数据源
export function paperEle_ref(cpElemId) {
    return request({
        url: `/zaozhi/cp_element/ele_ref/${cpElemId}`,
        method: "get"
    });
}
// 造纸行业和金属行业 是否关联别的数据源以及获取数据源
export function metalEle_ref(cpElemId) {
    return request({
        url: `/jinshu/cp_element/ele_ref/${cpElemId}`,
        method: "get"
    });
}
// 造纸行业和金属行业 零星废水 是否关联别的数据源以及获取数据源
export function lingxingEle_ref(cpElemId) {
    return request({
        url: `/lingxing/cp_element/ele_ref/${cpElemId}`,
        method: "get"
    });
}

//因子新增
export function add(prefix, query) {
    return request({
        url: `/${prefix}/cp_element/add`,
        method: "post",
        data: query
    });
}

//因子编辑
export function edit(prefix, query) {
    return request({
        url: `/${prefix}/cp_element/edit`,
        method: "post",
        data: query
    });
}

//因子删除
export function remove(prefix, id) {
    return request({
        url: `/${prefix}/cp_element/${id}`,
        method: "delete"
    });
}

//数采下拉
export function dropdown(prefix) {
    return request({
        url: `/${prefix}/checkpoint/0`,
        method: "get"
    });
}

//因子类型
export function elementType(prefix, query) {
    return request({
        url: `/${prefix}/cp_element/ele_type`,
        method: "post",
        data: query || {}
    });
}