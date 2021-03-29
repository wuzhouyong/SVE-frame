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
    method: "post",
    data: query
  });
}


//附件删除
export function delFile(id) {
  return request({
    url: `/com/delfile/${id}`,
    method: "get"
  });
}
