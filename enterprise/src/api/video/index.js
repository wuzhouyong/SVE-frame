import request from "@/utils/request";

//视频列表
export function list(prefix, query, page) {
    return request({
        url: `/${prefix}/video/list?${page}`,
        method: "post",
        data: query
    });
}

//视频新增
export function add(prefix, query) {
    return request({
        url: `/${prefix}/video/add`,
        method: "post",
        data: query
    });
}

//视频编辑
export function edit(prefix, query) {
    return request({
        url: `/${prefix}/video/edit`,
        method: "post",
        data: query
    });
}

//视频删除
export function remove(prefix, id) {
    return request({
        url: `/${prefix}/video/${id}`,
        method: "delete"
    });
}

export function getToken(prefix, id) {
    return request({
        url: `/${prefix}/video/get_token?id=${id}`,
        method: "get"
    });
}

export function getVideoWS(prefix, id) {
    return request({
        url: `/${prefix}/video/get_ws_address?id=${id}`,
        method: "get"
    });
}