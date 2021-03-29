import request from "@/utils/request";

//日传输率
export function getTransfer(query, platform, page) {
  return request({
    url: `/${platform}/data_search/transfer?${page}`,
    method: "post",
    data: query
  });
}

//月传输率
export function getTransferMonth(query, platform, page) {
  return request({
    url: `/${platform}/data_search/transfer_month?${page}`,
    method: "post",
    data: query
  });
}
