/**
 * 通用js方法封装处理
 * Copyright (c) 2019 ruoyi
 */

const baseURL = process.env.VUE_APP_BASE_API;

// 日期格式化
export function parseTime(time, pattern) {
  if (arguments.length === 0 || !time) {
    return null;
  }
  const format = pattern || "{y}-{m}-{d} {h}:{i}:{s}";
  let date;
  if (typeof time === "object") {
    date = time;
  } else {
    if (typeof time === "string" && /^[0-9]+$/.test(time)) {
      time = parseInt(time);
    } else if (typeof time === "string") {
      time = time.replace(new RegExp(/-/gm), "/");
    }
    if (typeof time === "number" && time.toString().length === 10) {
      time = time * 1000;
    }
    date = new Date(time);
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  };
  const time_str = format.replace(/{(y|m|d|h|i|s|a)+}/g, (result, key) => {
    let value = formatObj[key];
    // Note: getDay() returns 0 on Sunday
    if (key === "a") {
      return ["日", "一", "二", "三", "四", "五", "六"][value];
    }
    if (result.length > 0 && value < 10) {
      value = "0" + value;
    }
    return value || 0;
  });
  return time_str;
}

// 表单重置
export function resetForm(refName) {
  if (this.$refs[refName]) {
    this.$refs[refName].resetFields();
  }
}

// 添加日期范围
export function addDateRange(params, dateRange) {
  var search = params;
  search.beginTime = "";
  search.endTime = "";
  if (null != dateRange && "" != dateRange) {
    search.beginTime = this.dateRange[0];
    search.endTime = this.dateRange[1];
  }
  return search;
}

// 回显数据字典
export function selectDictLabel(datas, value) {
  var actions = [];
  Object.keys(datas).map(key => {
    if (datas[key].dictValue == "" + value) {
      actions.push(datas[key].dictLabel);
      return false;
    }
  });
  return actions.join("");
}

// 通用下载方法
export function download(fileName) {
  window.location.href =
    baseURL +
    "/common/download?fileName=" +
    encodeURI(fileName) +
    "&delete=" +
    true;
}

// 字符串格式化(%s )
export function sprintf(str) {
  var args = arguments,
    flag = true,
    i = 1;
  str = str.replace(/%s/g, function() {
    var arg = args[i++];
    if (typeof arg === "undefined") {
      flag = false;
      return "";
    }
    return arg;
  });
  return flag ? str : "";
}

// 转换字符串，undefined,null等转化为""
export function praseStrEmpty(str) {
  if (!str || str == "undefined" || str == "null") {
    return "";
  }
  return str;
}

/**
 * 构造树型结构数据
 * @param {*} data 数据源
 * @param {*} id id字段 默认 'id'
 * @param {*} parentId 父节点字段 默认 'parentId'
 * @param {*} children 孩子节点字段 默认 'children'
 * @param {*} rootId 根Id 默认 0
 */
export function handleTree(data, id, parentId, children, rootId) {
  id = id || "id";
  parentId = parentId || "parentId";
  children = children || "children";
  rootId = rootId || 0;
  //对源数据深度克隆
  const cloneData = JSON.parse(JSON.stringify(data));
  //循环所有项
  const treeData = cloneData.filter(father => {
    let branchArr = cloneData.filter(child => {
      //返回每一项的子级数组
      return father[id] === child[parentId];
    });
    branchArr.length > 0 ? (father.children = branchArr) : "";
    //返回第一层
    return father[parentId] === rootId;
  });
  return treeData;
  // return treeData != "" ? treeData : data;
}

export function handleDeptTree(data, id, parentId, children, rootId) {
  id = id || "id";
  parentId = parentId || "parentId";
  children = children || "children";
  rootId = rootId || 0;
  //对源数据深度克隆
  const cloneData = JSON.parse(JSON.stringify(data));
  //循环所有项
  const treeData = cloneData.filter(father => {
    let branchArr = cloneData.filter(child => {
      //返回每一项的子级数组
      return father[id] === child[parentId];
    });
    branchArr.length > 0 ? (father.children = branchArr) : "";
    //返回第一层
    return father[parentId] === rootId;
  });
  return treeData != "" ? treeData : data;
}

//格式化分页数据
export function urlParameter(sort) {
  return Object.keys(sort)
    .map(function(key) {
      return encodeURIComponent(key) + "=" + encodeURIComponent(sort[key]);
    })
    .join("&");
}

export default {
  filterData(obj) {
    //过滤数据
    var param = {};
    if (obj === null || obj === undefined || obj === "") return param;
    for (var key in obj) {
      if (this.dataType(obj[key]) === "Object") {
        param[key] = this.filterData(obj[key]);
      } else if (
        obj[key] !== null &&
        obj[key] !== undefined &&
        obj[key] !== ""
      ) {
        param[key] = obj[key];
      }
    }
    return param;
  },
  dataType(obj) {
    if (obj === null) return "Null";
    if (obj === undefined) return "Undefined";
    return Object.prototype.toString.call(obj).slice(8, -1);
  }
};

//获取7天前(num是正数表示之后的时间，num负数表示之前的时间，0表示今天)
export function fun_date(num) {
  let date1 = new Date();
  let time1 =
    date1.getFullYear() +
    "-" +
    (date1.getMonth() + 1) +
    "-" +
    date1.getDate();
  let date2 = new Date(date1);
  date2.setDate(date1.getDate() + num);
  let oMonth = date2.getMonth() + 1;
  if (oMonth < 10) {
    oMonth = "0" + oMonth;
  }
  let time2 = date2.getFullYear() + "-" + oMonth + "-" + date2.getDate();
  return time2;
}
