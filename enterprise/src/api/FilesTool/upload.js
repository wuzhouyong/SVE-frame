import request from "@/utils/request";

//上传
export function uploadFile(query) {
  return request({
    url: `/com/file/upload`,
    method: "post",
    data: query
  });
}

//附件列表
export function fileList(query) {
  return request({
    url: `/com/file/list`,
    method: "get",
    params: query
  });
}


//附件删除
export function delFile(query) {
  return request({
    url: `/com/file/delfile`,
    method: "get",
    params: query
  });
}


//下载文件
export function getFile(query) {
  return request({
    url: `/com/file/getfile`,
    method: "get",
    params: query
  });
}
