import request from "@/utils/request";

//列表
export function dgomAttachList (ids, query) {
  return request({
    url: `/dgom/attach/list/${ids}`,
    method: "get",
    params: query
  });
}
//上传
export function dgomAttachUpload (refIds, query) {
  return request({
    url: `/dgom/attach/upload/${refIds}`,
    method: "post",
    data: query
  });
}

//删除
export function dgomAttachRemove (fileIds) {
  return request({
    url: `/dgom/attach/remove/${fileIds}`,
    method: "delete",
  });
}
