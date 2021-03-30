function regFun(a, b) {
  let reg = `^(([1-9]{1}\\d{0,${a}})|(0{1}))((\\.\\d{1,${b}})$|$)`;
  let newReg = new RegExp(reg);
  return newReg;
}

export function validateElType(rule, value, callback) {
  if (
    value.every(e => {
      if (!!e.eleTypeId && !!e.eleDesc && !!e.eleCode && !!e.collectId) {
        if (e.eleCode.length !== 6) {
          callback(new Error("因子编码必须为6位!"));
          return false;
        } else if (e.eleTypeId !== e.eleCode.substring(0, 4)) {
          callback(new Error("因子编码前四位必须和因子类型对应!"));
          return false;
        } else {
          return true;
        }
      } else {
        return false;
      }
    })
  ) {
    callback();
  } else {
    callback(new Error("请完整填写因子信息"));
  }
};

//最大容升&截面积(零星废水=>水池)(10,2)
export function validateFullVolume(rule, value, callback) {
  if (!regFun(7, 2).test(value)) {
    callback(new Error("范围为(0~99999999.99)"));
  } else {
    callback();
  }
};

//水池高度(零星废水=>水池)(4,2)
export function validateMaxHeight(rule, value, callback) {
  if (!regFun(1, 2).test(value)) {
    callback(new Error("范围为(0~99.99)"));
  } else {
    callback();
  }
};

//预警高度(零星废水=>水池)(5,3)
export function validateWarnRate(rule, value, callback) {
  if (!regFun(1, 3).test(value)) {
    callback(new Error("范围为(0~99.999)"));
  } else {
    callback();
  }
};

//密度(零星废水=>水池)(4,3)
export function validateDensity(rule, value, callback) {
  if (!regFun(0, 3).test(value)) {
    callback(new Error("范围为(0~9.999)"));
  } else {
    callback();
  }
};

