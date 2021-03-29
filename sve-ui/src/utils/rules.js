function regFun(a, b) {
    let reg = `^(([1-9]{1}\\d{0,${a}})|(0{1}))((\\.\\d{1,${b}})$|$)`;
    let newReg = new RegExp(reg);
    return newReg;
  }
   //最大容升&截面积(10,2)
  export function validateRange(rule, value, callback) {
    if (!regFun(7, 2).test(value)) {
      callback(new Error("范围为(0~99999999.99)"));
    } else {
      callback();
    }
  };

  //报警液位高度
  export function validateWarnRate(rule, value, callback) {
    if (!regFun(1, 3).test(value)) {
      callback(new Error("范围为(0~99.999)"));
    } else {
      callback();
    }
  };
  
  