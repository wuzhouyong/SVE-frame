import request from "@/utils/request";

//视频列表
export function videoList(query, platform, page) {
  return request({
    url: `/${platform}/video/list?${page}`,
    method: "post",
    data: query
  });
}

//视频新增
export function videoAdd(query, platform) {
  return request({
    url: `/${platform}/video/add`,
    method: "post",
    data: query
  });
}

//视频编辑
export function videoEdit(query, platform) {
  return request({
    url: `/${platform}/video/edit`,
    method: "post",
    data: query
  });
}

//视频删除
export function videoDel(id, platform) {
  return request({
    url: `/${platform}/video/${id}`,
    method: "delete"
  });
}

export function getToken(id, platform) {
  return request({
    url: `/${platform}/video/get_token?id=${id}`,
    method: "get"
  });
}

// export function getVideoWS(id, platform) {
//   return request({
//     url: `/${platform}/video/get_ws_address?id=${id}&time`,
//     method: "get"
//   });
// }

export function getVideoWS(platform, id, time) {
  let oUrl = time ? `/${platform}/video/get_ws_address?id=${id}&time=${time}` : `/${platform}/video/get_ws_address?id=${id}`;
  return request({
    url: oUrl,
    method: "get",
  });
}


//企业列表
export function sourceList(query, page, platform) {
  return request({
    url: `/${platform}/source/list?${page}`,
    method: "post",
    data: query
  });
}
